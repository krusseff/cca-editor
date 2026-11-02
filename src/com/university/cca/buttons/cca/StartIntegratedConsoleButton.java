package com.university.cca.buttons.cca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.cca.execution.CcaConsoleOutputDialog;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.interpreter.CcaInterpreter;
import com.university.cca.interpreter.CcaInterpreter.ExecutionResult;
import com.university.cca.util.MouseCursorUtil;

/**
 * Button that starts the CCA scenario using the integrated ccaPL interpreter (Java classes).
 * Unlike {@link StartConsoleScenarioButton} which uses the external ccaPL.jar via ProcessBuilder,
 * this button invokes the interpreter directly within the same JVM, allowing future enhancements
 * and extensions to the interpreter.
 * 
 * Based on ccaPL Interpreter version 2.0 by François Siewe (De Montfort University).
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class StartIntegratedConsoleButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(StartIntegratedConsoleButton.class);

	private static final String BUTTON_NAME = "Start Console Scenario (ccaPL Interpreter Integrated)";
	private static final String BUTTON_TOOL_TIP = "Execute the generated CCA scenario using the integrated ccaPL interpreter "
			+ "(Java classes from version 2.0 by François Siewe, enhanced for CCA Editor)";

	private AppMainFrame parentFrame;

	public StartIntegratedConsoleButton(AppMainFrame parentFrame) {
		this.parentFrame = parentFrame;

		this.setText(BUTTON_NAME);
		this.setIcon(new ImageIcon(Constants.START_CONSOLE_CCA_ICON_PATH));

		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		this.setToolTipText(BUTTON_TOOL_TIP);

		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Start Integrated Console Scenario Button is clicked");

		this.setEnabled(false);

		SwingWorker<ExecutionResult, Void> worker = new SwingWorker<ExecutionResult, Void>() {
			@Override
			protected ExecutionResult doInBackground() {
				return CcaInterpreter.executeWithResult(Constants.AMBIENTS_CCA_FILE_PATH);
			}

			@Override
			protected void done() {
				try {
					ExecutionResult result = get();

					if (result.isSuccess() && result.hasOutput()) {
						new CcaConsoleOutputDialog(getParentFrame(), result.getOutput(), true);
					} else if (result.hasOutput()) {
						String combinedOutput = result.getOutput() + "\n--- ERROR ---\n" + result.getErrorMessage();
						new CcaConsoleOutputDialog(getParentFrame(), combinedOutput, false);
					} else {
						JOptionPane.showMessageDialog(
							getParentFrame(),
							result.getErrorMessage(),
							"CCA Execution Error",
							JOptionPane.ERROR_MESSAGE
						);
					}
				} catch (Exception ex) {
					logger.error("Error retrieving CCA integrated execution result", ex);
					JOptionPane.showMessageDialog(
						getParentFrame(),
						"An unexpected error occurred during CCA execution: " + ex.getMessage(),
						"CCA Execution Error",
						JOptionPane.ERROR_MESSAGE
					);
				} finally {
					setEnabled(true);
				}
			}
		};

		worker.execute();
	}

	// Getters and Setters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}
