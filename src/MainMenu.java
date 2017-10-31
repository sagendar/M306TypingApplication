import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu implements ActionListener{
	JButton startButton;
	JButton exitButton;
	
	DifficultyMenu difficulty = new DifficultyMenu();

	public void openMainMenu(){
		
		JFrame frame = new JFrame("Typing Tutor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		
		JLabel titleLabel = new JLabel("Typing Tutor"){
			{
			setFont(getFont().deriveFont (50.0f));
			}
		};
		
		startButton = new JButton("Start"){
			{
			setSize(250,50);
			setMaximumSize(getSize());
			}
		};
		exitButton = new JButton("Exit"){
			{
			setSize(250,50);
			setMaximumSize(getSize());
			}
		};
		startButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		frame.add(panel);
		panel.add(Box.createRigidArea(new Dimension(20, 50)));
		panel.add(titleLabel);
		panel.add(Box.createRigidArea(new Dimension(50, 90)));
		panel.add(startButton);
		panel.add(Box.createRigidArea(new Dimension(100, 100)));
		panel.add(exitButton);
		
		
		frame.setSize(450, 600);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == startButton){
			DifficultyMenu.difficultyMenu();
		}
		
	}

}
