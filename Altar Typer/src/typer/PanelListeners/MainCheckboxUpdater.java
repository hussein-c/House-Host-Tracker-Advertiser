package typer.PanelListeners;

import typer.MainListeners.TyperThread;
import typer.Panels.CentralPanel;
import typer.Tools.Config;
import typer.Tools.Settings;

public class MainCheckboxUpdater {

	public static void updateMainSettings() { //Dynamically checks which check boxes are selected and updates/applies those changes accordingly in the configuration file and settings
		if (CentralPanel.pinToTop.isSelected()) {
			Settings.mainPageSettings.setPinToTop(1);
			Settings.frame.setAlwaysOnTop(true);
		}
		else {
			Settings.mainPageSettings.setPinToTop(0);
			Settings.frame.setAlwaysOnTop(false);
		}
		if (CentralPanel.onlyVerifiedHosts.isSelected()) {
			Settings.mainPageSettings.setOnlyVerifiedHosts(1);
		}
		else {
			Settings.mainPageSettings.setOnlyVerifiedHosts(0);
		}
		if (CentralPanel.onlyHostAdvertisements.isSelected()) {
			Settings.mainPageSettings.setAdvertiseTrackerHosts(1);
		}
		else {
			Settings.mainPageSettings.setAdvertiseTrackerHosts(0);
		}
		if (CentralPanel.onlyCommunityAdvertisements.isSelected()) {
			Settings.mainPageSettings.setAdvertiseCommunityLines(1);
		}
		else {
			Settings.mainPageSettings.setAdvertiseCommunityLines(0);
		}
		if (CentralPanel.onlyBoneJobAdvertisements.isSelected()) {
			Settings.mainPageSettings.setAdvertiseBoneJobLines(1);
		}
		else {
			Settings.mainPageSettings.setAdvertiseBoneJobLines(0);
		}
		Config.updateConfigFile();
		Settings.initialiseSettings();
		if (TyperThread.running == false) { //Enables or disables the Start button dynamically depending on which advertisements check boxes are selected
			if (CentralPanel.onlyHostAdvertisements.isSelected() || CentralPanel.onlyCommunityAdvertisements.isSelected() || CentralPanel.onlyBoneJobAdvertisements.isSelected()) {
				CentralPanel.startTyping.setEnabled(true);
			}
			else {
				CentralPanel.startTyping.setEnabled(false);
			}
		}
	}

	public static void updateMainCheckBoxes() { //After the user starts the application, it checks the values to then enable the Start button and/or select check boxes where appropriate
		if (Settings.mainPageSettings.getPinToTop() == 1) {
			CentralPanel.pinToTop.setSelected(true);
		}
		else {
			CentralPanel.pinToTop.setSelected(false);
		}
		if (Settings.mainPageSettings.getOnlyVerifiedHosts() == 1) {
			CentralPanel.onlyVerifiedHosts.setSelected(true);
		}
		else {
			CentralPanel.onlyVerifiedHosts.setSelected(false);
		}
		if (Settings.mainPageSettings.getAdvertiseTrackerHosts() == 1) {
			CentralPanel.onlyHostAdvertisements.setSelected(true);
		}
		else {
			CentralPanel.onlyHostAdvertisements.setSelected(false);
		}
		if (Settings.mainPageSettings.getAdvertiseCommunityLines() == 1) {
			CentralPanel.onlyCommunityAdvertisements.setSelected(true);
		}
		else {
			CentralPanel.onlyCommunityAdvertisements.setSelected(false);
		}
		if (Settings.mainPageSettings.getAdvertiseBoneJobLines() == 1) {
			CentralPanel.onlyBoneJobAdvertisements.setSelected(true);
		}
		else {
			CentralPanel.onlyBoneJobAdvertisements.setSelected(false);
		}
		if (Settings.mainPageSettings.getAdvertiseTrackerHosts() == 1 || Settings.mainPageSettings.getAdvertiseCommunityLines() == 1 || Settings.mainPageSettings.getAdvertiseBoneJobLines() == 1) {
			CentralPanel.startTyping.setEnabled(true);
		}
		else {
			CentralPanel.startTyping.setEnabled(false);
		}
	}
}