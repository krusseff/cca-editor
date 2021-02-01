package com.university.cca.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.university.cca.buttons.BusStationButton;
import com.university.cca.buttons.CoffeeButton;
import com.university.cca.buttons.HospitalButton;
import com.university.cca.buttons.HotelButton;
import com.university.cca.buttons.MuseumButton;
import com.university.cca.buttons.ParkingButton;
import com.university.cca.buttons.PharmacyButton;
import com.university.cca.buttons.HarbourButton;
import com.university.cca.buttons.RestaurantButton;
import com.university.cca.buttons.SchoolButton;
import com.university.cca.buttons.ShopButton;
import com.university.cca.buttons.UniversityButton;
import com.university.cca.util.CCAUtils;

/**
 * Main Panel of CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AppMainPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int GRID_ROWS = 0;
	private static final int GRID_COLS = 3;
	
	private JFrame parentFrame;

	public AppMainPanel(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setLayout(getGridLayout());
		setMainPanelSize();
		
		addContentToPanel();
		
		parentFrame.add(this);
		this.setVisible(true);
	}
	
	private void addContentToPanel() {
		this.add(new HospitalButton(parentFrame));
		this.add(new HarbourButton(parentFrame));
		this.add(new HotelButton(parentFrame));
		this.add(new BusStationButton(parentFrame));
		this.add(new PharmacyButton(parentFrame));
		this.add(new ShopButton(parentFrame));
		this.add(new SchoolButton(parentFrame));
		this.add(new MuseumButton(parentFrame));
		this.add(new UniversityButton(parentFrame));
		this.add(new RestaurantButton(parentFrame));
		this.add(new CoffeeButton(parentFrame));
		this.add(new ParkingButton(parentFrame));
	}
	
	private void setMainPanelSize() {
		CCAUtils ccaUtils = new CCAUtils();
	    Dimension screenSize = ccaUtils.getScreenSize();
	    this.setBounds(1, 20, screenSize.width / 2, ((screenSize.height / 2) - 30));
	}
	
	private GridLayout getGridLayout() {
		GridLayout layout = new GridLayout(GRID_ROWS, GRID_COLS);
        layout.setHgap(10);
        layout.setVgap(10);
        
        return layout;
	}
}
