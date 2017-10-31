import Helper.FileHelper;
import Model.Word;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

public class DifficultyMenu {
	
	static JButton easyButton;
	static JButton mediumButton;
	static JButton hardButton;
	static TypingTutor tp = new TypingTutor();

	public static void difficultyMenu(){
		
		JFrame frame = new JFrame("Typing Tutor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		
		JLabel titleLabel = new JLabel("Select Difficulty"){
			{
			setFont(getFont().deriveFont (50.0f));
			}
		};
		
		easyButton = new JButton("Easy"){
			{
			setSize(250,50);
			setMaximumSize(getSize());
			}
		};
		mediumButton = new JButton("Medium"){
			{
			setSize(250,50);
			setMaximumSize(getSize());
			}
		};
		hardButton = new JButton("Hard"){
			{
			setSize(250,50);
			setMaximumSize(getSize());
			}
		};
		
		DifficultyHandler handler = new DifficultyHandler();
		easyButton.addActionListener(handler);
		mediumButton.addActionListener(handler);
		hardButton.addActionListener(handler);
		
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		easyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		mediumButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		hardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		frame.add(panel);
		panel.add(Box.createRigidArea(new Dimension(20, 50)));
		panel.add(titleLabel);
		panel.add(Box.createRigidArea(new Dimension(50, 50)));
		panel.add(easyButton);
		panel.add(Box.createRigidArea(new Dimension(100, 100)));
		panel.add(mediumButton);
		panel.add(Box.createRigidArea(new Dimension(100, 100)));
		panel.add(hardButton);
		
		
		frame.setSize(450, 600);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}

	private static String chooseLanguage() {

		FileHelper fileHelper = new FileHelper();
		ArrayList<Word> words = fileHelper.readFile();
		Set<String> languages = new HashSet<String>();
		for(int i = 0; i < words.size(); i++) {
			languages.add(words.get(i).getLanguage());
		}
		ArrayList<String> lang = new ArrayList<>();
		for (String l: languages) {
			lang.add(l);
		}

		String[] array = lang.toArray(new String[lang.size()]);


		JFrame frame = new JFrame("Choose Programming Language");
		String language = (String) JOptionPane.showInputDialog(frame,
				"Which language do you want to train?",
				"Choose Programming Language",
				JOptionPane.QUESTION_MESSAGE,
				null,
				array,
				array[0]);

		return language;
	}

	private static class DifficultyHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == easyButton){
				tp.difficulty = 40;
				tp.typingTutor(chooseLanguage());
				
			}
			if(e.getSource() == mediumButton){
				tp.difficulty = 25;
				tp.typingTutor(chooseLanguage());
				
			}
			if(e.getSource() == hardButton){
				tp.difficulty = 10;
				tp.typingTutor(chooseLanguage());
				
			}
			
		}
		
	}
	
}
