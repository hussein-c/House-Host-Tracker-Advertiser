package typer.PanelListeners;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

import typer.Tools.Config;
import typer.Tools.Settings;

public class AdvertisementsListener {

	public static JTextField noHostAdvert = new JTextField(Settings.noHostMessage);
	public static JTextArea communityAdverts = new JTextArea(Settings.communityMessages);
	public static JTextArea boneJobAdverts = new JTextArea(Settings.boneJobMessages);

	public static void advertisementsPanel() {
		//Initialises the No Host Advertisement check box, edit check box, line number and character count
		JCheckBox editNoHostAdvert = new JCheckBox("Edit No Host Line");
		editNoHostAdvert.setToolTipText("Tick this checkbox to edit the no host advertisement table below.");
		editNoHostAdvert.setBounds(0, 30, 150, 15);
		noHostAdvert = new JTextField(Settings.noHostMessage);
		noHostAdvert.setBounds(1, 60, 570, 25);
		noHostAdvert.setEditable(false);
		JLabel noHostAdvertLineNumber = new JLabel();
		noHostAdvertLineNumber.setBounds(1, 85, 70, 15);
		JLabel noHostAdvertCharacters = new JLabel();
		noHostAdvertCharacters.setBounds(0, 100, 90, 15);
		JLabel noHostAdvertCharacterCount = new JLabel();
		noHostAdvertCharacterCount.setBounds(69, 100, 40, 15);
		editNoHostAdvert.addActionListener(e -> {
			//If the user has selected the Edit No Host Advertisements check box
			if (editNoHostAdvert.isSelected()) {
				noHostAdvert.setEditable(true);
			}
			else {
				noHostAdvert.setEditable(false);
			}
			noHostAdvert.addFocusListener(new FocusListener(){
				@Override
				public void focusGained(FocusEvent e){
					noHostAdvert.addCaretListener(new CaretListener() { //Finds the caret position to output the correct data to Line and Characters which change dynamically
						@Override
						public void caretUpdate(CaretEvent e) {
							if (editNoHostAdvert.isSelected()) { //If the user has selected the Edit No Host Advertisements check box
								int noHostAdvertCaretPosition = noHostAdvert.getCaretPosition();
								int noHostAdvertRowNumber = (noHostAdvertCaretPosition == 0) ? 1 : 0;
								int noHostAdvertOffset;
								for (noHostAdvertOffset = noHostAdvertCaretPosition; noHostAdvertOffset > 0;) {
									try {
										noHostAdvertOffset = Utilities.getRowStart(noHostAdvert, noHostAdvertOffset) - 1;
									} catch (BadLocationException e1) {
										//e1.printStackTrace();
									}
									noHostAdvertRowNumber++;
								}
								//Finds the correct index for each individual line
								int noHostAdvertLineCount = noHostAdvert.getText().split("\n").length;
								String[] noHostAdvertList = new String[noHostAdvertLineCount];
								int noHostAdvertCorrectIndex = 0;
								for (int noHostAdvertIndex = 0; noHostAdvertIndex < noHostAdvertLineCount; noHostAdvertIndex++) {
									noHostAdvertList = noHostAdvert.getText().split("\n");
									if (noHostAdvertIndex == noHostAdvertRowNumber - 1) {
										noHostAdvertCorrectIndex = noHostAdvertIndex;
									}
								}
								//If a line has over 80 characters, Characters would turn red in colour
								if (noHostAdvertList[noHostAdvertCorrectIndex].length() >= 81) {
									noHostAdvertLineNumber.setText("Line: " + noHostAdvertRowNumber);
									noHostAdvertCharacters.setText("Characters: ");
									noHostAdvertCharacterCount.setText(Integer.toString(noHostAdvertList[noHostAdvertCorrectIndex].length()));
									noHostAdvertCharacterCount.setForeground(Color.RED);
								}
								//If a line has under 80 characters, Characters would turn black in colour
								else {
									noHostAdvertLineNumber.setText("Line: " + noHostAdvertRowNumber);
									noHostAdvertCharacters.setText("Characters: ");
									noHostAdvertCharacterCount.setText(Integer.toString(noHostAdvertList[noHostAdvertCorrectIndex].length()));
									noHostAdvertCharacterCount.setForeground(Color.BLACK);
								}
							}
						}
					});
				}
				@Override
				public void focusLost(FocusEvent e) { //Line and Characters do not display if there is no caret in the No Host Advertisements text box
					noHostAdvertLineNumber.setText("");
					noHostAdvertCharacters.setText("");
					noHostAdvertCharacterCount.setText("");
				}
			});
		});
		//Initialises the Community Advertisements check box, edit check box, line number and character count
		JCheckBox editCommunityAdverts = new JCheckBox("Edit Community Lines");
		editCommunityAdverts.setToolTipText("Tick this checkbox to edit the community advertisements' table below.");
		editCommunityAdverts.setBounds(0, 135, 150, 15);
		communityAdverts = new JTextArea(Settings.communityMessages);
		communityAdverts.setEditable(false);
		JScrollPane communityAdvertsScroller = new JScrollPane(communityAdverts);
		communityAdvertsScroller.setBounds(0, 165, 570, 150);
		JLabel communityAdvertsLineNumber = new JLabel();
		communityAdvertsLineNumber.setBounds(1, 315, 70, 15);
		JLabel communityAdvertsCharacters = new JLabel();
		communityAdvertsCharacters.setBounds(0, 330, 90, 15);
		JLabel communityAdvertsCharacterCount = new JLabel();
		communityAdvertsCharacterCount.setBounds(69, 330, 40, 15);
		editCommunityAdverts.addActionListener(e -> {
			//If the user has selected the Edit Community Advertisements check box
			if (editCommunityAdverts.isSelected()) {
				communityAdverts.setEditable(true);
			}
			else {
				communityAdverts.setEditable(false);
			}
			communityAdverts.addFocusListener(new FocusListener(){
				@Override
				public void focusGained(FocusEvent e){
					communityAdverts.addCaretListener(new CaretListener() { //Finds the caret position to output the correct data to Line and Characters which change dynamically
						@Override
						public void caretUpdate(CaretEvent e) {
							if (editCommunityAdverts.isSelected()) { //If the user has selected the Edit Community Advertisements check box
								int communityAdvertsCaretPosition = communityAdverts.getCaretPosition();
								int communityAdvertsRowNumber = (communityAdvertsCaretPosition == 0) ? 1 : 0;
								int communityAdvertsOffset;
								for (communityAdvertsOffset = communityAdvertsCaretPosition; communityAdvertsOffset > 0;) {
									try {
										communityAdvertsOffset = Utilities.getRowStart(communityAdverts, communityAdvertsOffset) - 1;
									} catch (BadLocationException e1) {
										//e1.printStackTrace();
									}
									communityAdvertsRowNumber++;
								}
								//Finds the correct index for each individual line
								int communityAdvertsLineCount = communityAdverts.getText().split("\n").length;
								String[] communityAdvertsList = new String[communityAdvertsLineCount];
								int communityAdvertsCorrectIndex = 0;
								for (int communityAdvertsIndex = 0; communityAdvertsIndex < communityAdvertsLineCount; communityAdvertsIndex++) {
									communityAdvertsList = communityAdverts.getText().split("\n");
									if (communityAdvertsIndex == communityAdvertsRowNumber - 1) {
										communityAdvertsCorrectIndex = communityAdvertsIndex;
									}
								}
								//If a line has over 80 characters, Characters would turn red in colour
								if (communityAdvertsList[communityAdvertsCorrectIndex].length() >= 81) {
									communityAdvertsLineNumber.setText("Line: " + communityAdvertsRowNumber);
									communityAdvertsCharacters.setText("Characters: ");
									communityAdvertsCharacterCount.setText(Integer.toString(communityAdvertsList[communityAdvertsCorrectIndex].length()));
									communityAdvertsCharacterCount.setForeground(Color.RED);
								}
								//If a line has under 80 characters, Characters would turn black in colour
								else {
									communityAdvertsLineNumber.setText("Line: " + communityAdvertsRowNumber);
									communityAdvertsCharacters.setText("Characters: ");
									communityAdvertsCharacterCount.setText(Integer.toString(communityAdvertsList[communityAdvertsCorrectIndex].length()));
									communityAdvertsCharacterCount.setForeground(Color.BLACK);
								}
							}
						}
					});
				}
				@Override
				public void focusLost(FocusEvent e) { //Line and Characters do not display if there is no caret in the Community Advertisements text box
					communityAdvertsLineNumber.setText("");
					communityAdvertsCharacters.setText("");
					communityAdvertsCharacterCount.setText("");
				}
			});
		});
		//Initialises the Bone Job Advertisements check box, edit check box, line number and character count
		JCheckBox editBoneJobAdverts = new JCheckBox("Edit Bone Job Lines");
		editBoneJobAdverts.setToolTipText("Tick this checkbox to edit the bone job advertisements' table below.");
		editBoneJobAdverts.setBounds(0, 365, 140, 15);
		boneJobAdverts = new JTextArea(Settings.boneJobMessages);
		boneJobAdverts.setEditable(false);
		JScrollPane boneJobAdvertsScroller = new JScrollPane(boneJobAdverts);
		boneJobAdvertsScroller.setBounds(0, 395, 570, 150);
		JLabel boneJobAdvertsLineNumber = new JLabel();
		boneJobAdvertsLineNumber.setBounds(1, 545, 70, 15);
		JLabel boneJobAdvertsCharacters = new JLabel();
		boneJobAdvertsCharacters.setBounds(0, 560, 90, 15);
		JLabel boneJobAdvertsCharacterCount = new JLabel();
		boneJobAdvertsCharacterCount.setBounds(69, 560, 40, 15);
		editBoneJobAdverts.addActionListener(e -> {
			//If the user has selected the Edit Bone Job Advertisements check box
			if (editBoneJobAdverts.isSelected()) {
				boneJobAdverts.setEditable(true);
			}
			else {
				boneJobAdverts.setEditable(false);
			}
			boneJobAdverts.addFocusListener(new FocusListener(){
				@Override
				public void focusGained(FocusEvent e){
					boneJobAdverts.addCaretListener(new CaretListener() { //Finds the caret position to output the correct data to Line and Characters which change dynamically
						@Override
						public void caretUpdate(CaretEvent e) {
							if (editBoneJobAdverts.isSelected()) { //If the user has selected the Edit Bone Job Advertisements check box
								int boneJobAdvertsCaretPosition = boneJobAdverts.getCaretPosition();
								int boneJobAdvertsRowNumber = (boneJobAdvertsCaretPosition == 0) ? 1 : 0;
								int boneJobAdvertsOffset;
								for (boneJobAdvertsOffset = boneJobAdvertsCaretPosition; boneJobAdvertsOffset > 0;) {
									try {
										boneJobAdvertsOffset = Utilities.getRowStart(boneJobAdverts, boneJobAdvertsOffset) - 1;
									} catch (BadLocationException e1) {
										//e1.printStackTrace();
									}
									boneJobAdvertsRowNumber++;
								}
								//Finds the correct index for each individual line
								int boneJobAdvertsLineCount = boneJobAdverts.getText().split("\n").length;
								String[] boneJobAdvertsList = new String[boneJobAdvertsLineCount];
								int boneJobAdvertsCorrectIndex = 0;
								for (int boneJobAdvertsIndex = 0; boneJobAdvertsIndex < boneJobAdvertsLineCount; boneJobAdvertsIndex++) {
									boneJobAdvertsList = boneJobAdverts.getText().split("\n");
									if (boneJobAdvertsIndex == boneJobAdvertsRowNumber - 1) {
										boneJobAdvertsCorrectIndex = boneJobAdvertsIndex;
									}
								}
								//If a line has over 80 characters, Characters would turn red in colour
								if (boneJobAdvertsList[boneJobAdvertsCorrectIndex].length() >= 81) {
									boneJobAdvertsLineNumber.setText("Line: " + boneJobAdvertsRowNumber);
									boneJobAdvertsCharacters.setText("Characters: ");
									boneJobAdvertsCharacterCount.setText(Integer.toString(boneJobAdvertsList[boneJobAdvertsCorrectIndex].length()));
									boneJobAdvertsCharacterCount.setForeground(Color.RED);
								}
								//If a line has under 80 characters, Characters would turn black in colour
								else {
									boneJobAdvertsLineNumber.setText("Line: " + boneJobAdvertsRowNumber);
									boneJobAdvertsCharacters.setText("Characters: ");
									boneJobAdvertsCharacterCount.setText(Integer.toString(boneJobAdvertsList[boneJobAdvertsCorrectIndex].length()));
									boneJobAdvertsCharacterCount.setForeground(Color.BLACK);
								}
							}
						}
					});
				}
				@Override
				public void focusLost(FocusEvent e) { //Line and Characters do not display if there is no caret in the Bone Job Advertisements text box
					boneJobAdvertsLineNumber.setText("");
					boneJobAdvertsCharacters.setText("");
					boneJobAdvertsCharacterCount.setText("");
				}
			});
		});
		JPanel advertisementsPanel = new JPanel();
		//Adds all the check boxes, text boxes and their respective line and character counts
		advertisementsPanel.add(editNoHostAdvert);
		advertisementsPanel.add(noHostAdvert);
		advertisementsPanel.add(noHostAdvertLineNumber);
		advertisementsPanel.add(noHostAdvertCharacters);
		advertisementsPanel.add(noHostAdvertCharacterCount);
		advertisementsPanel.add(editCommunityAdverts);
		advertisementsPanel.add(communityAdvertsScroller);
		advertisementsPanel.add(communityAdvertsLineNumber);
		advertisementsPanel.add(communityAdvertsCharacters);
		advertisementsPanel.add(communityAdvertsCharacterCount);
		advertisementsPanel.add(editBoneJobAdverts);
		advertisementsPanel.add(boneJobAdvertsScroller);
		advertisementsPanel.add(boneJobAdvertsLineNumber);
		advertisementsPanel.add(boneJobAdvertsCharacters);
		advertisementsPanel.add(boneJobAdvertsCharacterCount);
		advertisementsPanel.setPreferredSize(new Dimension(576, 584));
		advertisementsPanel.setLayout(null);
		//User confirmation dialog
		Object[] options = {"Apply Changes", "Cancel"};
		int confirmation = JOptionPane.showOptionDialog(Settings.frame, advertisementsPanel,
				"Advertisements", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
		//If the user applies any changes, the configuration file and settings would automatically update
		if (confirmation == JOptionPane.OK_OPTION) {
			Config.updateConfigFile();
			Settings.initialiseSettings();
			JOptionPane.showMessageDialog(Settings.frame, "Any changes you made have now been applied.", "Advertisements", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			return;
		}
	}
}