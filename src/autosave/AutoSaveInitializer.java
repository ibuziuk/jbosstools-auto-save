package autosave;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.EditorReference;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.progress.UIJob;

import autosave.listeners.AutoSaveDocumentListener;

public final class AutoSaveInitializer {
	public static final AutoSaveInitializer INSTANCE = new AutoSaveInitializer();
	
	private IEditorPart currentEditor;
	private EditorListener currentEditorListener;
	private AutoSaveDocumentListener currentDocumentListener;
	
	
	private AutoSaveInitializer() {
	}
	
	public void init() {
		inizializeEditorListener();
	}
	
	public void reset() {
		if (this.currentEditor != null) {
			IDocument document = (IDocument) this.currentEditor.getAdapter(IDocument.class);
			if (document != null && currentDocumentListener != null) {
				UIJob job = currentDocumentListener.getJob();
				if (job != null) {
					job.cancel();
				}
				document.removeDocumentListener(currentDocumentListener);
			}
		}
		
		if (currentEditorListener != null) {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().removePartListener(currentEditorListener);
		}

		this.currentEditor = null;
		this.currentDocumentListener = null;
		this.currentEditorListener = null;
	}
	
	private void editorChanged(IEditorPart editor) {
		if (currentEditor == editor) {
			// do nothing
		} else if (editor == null) {
			setCurrentEditor(null);
		} else {
			setCurrentEditor(editor);
		}
	}
	
	private void inizializeEditorListener() {
		currentEditorListener = new EditorListener();
//		Workbench.getInstance().getActiveWorkbenchWindow().getPartService().addPartListener(currentEditorListener);
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().addPartListener(currentEditorListener);
	}
	
	
	public boolean isCurrentEditor(IEditorPart editor) {
		return (currentEditor == editor);
	}
	

	public IEditorPart getCurrentEditor() {
		return currentEditor;
	}

	public void setCurrentEditor(IEditorPart editor) {
		if (getCurrentEditor() != null) {
			IDocument document = (IDocument) getCurrentEditor().getAdapter(IDocument.class);
			if (document != null && currentDocumentListener != null) {
				currentDocumentListener.getJob().cancel();
				document.removeDocumentListener(currentDocumentListener);
				
			}
		}

		this.currentEditor = editor;

		if (getCurrentEditor() != null) {
			IDocument document = (IDocument) this.currentEditor.getAdapter(IDocument.class);
			if (document != null) {
				currentDocumentListener = new AutoSaveDocumentListener(getCurrentEditor());
				document.addDocumentListener(currentDocumentListener);
			}
		}
	}
			
	private class EditorListener implements IPartListener2 {

		@Override
		public void partActivated(IWorkbenchPartReference partRef) {
			if (partRef instanceof EditorReference) {
				IEditorPart editor = ((EditorReference) partRef).getEditor(false);
				editorChanged(editor);
				test();
			}
		}

		@Override
		public void partBroughtToTop(IWorkbenchPartReference partRef) {
			// TODO Auto-generated method stub

		}

		@Override
		public void partClosed(IWorkbenchPartReference partRef) {
			if (partRef instanceof EditorReference) {
				IEditorPart editorPart = ((EditorReference) partRef).getEditor(false);
				if (isCurrentEditor(editorPart)) {
					editorChanged(null);
					test();
				}
			}
		}

		@Override
		public void partDeactivated(IWorkbenchPartReference partRef) {
			// TODO Auto-generated method stub

		}

		@Override
		public void partOpened(IWorkbenchPartReference partRef) {
			// TODO Auto-generated method stub

		}

		@Override
		public void partHidden(IWorkbenchPartReference partRef) {
			// TODO Auto-generated method stub

		}

		@Override
		public void partVisible(IWorkbenchPartReference partRef) {
			// TODO Auto-generated method stub

		}

		@Override
		public void partInputChanged(IWorkbenchPartReference partRef) {
			IWorkbenchPage page = partRef.getPage();
			if (page != null) {
				IEditorPart editor = page.getActiveEditor();
				if (editor != null) {
					setCurrentEditor(editor);
					test();
				}
			}

		}

	}
	
	private void test() {
		System.out.println("editor changed");
	}
	
//	private IWorkbenchPage getActivePage() {
//		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//	}
	
}
