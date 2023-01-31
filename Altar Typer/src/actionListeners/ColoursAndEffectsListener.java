package actionListeners;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import panels.CentralPanel;
import source.Config;
import source.StartupSettings;

public class ColoursAndEffectsListener {

	public static void coloursAndEffectsPanel() {
		updateColoursAndEffectsCheckBoxes();
		JPanel coloursAndEffectsPanel = new JPanel();
		//Adds all the colouts and effects check boxes while taking into account their values in the configuration file
		coloursAndEffectsPanel.add(CentralPanel.yellow);
		coloursAndEffectsPanel.add(CentralPanel.purple);
		coloursAndEffectsPanel.add(CentralPanel.red);
		coloursAndEffectsPanel.add(CentralPanel.green);
		coloursAndEffectsPanel.add(CentralPanel.white);
		coloursAndEffectsPanel.add(CentralPanel.cyan);
		coloursAndEffectsPanel.add(CentralPanel.flash1);
		coloursAndEffectsPanel.add(CentralPanel.flash2);
		coloursAndEffectsPanel.add(CentralPanel.flash3);
		coloursAndEffectsPanel.add(CentralPanel.glow1);
		coloursAndEffectsPanel.add(CentralPanel.glow2);
		coloursAndEffectsPanel.add(CentralPanel.glow3);
		coloursAndEffectsPanel.add(CentralPanel.wave);
		coloursAndEffectsPanel.add(CentralPanel.wave2);
		coloursAndEffectsPanel.add(CentralPanel.scroll);
		coloursAndEffectsPanel.add(CentralPanel.slide);
		coloursAndEffectsPanel.add(CentralPanel.shake);
		coloursAndEffectsPanel.setPreferredSize(new Dimension(195, 390));
		coloursAndEffectsPanel.setLayout(null);
		//User confirmation dialog
		Object[] options = {"Apply Changes", "Cancel"};
		int confirmation = JOptionPane.showOptionDialog(StartupSettings.frame, coloursAndEffectsPanel,
				"Colours and Effects", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
		//If the user applies any changes, the configuration file and settings would automatically update
		if (confirmation == JOptionPane.OK_OPTION) {
			updateColoursAndEffectsSettings();
			Config.updateConfigFile();
			StartupSettings.initialiseSettings();
			JOptionPane.showMessageDialog(StartupSettings.frame, "Any changes you made have now been applied.", "Colours & Effects", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			return;
		}
	}

	public static void updateColoursAndEffectsCheckBoxes() { //Selects or unselects check boxes depending on what values they have in the configuration file
		if (StartupSettings.colourAndEffectSettings.getYellow() == 1) {
			CentralPanel.yellow.setSelected(true);
		}
		else {
			CentralPanel.yellow.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getPurple() == 1) {
			CentralPanel.purple.setSelected(true);
		}
		else {
			CentralPanel.purple.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getRed() == 1) {
			CentralPanel.red.setSelected(true);
		}
		else {
			CentralPanel.red.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getGreen() == 1) {
			CentralPanel.green.setSelected(true);
		}
		else {
			CentralPanel.green.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getWhite() == 1) {
			CentralPanel.white.setSelected(true);
		}
		else {
			CentralPanel.white.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getCyan() == 1) {
			CentralPanel.cyan.setSelected(true);
		}
		else {
			CentralPanel.cyan.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getFlash1() == 1) {
			CentralPanel.flash1.setSelected(true);
		}
		else {
			CentralPanel.flash1.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getFlash2() == 1) {
			CentralPanel.flash2.setSelected(true);
		}
		else {
			CentralPanel.flash2.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getFlash3() == 1) {
			CentralPanel.flash3.setSelected(true);
		}
		else {
			CentralPanel.flash3.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getGlow1() == 1) {
			CentralPanel.glow1.setSelected(true);
		}
		else {
			CentralPanel.glow1.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getGlow2() == 1) {
			CentralPanel.glow2.setSelected(true);
		}
		else {
			CentralPanel.glow2.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getGlow3() == 1) {
			CentralPanel.glow3.setSelected(true);
		}
		else {
			CentralPanel.glow3.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getWave() == 1) {
			CentralPanel.wave.setSelected(true);
		}
		else {
			CentralPanel.wave.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getWave2() == 1) {
			CentralPanel.wave2.setSelected(true);
		}
		else {
			CentralPanel.wave2.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getScroll() == 1) {
			CentralPanel.scroll.setSelected(true);
		}
		else {
			CentralPanel.scroll.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getSlide() == 1) {
			CentralPanel.slide.setSelected(true);
		}
		else {
			CentralPanel.slide.setSelected(false);
		}
		if (StartupSettings.colourAndEffectSettings.getShake() == 1) {
			CentralPanel.shake.setSelected(true);
		}
		else {
			CentralPanel.shake.setSelected(false);
		}
	}

	public static void updateColoursAndEffectsSettings() { /*After the user applies any changes, it would either set 0 or 1 to each colour and effect, depending on their selection*/
		if (CentralPanel.yellow.isSelected()) {
			StartupSettings.colourAndEffectSettings.setYellow(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setYellow(0);
		}
		if (CentralPanel.purple.isSelected()) {
			StartupSettings.colourAndEffectSettings.setPurple(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setPurple(0);
		}
		if (CentralPanel.red.isSelected()) {
			StartupSettings.colourAndEffectSettings.setRed(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setRed(0);
		}
		if (CentralPanel.green.isSelected()) {
			StartupSettings.colourAndEffectSettings.setGreen(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setGreen(0);
		}
		if (CentralPanel.white.isSelected()) {
			StartupSettings.colourAndEffectSettings.setWhite(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setWhite(0);
		}
		if (CentralPanel.cyan.isSelected()) {
			StartupSettings.colourAndEffectSettings.setCyan(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setCyan(0);
		}
		if (CentralPanel.flash1.isSelected()) {
			StartupSettings.colourAndEffectSettings.setFlash1(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setFlash1(0);
		}
		if (CentralPanel.flash2.isSelected()) {
			StartupSettings.colourAndEffectSettings.setFlash2(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setFlash2(0);
		}
		if (CentralPanel.flash3.isSelected()) {
			StartupSettings.colourAndEffectSettings.setFlash3(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setFlash3(0);
		}
		if (CentralPanel.glow1.isSelected()) {
			StartupSettings.colourAndEffectSettings.setGlow1(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setGlow1(0);
		}
		if (CentralPanel.glow2.isSelected()) {
			StartupSettings.colourAndEffectSettings.setGlow2(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setGlow2(0);
		}
		if (CentralPanel.glow3.isSelected()) {
			StartupSettings.colourAndEffectSettings.setGlow3(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setGlow3(0);
		}
		if (CentralPanel.wave.isSelected()) {
			StartupSettings.colourAndEffectSettings.setWave(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setWave(0);
		}
		if (CentralPanel.wave2.isSelected()) {
			StartupSettings.colourAndEffectSettings.setWave2(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setWave2(0);
		}
		if (CentralPanel.scroll.isSelected()) {
			StartupSettings.colourAndEffectSettings.setScroll(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setScroll(0);
		}
		if (CentralPanel.slide.isSelected()) {
			StartupSettings.colourAndEffectSettings.setSlide(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setSlide(0);
		}
		if (CentralPanel.shake.isSelected()) {
			StartupSettings.colourAndEffectSettings.setShake(1);
		}
		else {
			StartupSettings.colourAndEffectSettings.setShake(0);
		}
	}
}