package autosave.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import autosave.util.EditorUtil;

public class AutoSaveJob extends Job {
	private static final String NAME = "Auto Save Job";
	private static final long DELAY = 2000;
	private static volatile AutoSaveJob instance;
	private boolean running = true;
	
	
	public static synchronized AutoSaveJob getInstance() {
		AutoSaveJob localInstance = instance;
		if (instance == null) {
			synchronized (AutoSaveJob.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new AutoSaveJob();
				}
			}
		}
		return localInstance;
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
		System.out.println("Perform Save"); // TODO Need to delete it
		EditorUtil.saveDirtyEditors();
	}

}
