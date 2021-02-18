package com.university.cca.menu;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;

public class FileMenu extends JMenu {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(FileMenu.class);

    private JFrame parentFrame;
    
    public FileMenu(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
    	this.setText(Constants.FILE);
		this.setIcon(new ImageIcon(Constants.FILE_ICON_PATH));

		// TODO
		this.setEnabled(false);
		
    	logger.info("File option of the menu is initialized");
    }
    
    // Elements:
    // 1. New
    // 2. Open
    // 3. Save
    // 4. Separator: fileMenu.addSeparator();
    // 5. Import...
    // 6. Export...
    // 7. Separator: fileMenu.addSeparator();
    // 8. Exit
    
    // var eMenuItem = new JMenuItem("Exit", exitIcon);
    // eMenuItem.setMnemonic(KeyEvent.VK_E);
    // eMenuItem.setToolTipText("Exit application");
    // eMenuItem.addActionListener((event) -> System.exit(0));
    
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}
