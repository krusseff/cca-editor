package com.university.cca.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Tests for the CommunicationType enum.
 *
 * @author Konstantin Rusev
 * @version 3.0
 */
public class CommunicationTypeTest {

	// --- inverse ---

	@Test
	public void testInverseParentToChildReturnsChildToParent() {
		assertEquals(CommunicationType.CHILD_TO_PARENT, CommunicationType.PARENT_TO_CHILD.inverse());
	}

	@Test
	public void testInverseChildToParentReturnsParentToChild() {
		assertEquals(CommunicationType.PARENT_TO_CHILD, CommunicationType.CHILD_TO_PARENT.inverse());
	}

	@Test
	public void testInverseSiblingReturnsSibling() {
		assertEquals(CommunicationType.SIBLING, CommunicationType.SIBLING.inverse());
	}

	@Test
	public void testInverseLocalReturnsLocal() {
		assertEquals(CommunicationType.LOCAL, CommunicationType.LOCAL.inverse());
	}

	@Test
	public void testInverseUnsupportedReturnsUnsupported() {
		assertEquals(CommunicationType.UNSUPPORTED, CommunicationType.UNSUPPORTED.inverse());
	}

	@Test
	public void testDoubleInverseParentToChild() {
		assertEquals(CommunicationType.PARENT_TO_CHILD,
			CommunicationType.PARENT_TO_CHILD.inverse().inverse());
	}

	@Test
	public void testDoubleInverseChildToParent() {
		assertEquals(CommunicationType.CHILD_TO_PARENT,
			CommunicationType.CHILD_TO_PARENT.inverse().inverse());
	}

	// --- displayName ---

	@Test
	public void testSiblingDisplayName() {
		assertEquals("Sibling (::)", CommunicationType.SIBLING.getDisplayName());
	}

	@Test
	public void testParentToChildDisplayName() {
		assertEquals("Parent to Child (#)", CommunicationType.PARENT_TO_CHILD.getDisplayName());
	}

	@Test
	public void testChildToParentDisplayName() {
		assertEquals("Child to Parent (@)", CommunicationType.CHILD_TO_PARENT.getDisplayName());
	}

	@Test
	public void testLocalDisplayName() {
		assertEquals("Local", CommunicationType.LOCAL.getDisplayName());
	}

	@Test
	public void testUnsupportedDisplayName() {
		assertEquals("Unsupported", CommunicationType.UNSUPPORTED.getDisplayName());
	}

	// --- description ---

	@Test
	public void testAllValuesHaveDescription() {
		for (CommunicationType type : CommunicationType.values()) {
			assertNotNull(type.getDescription(), type.name() + " should have a description");
			assertNotNull(type.getDisplayName(), type.name() + " should have a display name");
		}
	}

	// --- values ---

	@Test
	public void testValueCount() {
		assertEquals(5, CommunicationType.values().length, "Should have exactly 5 communication types");
	}
}
