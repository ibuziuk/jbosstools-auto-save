package autosave.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import autosave.util.EditorUtil;

public class AutoSaveJob extends Job {
	private static final String NAME = "Auto Save Job";
	private static final long DELAY = 1500;
	private static AutoSaveJob instance;
	private boolean running = true;
	
	
	public static synchronized AutoSaveJob getInstance() {
        if (instance == null) {
            instance = new AutoSaveJob();
        }
        return instance;
    }
	
	private AutoSaveJob() {
		super(NAME);
	}

	@Override
	public IStatus run(IProgressMonitor monitor) {
		saveDirtyEditors();
		schedule(DELAY);
		return Status.OK_STATUS;
	}

	public boolean shouldSchedule() {
		return running;
	}

	public void stop() {
		running = false;
	}
	
	private void saveDirtyEditors() {
		System.out.println("Perform Save");
		EditorUtil.saveDirtyEditors();
	}

}
