package actionListeners;

import panels.CentralPanel;
import source.Config;
import source.StartupSettings;
import typer.TyperThread;

public class MainCheckboxUpdater {

	public static void updateMainSettings() { //Dynamically checks which check boxes are selected and updates/applies those changes accordingly in the configuration file and settings
		if (CentralPanel.pinToTop.isSelected()) {
			StartupSettings.mainPageSettings.setPinToTop(1);
			StartupSettings.frame.setAlwaysOnTop(true);
		}
		else {
			StartupSettings.mainPageSettings.setPinToTop(0);
			StartupSettings.frame.setAlwaysOnTop(false);
		}
		if (CentralPanel.onlyVerifiedHosts.isSelected()) {
			StartupSettings.mainPageSettings.setOnlyVerifiedHosts(1);
		}
		else {
			StartupSettings.mainPageSettings.setOnlyVerifiedHosts(0);
		}
		if (CentralPanel.onlyHostAdvertisements.isSelected()) {
			StartupSettings.mainPageSettings.setAdvertiseTrackerHosts(1);
		}
		else {
			StartupSettings.mainPageSettings.setAdvertiseTrackerHosts(0);
		}
		if (CentralPanel.onlyCommunityAdvertisements.isSelected()) {
			StartupSettings.mainPageSettings.setAdvertiseCommunityLines(1);
		}
		else {
			StartupSettings.mainPageSettings.setAdvertiseCommunityLines(0);
		}
		if (CentralPanel.onlyBoneJobAdvertisements.isSelected()) {
			StartupSettings.mainPageSettings.setAdvertiseBoneJobLines(1);
		}
		else {
			StartupSettings.mainPageSettings.setAdvertiseBoneJobLines(0);
		}
		Config.updateConfigFile();
		StartupSettings.initialiseSettings();
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
		if (StartupSettings.mainPageSettings.getPinToTop() == 1) {
			CentralPanel.pinToTop.setSelected(true);
		}
		else {
			CentralPanel.pinToTop.setSelected(false);
		}
		if (StartupSettings.mainPageSettings.getOnlyVerifiedHosts() == 1) {
			CentralPanel.onlyVerifiedHosts.setSelected(true);
		}
		else {
			CentralPanel.onlyVerifiedHosts.setSelected(false);
		}
		if (StartupSettings.mainPageSettings.getAdvertiseTrackerHosts() == 1) {
			CentralPanel.onlyHostAdvertisements.setSelected(true);
		}
		else {
			CentralPanel.onlyHostAdvertisements.setSelected(false);
		}
		if (StartupSettings.mainPageSettings.getAdvertiseCommunityLines() == 1) {
			CentralPanel.onlyCommunityAdvertisements.setSelected(true);
		}
		else {
			CentralPanel.onlyCommunityAdvertisements.setSelected(false);
		}
		if (StartupSettings.mainPageSettings.getAdvertiseBoneJobLines() == 1) {
			CentralPanel.onlyBoneJobAdvertisements.setSelected(true);
		}
		else {
			CentralPanel.onlyBoneJobAdvertisements.setSelected(false);
		}
		if (StartupSettings.mainPageSettings.getAdvertiseTrackerHosts() == 1 || StartupSettings.mainPageSettings.getAdvertiseCommunityLines() == 1 || StartupSettings.mainPageSettings.getAdvertiseBoneJobLines() == 1) {
			CentralPanel.startTyping.setEnabled(true);
		}
		else {
			CentralPanel.startTyping.setEnabled(false);
		}
	}
}