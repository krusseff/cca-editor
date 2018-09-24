package com.university.cca.util;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Utility methods for CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CCAUtils {
	public Dimension getScreenSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize;
	}
}
