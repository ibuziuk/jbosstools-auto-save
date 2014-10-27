package autosave.states;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;

class AutoSaveState extends AbstractSourceProvider {
	public final static String STATE = "jboss.tools.auto.save.active";
	public final static String ENABLED = "ENABLED";
	public final static String DISENABLED = "DISENABLED";
	private boolean enabled = true;

	@Override
	public void dispose() {
	}

	@Override
	public Map<String, String> getCurrentState() {
	    Map<String, String> map = new HashMap(1);
	    String value = enabled ? ENABLED : DISENABLED;
	    map.put(STATE, value);
	    return map;
	}

	@Override
	public String[] getProvidedSourceNames() {
		return new String[] { STATE };
	}

}
