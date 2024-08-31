package editor;

import javax.swing.*;
import java.io.*;

public class FileHandler {
    private TextEditor editor;

    public FileHandler(TextEditor editor) {
        this.editor = editor;
    }

    public void openFile() {
        JFileChooser fileChooser = new JFileChooser("f:");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());//get selected file

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                StringBuilder content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }

                editor.getTextArea().setText(content.toString());//entire content to string
            } catch (Exception ex) {///shows error in editorbox
                JOptionPane.showMessageDialog(editor, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(editor, "The user cancelled the operation");
        }
    }

    public void saveFile() {
        JFileChooser fileChooser = new JFileChooser("f:");
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(editor.getTextArea().getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(editor, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(editor, "The user cancelled the operation");
        }
    }

    public void printFile() {
        try {
            editor.getTextArea().print();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(editor, ex.getMessage());
        }
    }
}
