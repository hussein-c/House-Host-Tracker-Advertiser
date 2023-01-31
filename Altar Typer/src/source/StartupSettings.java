package source;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import actionListeners.AdvertisementsListener;
import data.ColoursAndEffects;
import data.MainCheckBoxes;
import data.TyperConfigs;
import panels.MainFrame;
import typer.HostUpdaterThread;

/**
 * Proprietary and confidential 
 * Written by Dakota Nickel <loyalmodding@gmail.com> and Hussein Chalak <husseinchalak6@gmail.com>
 * Original Author: Im Dakota#5882 on Discord
 * Approved Author: WarriorBuddy#9000 on Discord
 * Function: Advertising hosts, clan and website
 * Requirement: Latest version of Java
 * Version 7.2.0 Update: 09/2021 (MM/YYYY)
 */

public class StartupSettings {

	public static JFrame frame;
	private static HostUpdaterThread grabHosts = new HostUpdaterThread();
	public static TyperConfigs adjustTyperSettings = new TyperConfigs();
	public static MainCheckBoxes mainPageSettings = new MainCheckBoxes();
	public static ColoursAndEffects colourAndEffectSettings = new ColoursAndEffects();
	private static String[] settings;
	public static String communityMessages;
	public static String boneJobMessages;
	public static String noHostMessage;
	public static String configErrorMessage;
	static boolean errorFlag = false;

	public static void main(String[] args) {
		startProgram();
	}

	public static void startProgram() {
		try { //This is important on MacOS because it allows it to look like the Windows version
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(frame, "Layout is not supported.");
			System.exit(0);
		}
		if (!Config.configFile.exists()) {
			Config.replaceConfigFile();
			Config.replaceConfigFileDialog();
		}
		else {
			initialiseSettings();
		}
		frame = new MainFrame();
		System.setProperty("http.agent", "Chrome");
		grabHosts.start();
	}

	public static void initialiseSettings() { //Initialises all settings upon application start-up based on the configuration file data
		settings = Config.readFromConfigFile();
		int errorCount = 0;
		communityMessages = "";
		boneJobMessages = "";
		int colourAndEffectCount = 0;
		try {
			configErrorMessage = "The following have been entered incorrectly:\n";
			//Type Speed Delay
			if ((Integer.parseInt(settings[0]) > 0 && Integer.parseInt(settings[1]) > 0) && (Integer.parseInt(settings[0]) <= Integer.parseInt(settings[1]))) {
				adjustTyperSettings.setTypeSpeedDelayMin(Integer.parseInt(settings[0]));
				adjustTyperSettings.setTypeSpeedDelayMax(Integer.parseInt(settings[1]));
			}
			else {
				errorCount++;
				configErrorMessage += errorCount + ". TYPING_SPEED_MIN and/or TYPING_SPEED_MAX.\n";
			}
			//Lines Delay
			if ((Integer.parseInt(settings[2]) > 0 && Integer.parseInt(settings[3]) > 0) && (Integer.parseInt(settings[2]) <= Integer.parseInt(settings[3]))) {
				adjustTyperSettings.setDelayTimeMin(Integer.parseInt(settings[2]) * 1000);
				adjustTyperSettings.setDelayTimeMax(Integer.parseInt(settings[3]) * 1000);
			}
			else {
				errorCount++;
				configErrorMessage += errorCount + ". LINES_DELAY_MIN and/or LINES_DELAY_MAX.\n";
			}
			//Stop Time
			if (Integer.parseInt(settings[4]) > 0) {
				adjustTyperSettings.setStopTime(Integer.parseInt(settings[4]) * 3600);
			}
			else {
				errorCount++;
				configErrorMessage += errorCount + ". STOP_TIME.\n";
			}
			//Host Count
			if (Integer.parseInt(settings[5]) > 0) {
				adjustTyperSettings.setHostCount(Integer.parseInt(settings[5]));
			}
			else {
				errorCount++;
				configErrorMessage += errorCount + ". HOST_COUNT.\n";
			}
			//Server Dropdown Menu
			if (Integer.parseInt(settings[6]) >= 0 && Integer.parseInt(settings[6]) <= 9) {
				mainPageSettings.setServerDropdownMenuIndex(Integer.parseInt(settings[6]));
			}
			else {
				errorCount++;
				configErrorMessage += errorCount + ". SERVER_DROPDOWN_MENU.\n";
			}
			//Pin To Top
			if (Integer.parseInt(settings[7]) == 0 || Integer.parseInt(settings[7]) == 1) {
				mainPageSettings.setPinToTop(Integer.parseInt(settings[7]));
			}
			else {
				errorCount++;
				configErrorMessage += errorCount + ". PIN_TO_TOP.\n";
			}
			//Only Verified Hosts
			if (Integer.parseInt(settings[8]) == 0 || Integer.parseInt(settings[8]) == 1) {
				mainPageSettings.setOnlyVerifiedHosts(Integer.parseInt(settings[8]));
			}
			else {
				errorCount++;
				configErrorMessage += errorCount + ". ONLY_VERIFIED_HOSTS.\n";
			}
			//Advertise Tracker Hosts
			if (Integer.parseInt(settings[9]) == 0 || Integer.parseInt(settings[9]) == 1) {
				mainPageSettings.setAdvertiseTrackerHosts(Integer.parseInt(settings[9]));
			}
			else {
				errorCount++;
				configErrorMessage += errorCount + ". ADVERTISE_TRACKER_HOSTS.\n";
			}
			//Advertise Community Lines
			if (Integer.parseInt(settings[10]) == 0 || Integer.parseInt(settings[10]) == 1) {
				mainPageSettings.setAdvertiseCommunityLines(Integer.parseInt(settings[10]));
			}
			else {
				errorCount++;
				configErrorMessage += errorCount + ". ADVERTISE_COMMUNITY_LINES.\n";
			}
			//Advertise Bone Job Lines
			if (Integer.parseInt(settings[11]) == 0 || Integer.parseInt(settings[11]) == 1) {
				mainPageSettings.setAdvertiseBoneJobLines(Integer.parseInt(settings[11]));
			}
			else {
				errorCount++;
				configErrorMessage += errorCount + ". ADVERTISE_BONE_JOB_LINES.\n";
			}
			//Yellow
			if (Integer.parseInt(settings[12]) == 0 || Integer.parseInt(settings[12]) == 1) {
				colourAndEffectSettings.setYellow(Integer.parseInt(settings[12]));
			}
			else {
				errorCount++;
				colourAndEffectCount++;
				configErrorMessage += errorCount + ". Yellow, ";
			}
			//Purple
			if (Integer.parseInt(settings[13]) == 0 || Integer.parseInt(settings[13]) == 1) {
				colourAndEffectSettings.setPurple(Integer.parseInt(settings[13]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Purple, ";
			}
			//Red
			if (Integer.parseInt(settings[14]) == 0 || Integer.parseInt(settings[14]) == 1) {
				colourAndEffectSettings.setRed(Integer.parseInt(settings[14]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Red, ";
			}
			//Green
			if (Integer.parseInt(settings[15]) == 0 || Integer.parseInt(settings[15]) == 1) {
				colourAndEffectSettings.setGreen(Integer.parseInt(settings[15]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Green, ";
			}
			//White
			if (Integer.parseInt(settings[16]) == 0 || Integer.parseInt(settings[16]) == 1) {
				colourAndEffectSettings.setWhite(Integer.parseInt(settings[16]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "White, ";
			}
			//Cyan
			if (Integer.parseInt(settings[17]) == 0 || Integer.parseInt(settings[17]) == 1) {
				colourAndEffectSettings.setCyan(Integer.parseInt(settings[17]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Cyan, ";
			}
			//Flash1
			if (Integer.parseInt(settings[18]) == 0 || Integer.parseInt(settings[18]) == 1) {
				colourAndEffectSettings.setFlash1(Integer.parseInt(settings[18]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Flash1, ";
			}
			//Flash2
			if (Integer.parseInt(settings[19]) == 0 || Integer.parseInt(settings[19]) == 1) {
				colourAndEffectSettings.setFlash2(Integer.parseInt(settings[19]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Flash2, ";
			}
			//Flash3
			if (Integer.parseInt(settings[20]) == 0 || Integer.parseInt(settings[20]) == 1) {
				colourAndEffectSettings.setFlash3(Integer.parseInt(settings[20]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Flash3, ";
			}
			//Glow1
			if (Integer.parseInt(settings[21]) == 0 || Integer.parseInt(settings[21]) == 1) {
				colourAndEffectSettings.setGlow1(Integer.parseInt(settings[21]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Glow1, ";
			}
			//Glow2
			if (Integer.parseInt(settings[22]) == 0 || Integer.parseInt(settings[22]) == 1) {
				colourAndEffectSettings.setGlow2(Integer.parseInt(settings[22]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Glow2, ";
			}
			//Glow3
			if (Integer.parseInt(settings[23]) == 0 || Integer.parseInt(settings[23]) == 1) {
				colourAndEffectSettings.setGlow3(Integer.parseInt(settings[23]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Glow3, ";
			}
			//Wave
			if (Integer.parseInt(settings[24]) == 0 || Integer.parseInt(settings[24]) == 1) {
				colourAndEffectSettings.setWave(Integer.parseInt(settings[24]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Wave, ";
			}
			//Wave2
			if (Integer.parseInt(settings[25]) == 0 || Integer.parseInt(settings[25]) == 1) {
				colourAndEffectSettings.setWave2(Integer.parseInt(settings[25]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Wave2, ";
			}
			//Scroll
			if (Integer.parseInt(settings[26]) == 0 || Integer.parseInt(settings[26]) == 1) {
				colourAndEffectSettings.setScroll(Integer.parseInt(settings[26]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Scroll, ";
			}
			//Slide
			if (Integer.parseInt(settings[27]) == 0 || Integer.parseInt(settings[27]) == 1) {
				colourAndEffectSettings.setSlide(Integer.parseInt(settings[27]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Slide, ";
			}
			//Shake
			if (Integer.parseInt(settings[28]) == 0 || Integer.parseInt(settings[28]) == 1) {
				colourAndEffectSettings.setShake(Integer.parseInt(settings[28]));
			}
			else {
				colourAndEffectCount++;
				configErrorMessage += "Shake, ";
			}
			if (configErrorMessage.endsWith(", ")) {
				if (colourAndEffectCount == 1) {
					configErrorMessage = configErrorMessage.substring(0, configErrorMessage.lastIndexOf(","));
				}
				else {
					configErrorMessage = configErrorMessage.substring(0, configErrorMessage.lastIndexOf(","));
					configErrorMessage += ".\n";
				}
			}
			//Reads and initialises the No Host Advertisement lines
			boolean communityLineFlag = false;
			int noHostLine = 29;
			int communityLine = 29;
			for(int i = noHostLine; i < settings.length; i++) {
				if (settings[i].equals("#ADVERTISING_LINES")) {
					communityLineFlag = true;
					communityLine = i + 1;
					break;
				}
				else if (i == noHostLine) {
					noHostMessage = settings[i];
				}
			}
			//Reads and initialises the Community Advertisements lines
			boolean boneJobLineFlag = false;
			int boneJobLine = 29;
			if (communityLineFlag == true) {
				for(int i = communityLine; i < settings.length; i++) {
					if (settings[i].equals("#BONE_JOB_ADVERTISING_LINES")) {
						boneJobLineFlag = true;
						boneJobLine = i + 1;
						break;
					}
					else if (i == communityLine) {
						communityMessages = settings[i];
					}
					else {
						communityMessages += "\n" + settings[i];
					}
				}
			}
			//Reads and initialises the Bone Job Advertisements lines
			if (boneJobLineFlag == true) {
				for(int i = boneJobLine; i < settings.length; i++) {
					if (i == boneJobLine) {
						boneJobMessages = settings[i];
					}
					else {
						boneJobMessages += "\n" + settings[i];
					}
				}
			}
			AdvertisementsListener.noHostAdvert.setText(noHostMessage);
			AdvertisementsListener.communityAdverts.setText(communityMessages);
			AdvertisementsListener.boneJobAdverts.setText(boneJobMessages);
			if (errorCount > 0) {
				errorFlag = true;
				StartupSettings.configErrorMessage += "\nBut any other changes you made have now been applied. What would you like to do?";
				Config.configValidationErrorMessage();
			}
		}
		catch (Exception e) {
			Config.configErrorMessage();
		}
	}
}
