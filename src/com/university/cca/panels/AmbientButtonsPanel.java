package com.university.cca.panels;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.buttons.BusStationButton;
import com.university.cca.buttons.CityButton;
import com.university.cca.buttons.CoffeeButton;
import com.university.cca.buttons.ContinentButton;
import com.university.cca.buttons.CountryButton;
import com.university.cca.buttons.HospitalButton;
import com.university.cca.buttons.HotelButton;
import com.university.cca.buttons.MuseumButton;
import com.university.cca.buttons.OtherButton;
import com.university.cca.buttons.ParkingButton;
import com.university.cca.buttons.PharmacyButton;
import com.university.cca.buttons.HarbourButton;
import com.university.cca.buttons.RestaurantButton;
import com.university.cca.buttons.SchoolButton;
import com.university.cca.buttons.ShopButton;
import com.university.cca.buttons.UniversityButton;
import com.university.cca.constants.Constants;
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
	private static final int GRID_ROWS = 0;
	private static final int GRID_COLS = 1;
	
	private JFrame parentFrame;

	public AmbientButtonsPanel(JFrame parentFrame) {
		logger.info("Ambient buttons panel initialized");
		this.parentFrame = parentFrame;
		
		this.setLayout(PanelsUtil.getGridLayout(GRID_ROWS, GRID_COLS));
		this.setBackground(Constants.LIGHT_GREY);
		PanelsUtil.setPanelSize(this);
		
		addContentToPanel();
		
		JScrollPane scrollPanel = new JScrollPane(this);
		
		parentFrame.add(scrollPanel);
		this.setVisible(true);
	}
	
	/**
	 * Method that adds content to the panel with all of the ambient buttons
	 */
	private void addContentToPanel() {
		this.add(createHeaderLabel());
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
		this.add(new OtherButton(parentFrame));
	}
	
	/**
	 * Create a header label of the panel with all of the ambient buttons
	 */
	private JLabel createHeaderLabel() {
		JLabel label = new JLabel("Ambients Library", JLabel.CENTER);
		label.setFont(new Font(Constants.FONT_ARIAL, Font.BOLD + Font.ITALIC, Constants.FONT_SIZE_FOURTEEN));
		label.setBackground(Constants.LIGHT_GREY);
		label.setOpaque(true);
		
		return label;
	}
}
