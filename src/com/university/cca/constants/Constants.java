package com.university.cca.constants;

import java.awt.Color;

/**
 * All constants for CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public final class Constants {
	public static final String APPLICATION_TITLE = "CCA Editor";
	
	// Ambient files paths
	public static final String AMBIENTS_CSV_FILE_PATH = "./files/csv/ambients.csv";
	public static final String AMBIENTS_CCA_FILE_PATH = "./files/cca/ambients.cca";
	public static final String MESSAGES_CSV_FILE_PATH = "./files/csv/messages.csv";
	
	// Application system images paths
	public static final String IMAGES_ICON_JPG = "./images/app/icon.jpg";
	public static final String IMAGES_LOADING_SCREEN_JPG = "./images/app/loading-screen.jpg";
	public static final String SUCCESS_ICON_PATH = "./images/buttons/check.png";
	public static final String CANCEL_ICON_PATH = "./images/buttons/cancel.png";
	public static final String MESSAGE_ICON_PATH = "./images/buttons/message.png";
	
	// File menu images paths
	public static final String FILE_ICON_PATH = "./images/menu/file/file.png";
	
	// Edit menu images paths
	public static final String EDIT_ICON_PATH = "./images/menu/edit/edit.png";
	public static final String CUT_ICON_PATH = "./images/menu/edit/cut.png";
	public static final String COPY_ICON_PATH = "./images/menu/edit/copy.png";
	public static final String PASTE_ICON_PATH = "./images/menu/edit/paste.png";
	
	// View menu images paths
	public static final String VIEW_ICON_PATH = "./images/menu/view/view.png";
	
	// About menu images paths
	public static final String ABOUT_ICON_PATH = "./images/menu/about/about.png";
	
	// Help menu images paths
	public static final String HELP_ICON_PATH = "./images/menu/help/help.png";
	
	// Ambient buttons images paths
	public static final String HOSPITAL_ICON_PATH = "./images/ambient_buttons/hospital.png";
	public static final String HARBOUR_ICON_PATH = "./images/ambient_buttons/port.png";
	public static final String HOTEL_ICON_PATH = "./images/ambient_buttons/hotel.png";
	public static final String BUS_STATION_ICON_PATH = "./images/ambient_buttons/bus_station.png";
	public static final String PHARMACY_ICON_PATH = "./images/ambient_buttons/pharmacy.png";
	public static final String SHOP_ICON_PATH = "./images/ambient_buttons/shop.png";
	public static final String SCHOOL_ICON_PATH = "./images/ambient_buttons/school.png";
	public static final String MUSEUM_ICON_PATH = "./images/ambient_buttons/museum.png";
	public static final String UNIVERSITY_ICON_PATH = "./images/ambient_buttons/university.png";
	public static final String RESTAURANT_ICON_PATH = "./images/ambient_buttons/restaurant.png";
	public static final String COFFEE_ICON_PATH = "./images/ambient_buttons/coffee.png";
	public static final String PARKING_ICON_PATH = "./images/ambient_buttons/parking.png";
	public static final String CITY_ICON_PATH = "./images/ambient_buttons/city.png";
	public static final String COUNTRY_ICON_PATH = "./images/ambient_buttons/country.png";
	public static final String CONTINENT_ICON_PATH = "./images/ambient_buttons/continent.png";
	
	public static final String FONT_ARIAL = "Arial";
	public static final int FONT_SIZE_FOURTEEN = 14;
	public static final Color LIGHT_GREY = new Color(222, 222, 222);
	
	public static final String MAIN_FRAME_NAME = "Main Frame Panel";
	
	// Menu text labels
	public static final String FILE = "File";
	
	public static final String EDIT = "Edit";
	public static final String EDIT_ITEM_CUT = "Cut";
	public static final String EDIT_ITEM_COPY = "Copy";
	public static final String EDIT_ITEM_PASTE = "Paste";

	public static final String VIEW = "View";
	
	public static final String HELP = "Help";
	public static final String HELP_ITEM_FAQ = "FAQ";
	
	public static final String ABOUT = "About";
	public static final String ABOUT_ITEM_CCA = "About CCA";
	public static final String ABOUT_ITEM_CCA_EDITOR = "About CCA Editor";
	
	private Constants() {
		// private constructor to hide the implicit public one
	}
}
