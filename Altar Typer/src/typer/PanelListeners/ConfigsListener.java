package typer.PanelListeners;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import typer.Tools.Config;
import typer.Tools.Settings;

public class ConfigsListener {

	public static void configsPanel() {
		//Initialises the Minimum Typing Speed label, text box positioning and values
		JLabel typeSpeedDelayMin = new JLabel("Minimum Typing Speed (1 millisecond or more):");
		typeSpeedDelayMin.setBounds(0, 30, 275, 20);
		JTextField typeSpeedDelayMinField = new JTextField("" + Settings.adjustTyperSettings.getTypeSpeedDelayMin());
		typeSpeedDelayMinField.setBounds(280, 30, 45, 20);
		JLabel typeSpeedDelayMinValidation = new JLabel("");
		typeSpeedDelayMinValidation.setBounds(329, 30, 175, 20);
		typeSpeedDelayMinField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent key) {
				//Checks what keys the user enters before making the text box editable
				if ((key.getKeyCode() == KeyEvent.VK_BACK_SPACE || (key.getKeyCode()>=48 && key.getKeyCode()<=57))) {
					typeSpeedDelayMinField.setEditable(true);
					typeSpeedDelayMinValidation.setText("");
				}
				else {
					typeSpeedDelayMinField.setEditable(false);
					typeSpeedDelayMinValidation.setForeground(Color.RED);
					typeSpeedDelayMinValidation.setText("Enter only numeric digits (0-9).");
				}
			}
		});
		//Initialises the Maximum Typing Speed label, text box positioning and values
		JLabel typeSpeedDelayMax = new JLabel("Maximum Typing Speed (1 millisecond or more):");
		typeSpeedDelayMax.setBounds(0, 60, 275, 20);
		JTextField typeSpeedDelayMaxField = new JTextField("" + Settings.adjustTyperSettings.getTypeSpeedDelayMax());
		typeSpeedDelayMaxField.setBounds(280, 60, 45, 20);
		JLabel typeSpeedDelayMaxValidation = new JLabel("");
		typeSpeedDelayMaxValidation.setBounds(329, 60, 175, 20);
		typeSpeedDelayMaxField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent key) {
				//Checks what keys the user enters before making the text box editable
				if ((key.getKeyCode() == KeyEvent.VK_BACK_SPACE || (key.getKeyCode()>=48 && key.getKeyCode()<=57))) {
					typeSpeedDelayMaxField.setEditable(true);
					typeSpeedDelayMaxValidation.setText("");
				}
				else {
					typeSpeedDelayMaxField.setEditable(false);
					typeSpeedDelayMaxValidation.setForeground(Color.RED);
					typeSpeedDelayMaxValidation.setText("Enter only numeric digits (0-9).");
				}
			}
		});
		//Initialises the Minimum Lines Delay label, text box positioning and values
		JLabel delayTimeMin = new JLabel("Minimum Lines Delay (1 second or more):");
		delayTimeMin.setBounds(20, 90, 255, 20);
		JTextField delayTimeMinField = new JTextField("" + Settings.adjustTyperSettings.getDelayTimeMin() / 1000);
		delayTimeMinField.setBounds(280, 90, 45, 20);
		JLabel delayTimeMinValidation = new JLabel("");
		delayTimeMinValidation.setBounds(329, 90, 175, 20);
		delayTimeMinField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent key) {
				//Checks what keys the user enters before making the text box editable
				if ((key.getKeyCode() == KeyEvent.VK_BACK_SPACE || (key.getKeyCode()>=48 && key.getKeyCode()<=57))) {
					delayTimeMinField.setEditable(true);
					delayTimeMinValidation.setText("");
				}
				else {
					delayTimeMinField.setEditable(false);
					delayTimeMinValidation.setForeground(Color.RED);
					delayTimeMinValidation.setText("Enter only numeric digits (0-9).");
				}
			}
		});
		//Initialises the Maximum Lines Delay label, text box positioning and values
		JLabel delayTimeMax = new JLabel("Maximum Lines Delay (1 second or more):");
		delayTimeMax.setBounds(20, 120, 255, 20);
		JTextField delayTimeMaxField = new JTextField("" + Settings.adjustTyperSettings.getDelayTimeMax() / 1000);
		delayTimeMaxField.setBounds(280, 120, 45, 20);
		JLabel delayTimeMaxValidation = new JLabel("");
		delayTimeMaxValidation.setBounds(329, 120, 175, 20);
		delayTimeMaxField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent key) {
				//Checks what keys the user enters before making the text box editable
				if ((key.getKeyCode() == KeyEvent.VK_BACK_SPACE || (key.getKeyCode()>=48 && key.getKeyCode()<=57))) {
					delayTimeMaxField.setEditable(true);
					delayTimeMaxValidation.setText("");
				}
				else {
					delayTimeMaxField.setEditable(false);
					delayTimeMaxValidation.setForeground(Color.RED);
					delayTimeMaxValidation.setText("Enter only numeric digits (0-9).");
				}
			}
		});
		//Initialises the Stop Time label, text box positioning and values
		JLabel stopTime = new JLabel("Stop Time (1 hour or more):");
		stopTime.setBounds(50, 150, 225, 20);
		JTextField stopTimeField = new JTextField("" + Settings.adjustTyperSettings.getStopTime() / 3600);
		stopTimeField.setBounds(280, 150, 45, 20);
		JLabel stopTimeValidation = new JLabel("");
		stopTimeValidation.setBounds(329, 150, 175, 20);
		stopTimeField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent key) {
				//Checks what keys the user enters before making the text box editable
				if ((key.getKeyCode() == KeyEvent.VK_BACK_SPACE || (key.getKeyCode()>=48 && key.getKeyCode()<=57))) {
					stopTimeField.setEditable(true);
					stopTimeValidation.setText("");
				}
				else {
					stopTimeField.setEditable(false);
					stopTimeValidation.setForeground(Color.RED);
					stopTimeValidation.setText("Enter only numeric digits (0-9).");
				}
			}
		});
		//Initialises the Host Count label, text box positioning and values
		JLabel hostCount = new JLabel("Host Count (1 or more):");
		hostCount.setBounds(50, 180, 225, 20);
		JTextField hostCountField = new JTextField("" + Settings.adjustTyperSettings.getHostCount());
		hostCountField.setBounds(280, 180, 45, 20);
		JLabel hostCountValidation = new JLabel("");
		hostCountValidation.setBounds(329, 180, 175, 20);
		hostCountField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent key) {
				//Checks what keys the user enters before making the text box editable
				if ((key.getKeyCode() == KeyEvent.VK_BACK_SPACE || (key.getKeyCode()>=48 && key.getKeyCode()<=57))) {
					hostCountField.setEditable(true);
					hostCountValidation.setText("");
				}
				else {
					hostCountField.setEditable(false);
					hostCountValidation.setForeground(Color.RED);
					hostCountValidation.setText("Enter only numeric digits (0-9).");
				}
			}
		});
		JPanel configsPanel = new JPanel();
		//Adds the Minimum Typing Speed text box to the panel
		configsPanel.add(typeSpeedDelayMin);
		configsPanel.add(typeSpeedDelayMinField);
		configsPanel.add(typeSpeedDelayMinValidation);
		//Adds the Maximum Typing Speed text box to the panel
		configsPanel.add(typeSpeedDelayMax);
		configsPanel.add(typeSpeedDelayMaxField);
		configsPanel.add(typeSpeedDelayMaxValidation);
		//Adds the Minimum Lines Delay text box to the panel
		configsPanel.add(delayTimeMin);
		configsPanel.add(delayTimeMinField);
		configsPanel.add(delayTimeMinValidation);
		//Adds the Maximum Lines Delay text box to the panel
		configsPanel.add(delayTimeMax);
		configsPanel.add(delayTimeMaxField);
		configsPanel.add(delayTimeMaxValidation);
		//Adds the Stop Time text box to the panel
		configsPanel.add(stopTime);
		configsPanel.add(stopTimeField);
		configsPanel.add(stopTimeValidation);
		//Adds the Host Count text box to the panel
		configsPanel.add(hostCount);
		configsPanel.add(hostCountField);
		configsPanel.add(hostCountValidation);
		//Adds the positioning and layout of each text box on the panel
		typeSpeedDelayMin.setHorizontalAlignment(SwingConstants.RIGHT);
		typeSpeedDelayMax.setHorizontalAlignment(SwingConstants.RIGHT);
		delayTimeMin.setHorizontalAlignment(SwingConstants.RIGHT);
		delayTimeMax.setHorizontalAlignment(SwingConstants.RIGHT);
		stopTime.setHorizontalAlignment(SwingConstants.RIGHT);
		hostCount.setHorizontalAlignment(SwingConstants.RIGHT);
		configsPanel.setPreferredSize(new Dimension(500, 210));
		configsPanel.setLayout(null);
		//User confirmation dialog
		Object[] options = {"Apply Changes", "Cancel"};
		int confirmation = JOptionPane.showOptionDialog(Settings.frame, configsPanel,
				"Configs", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
		//If the user applies any changes, the configuration file and settings would automatically update if the values satisfy the conditions
		if (confirmation == JOptionPane.OK_OPTION) {
			boolean errorFlag = false;
			int count = 0;
			String errorMessage = "The following error(s) have occurred:\n";
			// Typing Speed Delay
			if ((Integer.parseInt(typeSpeedDelayMinField.getText()) > 0 && Integer.parseInt(typeSpeedDelayMaxField.getText()) > 0) && (Integer.parseInt(typeSpeedDelayMinField.getText()) <= Integer.parseInt(typeSpeedDelayMaxField.getText()))) {
				Settings.adjustTyperSettings.setTypeSpeedDelayMin(Integer.parseInt(typeSpeedDelayMinField.getText()));
				Settings.adjustTyperSettings.setTypeSpeedDelayMax(Integer.parseInt(typeSpeedDelayMaxField.getText()));
			}
			else if (Integer.parseInt(typeSpeedDelayMinField.getText()) <= 0 && Integer.parseInt(typeSpeedDelayMaxField.getText()) <= 0) {
				errorFlag = true;
				count++;
				errorMessage += count + ". Minimum and Maximum Typing Speed must be greater than 0.\n";
			}
			else if (Integer.parseInt(typeSpeedDelayMinField.getText()) <= 0) {
				errorFlag = true;
				count++;
				errorMessage += count + ". Minimum Typing Speed must be greater than 0.\n";
			}
			else if (Integer.parseInt(typeSpeedDelayMaxField.getText()) <= 0) {
				errorFlag = true;
				count++;
				errorMessage += count + ". Maximum Typing Speed must be greater than 0.\n";
			}
			else if (Integer.parseInt(typeSpeedDelayMinField.getText()) >= Integer.parseInt(typeSpeedDelayMaxField.getText())) {
				errorFlag = true;
				count++;
				errorMessage += count + ". Minimum Typing Speed must be less than or equal to Maximum Typing Speed.\n";
			}
			// Lines Delay
			if ((Integer.parseInt(delayTimeMinField.getText()) > 0 && Integer.parseInt(delayTimeMaxField.getText()) > 0) && (Integer.parseInt(delayTimeMinField.getText()) <= Integer.parseInt(delayTimeMaxField.getText()))) {
				Settings.adjustTyperSettings.setDelayTimeMin(Integer.parseInt(delayTimeMinField.getText()) * 1000);
				Settings.adjustTyperSettings.setDelayTimeMax(Integer.parseInt(delayTimeMaxField.getText()) * 1000);
			}
			else if (Integer.parseInt(delayTimeMinField.getText()) <= 0 && Integer.parseInt(delayTimeMaxField.getText()) <= 0) {
				errorFlag = true;
				count++;
				errorMessage += count + ". Minimum and Maximum Lines Delay must be greater than 0.\n";
			}
			else if (Integer.parseInt(delayTimeMinField.getText()) <= 0) {
				errorFlag = true;
				count++;
				errorMessage += count + ". Minimum Lines Delay must be greater than 0.\n";
			}
			else if (Integer.parseInt(delayTimeMaxField.getText()) <= 0) {
				errorFlag = true;
				count++;
				errorMessage += count + ". Maximum Lines Delay must be greater than 0.\n";
			}
			else if (Integer.parseInt(delayTimeMinField.getText()) >= Integer.parseInt(delayTimeMaxField.getText())) {
				errorFlag = true;
				count++;
				errorMessage += count + ". Minimum Lines Delay must be less than or equal to Maximum Lines Delay.\n";
			}
			// Stop Time
			if (Integer.parseInt(stopTimeField.getText()) > 0) {
				Settings.adjustTyperSettings.setStopTime(Integer.parseInt(stopTimeField.getText()) * 3600);
			}
			else {
				errorFlag = true;
				count++;
				errorMessage += count + ". Stop Time must be greater than 0.\n";
			}
			// Host Count
			if (Integer.parseInt(hostCountField.getText()) > 0) {
				Settings.adjustTyperSettings.setHostCount(Integer.parseInt(hostCountField.getText()));
			}
			else {
				errorFlag = true;
				count++;
				errorMessage += count + ". Host Count must be greater than 0.\n";
			}
			if (errorFlag == true) {
				errorMessage += "\nAny other changes you made have now been applied.";
				JOptionPane.showMessageDialog(Settings.frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				Config.updateConfigFile();
				Settings.initialiseSettings();
				JOptionPane.showMessageDialog(Settings.frame, "Any changes you made have now been applied.", "Configs", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else {
			return;
		}
	}
}