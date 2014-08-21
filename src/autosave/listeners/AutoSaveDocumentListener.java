package autosave.listeners;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.progress.UIJob;

import autosave.jobs.AutoSaveJob;

public class AutoSaveDocumentListener implements IDocumentListener  {
	private static final String AUTO_SAFE_JOB = "Auto Safe Job";
	private static final long DELAY = 500; 
	private UIJob job;
	private IEditorPart editor;
	
	public AutoSaveDocumentListener(IEditorPart editor) {
		this.editor = editor;
	}
	
	@Override
	public void documentAboutToBeChanged(DocumentEvent event) {		
	}

	@Override
	public void documentChanged(DocumentEvent event) {
		if (job == null || job.getState() != Job.WAITING) {
			if (job != null && job.getState() == Job.SLEEPING) {
				job.cancel();
			}
			job = new AutoSaveJob(AUTO_SAFE_JOB, editor);
		}
		job.schedule(DELAY);
		
	}
	
	public UIJob getJob() {
		return job;
	}
}
