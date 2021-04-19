package com.university.cca.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.buttons.ambients.BusButton;
import com.university.cca.buttons.ambients.BusStationButton;
import com.university.cca.buttons.ambients.CarButton;
import com.university.cca.buttons.ambients.CityButton;
import com.university.cca.buttons.ambients.CoffeeButton;
import com.university.cca.buttons.ambients.ContinentButton;
import com.university.cca.buttons.ambients.CountryButton;
import com.university.cca.buttons.ambients.HarbourButton;
import com.university.cca.buttons.ambients.HospitalButton;
import com.university.cca.buttons.ambients.HotelButton;
import com.university.cca.buttons.ambients.LorryButton;
import com.university.cca.buttons.ambients.MotorbikeButton;
import com.university.cca.buttons.ambients.MuseumButton;
import com.university.cca.buttons.ambients.OtherButton;
import com.university.cca.buttons.ambients.PAButton;
import com.university.cca.buttons.ambients.ParkingButton;
import com.university.cca.buttons.ambients.PharmacyButton;
import com.university.cca.buttons.ambients.RestaurantButton;
import com.university.cca.buttons.ambients.SchoolButton;
import com.university.cca.buttons.ambients.ShopButton;
import com.university.cca.buttons.ambients.TaxiButton;
import com.university.cca.buttons.ambients.TrainButton;
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
		this.add(new PAButton(this.parentFrame));
		this.add(new HospitalButton(this.parentFrame));
		this.add(new HarbourButton(this.parentFrame));
		this.add(new HotelButton(this.parentFrame));
		this.add(new BusStationButton(this.parentFrame));
		this.add(new PharmacyButton(this.parentFrame));
		this.add(new ShopButton(this.parentFrame));
		this.add(new SchoolButton(this.parentFrame));
		this.add(new MuseumButton(this.parentFrame));
		this.add(new UniversityButton(this.parentFrame));
		this.add(new RestaurantButton(this.parentFrame));
		this.add(new CoffeeButton(this.parentFrame));
		this.add(new ParkingButton(this.parentFrame));
		this.add(new CityButton(this.parentFrame));
		this.add(new CountryButton(this.parentFrame));
		this.add(new ContinentButton(this.parentFrame));
		this.add(new MotorbikeButton(this.parentFrame));
		this.add(new CarButton(this.parentFrame));
		this.add(new TaxiButton(this.parentFrame));
		this.add(new LorryButton(this.parentFrame));
		this.add(new BusButton(this.parentFrame));
		this.add(new TrainButton(this.parentFrame));
		this.add(new OtherButton(this.parentFrame));
	}
}
