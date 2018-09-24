package com.university.cca.panels;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.university.cca.util.CCAUtils;

/**
 * Main Panel of CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MainPanel {
	
	public void createMainPanel(JFrame jFrame, JPanel jPanel) {
		jPanel.setVisible(true);
		jPanel.setLayout(null);
		setMainPanelSize(jPanel);
		jFrame.add(jPanel);
	}
	
	private void setMainPanelSize(JPanel jPanel) {
		CCAUtils ccaUtils = new CCAUtils();
	    Dimension screenSize = ccaUtils.getScreenSize();
	    // jPanel.setBounds(1, 5, 818, 450); // TODO Set appropriate size
	    jPanel.setBounds(1, 20, screenSize.width / 2, ((screenSize.height / 2) - 30));
	}
}
