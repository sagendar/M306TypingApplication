import Helper.FileHelper;
import Model.Word;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class TypingTutor extends JFrame{
    JButton buttonStart=null;
    JPanel panel=null;
    JTextField textField=null;
    JLabel labelHit=null;
    JLabel labelMaxPoints=null;
    int score=0;
    int displayed=0;
    ArrayList<Word>  wordlist;
    WordRun word1=null;
    WordRun word2=null;
    WordRun word3=null;
    public static int difficulty;
    
    public void typingTutor(String language){
        setSize(400,400);
        setLayout(new BorderLayout());
        
        buttonStart=new JButton("Start");
        buttonStart.addActionListener(new ButtonHandler());
        add(buttonStart,BorderLayout.NORTH);
        
        panel=new DrawPanel();
        panel.setPreferredSize(new Dimension(300,300));
        add(panel,BorderLayout.CENTER);
        
        JPanel panel2=new JPanel();
        panel2.setLayout(new FlowLayout());
        
        textField=new JTextField(15);
        textField.getDocument().addDocumentListener(new ListenText());
        panel2.add(textField);

        labelHit=new JLabel("0");
        labelHit.setForeground(Color.green);
        panel2.add(labelHit);
        
        labelMaxPoints=new JLabel("0");
        labelMaxPoints.setForeground(Color.black);
        panel2.add(labelMaxPoints);
        
        add(panel2,BorderLayout.SOUTH);
        
        wordlist=new ArrayList<Word>();
        FileHelper fileHelper = new FileHelper();
        ArrayList<Word> tempWords = fileHelper.readFile();

        for(Word word : tempWords) {
            if(word.getLanguage().equalsIgnoreCase(language)) {
                wordlist.add(word);
            }
        }

        word1=new WordRun();
        word2=new WordRun();
        word3=new WordRun();
        
        word1.st=null;
        word2.st=null;
        word3.st=null;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        show();
    }
    class DrawPanel extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2=(Graphics2D)g;
            
            Font font=new Font("Arial",Font.BOLD,14);
            FontMetrics fm=this.getFontMetrics(font);
            g2.setColor(Color.BLACK);
            g2.setFont(font);
            if(word1.st!=null){
                g2.drawString(word1.st,word1.x,word1.y);
            }
            if(word2.st!=null){
                g2.drawString(word2.st,word2.x,word2.y);
            }
            if(word3.st!=null){
                g2.drawString(word3.st,word3.x,word3.y);
            }
        }
    }
    class WordRun implements Runnable{
        String st=null;
		int x;
        int y;
        
        public void run(){
            try{
                Random rand=null;
                while(true){
                    if((st==null)||(y==panel.getHeight())){
                        rand=new Random();
                        Thread.currentThread().sleep(rand.nextInt(100));
                        st=wordlist.get(rand.nextInt(32)).getWord();
                        y=0;
                        do{
                            x=rand.nextInt(panel.getWidth());
                        }while(x>(panel.getWidth()-100));
                        textField.setText("");
                        displayed++;
                        labelMaxPoints.setText(displayed+"");
                    }
                    else{
                        Thread.currentThread().sleep(difficulty);
                        y++;
                    } 
                    repaint();
                }
            }catch(Exception e){
            }    
        }
    }
    class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand()=="Start"){
                Thread t1=new Thread(word1);
                Thread t2=new Thread(word2);
                Thread t3=new Thread(word3);
               
                t1.start();
                try {
					t2.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
                
                t2.start();
                try {
					t2.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
                
                t3.start();
            }
        }
    }
    class ListenText implements DocumentListener{
        public void changedUpdate(DocumentEvent e) {
        }
        public void removeUpdate(DocumentEvent e) {    
        }
        public void insertUpdate(DocumentEvent e) {
            if(textField.getText().equals(word1.st)){
                word1.st=null;
                word1.y=0;
                score++;
                labelHit.setText(score+"");
            }
            if(textField.getText().equals(word2.st)){
                word2.st=null;
                word2.y=0;
                score++;
                labelHit.setText(score+"");
            }
            if(textField.getText().equals(word3.st)){
                word3.st=null;
                word3.y=0;
                score++;
                labelHit.setText(score+"");
            }
        }
    }
}