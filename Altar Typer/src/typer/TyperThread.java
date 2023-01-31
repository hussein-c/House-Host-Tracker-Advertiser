package typer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import actionListeners.StartAndStopListener;
import panels.CentralPanel;
import panels.MainFrame;
import source.StartupSettings;

public final class TyperThread implements Runnable {

	public static boolean running;
	private int timerCount;
	private int timerCountHours;
	private int timerCountMinutes;
	private int timerCountSeconds;
	private int characterCount = 0;
	static int enterCount = 0;

	public void start() {
		// The stop timer begins when the user starts the typer
		Thread worker = new Thread(this);
		worker.start();
	}

	private void startTimer(int stop_timer_time) {
		// Formats and displays the stop timer in hours, minutes and seconds
		timerCount = stop_timer_time;
		Timer stopTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timerCountHours = (timerCount / 3600);
				timerCountMinutes = (timerCount / 60) % 60;
				timerCountSeconds = timerCount % 60;
				// Adds the timer to the title
				StartupSettings.frame.setTitle(MainFrame.frameTitle + " (" + timerCountHours + "h " + timerCountMinutes + "m "
						+ timerCountSeconds + "s" + ")");
				timerCount--;
				if (StartAndStopListener.typerExecution == false) {
					/*
					 * When the typer has stopped running, it stops the timer and removes it from
					 * the title
					 */
					((Timer) e.getSource()).stop();
					StartupSettings.frame.setTitle(MainFrame.frameTitle);
				}
				if (timerCount <= 0) {
					/*
					 * When the timer reaches 0, it automatically stops the timer and typer. At the
					 * same time, it removes the timer from the title
					 */
					((Timer) e.getSource()).stop();
					CentralPanel.stopTyping.doClick();
					StartupSettings.frame.setTitle(MainFrame.frameTitle);
				}
			}
		});
		stopTimer.start();
	}

	private void sleep(int i) { // Adds a delay when appropriate
		try {
			Thread.sleep(i);
		}
		catch (InterruptedException e) {
			// Thread.currentThread().interrupt();
		}
	}

	public void stop() {
		/*
		 * After the typer stops, it resets the variables used to determine when the
		 * lines end and what advertisement lines to output
		 */
		characterCount = 0;
		enterCount = 0;
		running = false;
	}

	@Override
	public void run() {
		// Typer begins
		RobotTyper typer = new RobotTyper();
		typer.initialiseShiftCases();
		TyperSpam typerSpam = new TyperSpam();
		try {
			startTimer(StartupSettings.adjustTyperSettings.getStopTime());
			Thread.sleep(5000);
			CentralPanel.stopTyping.setEnabled(true);
			typer.startBot();
			running = true;
		}
		catch (InterruptedException e) {
			// Thread.currentThread().interrupt();
		}
		while (running == true) {
			/*
			 * Checks to see if Host Advertisements and/or Bone Job Advertisements check
			 * boxes are selected to output the correct data
			 */
			if (CentralPanel.onlyHostAdvertisements.isSelected()) {
				typerSpam.currentLineString = typerSpam.getHostSpam(typerSpam.arraySize);
			}
			else if (CentralPanel.onlyCommunityAdvertisements.isSelected()
					|| CentralPanel.onlyBoneJobAdvertisements.isSelected()) {
				typerSpam.getAdvertisements();
			}
			for (int i = 0; i < typerSpam.arraySize; i++) { // Keeps the typer running until the user decides to stop it
				if (typerSpam.currentLineString[i].equals("")) { // If the line is empty, stop the typer
					break;
				}
				for (char c : typerSpam.currentLineString[i].toCharArray()) { // For each individual character typed
					if (running == true) {
						typer.typeCharacter(c);
						characterCount++;
						/*
						 * Grabs a random number between the minimum and maximum typing speed delay to
						 * cause a delay
						 */
						sleep(typerSpam.getRandom(StartupSettings.adjustTyperSettings.getTypeSpeedDelayMin(),
								StartupSettings.adjustTyperSettings.getTypeSpeedDelayMax()));
						if (characterCount == typerSpam.currentLineString[i].length()) {
							// When it reaches the end of the host line
							try {
								Thread.sleep(1000);
								if (running == true) {
									// If the typer is still running after the end of the host line, continue typing
									typer.pressEnter();
									enterCount++;
									characterCount = 0;
									sleep(typerSpam.getRandom(StartupSettings.adjustTyperSettings.getDelayTimeMin(),
											StartupSettings.adjustTyperSettings.getDelayTimeMax()));
								}
								else {
									// Otherwise, stop the typer and return to the main page
									return;
								}
							}
							catch (InterruptedException e) {
								// Thread.currentThread().interrupt();
							}
						}
					}
					else {
						return;
					}
				}
			}
		}
	}
}