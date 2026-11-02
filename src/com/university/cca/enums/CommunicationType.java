package com.university.cca.enums;

/**
 * Enumeration that represents the communication relationship between two ambients
 * based on their position in the ambient hierarchy.
 *
 * In ccaPL, the location specifier in send/recv capabilities depends on this relationship:
 * - SIBLING:          n::send() / n::recv()   — ambients share the same parent
 * - PARENT_TO_CHILD:  n#send()  / n#recv()    — sender is the parent of the recipient
 * - CHILD_TO_PARENT:  @send()   / @recv()     — sender is a child of the recipient
 * - LOCAL:            send()    / recv()       — sender and recipient are the same ambient
 * - UNSUPPORTED:      relationship cannot be determined or is not directly supported
 *
 * @author Konstantin Rusev
 * @version 3.0
 */
public enum CommunicationType {

	SIBLING("Sibling (::)", "Ambients share the same parent ambient"),
	PARENT_TO_CHILD("Parent to Child (#)", "Sender is the parent of the recipient"),
	CHILD_TO_PARENT("Child to Parent (@)", "Sender is a child of the recipient"),
	LOCAL("Local", "Sender and recipient are the same ambient"),
	UNSUPPORTED("Unsupported", "Relationship cannot be determined");

	private final String displayName;
	private final String description;

	CommunicationType(String displayName, String description) {
		this.displayName = displayName;
		this.description = description;
	}

	/**
	 * Returns the inverse communication type.
	 * When generating capabilities for the RECIPIENT side of a message,
	 * the relationship is viewed from the opposite direction.
	 *
	 * PARENT_TO_CHILD inverses to CHILD_TO_PARENT and vice versa.
	 * SIBLING and LOCAL are symmetric.
	 */
	public CommunicationType inverse() {
		switch (this) {
			case PARENT_TO_CHILD: return CHILD_TO_PARENT;
			case CHILD_TO_PARENT: return PARENT_TO_CHILD;
			case SIBLING:         return SIBLING;
			case LOCAL:           return LOCAL;
			default:              return UNSUPPORTED;
		}
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getDescription() {
		return description;
	}
}
