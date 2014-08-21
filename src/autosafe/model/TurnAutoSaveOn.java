package autosafe.model;

import autosave.AutoSaveInitializer;

public class TurnAutoSaveOn implements Command {
	
	@Override
	public void execute() {
		AutoSaveInitializer.INSTANCE.init();
	}

}
