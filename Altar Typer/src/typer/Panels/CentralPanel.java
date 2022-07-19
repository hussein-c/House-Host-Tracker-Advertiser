package typer.Panels;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import typer.PanelListeners.AdvertisementsListener;
import typer.PanelListeners.ColoursAndEffectsListener;
import typer.PanelListeners.ConfigsListener;
import typer.PanelListeners.MainCheckboxUpdater;
import typer.PanelListeners.ServerTypeMenuListener;
import typer.PanelListeners.StartAndStopListener;
import typer.Tools.Config;
import typer.Tools.Settings;

@SuppressWarnings("serial")
public class CentralPanel extends JPanel {

	private JLabel hostLabel;
	public static DefaultTableModel tableModel;
	private JTable hostTable;
	private JScrollPane scrollPane;
	public static JButton coloursAndEffects;
	public static JButton advertisements;
	public static JButton blackList;
	public static JButton seeConfigs;
	public static JCheckBox pinToTop;
	public static JCheckBox onlyVerifiedHosts;
	public static JCheckBox onlyHostAdvertisements;
	public static JCheckBox onlyCommunityAdvertisements;
	public static JCheckBox onlyBoneJobAdvertisements;
	public static JComboBox<String> serverType;
	public static String[] servers = {"RS3 - 31 - Taverley", "RS3 - 31 - Yanille", "OSRS - 330 - Rimmington", "OSRS - 331 - Rimmington", "OSRS - 465 - Rimmington", "OSRS - 512 - Rimmington", "OSRS - 330 - Yanille", "OSRS - 331 - Yanille", "OSRS - 465 - Yanille", "OSRS - 512 - Yanille"};
	public static JButton startTyping;
	public static JButton stopTyping;
	public static JCheckBox yellow;
	public static JCheckBox purple;
	public static JCheckBox red;
	public static JCheckBox green;
	public static JCheckBox white;
	public static JCheckBox cyan;
	public static JCheckBox flash1;
	public static JCheckBox flash2;
	public static JCheckBox flash3;
	public static JCheckBox glow1;
	public static JCheckBox glow2;
	public static JCheckBox glow3;
	public static JCheckBox wave;
	public static JCheckBox wave2;
	public static JCheckBox slide;
	public static JCheckBox scroll;
	public static JCheckBox shake;

	public CentralPanel() { //Once the user starts the application, it loads everything on the central panel based on the configuration file
		setLayout(null);
		blackList();
		coloursAndEffects();
		advertisements();
		configs();
		hostLabel();
		hostDisplayPanel();
		pinToTop();
		onlyVerifiedHosts();
		showHostAdverts();
		showCommunityAdverts();
		showBoneJobAdverts();
		serverType();
		startButton();
		stopButton();
		MainCheckboxUpdater.updateMainCheckBoxes();
	}

	private void hostLabel() { //Host table label
		hostLabel = new JLabel("Currently Active Hosts");
		hostLabel.setBounds(5, 30, 300, 30);
		add(hostLabel);
	}

	private void hostDisplayPanel() { //Host table layout
		tableModel = new DefaultTableModel();
		String[] headers = {"Server", "Username", "World", "Location"};
		for (String s : headers) {
			tableModel.addColumn(s);
		}
		hostTable = new JTable(tableModel);
		hostTable.setEnabled(false);
		hostTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane = new JScrollPane(hostTable);
		scrollPane.setBounds(5, 60, 570, 150);
		add(scrollPane);
	}

	private void pinToTop() { //Pin to Top check box
		pinToTop = new JCheckBox("Pin To Top");
		pinToTop.setToolTipText("Tick this checkbox to keep this program on top of other programs.");
		pinToTop.setBounds(5, 220, 100, 15);
		pinToTop.addActionListener(e -> {
			//Any time the user selects or unselects the Pin to Top check box, it dynamically updates the configuration file and settings
			MainCheckboxUpdater.updateMainSettings();
		});
		add(pinToTop);
	}

	private void onlyVerifiedHosts() { //Only Verified Hosts check box
		onlyVerifiedHosts = new JCheckBox("Only Verified Hosts");
		onlyVerifiedHosts.setToolTipText("Tick this checkbox to advertise verified hosts only.");
		onlyVerifiedHosts.setBounds(5, 240, 180, 15);
		onlyVerifiedHosts.addActionListener(e -> {
			//Any time the user selects or unselects the Only Verified Hosts check box, it dynamically updates the configuration file and settings
			MainCheckboxUpdater.updateMainSettings();
		});
		add(onlyVerifiedHosts);
	}

	private void showHostAdverts() { //Advertise Tracker Hosts check box
		onlyHostAdvertisements = new JCheckBox("Advertise Tracker Hosts");
		onlyHostAdvertisements.setToolTipText("Tick this checkbox to advertise the hosts listed above.");
		onlyHostAdvertisements.setBounds(186, 220, 165, 15);
		onlyHostAdvertisements.addActionListener(e -> {
			//Any time the user selects or unselects the Advertise Tracker Hosts check box, it dynamically updates the configuration file and settings
			MainCheckboxUpdater.updateMainSettings();
		});
		add(onlyHostAdvertisements);
	}

	private void showCommunityAdverts() { //Advertise Community Lines check box
		onlyCommunityAdvertisements = new JCheckBox("Advertise Community Lines");
		onlyCommunityAdvertisements.setToolTipText("Tick this checkbox to advertise the community advertisements.");
		onlyCommunityAdvertisements.setBounds(186, 240, 180, 15);
		onlyCommunityAdvertisements.addActionListener(e -> {
			//Any time the user selects or unselects the Advertise Community Lines check box, it dynamically updates the configuration file and settings
			MainCheckboxUpdater.updateMainSettings();
		});
		add(onlyCommunityAdvertisements);
	}

	private void showBoneJobAdverts() { //Advertise Bone Job Lines check box
		onlyBoneJobAdvertisements = new JCheckBox("Advertise Bone Job Lines");
		onlyBoneJobAdvertisements.setToolTipText("Tick this checkbox to advertise the bone job advertisements.");
		onlyBoneJobAdvertisements.setBounds(186, 260, 180, 15);
		onlyBoneJobAdvertisements.addActionListener(e -> {
			//Any time the user selects or unselects the Bone Job Lines check box, it dynamically updates the configuration file and settings
			MainCheckboxUpdater.updateMainSettings();
		});
		add(onlyBoneJobAdvertisements);
	}

	private void serverType() { //Server type drop-down menu
		serverType = new JComboBox<String>(servers);
		serverType.setBounds(5, 290, 180, 30);
		serverType.setSelectedIndex(Settings.mainPageSettings.getServerDropdownMenuIndex());
		ServerTypeMenuListener.setCommunityAdvertisementsText();
		serverType.addActionListener(e -> {
			//Any time the user selects an option in the Server type dropdown-menu, it dynamically updates the configuration file, settings and advertisement data
			Settings.mainPageSettings.setServerDropdownMenuIndex(serverType.getSelectedIndex());
			ServerTypeMenuListener.setCommunityAdvertisementsText();
			Config.updateConfigFile();
		});
		add(serverType);
	}

	private void startButton() { //Start button
		startTyping = new JButton("Start Typing");
		startTyping.setBounds(190, 290, 140, 30);
		startTyping.addActionListener(new StartAndStopListener());
		add(startTyping);
	}

	private void stopButton() { //Stop button
		stopTyping = new JButton("Stop Typing");
		stopTyping.setEnabled(false);
		stopTyping.setBounds(340, 290, 220, 30);
		stopTyping.addActionListener(new StartAndStopListener());
		add(stopTyping);
	}

	private void blackList() { //Black List
		blackList = new JButton("Black List");
		blackList.setBounds(85, 5, 100, 20);
		blackList.addActionListener(e -> {
			String message = "The Black List is loading. Try again later.";
			int listSize = 1;
			for (String name : Settings.blackList) {
				//Checks if the Black List is empty or not to carry out the appropriate actions
				if (name.equals("null")) {
					message = "The Black List is empty.";
				}
				else {
					if (listSize == 1) {
						message = "Names:" + "\n";
					}
					message += listSize++ + ". " + name + "\n";
				}
			}
			JOptionPane.showMessageDialog(Settings.frame, message, "Black List", JOptionPane.INFORMATION_MESSAGE);
		});
		add(blackList);
	}

	private void coloursAndEffects() { //Colours and Effects button and interface check boxes
		coloursAndEffects = new JButton("Colours & Effects");
		coloursAndEffects.setBounds(190, 5, 135, 20);
		yellow = new JCheckBox("Yellow");
		yellow.setBounds(0, 30, 65, 20);
		purple = new JCheckBox("Purple");
		purple.setBounds(0, 60, 65, 20);
		red = new JCheckBox("Red");
		red.setBounds(0, 90, 65, 20);
		green = new JCheckBox("Green");
		green.setBounds(0, 120, 65, 20);
		white = new JCheckBox("White");
		white.setBounds(0, 150, 65, 20);
		cyan = new JCheckBox("Cyan");
		cyan.setBounds(0, 180, 65, 20);
		flash1 = new JCheckBox("Flash1");
		flash1.setBounds(0, 210, 65, 20);
		flash2 = new JCheckBox("Flash2");
		flash2.setBounds(0, 240, 65, 20);
		flash3 = new JCheckBox("Flash3");
		flash3.setBounds(0, 270, 65, 20);
		glow1 = new JCheckBox("Glow1");
		glow1.setBounds(0, 300, 65, 20);
		glow2 = new JCheckBox("Glow2");
		glow2.setBounds(0, 330, 65, 20);
		glow3 = new JCheckBox("Glow3");
		glow3.setBounds(0, 360, 65, 20);
		wave = new JCheckBox("Wave");
		wave.setBounds(90, 30, 65, 20);
		wave2 = new JCheckBox("Wave2");
		wave2.setBounds(90, 60, 65, 20);
		scroll = new JCheckBox("Scroll");
		scroll.setBounds(90, 90, 65, 20);
		slide = new JCheckBox("Slide");
		slide.setBounds(90, 120, 65, 20);
		shake = new JCheckBox("Shake");
		shake.setBounds(90, 150, 65, 20);
		ColoursAndEffectsListener.updateColoursAndEffectsCheckBoxes();
		coloursAndEffects.addActionListener(e -> {	
			//Opens the Colours and Effects interface
			ColoursAndEffectsListener.coloursAndEffectsPanel();
		});
		add(coloursAndEffects);
	}

	private void advertisements() { //Advertisements button
		advertisements = new JButton("Advertisements");
		advertisements.setBounds(330, 5, 130, 20);
		advertisements.addActionListener(e -> {
			//Opens the Advertisements interface
			AdvertisementsListener.advertisementsPanel();
		});
		add(advertisements);
	}

	private void configs() { //Configs button
		seeConfigs = new JButton("Configs");
		seeConfigs.setBounds(465, 5, 100, 20);
		seeConfigs.addActionListener(e -> {
			//Opens the Configs interface
			ConfigsListener.configsPanel();
		});
		add(seeConfigs);
	}
}