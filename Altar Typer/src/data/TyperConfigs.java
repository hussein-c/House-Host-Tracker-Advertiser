package data;

public final class TyperConfigs {

	private int typeSpeedDelayMin, typeSpeedDelayMax, delayTimeMin, delayTimeMax, stopTime, hostCount;

	// Minimum Typing Speed
	public int getTypeSpeedDelayMin() {
		return typeSpeedDelayMin;
	}

	public void setTypeSpeedDelayMin(int typeSpeedDelayMin) {
		this.typeSpeedDelayMin = typeSpeedDelayMin;
	}

	// Maximum Typing Speed
	public int getTypeSpeedDelayMax() {
		return typeSpeedDelayMax;
	}

	public void setTypeSpeedDelayMax(int typeSpeedDelayMax) {
		this.typeSpeedDelayMax = typeSpeedDelayMax;
	}

	// Minimum Lines Delay
	public int getDelayTimeMin() {
		return delayTimeMin;
	}

	public void setDelayTimeMin(int delayTimeMin) {
		this.delayTimeMin = delayTimeMin;
	}

	// Maximum Lines Delay
	public int getDelayTimeMax() {
		return delayTimeMax;
	}

	public void setDelayTimeMax(int delayTimeMax) {
		this.delayTimeMax = delayTimeMax;
	}

	// Stop Time
	public int getStopTime() {
		return stopTime;
	}

	public void setStopTime(int stopTime) {
		this.stopTime = stopTime;
	}

	// Host Count
	public int getHostCount() {
		return hostCount;
	}

	public void setHostCount(int hostCount) {
		this.hostCount = hostCount;
	}
}