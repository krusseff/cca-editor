package com.university.cca.constants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * All constants for CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public final class Constants {
	
	public static final String APPLICATION_TITLE = "CCA Editor";
	public static final String MAIN_FRAME_NAME = "CCA Editor Main Frame Panel";
	public static final String FONT_ARIAL = "Arial";
	public static final int FONT_SIZE_FOURTEEN = 14;
	public static final int ICON_GAP_SIZE = 8;
	public static final Color LIGHT_GREY = new Color(222, 222, 222);
	
	// Ambient files paths
	public static final String AMBIENTS_CSV_FILE_PATH = "./files/csv/ambients.csv";
	public static final String AMBIENTS_CCA_FILE_PATH = "./files/cca/ambients.cca";
	public static final String MESSAGES_CSV_FILE_PATH = "./files/csv/messages.csv";
	
	// Email configuration files paths
	public static final String EMAIL_CONFIG_FILE_PATH = "src/main/resources/email_config.json";
	
	// Application system images paths
	public static final String IMAGES_ICON_JPG = "./images/app/icon.jpg";
	public static final String IMAGES_LOADING_SCREEN_JPG = "./images/app/loading-screen.jpg";
	public static final String BACKGROUND_ICON_PATH = "./images/app/background_map.gif";
	public static final String BACKGROUND_LOVECH_ICON_PATH = "./images/app/lovech_map_mini.png";
	public static final String BACKGROUND_AGRICULTURE_ICON_PATH = "./images/app/agriculture.jpg";
	public static final String SUCCESS_ICON_PATH = "./images/buttons/check.png";
	public static final String CANCEL_ICON_PATH = "./images/buttons/cancel.png";
	public static final String CLOSE_ICON_PATH = "./images/buttons/close.png";
	public static final String SEND_ICON_PATH = "./images/buttons/send.png";
	public static final String MESSAGE_ICON_PATH = "./images/buttons/message.png";
	public static final String SAVE_BUTTON_ICON_PATH = "./images/buttons/save.png";
	
	// CCA related images paths
	public static final String GENERATOR_CCA_ICON_PATH = "./images/cca_buttons/generator_cca.png";
	public static final String GENERATE_CCA_ICON_PATH = "./images/cca_buttons/generate_cca.png";
	public static final String OPEN_CCA_ICON_PATH = "./images/cca_buttons/open_cca.png";
	public static final String START_CONSOLE_CCA_ICON_PATH = "./images/cca_buttons/start_cca.png";
	public static final String START_ANIMATED_CCA_ICON_PATH = "./images/cca_buttons/start_animated_cca.png";
	
	// CCA Team images paths
	public static final String STOYANOV_ICON_PATH = "./images/cca_team/prof-stoyanov.png";
	public static final String GLUSHKOVA_ICON_PATH = "./images/cca_team/t-glushkova.png";
	public static final String RUSEV_ICON_PATH = "./images/cca_team/k-russeff.png";
	
	// File menu images paths
	public static final String FILE_ICON_PATH = "./images/menu/file/file.png";
	public static final String NEW_ICON_PATH = "./images/menu/file/new.png";
	public static final String OPEN_ICON_PATH = "./images/menu/file/open.png";
	public static final String SAVE_ICON_PATH = "./images/menu/file/save.png";
	public static final String EXIT_ICON_PATH = "./images/menu/file/exit.png";
	public static final String IMPORT_ICON_PATH = "./images/menu/file/import.png";
	public static final String EXPORT_ICON_PATH = "./images/menu/file/export.png";
	public static final String REFRESH_ICON_PATH = "./images/menu/file/refresh.png";
	public static final String RESTART_ICON_PATH = "./images/menu/file/restart.png";
	public static final String TERMINAL_ICON_PATH = "./images/menu/file/terminal.png";
	
	// Edit menu images paths
	public static final String EDIT_ICON_PATH = "./images/menu/edit/edit.png";
	public static final String CUT_ICON_PATH = "./images/menu/edit/cut.png";
	public static final String COPY_ICON_PATH = "./images/menu/edit/copy.png";
	public static final String PASTE_ICON_PATH = "./images/menu/edit/paste.png";
	public static final String DELETE_ICON_PATH = "./images/menu/edit/delete.png";
	
	// View menu images paths
	public static final String VIEW_ICON_PATH = "./images/menu/view/view.png";
	public static final String SHOW_CSV_ICON_PATH = "./images/menu/view/show_csv.png";
	public static final String SHOW_CCA_ICON_PATH = "./images/menu/view/show_ambients_cca.png";
	public static final String SHOW_AMBIENTS_INFO_ICON_PATH = "./images/menu/view/show_ambients_info.png";
	
	// About menu images paths
	public static final String ABOUT_ICON_PATH = "./images/menu/about/about.png";
	public static final String ABOUT_CCA_ICON_PATH = "./images/menu/about/about_cca.png";
	public static final String CCA_TEAM_ICON_PATH = "./images/menu/about/cca_team.png";
	public static final String LINKS_ICON_PATH = "./images/menu/about/useful_links.png";
	public static final String TECH_ICON_PATH = "./images/menu/about/technologies.png";
	public static final String CONTACT_ICON_PATH = "./images/menu/about/contact_us.png";
	
	// Help menu images paths
	public static final String HELP_ICON_PATH = "./images/menu/help/help.png";
	public static final String TERMS_ICON_PATH = "./images/menu/help/terms.png";
	public static final String SHORTCUTS_ICON_PATH = "./images/menu/help/tips_tricks.png";
	
	// Ambient buttons images paths
	public static final String PA_ICON_PATH = "./images/ambient_buttons/personal_assistant.png";
	public static final String HOSPITAL_ICON_PATH = "./images/ambient_buttons/hospital.png";
	public static final String HARBOUR_ICON_PATH = "./images/ambient_buttons/port.png";
	public static final String HOTEL_ICON_PATH = "./images/ambient_buttons/hotel.png";
	public static final String BUS_ICON_PATH = "./images/ambient_buttons/bus.png";
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
	public static final String MOTORBIKE_ICON_PATH = "./images/ambient_buttons/motorbike.png";
	public static final String CAR_ICON_PATH = "./images/ambient_buttons/car.png";
	public static final String TAXI_ICON_PATH = "./images/ambient_buttons/taxi.png";
	public static final String LORRY_ICON_PATH = "./images/ambient_buttons/lorry.png";
	public static final String TRAIN_ICON_PATH = "./images/ambient_buttons/train.png";
	public static final String OPERATIONS_ICON_PATH = "./images/ambient_buttons/operations_center.png";
	public static final String WEATHER_STATION_ICON_PATH = "./images/ambient_buttons/weather_station.png";
	public static final String HUMIDITY_STATION_ICON_PATH = "./images/ambient_buttons/humidity_station.png";
	public static final String PUMP_STATION_ICON_PATH = "./images/ambient_buttons/pump_station.png";
	public static final String SPRINKLER_ICON_PATH = "./images/ambient_buttons/sprinkler.png";
	public static final String ORCHARD_ICON_PATH = "./images/ambient_buttons/orchard.png";
	public static final String GARDEN_ICON_PATH = "./images/ambient_buttons/garden.png";
	public static final String GREENHOUSE_ICON_PATH = "./images/ambient_buttons/greenhouse.png";
	public static final String TRACTOR_ICON_PATH = "./images/ambient_buttons/tractor.png";
	public static final String PLANTER_ICON_PATH = "./images/ambient_buttons/planter.png";
	public static final String HARVESTER_ICON_PATH = "./images/ambient_buttons/harvester.png";
	public static final String MOWER_ICON_PATH = "./images/ambient_buttons/mower.png";
	public static final String BALER_ICON_PATH = "./images/ambient_buttons/baler.png";
	public static final String DRONE_ICON_PATH = "./images/ambient_buttons/drone.png";
	public static final String OTHER_ICON_PATH = "./images/ambient_buttons/other.png";
	
	// Menu Text Labels
	public static final String FILE = "File";
	public static final String FILE_ITEM_NEW = "New...";
	public static final String FILE_ITEM_OPEN = "Open...";
	public static final String FILE_ITEM_SAVE = "Save...";
	public static final String FILE_ITEM_EXIT = "Exit...";
	public static final String FILE_ITEM_IMPORT = "Import...";
	public static final String FILE_ITEM_EXPORT = "Export...";
	public static final String FILE_ITEM_REFRESH = "Refresh...";
	public static final String FILE_ITEM_RESTART = "Restart...";
	public static final String FILE_ITEM_TERMINAL = "Open Terminal...";
	
	public static final String EDIT = "Edit";
	public static final String EDIT_ITEM_CUT = "Cut...";
	public static final String EDIT_ITEM_COPY = "Copy...";
	public static final String EDIT_ITEM_PASTE = "Paste...";
	public static final String EDIT_ITEM_DELETE = "Delete...";

	public static final String VIEW = "View";
	public static final String VIEW_ITEM_AMBIENTS_CSV = "Show Ambients CSV File";
	public static final String VIEW_ITEM_MESSAGES_CSV = "Show Messages CSV File";
	public static final String VIEW_ITEM_AMBIENTS_CCA = "Show Ambients CCA File";
	public static final String VIEW_ITEM_AMBIENTS = "Show Ambients";
	public static final String VIEW_ITEM_MESSAGES = "Show Messages";
	
	public static final String HELP = "Help";
	public static final String HELP_ITEM_FAQ = "FAQs";
	public static final String HELP_ITEM_TERMS = "T&C's";
	public static final String HELP_ITEM_SHORTCUTS = "Tips and Tricks...";
	
	public static final String ABOUT = "About";
	public static final String ABOUT_ITEM_CCA = "About CCA";
	public static final String ABOUT_ITEM_CCA_EDITOR = "About CCA Editor";
	public static final String ABOUT_ITEM_CCA_TEAM = "CCA Editor Team";
	public static final String ABOUT_ITEM_LINKS = "Useful Links";
	public static final String ABOUT_ITEM_TECH = "Technologies";
	public static final String ABOUT_ITEM_CONTACT = "Contact Us";
	
	// Shortcuts
	public static final int CTRL_SHORTCUT = ActionEvent.CTRL_MASK;
	
	// File menu shortcuts
	public static final int NEW_SHORTCUT = KeyEvent.VK_N;
	public static final int OPEN_SHORTCUT = KeyEvent.VK_O;
	public static final int SAVE_SHORTCUT = KeyEvent.VK_S;
	public static final int EXIT_SHORTCUT = KeyEvent.VK_E;
	public static final int IMPORT_SHORTCUT = KeyEvent.VK_I;
	public static final int EXPORT_SHORTCUT = KeyEvent.VK_X;
	public static final int REFRESH_SHORTCUT = KeyEvent.VK_R;
	public static final int RESTART_SHORTCUT = KeyEvent.VK_L;
	public static final int TERMINAL_SHORTCUT = KeyEvent.VK_T;
	
	// Edit menu shortcuts
	public static final int CUT_SHORTCUT = KeyEvent.VK_U;
	public static final int COPY_SHORTCUT = KeyEvent.VK_C;
	public static final int PASTE_SHORTCUT = KeyEvent.VK_P;
	public static final int DELETE_SHORTCUT = KeyEvent.VK_D;
	
	// About menu shortcuts
	public static final int ABOUT_CCA_SHORTCUT = KeyEvent.VK_0;
	public static final int ABOUT_CCA_EDITOR_SHORTCUT = KeyEvent.VK_1;
	public static final int CCA_TEAM_SHORTCUT = KeyEvent.VK_2;
	public static final int USEFUL_LINKS_SHORTCUT = KeyEvent.VK_3;
	public static final int TECHNOLOGIES_SHORTCUT = KeyEvent.VK_4;
	public static final int CONTACT_US_SHORTCUT = KeyEvent.VK_5;
	public static final int SHOW_AMBIENTS_CSV_SHORTCUT = KeyEvent.VK_6;
	public static final int SHOW_MESSAGES_CSV_SHORTCUT = KeyEvent.VK_7;
	public static final int SHOW_AMBIENTS_SHORTCUT = KeyEvent.VK_8;
	public static final int SHOW_MESSAGES_SHORTCUT = KeyEvent.VK_9;
	public static final int SHOW_AMBIENTS_CCA_SHORTCUT = KeyEvent.VK_A;
	
	private Constants() {
		// private constructor to hide the implicit public one
	}
}
