package typer;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import panels.CentralPanel;

final class RobotTyper {

	private Robot bot;
	private int keyStroke;
	private Set<Character> shiftCases;
	private Map<Character, Integer> mapStrokes;

	protected void startBot() { // Initialises the typing bot
		try {
			bot = new Robot();
		} catch (AWTException e) {
			CentralPanel.stopTyping.doClick();
			JOptionPane.showMessageDialog(null, "Oops! Something went wrong with the bot. Please try again.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void typeCharacter(char c) { // Types Characters in a line automatically for the user
		keyStroke = mapStrokes.get(Character.toLowerCase(c));
		// Checks if the character in the line is capitalised to carry out the
		// appropriate actions
		if (shiftCases.contains(c)) {
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(keyStroke);
			bot.keyRelease(keyStroke);
			bot.keyRelease(KeyEvent.VK_SHIFT);
		} else {
			bot.keyPress(keyStroke);
			bot.keyRelease(keyStroke);
		}
	}

	protected void pressEnter() { // Presses the 'Enter' key automatically for the user
		bot.keyPress(KeyEvent.VK_ENTER);
		bot.keyRelease(KeyEvent.VK_ENTER);
	}

	protected void initialiseShiftCases() { // initialises all types of characters for the typing bot
		// Stores each character and symbol requiring the Shift button to be pressed
		shiftCases = Stream.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '{',
				'}', '|', ':', '<', '>', '?', '+', '"').collect(Collectors.toSet());
		// Stores ASCII values for each character and symbol
		mapStrokes = new HashMap<>();
		mapStrokes.put('a', KeyEvent.VK_A);
		mapStrokes.put('b', KeyEvent.VK_B);
		mapStrokes.put('c', KeyEvent.VK_C);
		mapStrokes.put('d', KeyEvent.VK_D);
		mapStrokes.put('e', KeyEvent.VK_E);
		mapStrokes.put('f', KeyEvent.VK_F);
		mapStrokes.put('g', KeyEvent.VK_G);
		mapStrokes.put('h', KeyEvent.VK_H);
		mapStrokes.put('i', KeyEvent.VK_I);
		mapStrokes.put('j', KeyEvent.VK_J);
		mapStrokes.put('k', KeyEvent.VK_K);
		mapStrokes.put('l', KeyEvent.VK_L);
		mapStrokes.put('m', KeyEvent.VK_M);
		mapStrokes.put('n', KeyEvent.VK_N);
		mapStrokes.put('o', KeyEvent.VK_O);
		mapStrokes.put('p', KeyEvent.VK_P);
		mapStrokes.put('q', KeyEvent.VK_Q);
		mapStrokes.put('r', KeyEvent.VK_R);
		mapStrokes.put('s', KeyEvent.VK_S);
		mapStrokes.put('t', KeyEvent.VK_T);
		mapStrokes.put('u', KeyEvent.VK_U);
		mapStrokes.put('v', KeyEvent.VK_V);
		mapStrokes.put('w', KeyEvent.VK_W);
		mapStrokes.put('x', KeyEvent.VK_X);
		mapStrokes.put('y', KeyEvent.VK_Y);
		mapStrokes.put('z', KeyEvent.VK_Z);
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
		// All the symbols that require shift are below
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
	}
}