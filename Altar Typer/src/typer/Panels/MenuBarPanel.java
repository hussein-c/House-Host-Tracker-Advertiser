package typer.Panels;

import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import typer.PanelListeners.MenuBarListener;

@SuppressWarnings("serial")
public class MenuBarPanel extends JPanel {

	private JMenuBar menuBar;
	public static JMenu configMenu;
	public static JMenu communityMenu;
	public static JMenu javaMenu;
	private JMenuItem newConfig;
	private JMenuItem openConfig;
	private JMenuItem applyChanges;
	private JMenuItem portal;
	private JMenuItem forums;
	private JMenuItem discord;
	private JMenuItem tracker;
	private JMenuItem javaDownload;

	public MenuBarPanel() { //Adjusts the layout and positioning of the menu bar
		setLayout(new BorderLayout());
		menuBar = new JMenuBar();
		menuBar();
		add(menuBar, BorderLayout.CENTER);
	}

	private void menuBar() {
		//Adds a Config File menu with 3 sub-menus to the menu bar
		configMenu = new JMenu("Config File");
		menuBar.add(configMenu);
		newConfig = new JMenuItem("New");
		newConfig.addActionListener(new MenuBarListener());
		configMenu.add(newConfig);
		openConfig = new JMenuItem("Open");
		openConfig.addActionListener(new MenuBarListener());
		configMenu.add(openConfig);
		applyChanges = new JMenuItem("Apply Changes");
		applyChanges.addActionListener(new MenuBarListener());
		configMenu.add(applyChanges);
		//Adds a Community menu with 4 sub-menus to the menu bar
		communityMenu = new JMenu("Community");
		menuBar.add(communityMenu);
		portal = new JMenuItem("Portal");
		portal.addActionListener(new MenuBarListener());
		communityMenu.add(portal);
		forums = new JMenuItem("Forums");
		forums.addActionListener(new MenuBarListener());
		communityMenu.add(forums);
		discord = new JMenuItem("Discord");
		discord.addActionListener(new MenuBarListener());
		communityMenu.add(discord);
		tracker = new JMenuItem("HHT");
		tracker.addActionListener(new MenuBarListener());
		communityMenu.add(tracker);
		//Adds a Java menu with a sub-menu to the menu bar
		javaMenu = new JMenu("Java");
		menuBar.add(javaMenu);
		javaDownload = new JMenuItem("Download Page");
		javaDownload.addActionListener(new MenuBarListener());
		javaMenu.add(javaDownload);
	}
}
