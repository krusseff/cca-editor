package com.university.cca.generator;

import com.university.cca.enums.CommunicationType;

/**
 * Builds individual ccaPL capability strings with the correct location specifier
 * based on the communication type between two ambients.
 *
 * ccaPL location specifiers:
 *   (empty) = locally          — send(msg)         / recv(msg)
 *   ::      = sibling          — n::send(msg)      / n::recv(msg)
 *   #       = parent-to-child  — childName#send(msg) / childName#recv(msg)
 *   @       = child-to-parent  — @send(msg)        / @recv(msg)
 *
 * @author Konstantin Rusev
 * @version 3.0
 */
public class CCACapabilityBuilder {

	private CCACapabilityBuilder() {
		// Prevent instantiation
	}

	/**
	 * Generates a send capability string with the appropriate location specifier.
	 *
	 * @param type         the communication type from THIS ambient's perspective
	 * @param otherAmbient the name of the ambient being communicated with
	 * @param parameters   the message parameters (already sanitized for ccaPL)
	 * @return the ccaPL send capability, e.g. "bob::send(hello)" or "@send(hello)"
	 */
	public static String buildSend(CommunicationType type, String otherAmbient, String parameters) {
		switch (type) {
			case SIBLING:
				return otherAmbient + "::send(" + parameters + ")";
			case PARENT_TO_CHILD:
				return otherAmbient + "#send(" + parameters + ")";
			case CHILD_TO_PARENT:
				return "@send(" + parameters + ")";
			case LOCAL:
				return "send(" + parameters + ")";
			default:
				throw new IllegalArgumentException("Unsupported communication type for send: " + type);
		}
	}

	/**
	 * Generates a recv capability string with the appropriate location specifier.
	 *
	 * @param type         the communication type from THIS ambient's perspective
	 * @param otherAmbient the name of the ambient being communicated with
	 * @param parameters   the receive parameters (already sanitized for ccaPL)
	 * @return the ccaPL recv capability, e.g. "bob::recv(x)" or "@recv(x)"
	 */
	public static String buildRecv(CommunicationType type, String otherAmbient, String parameters) {
		switch (type) {
			case SIBLING:
				return otherAmbient + "::recv(" + parameters + ")";
			case PARENT_TO_CHILD:
				return otherAmbient + "#recv(" + parameters + ")";
			case CHILD_TO_PARENT:
				return "@recv(" + parameters + ")";
			case LOCAL:
				return "recv(" + parameters + ")";
			default:
				throw new IllegalArgumentException("Unsupported communication type for recv: " + type);
		}
	}
}
