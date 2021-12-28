package com.university.cca.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.buttons.ambients.BalerButton;
import com.university.cca.buttons.ambients.BusButton;
import com.university.cca.buttons.ambients.BusStationButton;
import com.university.cca.buttons.ambients.CarButton;
import com.university.cca.buttons.ambients.CityButton;
import com.university.cca.buttons.ambients.CoffeeButton;
import com.university.cca.buttons.ambients.ContinentButton;
import com.university.cca.buttons.ambients.CountryButton;
import com.university.cca.buttons.ambients.DroneButton;
import com.university.cca.buttons.ambients.GardenButton;
import com.university.cca.buttons.ambients.GreenHouseButton;
import com.university.cca.buttons.ambients.HarbourButton;
import com.university.cca.buttons.ambients.HarvesterButton;
import com.university.cca.buttons.ambients.HospitalButton;
import com.university.cca.buttons.ambients.HotelButton;
import com.university.cca.buttons.ambients.HumidityStationButton;
import com.university.cca.buttons.ambients.LorryButton;
import com.university.cca.buttons.ambients.MotorbikeButton;
import com.university.cca.buttons.ambients.MowerButton;
import com.university.cca.buttons.ambients.MuseumButton;
import com.university.cca.buttons.ambients.OperationsCenterButton;
import com.university.cca.buttons.ambients.OrchardButton;
import com.university.cca.buttons.ambients.OtherButton;
import com.university.cca.buttons.ambients.PAButton;
import com.university.cca.buttons.ambients.ParkingButton;
import com.university.cca.buttons.ambients.PharmacyButton;
import com.university.cca.buttons.ambients.PlanterButton;
import com.university.cca.buttons.ambients.PumpStationButton;
import com.university.cca.buttons.ambients.RestaurantButton;
import com.university.cca.buttons.ambients.SchoolButton;
import com.university.cca.buttons.ambients.ShopButton;
import com.university.cca.buttons.ambients.TaxiButton;
import com.university.cca.buttons.ambients.TractorButton;
import com.university.cca.buttons.ambients.TrainButton;
import com.university.cca.buttons.ambients.UniversityButton;
import com.university.cca.buttons.ambients.WaterSprinklerButton;
import com.university.cca.buttons.ambients.WeatherStationButton;
import com.university.cca.constants.ApplicationModes;
import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.CCAUtils;
import com.university.cca.util.PanelsUtil;

/**
 * Panel with all of the buttons that create ambients.
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
	
	private AppMainFrame parentFrame;

	public AmbientButtonsPanel(AppMainFrame parentFrame) {
		logger.info("Ambient buttons panel is initialized");
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
	 * Method that adds content to the panel with all of the ambient buttons.
	 */
	private void addContentToPanel() {

		switch (this.parentFrame.getApplicationMode()) {
			case ApplicationModes.SMART_CITY:
				addSmartCityButtonsToPanel();
				break;
				
			case ApplicationModes.SMART_AGRICULTURE:
				addSmartAgricultureButtonsToPanel();
				break;
	
			default:
				addSmartCityButtonsToPanel();
				break;
		}
	}
	
	/**
	 * Method that adds content to the panel with all of the smart city ambient buttons.
	 */
	private void addSmartCityButtonsToPanel() {
		this.add(new PAButton(getParentFrame()));
		this.add(new HospitalButton(getParentFrame()));
		this.add(new HarbourButton(getParentFrame()));
		this.add(new HotelButton(getParentFrame()));
		this.add(new BusStationButton(getParentFrame()));
		this.add(new PharmacyButton(getParentFrame()));
		this.add(new ShopButton(getParentFrame()));
		this.add(new SchoolButton(getParentFrame()));
		this.add(new MuseumButton(getParentFrame()));
		this.add(new UniversityButton(getParentFrame()));
		this.add(new RestaurantButton(getParentFrame()));
		this.add(new CoffeeButton(getParentFrame()));
		this.add(new ParkingButton(getParentFrame()));
		this.add(new CityButton(getParentFrame()));
		this.add(new CountryButton(getParentFrame()));
		this.add(new ContinentButton(getParentFrame()));
		this.add(new MotorbikeButton(getParentFrame()));
		this.add(new CarButton(getParentFrame()));
		this.add(new TaxiButton(getParentFrame()));
		this.add(new LorryButton(getParentFrame()));
		this.add(new BusButton(getParentFrame()));
		this.add(new TrainButton(getParentFrame()));
		this.add(new OtherButton(getParentFrame()));
	}
	
	/**
	 * Method that adds content to the panel with all of the smart agriculture ambient buttons.
	 */
	private void addSmartAgricultureButtonsToPanel() {
		this.add(new PAButton(getParentFrame()));
		this.add(new OperationsCenterButton(getParentFrame()));
		this.add(new WeatherStationButton(getParentFrame()));
		this.add(new HumidityStationButton(getParentFrame()));
		this.add(new PumpStationButton(getParentFrame()));
		this.add(new WaterSprinklerButton(getParentFrame()));
		this.add(new OrchardButton(getParentFrame()));
		this.add(new GardenButton(getParentFrame()));
		this.add(new GreenHouseButton(getParentFrame()));
		this.add(new DroneButton(getParentFrame()));
		this.add(new TractorButton(getParentFrame()));
		this.add(new PlanterButton(getParentFrame()));
		this.add(new HarvesterButton(getParentFrame()));
		this.add(new MowerButton(getParentFrame()));
		this.add(new BalerButton(getParentFrame()));
		this.add(new CityButton(getParentFrame()));
		this.add(new CountryButton(getParentFrame()));
		this.add(new ContinentButton(getParentFrame()));
		this.add(new OtherButton(getParentFrame()));
	}

	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}
