package autosafe.model;

import autosave.AutoSaveInitializer;

public class TurnAutoSaveOff implements Command {
	
	@Override
	public void execute() {
//		AutoSaveInitializer.INSTANCE.reset();
	}

}
