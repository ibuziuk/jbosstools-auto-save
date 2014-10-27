package autosave.util;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.State;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.RegistryToggleState;

public final class CommandUtil {

	private CommandUtil() {
	}

	public static boolean getCommandState(String commandID) {
		Command command = getCommand(commandID);
		State state = command.getState(RegistryToggleState.STATE_ID);
		return (Boolean) state.getValue();
	}
	
	public static Command getCommand(String commandID) {
		ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
		return commandService.getCommand(commandID);
	}
}
