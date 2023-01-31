package source;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import actionListeners.AdvertisementsListener;
import actionListeners.MainCheckboxUpdater;
import actionListeners.MenuBarListener;
import actionListeners.ServerTypeMenuListener;
import panels.CentralPanel;
import typer.HostUpdaterThread;

public class Config {

	public static File configFile = new File(System.getProperty("user.home") + "/Desktop/AltarTyperConfigFile720.txt");
	private static boolean configFileDeletion;
	private static boolean configFileBackupStatus;

	public static void replaceConfigFile() { //Replaces the configuration file if the user wishes to do so
		configFileDeletion = false;
		if (configFile.exists()) {
			configFile.delete();
			configFileDeletion = true;
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
			writer.write("#TYPING_SPEED" + "\n");
			writer.write("[This must be entered in milliseconds]" + "\n");
			writer.write("TYPING_SPEED_MIN=10" + "\n");
			writer.write("TYPING_SPEED_MAX=20" + "\n");
			writer.write("\n");
			writer.write("#ADVERTISING_LINES_DELAY" + "\n");
			writer.write("[This must be entered in seconds]" + "\n");
			writer.write("LINES_DELAY_MIN=1" + "\n");
			writer.write("LINES_DELAY_MAX=1" + "\n");
			writer.write("\n");
			writer.write("#STOP_TIMER" + "\n");
			writer.write("[This must be entered in hours (1 hour or more)]" + "\n");
			writer.write("STOP_TIME=23" + "\n");
			writer.write("\n");
			writer.write("#HOST_COUNT" + "\n");
			writer.write("[This is the number of host lines that occur before an advertisement line (1 host line or more)]" + "\n");
			writer.write("HOST_COUNT=3" + "\n");
			writer.write("\n");
			writer.write("#SERVER_DROPDOWN_MENU" + "\n");
			writer.write("[0 = RS3 - 31 - Taverley]" + "\n");
			writer.write("[1 = RS3 - 31 - Yanille]" + "\n");
			writer.write("[2 = OSRS - 330 - Rimmington]" + "\n");
			writer.write("[3 = OSRS - 331 - Rimmington]" + "\n");
			writer.write("[4 = OSRS - 465 - Rimmington]" + "\n");
			writer.write("[5 = OSRS - 512 - Rimmington]" + "\n");
			writer.write("[6 = OSRS - 330 - Yanille]" + "\n");
			writer.write("[7 = OSRS - 331 - Yanille]" + "\n");
			writer.write("[8 = OSRS - 465 - Yanille]" + "\n");
			writer.write("[9 = OSRS - 512 - Yanille]" + "\n");
			writer.write("SERVER_DROPDOWN_MENU=0" + "\n");
			writer.write("\n");
			writer.write("#PIN_TO_TOP" + "\n");
			writer.write("[This allows you to keep this program on top of other programs. 0 = unchecked, 1 = checked]" + "\n");
			writer.write("PIN_TO_TOP=0" + "\n");
			writer.write("\n");
			writer.write("#ONLY_VERIFIED_HOSTS" + "\n");
			writer.write("[This allows you to only advertise verified hosts. 0 = unchecked, 1 = checked]" + "\n");
			writer.write("ONLY_VERIFIED_HOSTS=1" + "\n");
			writer.write("\n");
			writer.write("#ADVERTISE_TRACKER_HOSTS" + "\n");
			writer.write("[This allows you to advertise active hosts listed on the tracker. 0 = unchecked, 1 = checked]" + "\n");
			writer.write("ADVERTISE_TRACKER_HOSTS=1" + "\n");
			writer.write("\n");
			writer.write("#ADVERTISE_COMMUNITY_LINES" + "\n");
			writer.write("[This allows you to advertise community advertisements. 0 = unchecked, 1 = checked]" + "\n");
			writer.write("ADVERTISE_COMMUNITY_LINES=1" + "\n");
			writer.write("\n");
			writer.write("#ADVERTISE_BONE_JOB_LINES" + "\n");
			writer.write("[This allows you to advertise bone job advertisements. 0 = unchecked, 1 = checked]" + "\n");
			writer.write("ADVERTISE_BONE_JOB_LINES=0" + "\n");
			writer.write("\n");
			writer.write("#COLOURS" + "\n");
			writer.write("[This allows you to choose the colour(s) that you wish to use while typing. 0 = unchecked, 1 = checked]" + "\n");
			writer.write("Yellow=1" + "\n");
			writer.write("Purple=0" + "\n");
			writer.write("Red=0" + "\n");
			writer.write("Green=1" + "\n");
			writer.write("White=1" + "\n");
			writer.write("Cyan=1" + "\n");
			writer.write("Flash1=0" + "\n");
			writer.write("Flash2=0" + "\n");
			writer.write("Flash3=0" + "\n");
			writer.write("Glow1=1" + "\n");
			writer.write("Glow2=0" + "\n");
			writer.write("Glow3=1" + "\n");
			writer.write("\n");
			writer.write("#EFFECTS" + "\n");
			writer.write("[This allows you to choose the effect(s) that you wish to use while typing. 0 = unchecked, 1 = checked]" + "\n");
			writer.write("Wave=0" + "\n");
			writer.write("Wave2=0" + "\n");
			writer.write("Scroll=0" + "\n");
			writer.write("Slide=0" + "\n");
			writer.write("Shake=0" + "\n");
			writer.write("\n");
			writer.write("#NO_HOST_LINE" + "\n");
			writer.write("No hosts are open. Please see [ Altar ] fc for more info" + "\n");
			writer.write("\n");
			writer.write("#ADVERTISING_LINES" + "\n");
			writer.write("~ [ Altar ] ~ Serving the prayer community since 2009! ~ [ Altar ] ~" + "\n");
			writer.write("~ [ Altar ] ~ Friends Chat for optimal xp/hr in prayer!" + "\n");
			writer.write("Anyone can learn to host! Join ~ [ 07 Altar ] ~ cc" + "\n");
			writer.write("Have an in-game question? Ask in ~ [ 07 Altar ] ~ Clan Chat!" + "\n");
			writer.write("Need a G Altar? Join ~ [ 07 Altar ] ~ Clan Chat! :D" + "\n");
			writer.write("Want a fun place to hang out? Join ~ [ 07 Altar ] ~ cc" + "\n");
			writer.write("\n");
			writer.write("#BONE_JOB_ADVERTISING_LINES" + "\n");
			writer.write("~ [ Bone Job ] cc for Bone Runners [ Bone Job ] ~" + "\n");
			writer.close();
		} catch (IOException e) {
			configErrorMessage();
		}
		StartupSettings.initialiseSettings();
	}

	public static void replaceConfigFileDialog() { //User confirmation dialogue for when the configuration file is created or replaced
		if (configFileDeletion == true) {
			JOptionPane.showMessageDialog(StartupSettings.frame, "Config file replaced:\n" + configFile + "\nIts configuration has been applied automatically.", "Config File", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(StartupSettings.frame, "Config file created:\n" + configFile + "\nIts configuration has been applied automatically.", "Config File", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void updateConfigFile() { //Updates the configuration file with new data
		String[] linesToWriteToConfigFile = {"#TYPING_SPEED" + "\n",
				"[This must be entered in milliseconds]" + "\n",
				"TYPING_SPEED_MIN=" + StartupSettings.adjustTyperSettings.getTypeSpeedDelayMin() + "\n",
				"TYPING_SPEED_MAX=" + StartupSettings.adjustTyperSettings.getTypeSpeedDelayMax() + "\n",
				"\n",
				"#ADVERTISING_LINES_DELAY" + "\n",
				"[This must be entered in seconds]" + "\n",
				"LINES_DELAY_MIN=" + StartupSettings.adjustTyperSettings.getDelayTimeMin() / 1000 + "\n",
				"LINES_DELAY_MAX=" + StartupSettings.adjustTyperSettings.getDelayTimeMax() / 1000 + "\n",
				"\n",
				"#STOP_TIMER" + "\n",
				"[This must be entered in hours (1 hour or more)]" + "\n",
				"STOP_TIME=" + StartupSettings.adjustTyperSettings.getStopTime() / 3600 + "\n",
				"\n",
				"#HOST_COUNT" + "\n",
				"[This is the number of host lines that occur before an advertisement line (1 host line or more)]" + "\n",
				"HOST_COUNT=" + StartupSettings.adjustTyperSettings.getHostCount() + "\n",
				"\n",
				"#SERVER_DROPDOWN_MENU" + "\n",
				"[0 = RS3 - 31 - Taverley]" + "\n", 
				"[1 = RS3 - 31 - Yanille]" + "\n", 
				"[2 = OSRS - 330 - Rimmington]" + "\n", 
				"[3 = OSRS - 331 - Rimmington]" + "\n", 
				"[4 = OSRS - 465 - Rimmington]" + "\n", 
				"[5 = OSRS - 512 - Rimmington]" + "\n", 
				"[6 = OSRS - 330 - Yanille]" + "\n", 
				"[7 = OSRS - 331 - Yanille]" + "\n", 
				"[8 = OSRS - 465 - Yanille]" + "\n", 
				"[9 = OSRS - 512 - Yanille]" + "\n", 
				"SERVER_DROPDOWN_MENU=" + StartupSettings.mainPageSettings.getServerDropdownMenuIndex() + "\n",
				"\n",
				"#PIN_TO_TOP" + "\n",
				"[This allows you to keep this program on top of other programs. 0 = unchecked, 1 = checked]" + "\n",
				"PIN_TO_TOP=" + StartupSettings.mainPageSettings.getPinToTop() + "\n",
				"\n",
				"#ONLY_VERIFIED_HOSTS" + "\n",
				"[This allows you to only advertise verified hosts. 0 = unchecked, 1 = checked]" + "\n",
				"ONLY_VERIFIED_HOSTS=" + StartupSettings.mainPageSettings.getOnlyVerifiedHosts() + "\n",
				"\n",
				"#ADVERTISE_TRACKER_HOSTS" + "\n",
				"[This allows you to advertise active hosts listed on the tracker. 0 = unchecked, 1 = checked]" + "\n",
				"ADVERTISE_TRACKER_HOSTS=" + StartupSettings.mainPageSettings.getAdvertiseTrackerHosts() + "\n",
				"\n",
				"#ADVERTISE_COMMUNITY_LINES" + "\n",
				"[This allows you to advertise community advertisements. 0 = unchecked, 1 = checked]" + "\n",
				"ADVERTISE_COMMUNITY_LINES=" + StartupSettings.mainPageSettings.getAdvertiseCommunityLines() + "\n",
				"\n",
				"#ADVERTISE_BONE_JOB_LINES" + "\n",
				"[This allows you to advertise bone job advertisements. 0 = unchecked, 1 = checked]" + "\n",
				"ADVERTISE_BONE_JOB_LINES=" + StartupSettings.mainPageSettings.getAdvertiseBoneJobLines() + "\n",
				"\n",
				"#COLOURS" + "\n",
				"[This allows you to choose the colour(s) that you wish to use while typing. 0 = unchecked, 1 = checked]" + "\n",
				"Yellow=" + StartupSettings.colourAndEffectSettings.getYellow() + "\n",
				"Purple=" + StartupSettings.colourAndEffectSettings.getPurple() + "\n",
				"Red=" + StartupSettings.colourAndEffectSettings.getRed() + "\n",
				"Green=" + StartupSettings.colourAndEffectSettings.getGreen() + "\n",
				"White=" + StartupSettings.colourAndEffectSettings.getWhite() + "\n",
				"Cyan=" + StartupSettings.colourAndEffectSettings.getCyan() + "\n",
				"Flash1=" + StartupSettings.colourAndEffectSettings.getFlash1() + "\n",
				"Flash2=" + StartupSettings.colourAndEffectSettings.getFlash2() + "\n",
				"Flash3=" + StartupSettings.colourAndEffectSettings.getFlash2() + "\n",
				"Glow1=" + StartupSettings.colourAndEffectSettings.getGlow1() + "\n",
				"Glow2=" + StartupSettings.colourAndEffectSettings.getGlow2() + "\n",
				"Glow3=" + StartupSettings.colourAndEffectSettings.getGlow3() + "\n",
				"\n",
				"#EFFECTS" + "\n",
				"[This allows you to choose the effect(s) that you wish to use while typing. 0 = unchecked, 1 = checked]" + "\n",
				"Wave=" + StartupSettings.colourAndEffectSettings.getWave() + "\n",
				"Wave2=" + StartupSettings.colourAndEffectSettings.getWave2() + "\n",
				"Scroll=" + StartupSettings.colourAndEffectSettings.getScroll() + "\n",
				"Slide=" + StartupSettings.colourAndEffectSettings.getSlide() + "\n",
				"Shake=" + StartupSettings.colourAndEffectSettings.getShake() + "\n"};

		String[] noHostSpamLineToWriteToConfigFile = AdvertisementsListener.noHostAdvert.getText().split("\n");
		String[] communitySpamLinesToWriteToConfigFile = AdvertisementsListener.communityAdverts.getText().split("\n");
		String[] boneJobSpamLinesToWriteToConfigFile = AdvertisementsListener.boneJobAdverts.getText().split("\n");
		writeToConfigFile(linesToWriteToConfigFile, noHostSpamLineToWriteToConfigFile, communitySpamLinesToWriteToConfigFile, boneJobSpamLinesToWriteToConfigFile);
	}

	public static void writeToConfigFile(String[] lines, String[] noHostSpamLine, String[] communitySpamLines, String[] boneJobSpamLines) { 
		//Writes advertisements lines to the configuration file
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
			for (String s : lines) {
				writer.write(s);
			}
			writer.write("\n");
			writer.write("#NO_HOST_LINE" + "\n");
			for (String s : noHostSpamLine) {
				writer.write(s + "\n");
			}
			writer.write("\n");
			writer.write("#ADVERTISING_LINES" + "\n");
			for (String s : communitySpamLines) {
				writer.write(s + "\n");
			}
			writer.write("\n");
			writer.write("#BONE_JOB_ADVERTISING_LINES" + "\n");
			for (String s : boneJobSpamLines) {
				writer.write(s + "\n");
			}
			writer.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(StartupSettings.frame, "Oops! Something went wrong with writing to the config file. Please delete the config file located at:\n" + configFile, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static String[] readFromConfigFile() { //Reads the advertisements and settings data from the configuration file
		Scanner scanner = null;
		//Checks whether the configuration file is on the desktop
		try {
			scanner = new Scanner(configFile);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(StartupSettings.frame, "Oops! Something went wrong with reading the config file. Please delete the config file located at:\n" + configFile, "Error", JOptionPane.ERROR_MESSAGE);
		}
		String configFileContents = "";
		//While there is a new line, continue scanning
		while (scanner.hasNextLine()) {
			String currentLine = scanner.nextLine();
			if (currentLine.equals("#ADVERTISING_LINES")) {
				configFileContents += currentLine;
				configFileContents += "\n";
			}
			else if (currentLine.equals("#BONE_JOB_ADVERTISING_LINES")) {
				configFileContents += currentLine;
				configFileContents += "\n";
			}
			else if (!currentLine.startsWith("[") && !currentLine.startsWith("#") && !currentLine.equals("")) {
				configFileContents += currentLine;
				configFileContents += "\n";
			}
		}
		scanner.close();
		String[] settings = configFileContents.split("\n");
		for (int i = 0; i < settings.length; i++) {
			settings[i] = settings[i].substring(settings[i].indexOf("=") + 1, settings[i].length());
		}
		return settings;
	}

	private static void backupConfigFile() { //Backs up the configuration file when it is corrupt or missing important information
		configFileBackupStatus = false;
		Scanner scanner = null;
		//Checks whether the configuration file is on the desktop
		try {
			scanner = new Scanner(configFile);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(StartupSettings.frame, configFile + " could not be located.", "Config File", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String configFileContents = "";
		while (scanner.hasNextLine()) {
			String currentLine = scanner.nextLine();
			configFileContents += currentLine;
			configFileContents += "\n";
		}
		scanner.close();
		//Displays a file interface for the user to select the name and location for the backup file
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setSelectedFile(new File("AltarTyperConfigBackupFile600"));
		int selectedOption = fileChooser.showSaveDialog(null);
		if (selectedOption == JFileChooser.APPROVE_OPTION) {
			String filePath = fileChooser.getSelectedFile().getAbsolutePath();
			File backupConfigFile = new File(filePath + ".txt");
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(backupConfigFile));
				writer.write(configFileContents);
				writer.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(StartupSettings.frame, "Oops! Something went wrong with writing to the config backup file. Please delete the config backup file located at:\n" + backupConfigFile, "Error", JOptionPane.ERROR_MESSAGE);
			}
			configFileBackupStatus = true;
		}
		else if (selectedOption == JFileChooser.CANCEL_OPTION) {
			return;
		}
	}

	public static void configErrorMessage() { //Error message that displays to the user when the data in the configuration file is missing or invalid
		StartupSettings.configErrorMessage = "The config file was unable to be read. Its settings have either a non-numeric value or been deleted.\nAs a result, the config file will be replaced. You may wish to back up the current config file.";
		Object[] options = {"Continue with backup", "Continue without backup"};
		int dialogOption = JOptionPane.showOptionDialog(StartupSettings.frame, StartupSettings.configErrorMessage,
				"Config File", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
		if (MenuBarListener.applyChanges == true) {
			//If the user has applied any changes via the menu bar
			if (dialogOption == JOptionPane.YES_OPTION) {
				backupConfigFile();
				if (configFileBackupStatus == false) {
					configErrorMessage();
					return;
				}
				newConfigFile();
				return;
			}
			else if (dialogOption == JOptionPane.NO_OPTION) {
				newConfigFile();
				return;
			}
			else {
				configErrorMessage();
				return;
			}
		}
		else {
			//If it happens upon application startup
			if (dialogOption == JOptionPane.YES_OPTION) {
				backupConfigFile();
				if (configFileBackupStatus == false) {
					configErrorMessage();
					return;
				}
				replaceConfigFile();
				replaceConfigFileDialog();
				return;
			}
			else if (dialogOption == JOptionPane.NO_OPTION) {
				replaceConfigFile();
				replaceConfigFileDialog();
				return;
			}
			else {
				//Error dialogue reappears until the user selects either option
				configErrorMessage();
				return;
			}
		}
	}

	public static void configValidationErrorMessage() { //Error message that displays to the user when the configuration file is corrupt or missing important information
		boolean optionFlag = false;
		while (optionFlag == false) {
			if (MenuBarListener.applyChanges == true) {
				//If the user has applied any changes via the menu bar, it will display the following error dialogue with options if it has come across any issues with the configuration file
				Object[] options = {"Continue", "OPEN the config file", "REPLACE the config file"};
				int dialogOption = JOptionPane.showOptionDialog(StartupSettings.frame, StartupSettings.configErrorMessage,
						"Config File", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
				if (dialogOption == JOptionPane.YES_OPTION) {
					optionFlag = true;
					return;
				}
				else if (dialogOption == JOptionPane.NO_OPTION) {
					optionFlag = true;
					openConfigFile();
					return;
				}
				else if (dialogOption == JOptionPane.CANCEL_OPTION) {
					optionFlag = true;
					newConfigFile();
					return;
				}
			}
			else {
				//It will display the following error dialogue with options if it has come across any issues with the configuration file upon application startup
				Object[] options = {"OPEN the config file and exit the program", "REPLACE the config file"};
				int dialogOption = JOptionPane.showOptionDialog(StartupSettings.frame, StartupSettings.configErrorMessage,
						"Config File", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
				if (dialogOption == JOptionPane.YES_OPTION) {
					optionFlag = true;
					openConfigFile();
					System.exit(0);
				}
				else if (dialogOption == JOptionPane.NO_OPTION) {
					optionFlag = true;
					replaceConfigFile();
					replaceConfigFileDialog();
					return;
				} 
			}
		}
	}

	public static void newConfigFile() { //Creates a new configuration file if the user wishes to do so
		replaceConfigFile();
		StartupSettings.mainPageSettings.setServerDropdownMenuIndex(CentralPanel.serverType.getSelectedIndex());
		ServerTypeMenuListener.setCommunityAdvertisementsText();
		updateConfigFile();
		setFrameOnTop();
		MainCheckboxUpdater.updateMainCheckBoxes();
		HostUpdaterThread.listOnlyVerifiedHosts();
		replaceConfigFileDialog();
	}

	public static void openConfigFile() { //Opens the existing configuration file if the user wishes to do so
		if (configFile.exists()) {
			try {
				Desktop desktop = null;
				//Checks to see if it can access the user's desktop to open the configuration file
				if (Desktop.isDesktopSupported()) {
					desktop = Desktop.getDesktop();
				}
				desktop.open(configFile);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(StartupSettings.frame, "Oops! Something went wrong with opening the config file. Please try again.", "Config File", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(StartupSettings.frame, configFile + " could not be located.", "Config File", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void applyChanges() { //Applies any changes made by the user to the configuration file and settings
		if (configFile.exists()) {
			StartupSettings.initialiseSettings();
			CentralPanel.serverType.setSelectedIndex(StartupSettings.mainPageSettings.getServerDropdownMenuIndex());
			updateConfigFile();
			setFrameOnTop();
			MainCheckboxUpdater.updateMainCheckBoxes();
			HostUpdaterThread.listOnlyVerifiedHosts();
			if (StartupSettings.errorFlag == false) {
				JOptionPane.showMessageDialog(StartupSettings.frame, "Any changes you made to your config file have now been applied.", "Config File", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(StartupSettings.frame, configFile + " could not be located.", "Config File", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void setFrameOnTop() { //Sets the application window on top of other windows depending on its value
		if (StartupSettings.mainPageSettings.getPinToTop() == 1) {
			StartupSettings.frame.setAlwaysOnTop(true);
		}
		else {
			StartupSettings.frame.setAlwaysOnTop(false);
		}
	}

	public static void openWebpage(String url) { //Opens the web page URL depending on which button the user clicked on the menu bar
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(new URL(url).toURI());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(StartupSettings.frame, "Oops! Something went wrong with opening the Web page. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static void writeErrorReport(Exception e) { //Writes an error report in a text file dynamically and places it on the user's desktop to send for troubleshooting purposes
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String sStackTrace = sw.toString();
		DateTimeFormatter currentDateAndTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		File desktopFile = new File(System.getProperty("user.home") + "/Desktop/Altar Typer Error " + System.currentTimeMillis() + ".txt");
		try {
			PrintWriter writer = new PrintWriter(desktopFile, "UTF-8");
			writer.println(currentDateAndTime.format(now));
			writer.println(sStackTrace);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			return;
		}
	}
}
