package typer;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

import actionListeners.AdvertisementsListener;
import data.Colour;
import data.Effect;
import data.Host;
import panels.CentralPanel;
import source.StartupSettings;
import source.Website;

final class TyperSpam {

	protected final int arraySize = 5;
	protected String[] currentLineString = new String[arraySize];
	protected int previousSpamIndex = -1;

	protected String[] getHostSpam(int arraySize) {
		// Starts grabbing the host data once the typer has started
		ArrayList<Host> hostList = new ArrayList<Host>();
		if (HostUpdaterThread.timerEnd == false) {
			// When the host table has data available
			hostList = Website.overrideList;
			for (Host h : Website.overrideList) {
				if (h.getUsername().equals("null")) {
					hostList = Website.allHosts;
				}
			}
		}
		else {
			// Grabs the backed up host data while it is updating the host table
			hostList = Website.backUpOverrideList;
			for (Host h : Website.backUpOverrideList) {
				if (h.getUsername().equals("null")) {
					hostList = Website.backUpAllHosts;
				}
			}
		}
		String locationAndServer = CentralPanel.serverType.getSelectedItem().toString().replace(" - ", " ");
		String[] locationAndServerSubData = locationAndServer.split(" ");
		String tempLine = "";
		String tempLine2 = "";
		for (Host h : hostList) {
			// Grabs the blacklist or backed up blacklist data depending on the timer
			String username = h.getUsername().toLowerCase().replace("*", "");
			if (HostUpdaterThread.timerEnd == false) {
				if (Website.blackList.contains(username)) {
					continue;
				}
			}
			else if (Website.backUpBlackList.contains(username)) {
				continue;
			}
			// Organises and formats the host data
			if (CentralPanel.onlyVerifiedHosts.isSelected()) {
				if (h.getServer().equals(locationAndServerSubData[0]) && h.getWorld().equals(locationAndServerSubData[1])
						&& h.getLocation().equals(locationAndServerSubData[2])) {
					if (!h.getUsername().contains("*") && !h.getUsername().matches("^[ ]{1,}$")) {
						tempLine += "\"" + h.getUsername() + "\"";
					}
				}
			}
			else {
				if (h.getServer().equals(locationAndServerSubData[0]) && h.getWorld().equals(locationAndServerSubData[1])
						&& h.getLocation().equals(locationAndServerSubData[2])) {
					if (!h.getUsername().contains("*") && !h.getUsername().matches("^[ ]{1,}$")) {
						tempLine += "\"" + h.getUsername() + "\"";
					}
					else if (!h.getUsername().matches("^\\*[ ]{1,}$")) {
						tempLine2 += "\"" + h.getUsername() + "\"";
					}
				}
			}
		}
		String startOfLine = "";
		// Checks if the Server dropdown menu starts with RS3 to output correctly
		if (CentralPanel.serverType.getSelectedItem().toString().startsWith("RS3")) {
			startOfLine = useSelectedColour() + useSelectedEffect() + "Altar Houses: ";
		}
		else {
			startOfLine = useSelectedColour() + useSelectedEffect() + "07 Altar Houses: ";
		}
		// Adds the verified and non verified hosts to the same string/line
		if (!CentralPanel.onlyVerifiedHosts.isSelected()) {
			tempLine += tempLine2;
		}
		// Removes any unnecessary strings in the host display names
		tempLine = tempLine.replace("*", "");
		String[] hosts = tempLine.split(Pattern.quote("\"\""));
		for (int i = 0; i < hosts.length; i++) {
			hosts[i] = hosts[i].replace("\"\"", "").replace("\"", "");
		}
		// Supports up to 5 lines of hosts in-game, a max of 80 characters per line
		String firstLine = "", secondLine = "", thirdLine = "", fourthLine = "", fifthLine = "";
		String initialStartOfLine = "";
		for (int i = 0; i < hosts.length; i++) {
			hosts[i] = "[ " + hosts[i] + " ]";
			firstLine += hosts[i];
			secondLine += hosts[i];
			thirdLine += hosts[i];
			fourthLine += hosts[i];
			fifthLine += hosts[i];
			if (i == hosts.length - 1) { // Adds the colours and/or effects at the start of each applicable line
				firstLine = startOfLine + firstLine;
				secondLine = startOfLine + secondLine;
				thirdLine = startOfLine + thirdLine;
				fourthLine = startOfLine + fourthLine;
				fifthLine = startOfLine + fifthLine;
				initialStartOfLine = startOfLine;
			}
		}
		int hostLineCount = 0;
		boolean secondLineExecution = false, thirdLineExecution = false, fourthLineExecution = false,
				line5Execution = false;
		// Checks the total amount of characters to output the correct amount of lines
		if (firstLine.length() <= 80) {
			hostLineCount = 1;
			firstLine = firstLine.substring(0, firstLine.length());
			firstLine = firstLine.substring(0, firstLine.lastIndexOf("]") + 1);
		}
		else if (firstLine.length() > 80 && firstLine.length() <= 160) {
			secondLineExecution = true;
			hostLineCount = 2;
			firstLine = firstLine.substring(0, 79);
			firstLine = firstLine.substring(0, firstLine.lastIndexOf("]") + 1);
			secondLine = secondLine.substring(firstLine.lastIndexOf("]") + 1, secondLine.length());
			secondLine = secondLine.substring(0, secondLine.lastIndexOf("]") + 1);
			secondLine = initialStartOfLine + secondLine;
		}
		else if (firstLine.length() > 160 && firstLine.length() <= 240) {
			secondLineExecution = true;
			thirdLineExecution = true;
			hostLineCount = 3;
			firstLine = firstLine.substring(0, 79);
			firstLine = firstLine.substring(0, firstLine.lastIndexOf("]") + 1);
			secondLine = secondLine.substring(firstLine.lastIndexOf("]") + 1, secondLine.length());
			secondLine = secondLine.substring(0, secondLine.lastIndexOf("]") + 1);
			secondLine = initialStartOfLine + secondLine;
			thirdLine = thirdLine.substring(secondLine.lastIndexOf("]") + 1, thirdLine.length());
			thirdLine = thirdLine.substring(0, thirdLine.lastIndexOf("]") + 1);
			thirdLine = initialStartOfLine + thirdLine;
		}
		else if (firstLine.length() > 240 && firstLine.length() <= 320) {
			secondLineExecution = true;
			thirdLineExecution = true;
			fourthLineExecution = true;
			hostLineCount = 4;
			firstLine = firstLine.substring(0, 79);
			firstLine = firstLine.substring(0, firstLine.lastIndexOf("]") + 1);
			secondLine = secondLine.substring(firstLine.lastIndexOf("]") + 1, secondLine.length());
			secondLine = secondLine.substring(0, secondLine.lastIndexOf("]") + 1);
			secondLine = initialStartOfLine + secondLine;
			thirdLine = thirdLine.substring(secondLine.lastIndexOf("]") + 1, thirdLine.length());
			thirdLine = thirdLine.substring(0, thirdLine.lastIndexOf("]") + 1);
			thirdLine = initialStartOfLine + thirdLine;
			fourthLine = fourthLine.substring(thirdLine.lastIndexOf("]") + 1, fourthLine.length());
			fourthLine = fourthLine.substring(0, fourthLine.lastIndexOf("]") + 1);
			fourthLine = initialStartOfLine + fourthLine;
		}
		else if (firstLine.length() > 320 && firstLine.length() <= 400) {
			secondLineExecution = true;
			thirdLineExecution = true;
			fourthLineExecution = true;
			line5Execution = true;
			hostLineCount = 5;
			firstLine = firstLine.substring(0, 79);
			firstLine = firstLine.substring(0, firstLine.lastIndexOf("]") + 1);
			secondLine = secondLine.substring(firstLine.lastIndexOf("]") + 1, secondLine.length());
			secondLine = secondLine.substring(0, secondLine.lastIndexOf("]") + 1);
			secondLine = initialStartOfLine + secondLine;
			thirdLine = thirdLine.substring(secondLine.lastIndexOf("]") + 1, thirdLine.length());
			thirdLine = thirdLine.substring(0, thirdLine.lastIndexOf("]") + 1);
			thirdLine = initialStartOfLine + thirdLine;
			fourthLine = fourthLine.substring(thirdLine.lastIndexOf("]") + 1, fourthLine.length());
			fourthLine = fourthLine.substring(0, fourthLine.lastIndexOf("]") + 1);
			fourthLine = initialStartOfLine + fourthLine;
			fifthLine = fifthLine.substring(fourthLine.lastIndexOf("]") + 1, fifthLine.length());
			fifthLine = fifthLine.substring(0, fifthLine.lastIndexOf("]") + 1);
			fifthLine = initialStartOfLine + fifthLine;
		}
		if (secondLineExecution == false && thirdLineExecution == false && fourthLineExecution == false
				&& line5Execution == false) {
			secondLine = "";
			thirdLine = "";
			fourthLine = "";
			fifthLine = "";
		}
		else if (thirdLineExecution == false && fourthLineExecution == false && line5Execution == false) {
			thirdLine = "";
			fourthLine = "";
			fifthLine = "";
		}
		else if (fourthLineExecution == false && line5Execution == false) {
			fourthLine = "";
			fifthLine = "";
		}
		else if (line5Execution == false) {
			fifthLine = "";
		}
		/*
		 * After outputting the host lines x amount of times depending on the
		 * configuration file, it checks if the Community Advertisements and/or Bone Job
		 * Advertisements check boxes are selected to then output those advertisement
		 * lines.
		 */
		if ((hostLineCount * StartupSettings.adjustTyperSettings.getHostCount()) == TyperThread.enterCount) {
			if (CentralPanel.onlyCommunityAdvertisements.isSelected()
					|| CentralPanel.onlyBoneJobAdvertisements.isSelected()) {
				return getAdvertisements();
			}
			TyperThread.enterCount = -1;
		}
		// If Only Verified Hosts check box is selected but the host line is empty
		if (CentralPanel.onlyVerifiedHosts.isSelected() && tempLine.equals("")) {
			return noHost();
		}
		// If Only Verified Hosts check box is not selected but the host line is empty
		else if (!CentralPanel.onlyVerifiedHosts.isSelected() && (tempLine.equals("") && tempLine2.equals(""))) {
			return noHost();
		}
		String[] hostSpam = new String[arraySize];
		hostSpam[0] = firstLine;
		hostSpam[1] = secondLine;
		hostSpam[2] = thirdLine;
		hostSpam[3] = fourthLine;
		hostSpam[4] = fifthLine;
		return hostSpam;
	}

	protected String[] getAdvertisements() {
		/*
		 * Checks if the Community Advertisements and/or Bone Job Advertisements check
		 * boxes are selected to then grab the appropriate advertisement data from the
		 * configuration file
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

	private String[] noHost() {
		/*
		 * If there are no hosts and/or Advertise Tracker Hosts check box is not
		 * selected then it would output the no host line from the configuration file
		 */
		String[] noHost = AdvertisementsListener.noHostAdvert.getText().split("\n");
		currentLineString[0] = noHost[0];
		for (int i = 1; i < arraySize; i++) {
			currentLineString[i] = "";
		}
		return currentLineString;
	}

	private String useSelectedColour() {
		// Checks which colours check boxes are selected to then include that data when
		// it is typing in-game
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

	private String useSelectedEffect() {
		// Checks which effects check boxes are selected to then include that data when
		// it is typing in-game
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

	protected int getRandom(int smallestWait, int biggestWait) {
		// Generates a random number for colours, effects and advertisements to output
		// different data when it is typing in-game
		return new Random().nextInt(biggestWait - smallestWait + 1) + smallestWait;
	}
}