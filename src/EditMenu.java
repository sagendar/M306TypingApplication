import Helper.FileHelper;
import Model.Word;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.*;

public class EditMenu {

    static JButton addButton;
    static JButton displayButton;
    static JList<Word> list;
    static TypingTutor tp = new TypingTutor();

    public static void editMenu(){

        JFrame frame = new JFrame("Typing Tutor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        JLabel titleLabel = new JLabel("Edit Words"){
            {
                setFont(getFont().deriveFont (50.0f));
            }
        };

        FileHelper fileHelper = new FileHelper();

        addButton = new JButton("Add Word"){
            {
                setSize(250,50);
                setMaximumSize(getSize());
            }
        };


        displayButton = new JButton("Display Word"){
            {
                setSize(250,50);
                setMaximumSize(getSize());
            }
        };

        EditMenuHandler handler = new EditMenuHandler();
        addButton.addActionListener(handler);
        displayButton.addActionListener(handler);

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        displayButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        frame.add(panel);
        panel.add(Box.createRigidArea(new Dimension(20, 50)));
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(100, 100)));
        panel.add(addButton);
        panel.add(Box.createRigidArea(new Dimension(100, 100)));
        panel.add(displayButton);


        frame.setSize(450, 600);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    private static void addWord() {
        JFrame frame = new JFrame("Add new word");

        // prompt the user to enter their name
        String name = JOptionPane.showInputDialog(frame, "Enter a word and the corresponding language (word, lang)");

        FileHelper fileHelper = new FileHelper();
        ArrayList<Word> words = fileHelper.readFile();

        String[] values = name.split(",");
        values[1] = values[1].replace(" ", "");
        Word word = new Word(words.size(), values[0], values[1]);
        words.add(word);
        fileHelper.writeFile(words);
        // get the user's input. note that if they press Cancel, 'name' will be null
        System.out.printf("The user's name is '%s'.\n", name);

    }

    private static class EditMenuHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == addButton){
                addWord();
            }
            if(e.getSource() == displayButton){

            }

        }

    }

}
