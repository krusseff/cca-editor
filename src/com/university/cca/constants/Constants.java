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
	public static final String MAIN_FRAME_NAME = "Main Frame Panel";
	public static final String FONT_ARIAL = "Arial";
	public static final int FONT_SIZE_FOURTEEN = 14;
	public static final int ICON_GAP_SIZE = 8;
	public static final Color LIGHT_GREY = new Color(222, 222, 222);
	
	// Ambient files paths
	public static final String AMBIENTS_CSV_FILE_PATH = "./files/csv/ambients.csv";
	public static final String AMBIENTS_CCA_FILE_PATH = "./files/cca/ambients.cca";
	public static final String MESSAGES_CSV_FILE_PATH = "./files/csv/messages.csv";
	
	// Application system images paths
	public static final String IMAGES_ICON_JPG = "./images/app/icon.jpg";
	public static final String IMAGES_LOADING_SCREEN_JPG = "./images/app/loading-screen.jpg";
	public static final String SUCCESS_ICON_PATH = "./images/buttons/check.png";
	public static final String CANCEL_ICON_PATH = "./images/buttons/cancel.png";
	public static final String CLOSE_ICON_PATH = "./images/buttons/close.png";
	public static final String MESSAGE_ICON_PATH = "./images/buttons/message.png";
	
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
	
	// About menu images paths
	public static final String ABOUT_ICON_PATH = "./images/menu/about/about.png";
	
	// Help menu images paths
	public static final String HELP_ICON_PATH = "./images/menu/help/help.png";
	public static final String TERMS_ICON_PATH = "./images/menu/help/terms.png";
	public static final String SHORTCUTS_ICON_PATH = "./images/menu/help/tips_tricks.png";
	
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
	public static final String OTHER_ICON_PATH = "./images/ambient_buttons/other.png";
	
	// Menu text labels
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
	
	public static final String HELP = "Help";
	public static final String HELP_ITEM_FAQ = "FAQs";
	public static final String HELP_ITEM_TERMS = "T&C's";
	public static final String HELP_ITEM_SHORTCUTS = "Tips and Tricks...";
	
	public static final String ABOUT = "About";
	public static final String ABOUT_ITEM_CCA = "About CCA";
	public static final String ABOUT_ITEM_CCA_EDITOR = "About CCA Editor";
	
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
	
	private Constants() {
		// private constructor to hide the implicit public one
	}
}
