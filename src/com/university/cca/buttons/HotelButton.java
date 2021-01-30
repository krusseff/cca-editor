package com.university.cca.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.university.cca.constants.Constants;

public class HotelButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int numClicks = 0;
	
    public HotelButton() {
    	ImageIcon icon = new ImageIcon(Constants.HOTEL_ICON_PATH);

        this.setText("Hotel");
        this.setIcon(icon);
        this.setPreferredSize(new Dimension(190, 80));
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Hotel Button Clicked " + (numClicks++) + " times");
	}
}
