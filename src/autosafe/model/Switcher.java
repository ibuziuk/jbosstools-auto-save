package autosafe.model;

public class Switcher {
	private Command on;
	private Command off;

	public Switcher(Command on, Command off) {
		this.on = on;
		this.off = off;
	}

	public void turnOn() {
		on.execute();
	}

	public void turnOff() {
		off.execute();
	}

}
