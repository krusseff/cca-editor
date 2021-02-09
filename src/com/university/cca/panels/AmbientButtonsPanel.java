package com.university.cca.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.university.cca.buttons.BusStationButton;
import com.university.cca.buttons.CityButton;
import com.university.cca.buttons.CoffeeButton;
import com.university.cca.buttons.ContinentButton;
import com.university.cca.buttons.CountryButton;
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
import com.university.cca.constants.Constants;
import com.university.cca.util.CCAUtils;

/**
 * Panel with all of the buttons that create ambients
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientButtonsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int GRID_ROWS = 0;
	private static final int GRID_COLS = 1;
	
	private JFrame parentFrame;

	public AmbientButtonsPanel(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setLayout(getGridLayout());
		this.setBackground(Constants.LIGHT_GREY);
		setPanelSize();
		
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
	
	/**
	 * Set the size of the panel with all of the ambient buttons
	 */
	private void setPanelSize() {
		CCAUtils ccaUtils = new CCAUtils();
	    Dimension screenSize = ccaUtils.getScreenSize();
	    this.setBounds(1, 20, screenSize.width / 2, ((screenSize.height / 2) - 30));
	}
	
	/**
	 * Set the Grid Layout of the panel with all of the ambient buttons
	 */
	private GridLayout getGridLayout() {
		GridLayout layout = new GridLayout(GRID_ROWS, GRID_COLS);
        layout.setHgap(10);
        layout.setVgap(10);
        
        return layout;
	}
}
