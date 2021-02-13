package com.university.cca.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.CreateAmbientMsgDialog;

/**
 * The button that shows the dialog for creating a message between two ambients
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CreateMessageButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JFrame parentFrame;
	
    public CreateMessageButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
    	ImageIcon icon = new ImageIcon(Constants.MESSAGE_ICON_PATH);

        this.setText("Create Message");
        this.setIcon(icon);
        this.setPreferredSize(new Dimension(165, 35));
        
        this.addActionListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new CreateAmbientMsgDialog(parentFrame);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}
