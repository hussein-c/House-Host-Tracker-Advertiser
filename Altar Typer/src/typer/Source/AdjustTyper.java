package typer.Source;

public class AdjustTyper { //Get and Set methods for the typer configuration settings

	private int typeSpeedDelayMin; 
	private int typeSpeedDelayMax; 
	private int delayTimeMin; 
	private int delayTimeMax; 
	private int stopTime; 
	private int hostCount;

	public int getTypeSpeedDelayMin() {
		return typeSpeedDelayMin;
	}

	public void setTypeSpeedDelayMin(int typeSpeedDelayMin) {
		this.typeSpeedDelayMin = typeSpeedDelayMin;
	}

	public int getTypeSpeedDelayMax() {
		return typeSpeedDelayMax;
	}

	public void setTypeSpeedDelayMax(int typeSpeedDelayMax) {
		this.typeSpeedDelayMax = typeSpeedDelayMax;
	}

	public int getDelayTimeMin() {
		return delayTimeMin;
	}

	public void setDelayTimeMin(int delayTimeMin) {
		this.delayTimeMin = delayTimeMin;
	}

	public int getDelayTimeMax() {
		return delayTimeMax;
	}

	public void setDelayTimeMax(int delayTimeMax) {
		this.delayTimeMax = delayTimeMax;
	}

	public int getStopTime() {
		return stopTime;
	}

	public void setStopTime(int stopTime) {
		this.stopTime = stopTime;
	}

	public int getHostCount() {
		return hostCount;
	}

	public void setHostCount(int hostCount) {
		this.hostCount = hostCount;
	}
}