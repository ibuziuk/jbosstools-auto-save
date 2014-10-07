package autosafe.model;

import autosave.jobs.AutoSaveJob;

public class TurnAutoSaveOff implements Command {
	
	@Override
	public void execute() {
		AutoSaveJob.getInstance().cancel();
	}

}
