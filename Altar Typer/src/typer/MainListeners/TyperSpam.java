package typer.MainListeners;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

import typer.PanelListeners.AdvertisementsListener;
import typer.Panels.CentralPanel;
import typer.Source.Colour;
import typer.Source.Effect;
import typer.Source.Host;
import typer.Tools.Settings;

public class TyperSpam {

	static int arraySize = 5;
	static String[] currentLineString = new String[arraySize];
	static int previousSpamIndex = -1;

	static String[] getHostSpam(int arraySize) { //Starts grabbing the host data once the typer has started
		ArrayList<Host> hostList = new ArrayList<Host>();
		if (HostUpdaterThread.timerEnd == false) { //When the host table has data available
			hostList = Settings.overrideList;
			for (Host h : Settings.overrideList) {
				if (h.getUsername().equals("null")) {
					hostList = Settings.allHosts;
				}
			}
		}
		else { //After the timer hits 0, it grabs the backed up host data while it is updating the host table
			hostList = Settings.backUpOverrideList;
			for (Host h : Settings.backUpOverrideList) {
				if (h.getUsername().equals("null")) {
					hostList = Settings.backUpAllHosts;
				}
			}
		}
		String locationAndServer = CentralPanel.serverType.getSelectedItem().toString().replace(" - ", " ");
		String[] locationAndServerSubData = locationAndServer.split(" ");
		String[] hostSpam = new String[arraySize];
		String tempLine = "";
		String tempLine2 = "";
		for (Host h : hostList) {
			//Grabs the blacklist or backed up blacklist data depending on the status of the timer	  
			String username = h.getUsername().toLowerCase().replace("*", "");
			if (HostUpdaterThread.timerEnd == false) {
				if (Settings.blackList.contains(username)) {
					continue;
				}
			}
			else {
				if (Settings.backUpBlackList.contains(username)) {
					continue;
				}
			}
			//Checks if the Only Verified Hosts check box is selected and organises the data accordingly
			if (CentralPanel.onlyVerifiedHosts.isSelected()) {
				if (h.getServer().equals(locationAndServerSubData[0]) && h.getWorld().equals(locationAndServerSubData[1]) && h.getLocation().equals(locationAndServerSubData[2])) {
					if (!h.getUsername().contains("*")) {
						if (!h.getUsername().matches("^[ ]{1,}$")) {
							tempLine += "\"" + h.getUsername() + "\"";
						}
					}
				}
			} 
			else {
				if (h.getServer().equals(locationAndServerSubData[0]) && h.getWorld().equals(locationAndServerSubData[1]) && h.getLocation().equals(locationAndServerSubData[2])) {
					if (!h.getUsername().contains("*")) {
						if (!h.getUsername().matches("^[ ]{1,}$")) {
							tempLine += "\"" + h.getUsername() + "\"";
						}
					}
					else {
						if (!h.getUsername().matches("^\\*[ ]{1,}$")) {
							tempLine2 += "\"" + h.getUsername() + "\"";
						}
					}
				}
			}
		}
		String startOfLine = "";
		//Checks if the Server dropdown menu starts with RS3 to output the correct string
		if (CentralPanel.serverType.getSelectedItem().toString().startsWith("RS3")) {
			startOfLine = useSelectedColour() + useSelectedEffect() + "Altar Houses: ";
		}
		else {
			startOfLine = useSelectedColour() + useSelectedEffect() + "07 Altar Houses: ";
		}
		//Checks if the Only Verified Hosts check box is not selected and organises the data accordingly
		if (!CentralPanel.onlyVerifiedHosts.isSelected()) {
			tempLine += tempLine2;
		}
		//Removes any unnecessary strings in the host display names
		tempLine = tempLine.replace("*", "");
		String[] hosts = tempLine.split(Pattern.quote("\"\""));
		for (int i = 0; i < hosts.length; i++) {
			hosts[i] = hosts[i].replace("\"\"", "");
			hosts[i] = hosts[i].replace("\"", "");
		}
		//Supports up to 5 lines of hosts in-game, a max of 80 characters per line
		String line1 = "";
		String line2 = "";
		String line3 = "";
		String line4 = "";
		String line5 = "";
		String originalStartOfLine = "";
		for (int i = 0; i < hosts.length; i++) {
			hosts[i] = "[ " + hosts[i] + " ]";
			line1 += hosts[i];
			line2 += hosts[i];
			line3 += hosts[i];
			line4 += hosts[i];
			line5 += hosts[i];
			if (i == hosts.length - 1) { //Adds the colours and/or effects at the start of each applicable line
				line1 = startOfLine + line1;
				line2 = startOfLine + line2;
				line3 = startOfLine + line3;
				line4 = startOfLine + line4;
				line5 = startOfLine + line5;
				originalStartOfLine = startOfLine;
			}
		}
		int hostLineCount = 0;
		boolean line2Execution = false; 
		boolean line3Execution = false; 
		boolean line4Execution = false; 
		boolean line5Execution = false;
		//Checks the total amount of characters to output the correct amount of lines
		if (line1.length() <= 80) {
			hostLineCount = 1;
			line1 = line1.substring(0, line1.length());
			line1 = line1.substring(0, line1.lastIndexOf("]") + 1);
		}
		else if (line1.length() > 80 && line1.length() <= 160) {
			line2Execution = true;
			hostLineCount = 2;
			line1 = line1.substring(0, 79);
			line1 = line1.substring(0, line1.lastIndexOf("]") + 1);
			line2 = line2.substring(line1.lastIndexOf("]") + 1, line2.length());
			line2 = line2.substring(0, line2.lastIndexOf("]") + 1);
			line2 = originalStartOfLine + line2;
		}
		else if (line1.length() > 160 && line1.length() <= 240) {
			line2Execution = true;
			line3Execution = true;
			hostLineCount = 3;
			line1 = line1.substring(0, 79);
			line1 = line1.substring(0, line1.lastIndexOf("]") + 1);
			line2 = line2.substring(line1.lastIndexOf("]") + 1, line2.length());
			line2 = line2.substring(0, line2.lastIndexOf("]") + 1);
			line2 = originalStartOfLine + line2;
			line3 = line3.substring(line2.lastIndexOf("]") + 1, line3.length());
			line3 = line3.substring(0, line3.lastIndexOf("]") + 1);
			line3 = originalStartOfLine + line3;
		}
		else if (line1.length() > 240 && line1.length() <= 320) {
			line2Execution = true;
			line3Execution = true;
			line4Execution = true;
			hostLineCount = 4;
			line1 = line1.substring(0, 79);
			line1 = line1.substring(0, line1.lastIndexOf("]") + 1);
			line2 = line2.substring(line1.lastIndexOf("]") + 1, line2.length());
			line2 = line2.substring(0, line2.lastIndexOf("]") + 1);
			line2 = originalStartOfLine + line2;
			line3 = line3.substring(line2.lastIndexOf("]") + 1, line3.length());
			line3 = line3.substring(0, line3.lastIndexOf("]") + 1);
			line3 = originalStartOfLine + line3;
			line4 = line4.substring(line3.lastIndexOf("]") + 1, line4.length());
			line4 = line4.substring(0, line4.lastIndexOf("]") + 1);
			line4 = originalStartOfLine + line4;	
		}
		else if (line1.length() > 320 && line1.length() <= 400) {
			line2Execution = true;
			line3Execution = true;
			line4Execution = true;
			line5Execution = true;
			hostLineCount = 5;
			line1 = line1.substring(0, 79);
			line1 = line1.substring(0, line1.lastIndexOf("]") + 1);
			line2 = line2.substring(line1.lastIndexOf("]") + 1, line2.length());
			line2 = line2.substring(0, line2.lastIndexOf("]") + 1);
			line2 = originalStartOfLine + line2;
			line3 = line3.substring(line2.lastIndexOf("]") + 1, line3.length());
			line3 = line3.substring(0, line3.lastIndexOf("]") + 1);
			line3 = originalStartOfLine + line3;
			line4 = line4.substring(line3.lastIndexOf("]") + 1, line4.length());
			line4 = line4.substring(0, line4.lastIndexOf("]") + 1);
			line4 = originalStartOfLine + line4;
			line5 = line5.substring(line4.lastIndexOf("]") + 1, line5.length());
			line5 = line5.substring(0, line5.lastIndexOf("]") + 1);
			line5 = originalStartOfLine + line5;
		}
		if (line2Execution == false && line3Execution == false && line4Execution == false && line5Execution == false) {
			line2 = "";
			line3 = "";
			line4 = "";
			line5 = "";
		}
		else if (line3Execution == false && line4Execution == false && line5Execution == false) {
			line3 = "";
			line4 = "";
			line5 = "";
		}
		else if (line4Execution == false && line5Execution == false) {
			line4 = "";
			line5 = "";
		}
		else if (line5Execution == false) {
			line5 = "";
		}
		/*After outputting the host lines x amount of times depending on the configuration file, it checks if the Community Advertisements
		 *and/or Bone Job Advertisements check boxes are selected to then output those advertisement lines.
		 */
		if ((hostLineCount * Settings.adjustTyperSettings.getHostCount()) == TyperThread.enterCount) {
			if (CentralPanel.onlyCommunityAdvertisements.isSelected() || CentralPanel.onlyBoneJobAdvertisements.isSelected()) {
				return getAdvertisements();
			}
			TyperThread.enterCount = -1;
		}
		//If Only Verified Hosts check box is selected but the host line is empty
		if (CentralPanel.onlyVerifiedHosts.isSelected() && tempLine.equals("")) {
			return noHost();
		}
		//If Only Verified Hosts check box is not selected but the host line is empty
		else if (!CentralPanel.onlyVerifiedHosts.isSelected() && (tempLine.equals("") && tempLine2.equals(""))) {
			return noHost();
		}
		hostSpam[0] = line1;
		hostSpam[1] = line2;
		hostSpam[2] = line3;
		hostSpam[3] = line4;
		hostSpam[4] = line5;
		return hostSpam;
	}

	static String[] getAdvertisements() {
		/*Checks if the Community Advertisements and/or Bone Job Advertisements check boxes are selected to then grab the appropriate
		 *advertisement data from the configuration file
		 */
		if (CentralPanel.onlyCommunityAdvertisements.isSelected() && CentralPanel.onlyBoneJobAdvertisements.isSelected()) {
			String[] communitySpamTexts = AdvertisementsListener.communityAdverts.getText().split("\n");
			String[] boneJobSpamTexts = AdvertisementsListener.boneJobAdverts.getText().split("\n");
			int communitySpamTextsLength = communitySpamTexts.length;
			int boneJobSpamTextsLength = boneJobSpamTexts.length;
			String[] spamTextsCombined = new String[communitySpamTextsLength + boneJobSpamTextsLength];
			System.arraycopy(communitySpamTexts, 0, spamTextsCombined, 0, communitySpamTextsLength);
			System.arraycopy(boneJobSpamTexts, 0, spamTextsCombined, communitySpamTextsLength, boneJobSpamTextsLength);
			int randomSpamIndex = getRandom(0, spamTextsCombined.length - 1);
			while (randomSpamIndex == previousSpamIndex && spamTextsCombined.length > 1) {
				randomSpamIndex = getRandom(0, spamTextsCombined.length - 1);
			}
			previousSpamIndex = randomSpamIndex;
			currentLineString[0] = spamTextsCombined[randomSpamIndex];
			for (int i = 1; i < arraySize; i++) {
				currentLineString[i] = "";
			}
		}
		else if (CentralPanel.onlyCommunityAdvertisements.isSelected()) {
			String[] communitySpamTexts = AdvertisementsListener.communityAdverts.getText().split("\n");
			int randomSpamIndex = getRandom(0, communitySpamTexts.length - 1);
			while (randomSpamIndex == previousSpamIndex && communitySpamTexts.length > 1) {
				randomSpamIndex = getRandom(0, communitySpamTexts.length - 1);
			}
			previousSpamIndex = randomSpamIndex;
			currentLineString[0] = communitySpamTexts[randomSpamIndex];
			for (int i = 1; i < arraySize; i++) {
				currentLineString[i] = "";
			}
		}
		else if (CentralPanel.onlyBoneJobAdvertisements.isSelected()) {
			String[] boneJobSpamTexts = AdvertisementsListener.boneJobAdverts.getText().split("\n");
			int randomSpamIndex = getRandom(0, boneJobSpamTexts.length - 1);
			while (randomSpamIndex == previousSpamIndex && boneJobSpamTexts.length > 1) {
				randomSpamIndex = getRandom(0, boneJobSpamTexts.length - 1);
			}
			previousSpamIndex = randomSpamIndex;
			currentLineString[0] = boneJobSpamTexts[randomSpamIndex];
			for (int i = 1; i < arraySize; i++) {
				currentLineString[i] = "";
			}
		}
		TyperThread.enterCount = -1;
		return currentLineString;
	}

	private static String[] noHost() {
		/*If there are no hosts and/or Advertise Tracker Hosts check box is not selected then it would output the no host line from the
		 *configuration file
		 */
		String[] noHost = AdvertisementsListener.noHostAdvert.getText().split("\n");
		currentLineString[0] = noHost[0];
		for (int i = 1; i < arraySize; i++) {
			currentLineString[i] = "";
		}
		return currentLineString;
	}

	private static String useSelectedColour() {
		//Checks which colours check boxes are selected to then include that data when it is typing in-game
		ArrayList<Colour> allColours = new ArrayList<Colour>();
		String colourTempLine = "";
		if (CentralPanel.yellow.isSelected()) {
			allColours.add(new Colour(""));
		}
		if (CentralPanel.purple.isSelected()) {
			allColours.add(new Colour("purple:"));
		}
		if (CentralPanel.red.isSelected()) {
			allColours.add(new Colour("red:"));
		}
		if (CentralPanel.green.isSelected()) {
			allColours.add(new Colour("green:"));
		}
		if (CentralPanel.white.isSelected()) {
			allColours.add(new Colour("white:"));
		}
		if (CentralPanel.cyan.isSelected()) {
			allColours.add(new Colour("cyan:"));
		}
		if (CentralPanel.flash1.isSelected()) {
			allColours.add(new Colour("flash1:"));
		}
		if (CentralPanel.flash2.isSelected()) {
			allColours.add(new Colour("flash2:"));
		}
		if (CentralPanel.flash3.isSelected()) {
			allColours.add(new Colour("flash3:"));
		}
		if (CentralPanel.glow1.isSelected()) {
			allColours.add(new Colour("glow1:"));
		}
		if (CentralPanel.glow2.isSelected()) {
			allColours.add(new Colour("glow2:"));
		}
		if (CentralPanel.glow3.isSelected()) {
			allColours.add(new Colour("glow3:"));
		}
		if ((allColours.size() == 0) || (allColours.size() == 1 && CentralPanel.yellow.isSelected())) {
			return "";
		}
		for (Colour c : allColours) {
			colourTempLine += "\"" + c.getColour() + "\"";
		}
		String[] colours = colourTempLine.split(Pattern.quote("\"\""));
		for (int i = 0; i < colours.length; i++) {
			colours[i] = colours[i].replace("\"\"", "");
			colours[i] = colours[i].replace("\"", "");
		}
		int randomIndex = new Random().nextInt(colours.length);
		return colours[randomIndex];
	}

	private static String useSelectedEffect() {
		//Checks which effects check boxes are selected to then include that data when it is typing in-game
		ArrayList<Effect> allEffects = new ArrayList<Effect>();
		String effectTempLine = "";
		if (CentralPanel.wave.isSelected()) {
			allEffects.add(new Effect("wave:"));
		}
		if (CentralPanel.wave2.isSelected()) {
			allEffects.add(new Effect("wave2:"));
		}
		if (CentralPanel.scroll.isSelected()) {
			allEffects.add(new Effect("scroll:"));
		}
		if (CentralPanel.slide.isSelected()) {
			allEffects.add(new Effect("slide:"));
		}
		if (CentralPanel.shake.isSelected()) {
			allEffects.add(new Effect("shake:"));
		}
		if (allEffects.size() == 0) {
			return "";
		}
		for (Effect e : allEffects) {
			effectTempLine += "\"" + e.getEffect() + "\"";
		}
		String[] effects = effectTempLine.split(Pattern.quote("\"\""));
		for (int i = 0; i < effects.length; i++) {
			effects[i] = effects[i].replace("\"\"", "");
			effects[i] = effects[i].replace("\"", "");
		}
		int randomIndex = new Random().nextInt(effects.length);
		return effects[randomIndex];
	}

	public static int getRandom(int smallestWait, int biggestWait) {
		//Generates a random number for colours, effects and advertisements to output different data when it is typing in-game
		return new Random().nextInt(biggestWait - smallestWait + 1) + smallestWait;
	}
}