package com.university.cca.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.university.cca.enums.CommunicationType;

/**
 * Tests for the CCACapabilityBuilder — verifies correct ccaPL location specifiers
 * for each communication type.
 *
 * @author Konstantin Rusev
 * @version 3.0
 */
public class CCACapabilityBuilderTest {

	// --- buildSend: SIBLING ---

	@Test
	public void testBuildSendSibling() {
		String result = CCACapabilityBuilder.buildSend(CommunicationType.SIBLING, "Pharmacy1", "request_medicine");
		assertEquals("Pharmacy1::send(request_medicine)", result);
	}

	@Test
	public void testBuildSendSiblingMultipleParams() {
		String result = CCACapabilityBuilder.buildSend(CommunicationType.SIBLING, "Gateway", "temp,25");
		assertEquals("Gateway::send(temp,25)", result);
	}

	// --- buildSend: PARENT_TO_CHILD ---

	@Test
	public void testBuildSendParentToChild() {
		String result = CCACapabilityBuilder.buildSend(CommunicationType.PARENT_TO_CHILD, "FMI", "rooms_info");
		assertEquals("FMI#send(rooms_info)", result);
	}

	@Test
	public void testBuildSendParentToChildMultipleParams() {
		String result = CCACapabilityBuilder.buildSend(CommunicationType.PARENT_TO_CHILD, "Sensor1", "temp,25");
		assertEquals("Sensor1#send(temp,25)", result);
	}

	// --- buildSend: CHILD_TO_PARENT ---

	@Test
	public void testBuildSendChildToParent() {
		String result = CCACapabilityBuilder.buildSend(CommunicationType.CHILD_TO_PARENT, "University", "response");
		assertEquals("@send(response)", result);
	}

	@Test
	public void testBuildSendChildToParentIgnoresOtherAmbientName() {
		String result1 = CCACapabilityBuilder.buildSend(CommunicationType.CHILD_TO_PARENT, "ParentA", "msg");
		String result2 = CCACapabilityBuilder.buildSend(CommunicationType.CHILD_TO_PARENT, "ParentB", "msg");
		assertEquals(result1, result2, "Child-to-parent send should not use the other ambient name");
	}

	// --- buildSend: LOCAL ---

	@Test
	public void testBuildSendLocal() {
		String result = CCACapabilityBuilder.buildSend(CommunicationType.LOCAL, "Self", "hello");
		assertEquals("send(hello)", result);
	}

	// --- buildSend: UNSUPPORTED ---

	@Test
	public void testBuildSendUnsupportedThrowsException() {
		assertThrows(IllegalArgumentException.class, () ->
			CCACapabilityBuilder.buildSend(CommunicationType.UNSUPPORTED, "X", "msg")
		);
	}

	// --- buildRecv: SIBLING ---

	@Test
	public void testBuildRecvSibling() {
		String result = CCACapabilityBuilder.buildRecv(CommunicationType.SIBLING, "Hospital1", "request_medicine");
		assertEquals("Hospital1::recv(request_medicine)", result);
	}

	@Test
	public void testBuildRecvSiblingMultipleParams() {
		String result = CCACapabilityBuilder.buildRecv(CommunicationType.SIBLING, "Sensor1", "original_msg,response");
		assertEquals("Sensor1::recv(original_msg,response)", result);
	}

	// --- buildRecv: PARENT_TO_CHILD ---

	@Test
	public void testBuildRecvParentToChild() {
		String result = CCACapabilityBuilder.buildRecv(CommunicationType.PARENT_TO_CHILD, "FMI", "rooms_response");
		assertEquals("FMI#recv(rooms_response)", result);
	}

	// --- buildRecv: CHILD_TO_PARENT ---

	@Test
	public void testBuildRecvChildToParent() {
		String result = CCACapabilityBuilder.buildRecv(CommunicationType.CHILD_TO_PARENT, "University", "data");
		assertEquals("@recv(data)", result);
	}

	@Test
	public void testBuildRecvChildToParentIgnoresOtherAmbientName() {
		String result1 = CCACapabilityBuilder.buildRecv(CommunicationType.CHILD_TO_PARENT, "ParentA", "msg");
		String result2 = CCACapabilityBuilder.buildRecv(CommunicationType.CHILD_TO_PARENT, "ParentB", "msg");
		assertEquals(result1, result2, "Child-to-parent recv should not use the other ambient name");
	}

	// --- buildRecv: LOCAL ---

	@Test
	public void testBuildRecvLocal() {
		String result = CCACapabilityBuilder.buildRecv(CommunicationType.LOCAL, "Self", "x");
		assertEquals("recv(x)", result);
	}

	// --- buildRecv: UNSUPPORTED ---

	@Test
	public void testBuildRecvUnsupportedThrowsException() {
		assertThrows(IllegalArgumentException.class, () ->
			CCACapabilityBuilder.buildRecv(CommunicationType.UNSUPPORTED, "X", "msg")
		);
	}

	// --- Symmetry: send and recv use the same specifier for a given type ---

	@Test
	public void testSiblingSymmetry() {
		String send = CCACapabilityBuilder.buildSend(CommunicationType.SIBLING, "Bob", "msg");
		String recv = CCACapabilityBuilder.buildRecv(CommunicationType.SIBLING, "Bob", "msg");
		assertEquals("Bob::send(msg)", send);
		assertEquals("Bob::recv(msg)", recv);
	}

	@Test
	public void testParentToChildSymmetry() {
		String send = CCACapabilityBuilder.buildSend(CommunicationType.PARENT_TO_CHILD, "Child1", "data");
		String recv = CCACapabilityBuilder.buildRecv(CommunicationType.PARENT_TO_CHILD, "Child1", "data");
		assertEquals("Child1#send(data)", send);
		assertEquals("Child1#recv(data)", recv);
	}

	@Test
	public void testChildToParentSymmetry() {
		String send = CCACapabilityBuilder.buildSend(CommunicationType.CHILD_TO_PARENT, "Parent1", "info");
		String recv = CCACapabilityBuilder.buildRecv(CommunicationType.CHILD_TO_PARENT, "Parent1", "info");
		assertEquals("@send(info)", send);
		assertEquals("@recv(info)", recv);
	}

	// --- Edge cases ---

	@Test
	public void testBuildSendEmptyParams() {
		String result = CCACapabilityBuilder.buildSend(CommunicationType.SIBLING, "Bob", "");
		assertEquals("Bob::send()", result);
	}

	@Test
	public void testBuildRecvEmptyParams() {
		String result = CCACapabilityBuilder.buildRecv(CommunicationType.SIBLING, "Bob", "");
		assertEquals("Bob::recv()", result);
	}

	@Test
	public void testBuildSendWithUnderscoredNames() {
		String result = CCACapabilityBuilder.buildSend(CommunicationType.SIBLING, "Bus_Station", "want_coffee");
		assertEquals("Bus_Station::send(want_coffee)", result);
	}
}
