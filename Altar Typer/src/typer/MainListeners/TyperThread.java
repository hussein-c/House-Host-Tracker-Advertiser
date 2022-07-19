package typer.MainListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import typer.Tools.RobotTyper;
import typer.PanelListeners.StartAndStopListener;
import typer.Panels.CentralPanel;
import typer.Tools.Settings;

public class TyperThread implements Runnable {

	private Thread worker;
	public static boolean running;
	private int timerCount;
	private int timerCountHours;
	private int timerCountMinutes;
	private int timerCountSeconds;
	private int characterCount = 0;
	static int enterCount = 0;

	public void start() { //Starts the stop timer after it begins typing
		worker = new Thread(this);
		worker.start();
	}

	private void startTimer(int stop_timer_time) { //Formats and displays the stop timer in hours, minutes and seconds
		timerCount = stop_timer_time;
		Timer stopTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timerCountHours = (timerCount / 3600);
				timerCountMinutes = (timerCount / 60) % 60;
				timerCountSeconds = timerCount % 60;
				Settings.frame.setTitle(Settings.frameTitle + " (" + timerCountHours + "h " + timerCountMinutes + "m " + timerCountSeconds + "s" + ")"); //adds timer to title
				timerCount--;
				if (StartAndStopListener.typerExecution == false) { //When the typer has stopped running, it stops the timer and remove it from the title
					((Timer)e.getSource()).stop();
					Settings.frame.setTitle(Settings.frameTitle);
				}
				if (timerCount <= 0) { //When the timer reaches 0, it automatically stops the timer and typer. At the same time, it removes the timer from the title
					((Timer)e.getSource()).stop();
					CentralPanel.stopTyping.doClick();
					Settings.frame.setTitle(Settings.frameTitle);
				}
			}
		});
		stopTimer.start();
	}

	private void sleep(int i) { //Adds a delay when appropriate
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			//Thread.currentThread().interrupt();
		}
	}

	public void stop() { //After the typer stops, it resets the variables used to determine when the lines end and what advertisement lines to output
		TyperSpam.previousSpamIndex = -1;
		characterCount = 0;
		enterCount = 0;
		running = false;
	}

	public void run() { //Typer begins
		try {
			startTimer(Settings.adjustTyperSettings.getStopTime());
			Thread.sleep(5000);
			CentralPanel.stopTyping.setEnabled(true);
			RobotTyper.startBot();
			running = true;
		} catch (InterruptedException e) {
			//Thread.currentThread().interrupt();
		}
		while (running == true) {
			//Checks to see if Host Advertisements and/or Bone Job Advertisements check boxes are selected to output the correct data
			if (CentralPanel.onlyHostAdvertisements.isSelected()) {
				TyperSpam.currentLineString =  TyperSpam.getHostSpam(TyperSpam.arraySize);
			}
			else if (CentralPanel.onlyCommunityAdvertisements.isSelected() || CentralPanel.onlyBoneJobAdvertisements.isSelected()) {
				TyperSpam.getAdvertisements();
			}
			for (int i = 0; i < TyperSpam.arraySize; i++) { //Keeps the typer running until the user decides to stop it
				if (TyperSpam.currentLineString[i].equals("")) { //If the line is empty, stop the typer
					break;
				}
				for (char c : TyperSpam.currentLineString[i].toCharArray()) { //For each individual character typed
					if (running == true) {
						RobotTyper.typeCharacter(c);
						characterCount++;
						sleep(TyperSpam.getRandom(Settings.adjustTyperSettings.getTypeSpeedDelayMin(), Settings.adjustTyperSettings.getTypeSpeedDelayMax())); /*Grabs a
						 *random number between the minimum and maximum typing speed delay to cause a delay*/
						if (characterCount == TyperSpam.currentLineString[i].length()) { //When it reaches the end of the host line
							try {
								Thread.sleep(1000);
								if (running == true) { //If the typer is still running after the end of the host line, continue typing
									RobotTyper.pressEnter();
									enterCount++;
									characterCount = 0;
									sleep(TyperSpam.getRandom(Settings.adjustTyperSettings.getDelayTimeMin(), Settings.adjustTyperSettings.getDelayTimeMax()));
								}
								else { //Otherwise, stop the typer and return to the main page
									return;
								}
							} catch (InterruptedException e) {
								//Thread.currentThread().interrupt();
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