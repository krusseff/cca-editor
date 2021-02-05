package com.university.cca.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.university.caa.entities.Ambient;
import com.university.cca.buttons.CancelDialogButton;
import com.university.cca.util.CreateAmbientUtil;

public class CreateAmbientDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final int GRID_ROWS = 0;
	private static final int GRID_COLS = 2;
	private static final int TEXT_FIELD_SIZE = 16;

	private JTextField ambientNameTextField;
	private JTextField ambientLocationTextField;
	private JCheckBox staticCheckBox;
	private JComboBox<String> parentAmbientsComboBox;
	
	public CreateAmbientDialog(JFrame parent, String title, String message) {
        super(parent, title, true);
        
        addDialogContent(message);
        
        this.pack();
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
	}
	
	private void addDialogContent(String mainDialogMessage) {
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(getGridLayout());

        // first row of the dialog
        dialogPanel.add(new JLabel(mainDialogMessage));
        dialogPanel.add(new JLabel());
        
        // second row of the dialog
        ambientNameTextField = createTextField();
        dialogPanel.add(createLabel("Ambient Name:"));
        dialogPanel.add(ambientNameTextField);

        // third row of the dialog
        ambientLocationTextField = createTextField();
        dialogPanel.add(createLabel("Ambient Location:"));
        dialogPanel.add(ambientLocationTextField);

        // fourth row of the dialog
        staticCheckBox = new JCheckBox("", true);
        dialogPanel.add(createLabel("Static Ambient:"));
        dialogPanel.add(staticCheckBox);
        
        // fourth row of the dialog
        parentAmbientsComboBox = createComboBox();
        parentAmbientsComboBox.setSelectedIndex(-1); // set default empty value
        dialogPanel.add(createLabel("Parent Ambient Name:"));
        dialogPanel.add(parentAmbientsComboBox);
        
        // fifth row of the dialog with the buttons      
        JButton createAmbientButton = CreateAmbientUtil.createAmbientButton();
        dialogPanel.add(createAmbientButton);
        dialogPanel.add(new CancelDialogButton(this));
        
        // TODO: CreateAmbientDialog cr = this;
        createAmbientButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create Ambient Button Clicked");
				
				String name = ambientNameTextField.getText();
				String location = ambientLocationTextField.getText();
				boolean isStatic = staticCheckBox.isSelected();
				Object parentAmbient = parentAmbientsComboBox.getSelectedItem();
				
				if (CreateAmbientUtil.isValidAmbient(name, location, parentAmbient)) {
					Ambient ambient = CreateAmbientUtil.constructAmbient(name, location, isStatic, parentAmbient);
					System.out.println("New ambient: " + ambient);
					
					// TODO: Implement writing to files here
					// 1. File with ambient names only
					// 2. CCA file
				} else {
					// TODO Dialog with an error
					CreateAmbientUtil.showErrorDialog();
				}
	
				// TODO: cr.dispose();
			}
		});
        
        this.getContentPane().add(dialogPanel);
	}
	
	private JLabel createLabel(String labelName) {
		return new JLabel(labelName, JLabel.CENTER);
	}
	
	private JTextField createTextField() {
		return new JTextField(TEXT_FIELD_SIZE);
	}
	
	private JComboBox<String> createComboBox() {
		
		// TODO: Value should be read from file with all existing ambients
		// CSV format or each parent name in new line
		String[] parentAmbients = {"Hotel 1", "Hospital 1", "Bus Station 1", "Port 1"};  
		
		return new JComboBox<>(parentAmbients);
	}
	
	private GridLayout getGridLayout() {
		GridLayout layout = new GridLayout(GRID_ROWS, GRID_COLS);
        layout.setHgap(10);
        layout.setVgap(10);
        
        return layout;
	}
}
