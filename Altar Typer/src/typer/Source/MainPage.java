package typer.Source;

public class MainPage { //Get and Set methods for the drop-down and check box menus on the main page

	private int serverDropdownMenuIndex;
	private int pinToTop;
	private int onlyVerifiedHosts;
	private int advertiseTrackerHosts;
	private int advertiseCommunityLines;
	private int advertiseBoneJobLines;

	public int getServerDropdownMenuIndex() {
		return serverDropdownMenuIndex;
	}

	public void setServerDropdownMenuIndex(int serverDropdownMenuIndex) {
		this.serverDropdownMenuIndex = serverDropdownMenuIndex;
	}

	public int getPinToTop() {
		return pinToTop;
	}

	public void setPinToTop(int pinToTop) {
		this.pinToTop = pinToTop;
	}

	public int getOnlyVerifiedHosts() {
		return onlyVerifiedHosts;
	}

	public void setOnlyVerifiedHosts(int onlyVerifiedHosts) {
		this.onlyVerifiedHosts = onlyVerifiedHosts;
	}

	public int getAdvertiseTrackerHosts() {
		return advertiseTrackerHosts;
	}

	public void setAdvertiseTrackerHosts(int advertiseTrackerHosts) {
		this.advertiseTrackerHosts = advertiseTrackerHosts;
	}

	public int getAdvertiseCommunityLines() {
		return advertiseCommunityLines;
	}

	public void setAdvertiseCommunityLines(int advertiseCommunityLines) {
		this.advertiseCommunityLines = advertiseCommunityLines;
	}

	public int getAdvertiseBoneJobLines() {
		return advertiseBoneJobLines;
	}

	public void setAdvertiseBoneJobLines(int advertiseBoneJobLines) {
		this.advertiseBoneJobLines = advertiseBoneJobLines;
	}
}