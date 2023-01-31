package typer;

import java.util.ArrayList;

import data.Host;
import panels.CentralPanel;
import panels.MainFrame;
import source.Website;

public final class HostUpdaterThread implements Runnable {

	private boolean updating;
	private final int interval;
	protected static boolean timerEnd;

	public HostUpdaterThread() {
		// Timer interval
		interval = 30000;
		updating = false;
	}

	public void start() {
		// When the user starts the typer
		Thread worker = new Thread(this);
		worker.start();
	}

	public void stop() {
		// When the user stops the typer
		updating = false;
	}

	@Override
	public void run() {
		updating = true;
		while (updating == true) {
			/*
			 * After the 30 seconds timer hits 0, it fetches all the data from the web
			 * server to format it into the host table
			 */
			Website site = new Website();
			site.populateBlackList();
			site.populateOverrideList();
			site.populateRS3Hosts();
			site.populateOSRSHosts();
			listOnlyVerifiedHosts();
			CentralPanel.onlyVerifiedHosts.addActionListener(e -> {
				listOnlyVerifiedHosts();
			});
			timerEnd = false;
			for (int i = interval; i > -1; i = i - 1000) { // 30 seconds host updating timer
				try {
					if (i == 0) {
						/*
						 * When the timer hits 0, it backs up the host list while it is fetching web
						 * page data and typing hosts in-game at the same time
						 */
						Website.backUpBlackList = new ArrayList<String>();
						Website.backUpBlackList = Website.blackList;
						Website.backUpOverrideList = new ArrayList<Host>();
						Website.backUpOverrideList = Website.overrideList;
						Website.backUpAllHosts = new ArrayList<Host>();
						Website.backUpAllHosts = Website.allHosts;
						timerEnd = true;
						MainFrame.updateStatus("Refreshing Hosts now...");
					}
					else if (i == 1000) {
						MainFrame.updateStatus("Refreshing Hosts in " + i / 1000 + " second");
					}
					else {
						MainFrame.updateStatus("Refreshing Hosts in " + i / 1000 + " seconds");
					}
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {
					// Thread.currentThread().interrupt();
				}
			}
		}
	}

	public static void listOnlyVerifiedHosts() {
		if (CentralPanel.tableModel.getRowCount() > 0) {
			// Remove old hosts from the host table
			for (int i = CentralPanel.tableModel.getRowCount() - 1; i > -1; i--) {
				CentralPanel.tableModel.removeRow(i);
			}
		}
		for (Host h : Website.allHosts) {
			// Add back in new hosts
			CentralPanel.tableModel.addRow(new Object[] { h.getServer(), h.getUsername(), h.getWorld(), h.getLocation() });
			if (CentralPanel.onlyVerifiedHosts.isSelected()) {
				if (h.getUsername().contains("*")) {
					CentralPanel.tableModel.removeRow(CentralPanel.tableModel.getRowCount() - 1);
				}
			}
		}
	}
}