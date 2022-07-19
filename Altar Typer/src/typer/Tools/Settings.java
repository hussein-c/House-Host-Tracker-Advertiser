package typer.Tools;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import typer.MainListeners.HostUpdaterThread;
import typer.PanelListeners.AdvertisementsListener;
import typer.Panels.MainFrame;
import typer.Source.ColoursAndEffects;
import typer.Source.Host;
import typer.Source.MainPage;
import typer.Source.AdjustTyper;

/**
 * 2008 - 2022 Altar Community
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential 
 * Written by Dakota Nickel <loyalmodding@gmail.com> and Hussein Chalak <husseinchalak6@gmail.com>
 * Original Author: Im Dakota#5882 on Discord
 * Approved Author: WarriorBuddy#9000 on Discord
 * Function: Advertising hosts, clan and website to gain users
 * Requirements: Latest version of Java
 * Version 7.2.0 Update: 09/2022 (MM/YYYY)
 */

public class Settings {

	public static final int frameWidth = 610;
	public static final int frameHeight = 410;
	public static final String frameTitle = "Altar Typer (Version 7.2.0)";
	public static final String blackListLink = "https://www.altar.rs/HHT/typerblacklist.txt";
	public static final String overrideListLink = "https://www.altar.rs/HHT/typeroverridelist.txt";
	public static final String hostListLink = "https://www.altar.rs/altartracker.txt";
	public static JFrame frame;
	private static HostUpdaterThread grabHosts = new HostUpdaterThread();
	public static ArrayList<String> blackList;
	public static ArrayList<Host> overrideList;
	public static ArrayList<Host> allHosts;
	public static ArrayList<String> backUpBlackList;
	public static ArrayList<Host> backUpOverrideList;
	public static ArrayList<Host> backUpAllHosts;
	public static ArrayList<Character> shiftCases;
	public static Map<Character, Integer> mapStrokes;
	public static AdjustTyper adjustTyperSettings = new AdjustTyper();
	public static MainPage mainPageSettings = new MainPage();
	public static ColoursAndEffects colourAndEffectSettings = new ColoursAndEffects();
	private static String[] settings;
	public static String communityMessages;
	public static String boneJobMessages;
	public static String noHostMessage;
	private static int noHostLine = 29;
	private static int communityLine = 29;
	private static int boneJobLine = 29;
	public static String configErrorMessage;
	public static boolean errorFlag;

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
		initialiseShiftCases();
		frame = new MainFrame();
		System.setProperty("http.agent", "Chrome");
		grabHosts.start();
	}

	public static void initialiseSettings() { //Initialises all settings upon application start-up based on the configuration file data
		settings = Config.readFromConfigFile();
		communityMessages = "";
		boneJobMessages = "";
		int colourAndEffectCount = 0;
		try {
			errorFlag = false;
			int errorCount = 0;
			configErrorMessage = "The following have been entered incorrectly:\n";
			//Type Speed Delay
			if ((Integer.parseInt(settings[0]) > 0 && Integer.parseInt(settings[1]) > 0) && (Integer.parseInt(settings[0]) <= Integer.parseInt(settings[1]))) {
				adjustTyperSettings.setTypeSpeedDelayMin(Integer.parseInt(settings[0]));
				adjustTyperSettings.setTypeSpeedDelayMax(Integer.parseInt(settings[1]));
			}
			else if (Integer.parseInt(settings[0]) <= 0 && Integer.parseInt(settings[1]) <= 0) {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". TYPING_SPEED_MIN, TYPING_SPEED_MAX.\n";
			}
			else if (Integer.parseInt(settings[0]) <= 0) {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". TYPING_SPEED_MIN.\n";
			}
			else if (Integer.parseInt(settings[1]) <= 0) {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". TYPING_SPEED_MAX.\n";
			}
			else if (Integer.parseInt(settings[0]) >= Integer.parseInt(settings[1])) {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". TYPING_SPEED_MIN.\n";
			}
			//Lines Delay
			if ((Integer.parseInt(settings[2]) > 0 && Integer.parseInt(settings[3]) > 0) && (Integer.parseInt(settings[2]) <= Integer.parseInt(settings[3]))) {
				adjustTyperSettings.setDelayTimeMin(Integer.parseInt(settings[2]) * 1000);
				adjustTyperSettings.setDelayTimeMax(Integer.parseInt(settings[3]) * 1000);
			}
			else if (Integer.parseInt(settings[2]) <= 0 && Integer.parseInt(settings[3]) <= 0) {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". LINES_DELAY_MIN, LINES_DELAY_MAX.\n";
			}
			else if (Integer.parseInt(settings[2]) <= 0) {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". LINES_DELAY_MIN.\n";
			}
			else if (Integer.parseInt(settings[3]) <= 0) {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". LINES_DELAY_MAX.\n";
			}
			else if (Integer.parseInt(settings[2]) >= Integer.parseInt(settings[3])) {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". LINES_DELAY_MIN.\n";
			}
			//Stop Time
			if (Integer.parseInt(settings[4]) > 0) {
				adjustTyperSettings.setStopTime(Integer.parseInt(settings[4]) * 3600);
			}
			else {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". STOP_TIME.\n";
			}
			//Host Count
			if (Integer.parseInt(settings[5]) > 0) {
				adjustTyperSettings.setHostCount(Integer.parseInt(settings[5]));
			}
			else {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". HOST_COUNT.\n";
			}
			//Server Dropdown Menu
			if (Integer.parseInt(settings[6]) >= 0 && Integer.parseInt(settings[6]) <= 9) {
				mainPageSettings.setServerDropdownMenuIndex(Integer.parseInt(settings[6]));
			}
			else {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". SERVER_DROPDOWN_MENU.\n";
			}
			//Pin To Top
			if (Integer.parseInt(settings[7]) == 0 || Integer.parseInt(settings[7]) == 1) {
				mainPageSettings.setPinToTop(Integer.parseInt(settings[7]));
			}
			else {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". PIN_TO_TOP.\n";
			}
			//Only Verified Hosts
			if (Integer.parseInt(settings[8]) == 0 || Integer.parseInt(settings[8]) == 1) {
				mainPageSettings.setOnlyVerifiedHosts(Integer.parseInt(settings[8]));
			}
			else {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". ONLY_VERIFIED_HOSTS.\n";
			}
			//Advertise Tracker Hosts
			if (Integer.parseInt(settings[9]) == 0 || Integer.parseInt(settings[9]) == 1) {
				mainPageSettings.setAdvertiseTrackerHosts(Integer.parseInt(settings[9]));
			}
			else {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". ADVERTISE_TRACKER_HOSTS.\n";
			}
			//Advertise Community Lines
			if (Integer.parseInt(settings[10]) == 0 || Integer.parseInt(settings[10]) == 1) {
				mainPageSettings.setAdvertiseCommunityLines(Integer.parseInt(settings[10]));
			}
			else {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". ADVERTISE_COMMUNITY_LINES.\n";
			}
			//Advertise Bone Job Lines
			if (Integer.parseInt(settings[11]) == 0 || Integer.parseInt(settings[11]) == 1) {
				mainPageSettings.setAdvertiseBoneJobLines(Integer.parseInt(settings[11]));
			}
			else {
				errorFlag = true;
				errorCount++;
				configErrorMessage += errorCount + ". ADVERTISE_BONE_JOB_LINES.\n";
			}
			//Yellow
			if (Integer.parseInt(settings[12]) == 0 || Integer.parseInt(settings[12]) == 1) {
				colourAndEffectSettings.setYellow(Integer.parseInt(settings[12]));
			}
			else {
				errorFlag = true;
				errorCount++;
				colourAndEffectCount++;
				configErrorMessage += errorCount + ". Yellow, ";
			}
			//Purple
			if (Integer.parseInt(settings[13]) == 0 || Integer.parseInt(settings[13]) == 1) {
				colourAndEffectSettings.setPurple(Integer.parseInt(settings[13]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "Purple, ";
			}
			//Red
			if (Integer.parseInt(settings[14]) == 0 || Integer.parseInt(settings[14]) == 1) {
				colourAndEffectSettings.setRed(Integer.parseInt(settings[14]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "Red, ";
			}
			//Green
			if (Integer.parseInt(settings[15]) == 0 || Integer.parseInt(settings[15]) == 1) {
				colourAndEffectSettings.setGreen(Integer.parseInt(settings[15]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "Green, ";
			}
			//White
			if (Integer.parseInt(settings[16]) == 0 || Integer.parseInt(settings[16]) == 1) {
				colourAndEffectSettings.setWhite(Integer.parseInt(settings[16]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "White, ";
			}
			//Cyan
			if (Integer.parseInt(settings[17]) == 0 || Integer.parseInt(settings[17]) == 1) {
				colourAndEffectSettings.setCyan(Integer.parseInt(settings[17]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "Cyan, ";
			}
			//Flash1
			if (Integer.parseInt(settings[18]) == 0 || Integer.parseInt(settings[18]) == 1) {
				colourAndEffectSettings.setFlash1(Integer.parseInt(settings[18]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "Flash1, ";
			}
			//Flash2
			if (Integer.parseInt(settings[19]) == 0 || Integer.parseInt(settings[19]) == 1) {
				colourAndEffectSettings.setFlash2(Integer.parseInt(settings[19]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "Flash2, ";
			}
			//Flash3
			if (Integer.parseInt(settings[20]) == 0 || Integer.parseInt(settings[20]) == 1) {
				colourAndEffectSettings.setFlash3(Integer.parseInt(settings[20]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "Flash3, ";
			}
			//Glow1
			if (Integer.parseInt(settings[21]) == 0 || Integer.parseInt(settings[21]) == 1) {
				colourAndEffectSettings.setGlow1(Integer.parseInt(settings[21]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "Glow1, ";
			}
			//Glow2
			if (Integer.parseInt(settings[22]) == 0 || Integer.parseInt(settings[22]) == 1) {
				colourAndEffectSettings.setGlow2(Integer.parseInt(settings[22]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "Glow2, ";
			}
			//Glow3
			if (Integer.parseInt(settings[23]) == 0 || Integer.parseInt(settings[23]) == 1) {
				colourAndEffectSettings.setGlow3(Integer.parseInt(settings[23]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "Glow3, ";
			}
			//Wave
			if (Integer.parseInt(settings[24]) == 0 || Integer.parseInt(settings[24]) == 1) {
				colourAndEffectSettings.setWave(Integer.parseInt(settings[24]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "Wave, ";
			}
			//Wave2
			if (Integer.parseInt(settings[25]) == 0 || Integer.parseInt(settings[25]) == 1) {
				colourAndEffectSettings.setWave2(Integer.parseInt(settings[25]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "Wave2, ";
			}
			//Scroll
			if (Integer.parseInt(settings[26]) == 0 || Integer.parseInt(settings[26]) == 1) {
				colourAndEffectSettings.setScroll(Integer.parseInt(settings[26]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "Scroll, ";
			}
			//Slide
			if (Integer.parseInt(settings[27]) == 0 || Integer.parseInt(settings[27]) == 1) {
				colourAndEffectSettings.setSlide(Integer.parseInt(settings[27]));
			}
			else {
				errorFlag = true;
				colourAndEffectCount++;
				configErrorMessage += "Slide, ";
			}
			//Shake
			if (Integer.parseInt(settings[28]) == 0 || Integer.parseInt(settings[28]) == 1) {
				colourAndEffectSettings.setShake(Integer.parseInt(settings[28]));
			}
			else {
				errorFlag = true;
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
			boolean communityLineStatus = false;
			for(int i = noHostLine; i < settings.length; i++) {
				if (settings[i].equals("#ADVERTISING_LINES")) {
					communityLineStatus = true;
					communityLine = i + 1;
					break;
				}
				else if (i == noHostLine) {
					noHostMessage = settings[i];
				}
			}
			//Reads and initialises the Community Advertisements lines
			boolean boneJobLineStatus = false;
			if (communityLineStatus == true) {
				for(int i = communityLine; i < settings.length; i++) {
					if (settings[i].equals("#BONE_JOB_ADVERTISING_LINES")) {
						boneJobLineStatus = true;
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
			if (boneJobLineStatus == true) {
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
			if (errorFlag == true) {
				Settings.configErrorMessage += "\nBut any other changes you made have now been applied. What would you like to do?";
				Config.configValidationErrorMessage();
			}
		}
		catch (Exception e) {
			Config.configErrorMessage();
		}
	}

	public static void initialiseShiftCases() { //initialises all types of characters for the typing bot
		char[] shiftC = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '{', '}', '|', ':', '<', '>', '?', '+', '"'};
		shiftCases = new ArrayList<Character>();
		for (char c : shiftC) {
			shiftCases.add(c);
		}
		mapStrokes = new HashMap<>();
		mapStrokes.put('a', KeyEvent.VK_A);
		mapStrokes.put('b',  KeyEvent.VK_B);
		mapStrokes.put('c',  KeyEvent.VK_C);
		mapStrokes.put('d',  KeyEvent.VK_D);
		mapStrokes.put('e',  KeyEvent.VK_E);
		mapStrokes.put('f',  KeyEvent.VK_F);
		mapStrokes.put('g',  KeyEvent.VK_G);
		mapStrokes.put('h',  KeyEvent.VK_H);
		mapStrokes.put('i',  KeyEvent.VK_I);
		mapStrokes.put('j',  KeyEvent.VK_J);
		mapStrokes.put('k',  KeyEvent.VK_K);
		mapStrokes.put('l',  KeyEvent.VK_L);
		mapStrokes.put('m',  KeyEvent.VK_M);
		mapStrokes.put('n',  KeyEvent.VK_N);
		mapStrokes.put('o',  KeyEvent.VK_O);
		mapStrokes.put('p',  KeyEvent.VK_P);
		mapStrokes.put('q',  KeyEvent.VK_Q);
		mapStrokes.put('r',  KeyEvent.VK_R);
		mapStrokes.put('s',  KeyEvent.VK_S);
		mapStrokes.put('t',  KeyEvent.VK_T);
		mapStrokes.put('u',  KeyEvent.VK_U);
		mapStrokes.put('v',  KeyEvent.VK_V);
		mapStrokes.put('w',  KeyEvent.VK_W);
		mapStrokes.put('x',  KeyEvent.VK_X);
		mapStrokes.put('y',  KeyEvent.VK_Y);
		mapStrokes.put('z',  KeyEvent.VK_Z);
		mapStrokes.put('`', KeyEvent.VK_BACK_QUOTE);
		mapStrokes.put('0', KeyEvent.VK_0);
		mapStrokes.put('1', KeyEvent.VK_1);
		mapStrokes.put('2', KeyEvent.VK_2);
		mapStrokes.put('3', KeyEvent.VK_3);
		mapStrokes.put('4', KeyEvent.VK_4);
		mapStrokes.put('5', KeyEvent.VK_5);
		mapStrokes.put('6', KeyEvent.VK_6);
		mapStrokes.put('7', KeyEvent.VK_7);
		mapStrokes.put('8', KeyEvent.VK_8);
		mapStrokes.put('9', KeyEvent.VK_9);
		mapStrokes.put('-', KeyEvent.VK_MINUS);
		mapStrokes.put('=', KeyEvent.VK_EQUALS);
		mapStrokes.put('\t', KeyEvent.VK_TAB);
		mapStrokes.put('\n', KeyEvent.VK_ENTER);
		mapStrokes.put('[', KeyEvent.VK_OPEN_BRACKET);
		mapStrokes.put(']', KeyEvent.VK_CLOSE_BRACKET);
		mapStrokes.put('\\', KeyEvent.VK_BACK_SLASH);
		mapStrokes.put(';', KeyEvent.VK_SEMICOLON);
		mapStrokes.put('\'', KeyEvent.VK_QUOTE);
		mapStrokes.put('\"', KeyEvent.VK_QUOTEDBL);
		mapStrokes.put(',', KeyEvent.VK_COMMA);
		mapStrokes.put('.', KeyEvent.VK_PERIOD);
		mapStrokes.put('/', KeyEvent.VK_SLASH);
		mapStrokes.put(' ', KeyEvent.VK_SPACE);
		//All the symbols that require shift are below
		mapStrokes.put('~', KeyEvent.VK_BACK_QUOTE);
		mapStrokes.put('!', KeyEvent.VK_1);
		mapStrokes.put('@', KeyEvent.VK_2);
		mapStrokes.put('#', KeyEvent.VK_3);
		mapStrokes.put('$', KeyEvent.VK_4);
		mapStrokes.put('%', KeyEvent.VK_5);
		mapStrokes.put('^', KeyEvent.VK_6);
		mapStrokes.put('&', KeyEvent.VK_7);
		mapStrokes.put('*', KeyEvent.VK_8);
		mapStrokes.put('(', KeyEvent.VK_9);
		mapStrokes.put(')', KeyEvent.VK_0);
		mapStrokes.put('_', KeyEvent.VK_MINUS);
		mapStrokes.put('{', KeyEvent.VK_OPEN_BRACKET);
		mapStrokes.put('}', KeyEvent.VK_CLOSE_BRACKET);
		mapStrokes.put('|', KeyEvent.VK_BACK_SLASH);
		mapStrokes.put(':', KeyEvent.VK_SEMICOLON);
		mapStrokes.put('<', KeyEvent.VK_COMMA);
		mapStrokes.put('>', KeyEvent.VK_PERIOD);
		mapStrokes.put('?', KeyEvent.VK_SLASH);
		mapStrokes.put('+', KeyEvent.VK_EQUALS);
		mapStrokes.put('"', KeyEvent.VK_QUOTE);
		//-----------------
	}
}
