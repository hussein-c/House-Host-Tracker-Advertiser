package data;

public final class Host {

	private String server, location, world, username;

	public Host(String server, String username, String world, String location) {
		this.server = server;
		this.location = location;
		this.world = world;
		this.username = username;
	}

	public String getServer() {
		return this.server;
	}

	public String getLocation() {
		return this.location;
	}

	public String getWorld() {
		return this.world;
	}

	public String getUsername() {
		return this.username;
	}
}
