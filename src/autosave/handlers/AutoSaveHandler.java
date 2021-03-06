package autosave.handlers;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

import autosafe.model.Switcher;
import autosafe.model.TurnAutoSaveOff;
import autosafe.model.TurnAutoSaveOn;


public class AutoSaveHandler extends AbstractHandler implements IElementUpdater {
	private static final String ENABLE_TOOLTIP = "Enable Auto Safe";
	private static final String DISABLE_TOOLTIP = "Disable Auto Safe";	
	private Switcher switcher;
	private UIElement element;
	
	public AutoSaveHandler() {
		autosafe.model.Command turnAutoSaveOn = new TurnAutoSaveOn();
		autosafe.model.Command turnAutoSaveOff = new TurnAutoSaveOff();
		this.switcher = new Switcher(turnAutoSaveOn, turnAutoSaveOff);
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
		boolean newValue = !HandlerUtil.toggleCommandState(command);
		
		if (newValue) {
			switcher.turnOn();
			if (element != null) {
				element.setTooltip(DISABLE_TOOLTIP);				
			}
		} else {
			switcher.turnOff();
			if (element != null) {
				element.setTooltip(ENABLE_TOOLTIP);
			}
		}
		
		return null;
	}

	@Override
	public void updateElement(UIElement element, @SuppressWarnings("rawtypes") Map parameters) {
		this.element = element;
	}
	
}
