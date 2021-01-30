package com.university.cca.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.university.cca.buttons.BusStationButton;
import com.university.cca.buttons.HospitalButton;
import com.university.cca.buttons.HotelButton;
import com.university.cca.buttons.PortButton;
import com.university.cca.util.CCAUtils;

/**
 * Main Panel of CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AppMainPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public AppMainPanel(JFrame parentFrame) {
		this.setLayout(new FlowLayout());
		setMainPanelSize();
		
		addContentToPanel();
		
		parentFrame.add(this);
		this.setVisible(true);
	}
	
	private void addContentToPanel() {
		HospitalButton createHospitalButton = new HospitalButton();
		PortButton createPortButton = new PortButton();
		HotelButton createHotelButton = new HotelButton();
		BusStationButton createStationButton = new BusStationButton();
		
		this.add(createHospitalButton);
		this.add(createPortButton);
		this.add(createHotelButton);
		this.add(createStationButton);
	}
	
	private void setMainPanelSize() {
		CCAUtils ccaUtils = new CCAUtils();
	    Dimension screenSize = ccaUtils.getScreenSize();
	    this.setBounds(1, 20, screenSize.width / 2, ((screenSize.height / 2) - 30));
	}
}
