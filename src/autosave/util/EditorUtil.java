package autosave.util;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;


public final class EditorUtil {
	
	private EditorUtil() {
	}
		
	public static void saveDirtyEditors() {
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				IEditorReference[] references = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
				if (references != null) {
					NullProgressMonitor monitor = new NullProgressMonitor();
					for (IEditorReference r : references) {
						IEditorPart editor = r.getEditor(false);
						if (editor.isDirty()) {
							editor.doSave(monitor);
						}
					}
				}
			}
			
		});
	}
	
}
