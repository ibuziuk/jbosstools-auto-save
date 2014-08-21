package autosave.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.progress.UIJob;

public class AutoSaveJob extends UIJob {
	IEditorPart editor;

	public AutoSaveJob(String name, IEditorPart editor) {
		super(name);
		this.editor = editor;
	}

	@Override
	public IStatus runInUIThread(IProgressMonitor monitor) {
		if (editor != null) {
			editor.doSave(new NullProgressMonitor());
		}
		return Status.OK_STATUS;
	}

}
