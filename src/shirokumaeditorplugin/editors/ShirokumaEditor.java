package shirokumaeditorplugin.editors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.Doc;
import javax.xml.soap.Text;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.editors.text.TextEditor;

public class ShirokumaEditor extends TextEditor {

	public ShirokumaEditor() {
		super();
	}

	@Override
	protected void performSave(boolean overwrite,
			IProgressMonitor progressMonitor) {
		// 加工
		ISourceViewer viewer = getSourceViewer();
		IDocument doc = viewer.getDocument();
		String beartext = ShirokumaOperation(doc.get());
		doc.set(beartext);
		viewer.setDocument(doc);
		
		// デフォルト動作
		super.performSave(overwrite, progressMonitor);
		
		// メッセージ表示
		MessageDialog.openInformation(viewer.getTextWidget().getShell(), "セーブしてやったぜ", "まさにしろくま");
	}
	
	private String ShirokumaOperation(String text){
		String pattern = "しろ";
		String bear = "しろくま";
		String patternex = "しろくまくま";
		Matcher match = Pattern.compile(pattern).matcher(text);
		text = match.replaceAll(bear);
		match = Pattern.compile(patternex).matcher(text);
		text = match.replaceAll(bear);
		return text;
	}
}
