import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {

    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu fileMenu, editMenu;
    JMenuItem newFileItem, openFileItem, saveFileItem, closeItem, cutItem, copyItem, pasteItem, printItem;

    public TextEditor() {

        textArea = new JTextArea();
        textArea.setFont(new Font("Regular", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        newFileItem = new JMenuItem("New");
        openFileItem = new JMenuItem("Open");
        saveFileItem = new JMenuItem("Save");
        closeItem = new JMenuItem("Close");
        printItem = new JMenuItem("Print");
        fileMenu.add(newFileItem);
        fileMenu.add(openFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(closeItem);
        fileMenu.addSeparator();
        fileMenu.add(printItem);

        editMenu = new JMenu("Edit");
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        add(scrollPane);

        newFileItem.addActionListener(this);
        openFileItem.addActionListener(this);
        saveFileItem.addActionListener(this);
        closeItem.addActionListener(this);
        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        printItem.addActionListener(this);

        // Set the font for the "File" and "Edit" menus
        Font menuFont = new Font("Sans Serif", Font.PLAIN, 14);
        fileMenu.setFont(menuFont);
        editMenu.setFont(menuFont);

        // Set the font for the menu items
        Font menuItemFont = new Font("Sans Serif", Font.PLAIN, 14);
        newFileItem.setFont(menuItemFont);
        openFileItem.setFont(menuItemFont);
        saveFileItem.setFont(menuItemFont);
        closeItem.setFont(menuItemFont);
        printItem.setFont(menuItemFont);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == newFileItem) {
            textArea.setText("");
            setTitle("Text Editor");
        } else if (e.getSource() == openFileItem) {

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {

                File selectedFile = fileChooser.getSelectedFile();

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));

                    String line = null;
                    textArea.setText("");

                    while ((line = reader.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                    setTitle("Text Editor - " + selectedFile.getName());
                    reader.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == saveFileItem) {

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {

                File selectedFile = fileChooser.getSelectedFile();

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                    writer.write(textArea.getText());
                    writer.close();
                    setTitle("Text Editor - " + selectedFile.getName());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == closeItem) {
            System.exit(0);
        } else if (e.getSource() == cutItem) {
            textArea.cut();
        } else if (e.getSource() == copyItem) {
            textArea.copy();
        } else if (e.getSource() == pasteItem) {
            textArea.paste();
        } else if (e.getSource() == printItem) {

            try {
                textArea.print();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error printing file", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();
        textEditor.setTitle("My Text Editor");
        textEditor.setSize(800, 600);
        textEditor.setBackground(new Color(22,0,22));
        textEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textEditor.setVisible(true);
    }

}
