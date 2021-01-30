package com.university.cca.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.university.cca.constants.Constants;

public class BusStationButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int numClicks = 0;
	
    public BusStationButton() {
    	ImageIcon icon = new ImageIcon(Constants.BUS_STATION_ICON_PATH);

        this.setText("Bus Station");
        this.setIcon(icon);
        this.setPreferredSize(new Dimension(190, 80));
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Bus Station Button Clicked " + (numClicks++) + " times");
	}
}
