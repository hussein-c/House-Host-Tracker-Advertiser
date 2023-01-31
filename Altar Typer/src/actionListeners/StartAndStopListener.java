package actionListeners;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import panels.CentralPanel;
import panels.MenuBarPanel;
import source.StartupSettings;
import typer.TyperThread;

public class StartAndStopListener implements ActionListener {

	private static TyperThread typer;
	public static boolean typerExecution;
	private boolean errorFlag;

	@Override
	public void actionPerformed(ActionEvent e) {

		switch(e.getActionCommand()) {
		case "Start Typing":
			emptyAdvertisementValidation();
			if (errorFlag == false) { //If the advertisements are not empty, start the typer
				JOptionPane.showMessageDialog(StartupSettings.frame, "Once you press okay, this program will minimize.\nYou have 5 seconds to click inside the OSRS/RS3 client.");
				StartupSettings.frame.setState(Frame.ICONIFIED);
				typer = new TyperThread();
				typer.start();
				typerExecution = true;
				//Disables all buttons and check boxes on the main page once the typer has started
				MenuBarPanel.configMenu.setEnabled(false);
				MenuBarPanel.communityMenu.setEnabled(false);
				MenuBarPanel.javaMenu.setEnabled(false);
				CentralPanel.coloursAndEffects.setEnabled(false);
				CentralPanel.advertisements.setEnabled(false);
				CentralPanel.blackList.setEnabled(false);
				CentralPanel.seeConfigs.setEnabled(false);
				CentralPanel.pinToTop.setEnabled(false);
				CentralPanel.onlyVerifiedHosts.setEnabled(false);
				CentralPanel.onlyHostAdvertisements.setEnabled(false);
				CentralPanel.onlyCommunityAdvertisements.setEnabled(false);
				CentralPanel.onlyBoneJobAdvertisements.setEnabled(false);
				CentralPanel.serverType.setEnabled(false);
				CentralPanel.startTyping.setEnabled(false);
			}
			break;

		case "Stop Typing":
			typer.stop();
			typerExecution = false;
			//Re-enables all the buttons and check boxes on the main page once the typer has stopped
			MenuBarPanel.configMenu.setEnabled(true);
			MenuBarPanel.communityMenu.setEnabled(true);
			MenuBarPanel.javaMenu.setEnabled(true);
			CentralPanel.coloursAndEffects.setEnabled(true);
			CentralPanel.advertisements.setEnabled(true);
			CentralPanel.blackList.setEnabled(true);
			CentralPanel.seeConfigs.setEnabled(true);
			CentralPanel.pinToTop.setEnabled(true);
			CentralPanel.onlyVerifiedHosts.setEnabled(true);
			CentralPanel.onlyHostAdvertisements.setEnabled(true);
			CentralPanel.onlyCommunityAdvertisements.setEnabled(true);
			CentralPanel.onlyBoneJobAdvertisements.setEnabled(true);
			CentralPanel.serverType.setEnabled(true);
			CentralPanel.startTyping.setEnabled(true);
			CentralPanel.stopTyping.setEnabled(false);
			break;
		}
	}

	private void emptyAdvertisementValidation() { //Checks whether the advertisements are empty, and if so, it stops the user from starting the typer if they enabled the advertisement check box
		String onlyCommunityAdvertisements = StartupSettings.communityMessages.trim();
		String onlyBoneJobAdvertisements = StartupSettings.boneJobMessages.trim();
		errorFlag = false;
		if (CentralPanel.onlyCommunityAdvertisements.isSelected() && CentralPanel.onlyBoneJobAdvertisements.isSelected()) {
			if (onlyCommunityAdvertisements.equals("") && onlyBoneJobAdvertisements.equals("")) {
				errorFlag = true;
				JOptionPane.showMessageDialog(StartupSettings.frame, "Please add some community or bone job advertisements or uncheck their respective checkboxes.");
				return;
			}
		}
		else if (CentralPanel.onlyCommunityAdvertisements.isSelected()) {
			if (onlyCommunityAdvertisements.equals("")) {
				errorFlag = true;
				JOptionPane.showMessageDialog(StartupSettings.frame, "Please add some community advertisements or uncheck its checkbox.");
				return;
			}
		}
		else if (CentralPanel.onlyBoneJobAdvertisements.isSelected()) {
			if (onlyBoneJobAdvertisements.equals("")) {
				errorFlag = true;
				JOptionPane.showMessageDialog(StartupSettings.frame, "Please add some bone job advertisements or uncheck its checkbox.");
				return;
			}
		}
	}
}