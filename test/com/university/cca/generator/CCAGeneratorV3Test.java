package com.university.cca.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.university.cca.entities.Ambient;
import com.university.cca.entities.Message;
import com.university.cca.enums.AmbientType;
import com.university.cca.exceptions.AmbientMessageNotFoundException;

/**
 * Tests for CCAGeneratorV3 — the tree-based generator supporting
 * sibling, parent-to-child, and child-to-parent communication.
 *
 * @author Konstantin Rusev
 * @version 3.0
 */
public class CCAGeneratorV3Test {

	// =========================================================================
	// findNestedChildren
	// =========================================================================

	@Test
	public void testFindNestedChildren_parentToChildDetectsChild() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true),
			createAmbient("City", "", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("University", "FMI", "null", "null", "rooms_info")
		);

		Set<String> nested = CCAGeneratorV3.findNestedChildren(messages, ambients);

		assertEquals(1, nested.size());
		assertTrue(nested.contains("FMI"), "FMI should be nested as child of University");
	}

	@Test
	public void testFindNestedChildren_childToParentDetectsChild() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true),
			createAmbient("City", "", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("FMI", "University", "null", "null", "report")
		);

		Set<String> nested = CCAGeneratorV3.findNestedChildren(messages, ambients);

		assertEquals(1, nested.size());
		assertTrue(nested.contains("FMI"), "FMI should be nested (child sending to parent)");
	}

	@Test
	public void testFindNestedChildren_siblingNotNested() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", "City", true),
			createAmbient("Pharmacy", "City", true),
			createAmbient("City", "", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("Hospital", "Pharmacy", "null", "null", "request")
		);

		Set<String> nested = CCAGeneratorV3.findNestedChildren(messages, ambients);

		assertTrue(nested.isEmpty(), "Siblings should not be nested");
	}

	@Test
	public void testFindNestedChildren_mixedCommunication() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "Country", true),
			createAmbient("Hospital", "City", true),
			createAmbient("Pharmacy", "City", true),
			createAmbient("Lab", "Hospital", true),
			createAmbient("Country", "", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("Hospital", "Pharmacy", "null", "null", "request"),
			new Message("Hospital", "Lab", "null", "null", "test_request"),
			new Message("Lab", "Hospital", "null", "test_request", "test_result")
		);

		Set<String> nested = CCAGeneratorV3.findNestedChildren(messages, ambients);

		assertEquals(1, nested.size());
		assertTrue(nested.contains("Lab"), "Lab should be nested inside Hospital");
		assertFalse(nested.contains("Pharmacy"), "Pharmacy is a sibling, not nested");
	}

	@Test
	public void testFindNestedChildren_emptyMessages() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", "City", true)
		);

		Set<String> nested = CCAGeneratorV3.findNestedChildren(Collections.emptyList(), ambients);

		assertTrue(nested.isEmpty());
	}

	// =========================================================================
	// buildParentToChildrenMap
	// =========================================================================

	@Test
	public void testBuildParentToChildrenMap_singleChild() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true)
		);
		Set<String> nestedChildren = new HashSet<>(Arrays.asList("FMI"));

		Map<String, List<String>> map = CCAGeneratorV3.buildParentToChildrenMap(nestedChildren, ambients);

		assertEquals(1, map.size());
		assertTrue(map.containsKey("University"));
		assertEquals(1, map.get("University").size());
		assertEquals("FMI", map.get("University").get(0));
	}

	@Test
	public void testBuildParentToChildrenMap_multipleChildren() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true),
			createAmbient("FII", "University", true)
		);
		Set<String> nestedChildren = new HashSet<>(Arrays.asList("FMI", "FII"));

		Map<String, List<String>> map = CCAGeneratorV3.buildParentToChildrenMap(nestedChildren, ambients);

		assertEquals(1, map.size());
		assertTrue(map.containsKey("University"));
		assertEquals(2, map.get("University").size());
	}

	@Test
	public void testBuildParentToChildrenMap_emptySet() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", "City", true)
		);

		Map<String, List<String>> map = CCAGeneratorV3.buildParentToChildrenMap(
			Collections.emptySet(), ambients
		);

		assertTrue(map.isEmpty());
	}

	// =========================================================================
	// findTopLevelAmbients
	// =========================================================================

	@Test
	public void testFindTopLevelAmbients_allSiblings() {
		List<Message> messages = Arrays.asList(
			new Message("Hospital", "Pharmacy", "null", "null", "request"),
			new Message("Pharmacy", "Hospital", "null", "request", "response")
		);

		List<String> topLevel = CCAGeneratorV3.findTopLevelAmbients(messages, Collections.emptySet());

		assertEquals(2, topLevel.size());
		assertTrue(topLevel.contains("Hospital"));
		assertTrue(topLevel.contains("Pharmacy"));
	}

	@Test
	public void testFindTopLevelAmbients_childExcluded() {
		Set<String> nested = new HashSet<>(Arrays.asList("FMI"));
		List<Message> messages = Arrays.asList(
			new Message("University", "FMI", "null", "null", "rooms"),
			new Message("FMI", "University", "null", "rooms", "response")
		);

		List<String> topLevel = CCAGeneratorV3.findTopLevelAmbients(messages, nested);

		assertEquals(1, topLevel.size());
		assertTrue(topLevel.contains("University"));
		assertFalse(topLevel.contains("FMI"), "FMI is nested and should not be top-level");
	}

	@Test
	public void testFindTopLevelAmbients_sorted() {
		List<Message> messages = Arrays.asList(
			new Message("Zebra", "Alpha", "null", "null", "msg"),
			new Message("Middle", "Alpha", "null", "null", "msg2")
		);

		List<String> topLevel = CCAGeneratorV3.findTopLevelAmbients(messages, Collections.emptySet());

		assertEquals("Alpha", topLevel.get(0), "Should be sorted alphabetically");
		assertEquals("Middle", topLevel.get(1));
		assertEquals("Zebra", topLevel.get(2));
	}

	@Test
	public void testFindTopLevelAmbients_noDuplicates() {
		List<Message> messages = Arrays.asList(
			new Message("A", "B", "null", "null", "msg1"),
			new Message("B", "A", "null", "msg1", "msg2"),
			new Message("A", "B", "null", "null", "msg3")
		);

		List<String> topLevel = CCAGeneratorV3.findTopLevelAmbients(messages, Collections.emptySet());

		assertEquals(2, topLevel.size());
	}

	// =========================================================================
	// generateCapabilityLines — sibling communication
	// =========================================================================

	@Test
	public void testGenerateCapabilityLines_siblingInitiator() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", "City", true),
			createAmbient("Pharmacy", "City", true),
			createAmbient("City", "", true)
		);
		Message sent = new Message("Hospital", "Pharmacy", "null", "null", "request_medicine");
		Message response = new Message("Pharmacy", "Hospital", "null", "request_medicine", "medicine_ready");
		List<Message> messages = Arrays.asList(sent, response);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("Hospital", messages, ambients);

		assertEquals(2, lines.size(), "Initiator should produce two parallel lines");
		assertEquals("Pharmacy::send(request_medicine).0", lines.get(0));
		assertEquals("Pharmacy::recv(request_medicine,medicine_ready).0", lines.get(1));
	}

	@Test
	public void testGenerateCapabilityLines_siblingResponder() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", "City", true),
			createAmbient("Pharmacy", "City", true),
			createAmbient("City", "", true)
		);
		Message response = new Message("Pharmacy", "Hospital", "null", "request_medicine", "medicine_ready");
		List<Message> messages = Collections.singletonList(response);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("Pharmacy", messages, ambients);

		assertEquals(1, lines.size(), "Responder should produce one sequential item");
		assertTrue(lines.get(0).contains("Hospital::recv(request_medicine)."), "Should recv the original message");
		assertTrue(lines.get(0).contains("Hospital::send(request_medicine,medicine_ready).0"), "Should send the response");
	}

	// =========================================================================
	// generateCapabilityLines — parent-to-child communication
	// =========================================================================

	@Test
	public void testGenerateCapabilityLines_parentInitiator() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true),
			createAmbient("City", "", true)
		);
		Message sent = new Message("University", "FMI", "null", "null", "rooms_info");
		Message response = new Message("FMI", "University", "null", "rooms_info", "rooms_543");
		List<Message> messages = Arrays.asList(sent, response);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("University", messages, ambients);

		assertEquals(2, lines.size());
		assertEquals("FMI#send(rooms_info).0", lines.get(0), "Parent should use # to send to child");
		assertEquals("FMI#recv(rooms_info,rooms_543).0", lines.get(1), "Parent should use # to recv from child");
	}

	@Test
	public void testGenerateCapabilityLines_childResponder() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true),
			createAmbient("City", "", true)
		);
		Message response = new Message("FMI", "University", "null", "rooms_info", "rooms_543");
		List<Message> messages = Collections.singletonList(response);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("FMI", messages, ambients);

		assertEquals(1, lines.size());
		assertTrue(lines.get(0).contains("@recv(rooms_info)."), "Child should use @ to recv from parent");
		assertTrue(lines.get(0).contains("@send(rooms_info,rooms_543).0"), "Child should use @ to send to parent");
	}

	// =========================================================================
	// generateCapabilityLines — child-to-parent communication
	// =========================================================================

	@Test
	public void testGenerateCapabilityLines_childInitiator() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true),
			createAmbient("City", "", true)
		);
		Message sent = new Message("FMI", "University", "null", "null", "report");
		Message response = new Message("University", "FMI", "null", "report", "acknowledged");
		List<Message> messages = Arrays.asList(sent, response);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("FMI", messages, ambients);

		assertEquals(2, lines.size());
		assertEquals("@send(report).0", lines.get(0), "Child initiator should use @ to send");
		assertEquals("@recv(report,acknowledged).0", lines.get(1), "Child initiator should use @ to recv");
	}

	@Test
	public void testGenerateCapabilityLines_parentResponder() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true),
			createAmbient("City", "", true)
		);
		Message response = new Message("University", "FMI", "null", "report", "acknowledged");
		List<Message> messages = Collections.singletonList(response);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("University", messages, ambients);

		assertEquals(1, lines.size());
		assertTrue(lines.get(0).contains("FMI#recv(report)."), "Parent responder should use # to recv from child");
		assertTrue(lines.get(0).contains("FMI#send(report,acknowledged).0"), "Parent responder should use # to send to child");
	}

	// =========================================================================
	// generateCapabilityLines — special chars replacement
	// =========================================================================

	@Test
	public void testGenerateCapabilityLines_specialCharsReplaced() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Bus Station", "City", true),
			createAmbient("Coffee Shop", "City", true),
			createAmbient("City", "", true)
		);
		Message sent = new Message("Bus Station", "Coffee Shop", "null", "null", "want coffee");
		Message response = new Message("Coffee Shop", "Bus Station", "null", "want coffee", "here it is");
		List<Message> messages = Arrays.asList(sent, response);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("Bus Station", messages, ambients);

		assertEquals(2, lines.size());
		assertEquals("Coffee_Shop::send(want_coffee).0", lines.get(0));
		assertEquals("Coffee_Shop::recv(want_coffee,here_it_is).0", lines.get(1));
	}

	// =========================================================================
	// generateCapabilityLines — edge cases
	// =========================================================================

	@Test
	public void testGenerateCapabilityLines_noMessagesForAmbient() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", "City", true)
		);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("Hospital", Collections.emptyList(), ambients);

		assertTrue(lines.isEmpty());
	}

	@Test
	public void testGenerateCapabilityLines_unsupportedRelationshipSkipped() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("A", "CityA", true),
			createAmbient("B", "CityB", true),
			createAmbient("CityA", "Country", true),
			createAmbient("CityB", "Country", true),
			createAmbient("Country", "", true)
		);
		Message sent = new Message("A", "B", "null", "null", "hello");
		List<Message> messages = Collections.singletonList(sent);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("A", messages, ambients);

		assertTrue(lines.isEmpty(), "Unsupported relationship (different parents, not direct parent-child) should be skipped");
	}

	@Test
	public void testGenerateCapabilityLines_ambientOnlyRecipientSkipped() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", "City", true),
			createAmbient("Pharmacy", "City", true),
			createAmbient("City", "", true)
		);
		Message sent = new Message("Hospital", "Pharmacy", "null", "null", "request");
		List<Message> messages = Collections.singletonList(sent);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("Pharmacy", messages, ambients);

		assertTrue(lines.isEmpty(), "Ambient that is only a recipient (not sender) should produce no capabilities");
	}

	@Test
	public void testGenerateCapabilityLines_initiatorMissingResponseThrows() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", "City", true),
			createAmbient("Pharmacy", "City", true),
			createAmbient("City", "", true)
		);
		Message sent = new Message("Hospital", "Pharmacy", "null", "null", "request");
		List<Message> messages = Collections.singletonList(sent);

		assertThrows(AmbientMessageNotFoundException.class, () ->
			CCAGeneratorV3.generateCapabilityLines("Hospital", messages, ambients)
		);
	}

	// =========================================================================
	// generateAmbientBlock — sibling (flat, no nesting)
	// =========================================================================

	@Test
	public void testGenerateAmbientBlock_siblingInitiator() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", "City", true),
			createAmbient("Pharmacy", "City", true),
			createAmbient("City", "", true)
		);
		Message sent = new Message("Hospital", "Pharmacy", "null", "null", "request");
		Message response = new Message("Pharmacy", "Hospital", "null", "request", "done");
		List<Message> messages = Arrays.asList(sent, response);
		Map<String, List<String>> noChildren = new HashMap<>();

		String block = CCAGeneratorV3.generateAmbientBlock("Hospital", messages, noChildren, ambients, 0);

		assertNotNull(block);
		assertTrue(block.startsWith("Hospital["), "Should start with ambient name and opening bracket");
		assertTrue(block.endsWith("]"), "Should end with closing bracket");
		assertTrue(block.contains("Pharmacy::send(request).0"), "Should contain sibling send");
		assertTrue(block.contains("Pharmacy::recv(request,done).0"), "Should contain sibling recv");
		assertTrue(block.contains("|"), "Send and recv should be in parallel");
	}

	@Test
	public void testGenerateAmbientBlock_siblingResponder() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", "City", true),
			createAmbient("Pharmacy", "City", true),
			createAmbient("City", "", true)
		);
		Message response = new Message("Pharmacy", "Hospital", "null", "request", "done");
		List<Message> messages = Collections.singletonList(response);
		Map<String, List<String>> noChildren = new HashMap<>();

		String block = CCAGeneratorV3.generateAmbientBlock("Pharmacy", messages, noChildren, ambients, 0);

		assertNotNull(block);
		assertTrue(block.startsWith("Pharmacy["));
		assertTrue(block.contains("Hospital::recv(request)."), "Should contain sequential recv");
		assertTrue(block.contains("Hospital::send(request,done).0"), "Should contain sequential send");
		assertFalse(block.contains("|"), "Sequential recv.send should NOT have parallel delimiter");
	}

	// =========================================================================
	// generateAmbientBlock — parent-child (nested)
	// =========================================================================

	@Test
	public void testGenerateAmbientBlock_parentWithNestedChild() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true),
			createAmbient("City", "", true)
		);
		Message sent = new Message("University", "FMI", "null", "null", "rooms");
		Message response = new Message("FMI", "University", "null", "rooms", "room_543");
		List<Message> messages = Arrays.asList(sent, response);

		Map<String, List<String>> parentToChildren = new HashMap<>();
		parentToChildren.put("University", Arrays.asList("FMI"));

		String block = CCAGeneratorV3.generateAmbientBlock("University", messages, parentToChildren, ambients, 0);

		assertNotNull(block);
		assertTrue(block.startsWith("University["), "Should start with parent ambient");
		assertTrue(block.contains("FMI#send(rooms).0"), "Parent should have child send with #");
		assertTrue(block.contains("FMI#recv(rooms,room_543).0"), "Parent should have child recv with #");
		assertTrue(block.contains("FMI["), "Should contain nested child block");
		assertTrue(block.contains("@recv(rooms)."), "Child should use @ for recv");
		assertTrue(block.contains("@send(rooms,room_543).0"), "Child should use @ for send");
	}

	@Test
	public void testGenerateAmbientBlock_nestedChildIndentation() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Parent", "Root", true),
			createAmbient("Child", "Parent", true),
			createAmbient("Root", "", true)
		);
		Message sent = new Message("Parent", "Child", "null", "null", "hello");
		Message response = new Message("Child", "Parent", "null", "hello", "world");
		List<Message> messages = Arrays.asList(sent, response);

		Map<String, List<String>> parentToChildren = new HashMap<>();
		parentToChildren.put("Parent", Arrays.asList("Child"));

		String block = CCAGeneratorV3.generateAmbientBlock("Parent", messages, parentToChildren, ambients, 0);

		// Verify indentation structure
		String[] lines = block.split("\n");
		assertEquals("Parent[", lines[0], "First line: ambient name with opening bracket");
		assertTrue(lines[1].startsWith("\t"), "Capabilities should be indented with one tab");
		assertTrue(lines[lines.length - 1].equals("]"), "Last line should be closing bracket at depth 0");

		// Find the nested child block and verify its indentation
		boolean foundChildBlock = false;
		for (String line : lines) {
			if (line.contains("Child[")) {
				assertTrue(line.startsWith("\t"), "Child block should be at depth 1");
				foundChildBlock = true;
			}
			if (line.contains("@recv(")) {
				assertTrue(line.startsWith("\t\t"), "Child capabilities should be at depth 2");
			}
		}
		assertTrue(foundChildBlock, "Should contain nested Child block");
	}

	// =========================================================================
	// generateAmbientBlock — no messages returns null
	// =========================================================================

	@Test
	public void testGenerateAmbientBlock_noMessagesReturnsNull() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Empty", "City", true)
		);
		Map<String, List<String>> noChildren = new HashMap<>();

		String block = CCAGeneratorV3.generateAmbientBlock("Empty", Collections.emptyList(), noChildren, ambients, 0);

		assertNull(block, "Ambient with no messages and no children should return null");
	}

	// =========================================================================
	// generateAmbientBlock — mixed sibling + parent-child
	// =========================================================================

	@Test
	public void testGenerateAmbientBlock_mixedSiblingAndParentChild() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true),
			createAmbient("Hospital", "City", true),
			createAmbient("City", "", true)
		);
		// University sends to FMI (parent-to-child) and to Hospital (sibling)
		Message toChild = new Message("University", "FMI", "null", "null", "schedule");
		Message fromChild = new Message("FMI", "University", "null", "schedule", "confirmed");
		Message toSibling = new Message("University", "Hospital", "null", "null", "checkup");
		Message fromSibling = new Message("Hospital", "University", "null", "checkup", "ok");
		List<Message> messages = Arrays.asList(toChild, fromChild, toSibling, fromSibling);

		Map<String, List<String>> parentToChildren = new HashMap<>();
		parentToChildren.put("University", Arrays.asList("FMI"));

		String block = CCAGeneratorV3.generateAmbientBlock("University", messages, parentToChildren, ambients, 0);

		assertNotNull(block);
		// Sibling communication
		assertTrue(block.contains("Hospital::send(checkup).0"), "Should have sibling send to Hospital");
		assertTrue(block.contains("Hospital::recv(checkup,ok).0"), "Should have sibling recv from Hospital");
		// Parent-to-child communication
		assertTrue(block.contains("FMI#send(schedule).0"), "Should have child send with #");
		assertTrue(block.contains("FMI#recv(schedule,confirmed).0"), "Should have child recv with #");
		// Nested child
		assertTrue(block.contains("FMI["), "Should contain nested FMI block");
		assertTrue(block.contains("@recv(schedule)."), "FMI should use @ for recv");
		assertTrue(block.contains("@send(schedule,confirmed).0"), "FMI should use @ for send");
	}

	// =========================================================================
	// generateAmbientBlock — multiple messages between same ambients
	// =========================================================================

	@Test
	public void testGenerateCapabilityLines_multipleConversations() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", "City", true),
			createAmbient("Pharmacy", "City", true),
			createAmbient("City", "", true)
		);
		Message sent1 = new Message("Hospital", "Pharmacy", "null", "null", "need_aspirin");
		Message resp1 = new Message("Pharmacy", "Hospital", "null", "need_aspirin", "aspirin_ready");
		Message sent2 = new Message("Hospital", "Pharmacy", "null", "null", "need_bandage");
		Message resp2 = new Message("Pharmacy", "Hospital", "null", "need_bandage", "bandage_ready");
		List<Message> messages = Arrays.asList(sent1, resp1, sent2, resp2);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("Hospital", messages, ambients);

		assertEquals(4, lines.size(), "Two initiator messages should produce 4 capability lines");
		assertTrue(lines.get(0).contains("send(need_aspirin)"));
		assertTrue(lines.get(1).contains("recv(need_aspirin,aspirin_ready)"));
		assertTrue(lines.get(2).contains("send(need_bandage)"));
		assertTrue(lines.get(3).contains("recv(need_bandage,bandage_ready)"));
	}

	// =========================================================================
	// Output format validation — ccaPL syntax correctness
	// =========================================================================

	@Test
	public void testOutputFormat_siblingUsesDoubleColon() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("A", "Root", true),
			createAmbient("B", "Root", true),
			createAmbient("Root", "", true)
		);
		Message sent = new Message("A", "B", "null", "null", "msg");
		Message resp = new Message("B", "A", "null", "msg", "ack");
		List<Message> messages = Arrays.asList(sent, resp);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("A", messages, ambients);

		for (String line : lines) {
			assertTrue(line.contains("::"), "Sibling communication must use :: specifier");
			assertFalse(line.contains("#"), "Sibling should not use # specifier");
			assertFalse(line.contains("@"), "Sibling should not use @ specifier");
		}
	}

	@Test
	public void testOutputFormat_parentToChildUsesHash() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Parent", "Root", true),
			createAmbient("Child", "Parent", true),
			createAmbient("Root", "", true)
		);
		Message sent = new Message("Parent", "Child", "null", "null", "hello");
		Message resp = new Message("Child", "Parent", "null", "hello", "world");
		List<Message> messages = Arrays.asList(sent, resp);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("Parent", messages, ambients);

		for (String line : lines) {
			assertTrue(line.contains("#"), "Parent-to-child must use # specifier");
			assertFalse(line.contains("::"), "Parent-to-child should not use :: specifier");
			assertFalse(line.contains("@"), "Parent-to-child should not use @ specifier");
		}
	}

	@Test
	public void testOutputFormat_childToParentUsesAt() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Parent", "Root", true),
			createAmbient("Child", "Parent", true),
			createAmbient("Root", "", true)
		);
		Message resp = new Message("Child", "Parent", "null", "hello", "world");
		List<Message> messages = Collections.singletonList(resp);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("Child", messages, ambients);

		for (String line : lines) {
			assertTrue(line.contains("@"), "Child-to-parent must use @ specifier");
			assertFalse(line.contains("::"), "Child-to-parent should not use :: specifier");
			assertFalse(line.contains("#"), "Child-to-parent should not use # specifier");
		}
	}

	@Test
	public void testOutputFormat_allProcessesEndWithDotZero() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", "City", true),
			createAmbient("Pharmacy", "City", true),
			createAmbient("City", "", true)
		);
		Message sent = new Message("Hospital", "Pharmacy", "null", "null", "req");
		Message resp = new Message("Pharmacy", "Hospital", "null", "req", "ack");
		List<Message> messages = Arrays.asList(sent, resp);
		Map<String, List<String>> noChildren = new HashMap<>();

		String block = CCAGeneratorV3.generateAmbientBlock("Hospital", messages, noChildren, ambients, 0);

		assertTrue(block.contains(".0"), "All processes should terminate with .0 (inactivity)");
	}

	// =========================================================================
	// Forwarding chain detection
	// =========================================================================

	@Test
	public void testFindForwardingChains_detectsChain() {
		// A=STUDENT_PA, B=University (relay), C=FMI
		Message msg1 = new Message("STUDENT_PA", "University", "null", "null", "FMI rooms");
		Message msg2 = new Message("University", "FMI", "STUDENT_PA", "null", "FMI rooms from uni to fmi");
		Message msg3 = new Message("FMI", "University", "null", "FMI rooms from uni to fmi", "fmi to uni 543");
		Message msg4 = new Message("University", "STUDENT_PA", "null", "FMI rooms", "fmi to uni 543");
		List<Message> messages = Arrays.asList(msg1, msg2, msg3, msg4);

		List<CCAGeneratorV3.ForwardingChain> chains = CCAGeneratorV3.findForwardingChains("University", messages);

		assertEquals(1, chains.size(), "Should detect one forwarding chain");
		assertEquals(msg1, chains.get(0).originalRequest);
		assertEquals(msg2, chains.get(0).forwardToTarget);
		assertEquals(msg3, chains.get(0).responseFromTarget);
		assertEquals(msg4, chains.get(0).forwardBackToOrigin);
	}

	@Test
	public void testFindForwardingChains_nonRelayAmbientReturnsEmpty() {
		Message msg1 = new Message("STUDENT_PA", "University", "null", "null", "FMI rooms");
		Message msg2 = new Message("University", "FMI", "STUDENT_PA", "null", "FMI rooms from uni to fmi");
		Message msg3 = new Message("FMI", "University", "null", "FMI rooms from uni to fmi", "fmi to uni 543");
		Message msg4 = new Message("University", "STUDENT_PA", "null", "FMI rooms", "fmi to uni 543");
		List<Message> messages = Arrays.asList(msg1, msg2, msg3, msg4);

		List<CCAGeneratorV3.ForwardingChain> chainsAtA = CCAGeneratorV3.findForwardingChains("STUDENT_PA", messages);
		List<CCAGeneratorV3.ForwardingChain> chainsAtC = CCAGeneratorV3.findForwardingChains("FMI", messages);

		assertTrue(chainsAtA.isEmpty(), "STUDENT_PA is not a relay, should have no chains");
		assertTrue(chainsAtC.isEmpty(), "FMI is not a relay, should have no chains");
	}

	@Test
	public void testFindForwardingChains_noForwardingMessages() {
		Message sent = new Message("Hospital", "Pharmacy", "null", "null", "request");
		Message resp = new Message("Pharmacy", "Hospital", "null", "request", "done");
		List<Message> messages = Arrays.asList(sent, resp);

		List<CCAGeneratorV3.ForwardingChain> chains = CCAGeneratorV3.findForwardingChains("Hospital", messages);

		assertTrue(chains.isEmpty(), "No forwarding messages should yield no chains");
	}

	@Test
	public void testFindForwardingChains_incompleteChainSkipped() {
		// msg2 exists but msg3 (response from C) is missing
		Message msg1 = new Message("A", "B", "null", "null", "hello");
		Message msg2 = new Message("B", "C", "A", "null", "forwarded");
		List<Message> messages = Arrays.asList(msg1, msg2);

		List<CCAGeneratorV3.ForwardingChain> chains = CCAGeneratorV3.findForwardingChains("B", messages);

		assertTrue(chains.isEmpty(), "Incomplete chain should be skipped");
	}

	// =========================================================================
	// findForwardBack / findOriginalRequest helpers
	// =========================================================================

	@Test
	public void testFindForwardBack_found() {
		Message msg4 = new Message("B", "A", "null", "original", "response");
		List<Message> messages = Arrays.asList(
			new Message("A", "B", "null", "null", "original"),
			msg4
		);

		Message result = CCAGeneratorV3.findForwardBack(messages, "B", "A", "response");

		assertNotNull(result);
		assertEquals(msg4, result);
	}

	@Test
	public void testFindForwardBack_notFound() {
		List<Message> messages = Arrays.asList(
			new Message("A", "B", "null", "null", "original")
		);

		Message result = CCAGeneratorV3.findForwardBack(messages, "B", "A", "nonexistent");

		assertNull(result);
	}

	@Test
	public void testFindOriginalRequest_found() {
		Message msg1 = new Message("A", "B", "null", "null", "FMI rooms");
		List<Message> messages = Arrays.asList(msg1);

		Message result = CCAGeneratorV3.findOriginalRequest(messages, "A", "B", "FMI rooms");

		assertNotNull(result);
		assertEquals(msg1, result);
	}

	@Test
	public void testFindOriginalRequest_notFound() {
		List<Message> messages = Arrays.asList(
			new Message("A", "B", "null", "null", "wrong message")
		);

		Message result = CCAGeneratorV3.findOriginalRequest(messages, "A", "B", "FMI rooms");

		assertNull(result);
	}

	// =========================================================================
	// generateRelayItem — 4-step sequential relay
	// =========================================================================

	@Test
	public void testGenerateRelayItem_siblingToChild() {
		// A=STUDENT_PA (sibling of B), B=University (relay, parent of C), C=FMI (child of B)
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("STUDENT_PA", "City", true),
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true)
		);
		CCAGeneratorV3.ForwardingChain chain = new CCAGeneratorV3.ForwardingChain(
			new Message("STUDENT_PA", "University", "null", "null", "FMI rooms"),
			new Message("University", "FMI", "STUDENT_PA", "null", "FMI rooms from uni to fmi"),
			new Message("FMI", "University", "null", "FMI rooms from uni to fmi", "fmi to uni 543"),
			new Message("University", "STUDENT_PA", "null", "FMI rooms", "fmi to uni 543")
		);

		String result = CCAGeneratorV3.generateRelayItem("University", chain, ambients);

		assertNotNull(result);
		// Step 1: recv from sibling A
		assertTrue(result.contains("STUDENT_PA::recv(FMI_rooms)."), "Should recv from sibling STUDENT_PA with ::");
		// Step 2: send to child C
		assertTrue(result.contains("FMI#send(FMI_rooms_from_uni_to_fmi)."), "Should send to child FMI with #");
		// Step 3: recv from child C
		assertTrue(result.contains("FMI#recv(FMI_rooms_from_uni_to_fmi,fmi_to_uni_543)."), "Should recv from child FMI with #");
		// Step 4: send to sibling A
		assertTrue(result.contains("STUDENT_PA::send(FMI_rooms,fmi_to_uni_543).0"), "Should send to sibling STUDENT_PA with ::");
	}

	@Test
	public void testGenerateRelayItem_allSiblings() {
		// A, B (relay), C are all siblings
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("A", "City", true),
			createAmbient("B", "City", true),
			createAmbient("C", "City", true)
		);
		CCAGeneratorV3.ForwardingChain chain = new CCAGeneratorV3.ForwardingChain(
			new Message("A", "B", "null", "null", "request"),
			new Message("B", "C", "A", "null", "forwarded"),
			new Message("C", "B", "null", "forwarded", "response"),
			new Message("B", "A", "null", "request", "response")
		);

		String result = CCAGeneratorV3.generateRelayItem("B", chain, ambients);

		assertNotNull(result);
		assertTrue(result.contains("A::recv(request)."), "Step 1: recv from sibling A");
		assertTrue(result.contains("C::send(forwarded)."), "Step 2: send to sibling C");
		assertTrue(result.contains("C::recv(forwarded,response)."), "Step 3: recv from sibling C");
		assertTrue(result.contains("A::send(request,response).0"), "Step 4: send to sibling A");
	}

	@Test
	public void testGenerateRelayItem_correctSequentialOrder() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Root", "", true),
			createAmbient("A", "Root", true),
			createAmbient("B", "Root", true),
			createAmbient("C", "Root", true)
		);
		CCAGeneratorV3.ForwardingChain chain = new CCAGeneratorV3.ForwardingChain(
			new Message("A", "B", "null", "null", "req"),
			new Message("B", "C", "A", "null", "fwd"),
			new Message("C", "B", "null", "fwd", "resp"),
			new Message("B", "A", "null", "req", "resp")
		);

		String result = CCAGeneratorV3.generateRelayItem("B", chain, ambients);

		// Verify the 4 steps appear in correct sequential order
		int posRecvA = result.indexOf("A::recv(req).");
		int posSendC = result.indexOf("C::send(fwd).");
		int posRecvC = result.indexOf("C::recv(fwd,resp).");
		int posSendA = result.indexOf("A::send(req,resp).0");

		assertTrue(posRecvA >= 0, "Step 1 should exist");
		assertTrue(posSendC >= 0, "Step 2 should exist");
		assertTrue(posRecvC >= 0, "Step 3 should exist");
		assertTrue(posSendA >= 0, "Step 4 should exist");

		assertTrue(posRecvA < posSendC, "recv from A must come before send to C");
		assertTrue(posSendC < posRecvC, "send to C must come before recv from C");
		assertTrue(posRecvC < posSendA, "recv from C must come before send to A");
	}

	@Test
	public void testGenerateRelayItem_endsWithDotZero() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Root", "", true),
			createAmbient("A", "Root", true),
			createAmbient("B", "Root", true),
			createAmbient("C", "Root", true)
		);
		CCAGeneratorV3.ForwardingChain chain = new CCAGeneratorV3.ForwardingChain(
			new Message("A", "B", "null", "null", "req"),
			new Message("B", "C", "A", "null", "fwd"),
			new Message("C", "B", "null", "fwd", "resp"),
			new Message("B", "A", "null", "req", "resp")
		);

		String result = CCAGeneratorV3.generateRelayItem("B", chain, ambients);

		assertTrue(result.trim().endsWith(".0"), "Relay process must end with .0");
	}

	@Test
	public void testGenerateRelayItem_specialCharsReplaced() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Root", "", true),
			createAmbient("Bus Station", "Root", true),
			createAmbient("Info Hub", "Root", true),
			createAmbient("Coffee Shop", "Root", true)
		);
		CCAGeneratorV3.ForwardingChain chain = new CCAGeneratorV3.ForwardingChain(
			new Message("Bus Station", "Info Hub", "null", "null", "want coffee"),
			new Message("Info Hub", "Coffee Shop", "Bus Station", "null", "coffee order"),
			new Message("Coffee Shop", "Info Hub", "null", "coffee order", "here it is"),
			new Message("Info Hub", "Bus Station", "null", "want coffee", "here it is")
		);

		String result = CCAGeneratorV3.generateRelayItem("Info Hub", chain, ambients);

		assertNotNull(result);
		assertTrue(result.contains("Bus_Station"), "Spaces should be replaced with underscores");
		assertTrue(result.contains("Coffee_Shop"), "Spaces should be replaced with underscores");
		assertTrue(result.contains("want_coffee"), "Spaces in messages should be replaced");
		assertFalse(result.contains(" "), "No spaces should remain in the ccaPL output");
	}

	// =========================================================================
	// generateCapabilityLines — forwarding integration
	// =========================================================================

	@Test
	public void testGenerateCapabilityLines_relayGeneratesForwardingChain() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("A", "City", true),
			createAmbient("B", "City", true),
			createAmbient("C", "City", true)
		);
		Message msg1 = new Message("A", "B", "null", "null", "request");
		Message msg2 = new Message("B", "C", "A", "null", "forwarded");
		Message msg3 = new Message("C", "B", "null", "forwarded", "response");
		Message msg4 = new Message("B", "A", "null", "request", "response");
		List<Message> messages = Arrays.asList(msg1, msg2, msg3, msg4);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("B", messages, ambients);

		assertEquals(1, lines.size(), "Relay should produce exactly one 4-step sequential item");
		String relay = lines.get(0);
		assertTrue(relay.contains("A::recv(request)."), "Should contain step 1");
		assertTrue(relay.contains("C::send(forwarded)."), "Should contain step 2");
		assertTrue(relay.contains("C::recv(forwarded,response)."), "Should contain step 3");
		assertTrue(relay.contains("A::send(request,response).0"), "Should contain step 4");
	}

	@Test
	public void testGenerateCapabilityLines_originatorStillWorksWithForwarding() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("A", "City", true),
			createAmbient("B", "City", true),
			createAmbient("C", "City", true)
		);
		Message msg1 = new Message("A", "B", "null", "null", "request");
		Message msg2 = new Message("B", "C", "A", "null", "forwarded");
		Message msg3 = new Message("C", "B", "null", "forwarded", "response");
		Message msg4 = new Message("B", "A", "null", "request", "response");
		List<Message> messages = Arrays.asList(msg1, msg2, msg3, msg4);

		// A (the original sender) should still generate normal initiator
		List<String> lines = CCAGeneratorV3.generateCapabilityLines("A", messages, ambients);

		assertEquals(2, lines.size(), "Originator should produce two parallel lines");
		assertEquals("B::send(request).0", lines.get(0));
		assertEquals("B::recv(request,response).0", lines.get(1));
	}

	@Test
	public void testGenerateCapabilityLines_targetStillWorksWithForwarding() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("A", "City", true),
			createAmbient("B", "City", true),
			createAmbient("C", "City", true)
		);
		Message msg1 = new Message("A", "B", "null", "null", "request");
		Message msg2 = new Message("B", "C", "A", "null", "forwarded");
		Message msg3 = new Message("C", "B", "null", "forwarded", "response");
		Message msg4 = new Message("B", "A", "null", "request", "response");
		List<Message> messages = Arrays.asList(msg1, msg2, msg3, msg4);

		// C (the target) should still generate normal responder
		List<String> lines = CCAGeneratorV3.generateCapabilityLines("C", messages, ambients);

		assertEquals(1, lines.size(), "Target should produce one sequential responder item");
		assertTrue(lines.get(0).contains("B::recv(forwarded)."), "Should recv from B");
		assertTrue(lines.get(0).contains("B::send(forwarded,response).0"), "Should send back to B");
	}

	@Test
	public void testGenerateCapabilityLines_relayWithAdditionalNormalMessages() throws AmbientMessageNotFoundException {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("A", "City", true),
			createAmbient("B", "City", true),
			createAmbient("C", "City", true),
			createAmbient("D", "City", true)
		);
		// Forwarding chain: A → B → C → B → A
		Message msg1 = new Message("A", "B", "null", "null", "request");
		Message msg2 = new Message("B", "C", "A", "null", "forwarded");
		Message msg3 = new Message("C", "B", "null", "forwarded", "response");
		Message msg4 = new Message("B", "A", "null", "request", "response");
		// Normal sibling conversation: B ↔ D
		Message msg5 = new Message("B", "D", "null", "null", "hello");
		Message msg6 = new Message("D", "B", "null", "hello", "world");
		List<Message> messages = Arrays.asList(msg1, msg2, msg3, msg4, msg5, msg6);

		List<String> lines = CCAGeneratorV3.generateCapabilityLines("B", messages, ambients);

		// Should have: 1 relay item + 2 initiator lines for B→D = 3 total
		assertEquals(3, lines.size(), "Relay (1 item) + normal initiator (2 lines) = 3 items");

		// Verify the relay is present
		boolean hasRelay = lines.stream().anyMatch(l -> l.contains("A::recv(request)."));
		assertTrue(hasRelay, "Should contain the forwarding relay");

		// Verify normal communication is present
		boolean hasSendToD = lines.stream().anyMatch(l -> l.equals("D::send(hello).0"));
		boolean hasRecvFromD = lines.stream().anyMatch(l -> l.equals("D::recv(hello,world).0"));
		assertTrue(hasSendToD, "Should have normal send to D");
		assertTrue(hasRecvFromD, "Should have normal recv from D");
	}

	// =========================================================================
	// Full scenario: forwarding with parent-child nesting
	// =========================================================================

	@Test
	public void testGenerateAmbientBlock_fullForwardingScenario() throws AmbientMessageNotFoundException {
		// The real scenario: STUDENT_PA (sibling) → University (relay) → FMI (child of University)
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("STUDENT_PA", "City", true),
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true)
		);
		Message msg1 = new Message("STUDENT_PA", "University", "null", "null", "FMI rooms");
		Message msg2 = new Message("University", "FMI", "STUDENT_PA", "null", "FMI rooms from uni to fmi");
		Message msg3 = new Message("FMI", "University", "null", "FMI rooms from uni to fmi", "fmi to uni 543");
		Message msg4 = new Message("University", "STUDENT_PA", "null", "FMI rooms", "fmi to uni 543");
		List<Message> messages = Arrays.asList(msg1, msg2, msg3, msg4);

		Map<String, List<String>> parentToChildren = new HashMap<>();
		parentToChildren.put("University", Arrays.asList("FMI"));

		String block = CCAGeneratorV3.generateAmbientBlock("University", messages, parentToChildren, ambients, 0);

		assertNotNull(block);
		assertTrue(block.startsWith("University["), "Should start with University block");

		// Relay: 4-step sequential at University level
		assertTrue(block.contains("STUDENT_PA::recv(FMI_rooms)."), "Step 1: recv from sibling");
		assertTrue(block.contains("FMI#send(FMI_rooms_from_uni_to_fmi)."), "Step 2: send to child");
		assertTrue(block.contains("FMI#recv(FMI_rooms_from_uni_to_fmi,fmi_to_uni_543)."), "Step 3: recv from child");
		assertTrue(block.contains("STUDENT_PA::send(FMI_rooms,fmi_to_uni_543).0"), "Step 4: send back to sibling");

		// Nested FMI block
		assertTrue(block.contains("FMI["), "Should contain nested FMI block");
		assertTrue(block.contains("@recv(FMI_rooms_from_uni_to_fmi)."), "FMI should recv from parent with @");
		assertTrue(block.contains("@send(FMI_rooms_from_uni_to_fmi,fmi_to_uni_543).0"), "FMI should send to parent with @");
	}

	// =========================================================================
	// Helper
	// =========================================================================

	private Ambient createAmbient(String name, String parent, boolean isActive) {
		Ambient ambient = new Ambient();
		ambient.setName(name);
		ambient.setLocation("Loc");
		ambient.setLatitude("42.0");
		ambient.setLongitude("23.0");
		ambient.setStaticAmbient(true);
		ambient.setActiveAmbient(isActive);
		ambient.setParentAmbient(parent);
		ambient.setAmbientType(AmbientType.CITY);
		return ambient;
	}
}
