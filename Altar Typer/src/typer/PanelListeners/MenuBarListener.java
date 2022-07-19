package typer.PanelListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import typer.Tools.Config;

public class MenuBarListener implements ActionListener { //Menu options on top of the application
	public static boolean applyChanges;

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {

		case "New": //Creates a new configuration file
			Config.newConfigFile();
			break;

		case "Open": //Opens the existing configuration file
			Config.openConfigFile();
			break;

		case "Apply Changes": //Applies any changes to the existing configuration file
			applyChanges = true;
			Config.applyChanges();
			applyChanges = false;
			break;

		case "Portal": //Redirects the user to Altar Community Portal web page
			Config.openWebpage("https://www.altar.rs");
			break;

		case "Forums": //Redirects the user to Altar Community Forums
			Config.openWebpage("https://www.altar.rs/forums/");
			break;

		case "Discord": //Redirects the user to Altar Community Discord server
			Config.openWebpage("http://discord.gg/altar");
			break;

		case "HHT": //Redirects the user to the House Host Tracker discord channel
			Config.openWebpage("https://discord.gg/DfAhfAP");
			break;

		case "Download Page": //Redirects the user to the Java downloads page
			Config.openWebpage("https://www.java.com/en/download/");
			break;
		}
	}
}
