package typer.Tools;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import typer.Panels.CentralPanel;

public class RobotTyper {

	public static Robot bot;
	private static int keyStroke = 0;

	public static void startBot() { //Initialises the typing bot
		try {
			bot = new Robot();
		} catch (AWTException e) {
			CentralPanel.stopTyping.doClick();
			JOptionPane.showMessageDialog(null, "Oops! Something went wrong with the bot. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void typeCharacter(char c) { //Types Characters in a line automatically for the user
		keyStroke = Settings.mapStrokes.get(Character.toLowerCase(c));
		//Checks if the character in the line is capitalised to carry out the appropriate actions
		if (Settings.shiftCases.contains(c)) {
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(keyStroke);
			bot.keyRelease(keyStroke);
			bot.keyRelease(KeyEvent.VK_SHIFT);
		}
		else {
			bot.keyPress(keyStroke);
			bot.keyRelease(keyStroke);
		}
	}

	public static void pressEnter() { //Presses the 'Enter' key automatically for the user
		bot.keyPress(KeyEvent.VK_ENTER);
		bot.keyRelease(KeyEvent.VK_ENTER);
	}
}