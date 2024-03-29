package com.university.cca.util;

import java.awt.Cursor;

/**
 * Utility methods related to the mouse cursor of the CCA Editor application.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MouseCursorUtil {

	private MouseCursorUtil() {
		// Constructor that prevents creating an object of type MouseCursorUtil class
	}
	
	public static Cursor getMouseHand() {
		return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	}
	
	public static Cursor getMouseText() {
		return Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR);
	}
}
