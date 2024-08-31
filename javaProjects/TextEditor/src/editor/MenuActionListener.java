package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActionListener implements ActionListener {
    private TextEditor editor;

    public MenuActionListener(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        FileHandler fileHandler = new FileHandler(editor);

        switch (command) {
            case "New":
                editor.getTextArea().setText("");
                break;
            case "Open":
                fileHandler.openFile();
                break;
            case "Save":
                fileHandler.saveFile();
                break;
            case "Print":
                fileHandler.printFile();
                break;
        }
    }
}
