package com.university.cca.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.buttons.ambients.BusStationButton;
import com.university.cca.buttons.ambients.CarButton;
import com.university.cca.buttons.ambients.CityButton;
import com.university.cca.buttons.ambients.CoffeeButton;
import com.university.cca.buttons.ambients.ContinentButton;
import com.university.cca.buttons.ambients.CountryButton;
import com.university.cca.buttons.ambients.HarbourButton;
import com.university.cca.buttons.ambients.HospitalButton;
import com.university.cca.buttons.ambients.HotelButton;
import com.university.cca.buttons.ambients.MuseumButton;
import com.university.cca.buttons.ambients.OtherButton;
import com.university.cca.buttons.ambients.ParkingButton;
import com.university.cca.buttons.ambients.PharmacyButton;
import com.university.cca.buttons.ambients.RestaurantButton;
import com.university.cca.buttons.ambients.SchoolButton;
import com.university.cca.buttons.ambients.ShopButton;
import com.university.cca.buttons.ambients.UniversityButton;
import com.university.cca.constants.Constants;
import com.university.cca.util.CCAUtils;
import com.university.cca.util.PanelsUtil;

/**
 * Panel with all of the buttons that create ambients
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientButtonsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(AmbientButtonsPanel.class);
    private static final Dimension screenSize = CCAUtils.getScreenSize();
    private static final int GRID_ROWS = 0;
	private static final int GRID_COLS = 1;
	
	private JFrame parentFrame;

	public AmbientButtonsPanel(JFrame parentFrame) {
		logger.info("Ambient buttons panel initialized");
		this.parentFrame = parentFrame;
		
		this.setLayout(PanelsUtil.getGridLayout(GRID_ROWS, GRID_COLS));
		this.setBorder(BorderFactory.createTitledBorder("Ambients Library"));
		this.setBackground(Constants.LIGHT_GREY);
		
		this.setPreferredSize(new Dimension(screenSize.width / 7, screenSize.height));
	    this.setMaximumSize(new Dimension(screenSize.width / 7, screenSize.height));
		
		addContentToPanel();
		
		parentFrame.add(new JScrollPane(this), BorderLayout.WEST);
		this.setVisible(true);
	}
	
	/**
	 * Method that adds content to the panel with all of the ambient buttons
	 */
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
		this.add(new CityButton(parentFrame));
		this.add(new CountryButton(parentFrame));
		this.add(new ContinentButton(parentFrame));
		this.add(new CarButton(parentFrame));
		this.add(new OtherButton(parentFrame));
	}
}
