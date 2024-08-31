package editor;

import javax.swing.*;
import javax.swing.plaf.metal.*;
import java.awt.event.ActionListener;

public class TextEditor extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextArea textArea;

    public TextEditor() {
        setTitle("Editor");

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }

        textArea = new JTextArea();
        setJMenuBar(createMenuBar());

        add(new JScrollPane(textArea));
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem printItem = new JMenuItem("Print");

        ActionListener fileHandler = new MenuActionListener(this);
        newItem.addActionListener(fileHandler);
        openItem.addActionListener(fileHandler);
        saveItem.addActionListener(fileHandler);
        printItem.addActionListener(fileHandler);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(printItem);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutItem = new JMenuItem("cut");
        JMenuItem copyItem = new JMenuItem("copy");
        JMenuItem pasteItem = new JMenuItem("paste");

        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        JMenuItem closeItem = new JMenuItem("close");
        closeItem.addActionListener(e -> setVisible(false));

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(closeItem);

        return menuBar;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
