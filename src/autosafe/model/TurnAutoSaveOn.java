package autosafe.model;

import autosave.jobs.AutoSaveJob;

public class TurnAutoSaveOn implements Command {

	@Override
	public void execute() {
		AutoSaveJob.getInstance().schedule();
	}
	
}
