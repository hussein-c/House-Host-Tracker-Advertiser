package source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import data.Host;
import panels.MainFrame;

public class Website {

	private BufferedReader dataReader;
	private String blackListWebsiteData;
	private String overrideListWebsiteData;
	private String hostListWebsiteData;
	public static ArrayList<String> blackList;
	public static ArrayList<Host> overrideList;
	public static ArrayList<Host> allHosts;
	public static ArrayList<String> backUpBlackList;
	public static ArrayList<Host> backUpOverrideList;
	public static ArrayList<Host> backUpAllHosts;

	public Website() { //Initialises and grabs all the host data from the web pages
		String status = "Try again";
		int tryCount = 0;
		blackList = new ArrayList<String>();
		overrideList = new ArrayList<Host>();
		allHosts = new ArrayList<Host>();
		while (status.equals("Try again")) {
			try {
				readBlackList();
				readOverrideList();
				readHostList();
				status = "Stop";
			} catch (IOException e) {
				try {
					tryCount++;
					Thread.sleep(1500);
					MainFrame.updateStatus("Attempting to reconnect... Connection attempts: " + tryCount + " ");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}                                   
		}
	}

	private void readBlackList() throws ConnectException, IOException { //Connects to the Black List URL and reads the data
		final String blackListLink = "https://www.altar.rs/HHT/typerblacklist.txt";
		URL blackListUrl = new URL(blackListLink);
		URLConnection blackListUrlConnection = blackListUrl.openConnection();
		dataReader = new BufferedReader(new InputStreamReader(blackListUrlConnection.getInputStream()));
		String blackListLine;
		while ((blackListLine = dataReader.readLine()) != null) {
			blackListWebsiteData += " " + blackListLine;
			if (blackListWebsiteData.contains("#End_Block")) {
				break;
			}
		}
		dataReader.close();
	}

	private void readOverrideList() throws ConnectException, IOException { //Connects to the Override List URL and reads the data
		final String overrideListLink = "https://www.altar.rs/HHT/typeroverridelist.txt";
		URL overrideListUrl = new URL(overrideListLink);
		URLConnection overrideListUrlConnection = overrideListUrl.openConnection();
		dataReader = new BufferedReader(new InputStreamReader(overrideListUrlConnection.getInputStream()));
		String overrideListLine;
		while ((overrideListLine = dataReader.readLine()) != null) {
			overrideListWebsiteData += " " + overrideListLine;
			if (overrideListWebsiteData.contains("#End_Override_Host_List")) {
				break;
			}
		}
		dataReader.close();
	}

	private void readHostList() throws ConnectException, IOException { //Connects to the Host List URL and reads the data
		final String hostListLink = "https://www.altar.rs/altartracker.txt";
		URL hostListUrl = new URL(hostListLink);
		URLConnection hostListUrlConnection = hostListUrl.openConnection();
		dataReader = new BufferedReader(new InputStreamReader(hostListUrlConnection.getInputStream()));
		String hostListLine;
		while ((hostListLine = dataReader.readLine()) != null) {
			hostListWebsiteData += " " + hostListLine;
		}
		dataReader.close();
	}

	public void populateBlackList() { //Populates the Black List data onto the application
		String blackListString = this.blackListWebsiteData; //If null, its value is:null #Start_Block null #End_Block
		blackListString = blackListString.substring(blackListString.indexOf("#Start_Block"), blackListString.indexOf("#End_Block"));
		blackListString = blackListString.substring("#Start_Block".length() + 1, blackListString.length());
		blackListString = blackListString.trim();
		String[] blackListArray = blackListString.split(", ");
		for (String s : blackListArray) {
			blackList.add(s.toLowerCase());
		}
	}

	public void populateOverrideList() { //Populates the Override List data onto the application
		String overrideListString = this.overrideListWebsiteData; //If null, its value is:null #Start_Override_Host_List null #End_Override_Host_List
		overrideListString = overrideListString.substring(overrideListString.indexOf("#Start_Override_Host_List"), overrideListString.indexOf("#End_Override_Host_List"));
		overrideListString = overrideListString.substring("#Start_Override_Host_List".length() + 1, overrideListString.length());
		overrideListString = overrideListString.trim();
		if (overrideListString.toLowerCase().equals("null")) {
			overrideList.add(new Host("", "null", "", ""));
		}
		else {
			String[] overrideListArray = overrideListString.split(",, ");
			for (int i = 0; i < overrideListArray.length; i++) {
				String hostString = overrideListArray[i];
				String[] hostData = hostString.split(", ");
				overrideList.add(new Host(hostData[0].toUpperCase(), hostData[1], hostData[2], hostData[3]));
			}
		}
	}

	public void populateRS3Hosts() { //Populates the RS3 Hosts data onto the application in a table format
		String webString = this.hostListWebsiteData;
		if (webString != null) {
			webString = webString.substring(webString.indexOf("RS3"), webString.length()); 
			webString = webString.substring(webString.indexOf("[") + 1, webString.indexOf("]"));
			if (webString.equals("")) {
				//There are currently no open RS3 Hosts
				return;
			}
			webString = webString.replace("}, {", "\n");
			webString = webString.substring(1, webString.length() - 1);
			String[] datas = webString.split("\n");
			for (int i = 0; i < datas.length; i++) { //This will loop through each individual RS3 host
				String currentLine = datas[i];
				currentLine = currentLine.replace(", ", "\n");
				currentLine = currentLine.replace("\"", "");
				currentLine = currentLine.replace(": ", ":");
				String[] rs3SubData = currentLine.split("\n");
				String[] rs3HostData = new String[3];
				for (int index = 0; index < rs3SubData.length; index++) {
					if (rs3SubData[index].contains("Username:")) {
						rs3HostData[0] = rs3SubData[index].replace("Username:", "");
					}
					if (rs3SubData[index].contains("World:")) {
						rs3HostData[1] = rs3SubData[index].replace("World:", "");
					}
					if (rs3SubData[index].contains("loc:")) {
						rs3HostData[2] = rs3SubData[index].replace("loc:", "");
					}
				}
				allHosts.add(new Host("RS3", rs3HostData[0], rs3HostData[1], rs3HostData[2]));
			}
		}
	}

	public void populateOSRSHosts() { //Populates the OSRS Hosts data onto the application in a table format
		String webString = this.hostListWebsiteData;
		if (webString != null) {
			webString = webString.substring(webString.indexOf("OSRS"), webString.length()); 
			webString = webString.substring(webString.indexOf("[") + 1, webString.indexOf("]"));
			if (webString.equals("")) {
				//There are currently no open OSRS Hosts
				return;
			}
			webString = webString.replace("}, {", "\n");
			webString = webString.substring(1, webString.length() - 1);
			String[] datas = webString.split("\n");
			for (int i = 0; i < datas.length; i++) { //This will loop through each individual OSRS host
				String currentLine = datas[i];
				currentLine = currentLine.replace(", ", "\n");
				currentLine = currentLine.replace("\"", "");
				currentLine = currentLine.replace(": ", ":");
				String[] osrsSubData = currentLine.split("\n");
				String[] osrsHostData = new String[3];
				for (int index = 0; index < osrsSubData.length; index++) {
					if (osrsSubData[index].contains("Username:")) {
						osrsHostData[0] = osrsSubData[index].replace("Username:", "");
					}
					if (osrsSubData[index].contains("World:")) {
						osrsHostData[1] = osrsSubData[index].replace("World:", "");
					}
					if (osrsSubData[index].contains("loc:")) {
						osrsHostData[2] = osrsSubData[index].replace("loc:", "");
					}
				}
				allHosts.add(new Host("OSRS", osrsHostData[0], osrsHostData[1], osrsHostData[2]));
			}
		}
	}
}