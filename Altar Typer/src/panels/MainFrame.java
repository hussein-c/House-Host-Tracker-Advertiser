package panels;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import source.StartupSettings;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private MenuBarPanel menuBar;
	static JLabel status;
	static CentralPanel window = new CentralPanel();
	public static final String frameTitle = "Altar Typer (Version 7.2.0)";

	public MainFrame() { //Once the application starts up, it initialises and loads up the functionality and appearance operations
		super(frameTitle);
		final int frameWidth = 610;
		final int frameHeight = 410;
		setSize(frameWidth, frameHeight);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		System.setProperty("java.net.useSystemProxies", "true");
		pinToTop();
		statusText();
		menuPanel();
		centralPanel();
		revalidate();
		repaint();
	}

	private void menuPanel() { //Initialises and places the menu bar on top of the application
		menuBar = new MenuBarPanel();
		add(menuBar, BorderLayout.NORTH);
	}

	private void centralPanel() { //Initialises and centers the main page
		add(window, BorderLayout.CENTER);
	}

	private void pinToTop() { //Sets the application window to appear on top of every other window
		if (StartupSettings.mainPageSettings.getPinToTop() == 1) {
			setAlwaysOnTop(true);
		}
		else {
			setAlwaysOnTop(false);
		}
	}

	private void statusText() { //Status label that appears when the application starts up
		status = new JLabel("Loading...");
		status.setHorizontalAlignment(SwingConstants.RIGHT);
		add(status, BorderLayout.SOUTH);
	}

	public static void updateStatus(String text) { //After the application loads up, the 30 seconds timer appears in the same position as the Status label
		status.setText(text);
	}
}
