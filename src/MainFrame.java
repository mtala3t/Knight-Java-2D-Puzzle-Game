/*
 * MainFrame.java
 *
 * Created on 28 ›»—«Ì—, 2006, 04:23 „
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

/**
 *
 * @author Tetoooooooooooo
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;

public class MainFrame extends JFrame
{
    BoardGamePanel gamePanel=new BoardGamePanel();
    
    public MainFrame()
    {
        Toolkit kit=Toolkit.getDefaultToolkit();
        Image image=kit.getImage("Images/bn.gif");
         
        setTitle("Bondok Game  < TETOSOFT > ");
        setSize(590,500);
        setLocation(100,60);
        setIconImage(image);
        
        JLabel title=new JLabel(">> TETOSOFT <<");
        title.setBounds(130,10,350,30);
        title.setFont(new Font("Comic Sans MS",Font.BOLD,35));
        title.setForeground(Color.RED);
        Container contentPane=getContentPane();
        ToolPanel toolPanel=new ToolPanel();
        
        contentPane.add(title);
        contentPane.add(toolPanel);
        contentPane.add(gamePanel);
        
        JMenuBar JMBar=new JMenuBar();
	setJMenuBar(JMBar);
        JMenu FileItems=new JMenu("File");
	JMBar.add(FileItems);
	
	JMenuItem NewGame=new JMenuItem("New Game");
	FileItems.add(NewGame);
	
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        
    }
   

class ToolPanel extends JPanel implements ActionListener
{
    JButton retryButton;
    JButton exitButton;
    JButton backButton;
    JButton aboutButton;
    JButton showPossMovesButton;
    JButton pauseButton;
    JButton helpButton;
    
    JLabel timerLabel;
    JLabel timerValueLabel;
    JLabel scoreLabel;
    JLabel scoreValueLabel;
    
    Toolkit kit=Toolkit.getDefaultToolkit();
    Image currentPosImg=kit.getImage("Images/current.gif");
    Image coveredImg=kit.getImage("Images/coverd.gif");
    
    public ToolPanel() 
    {    
        setLocation(390,60);
        setSize(170,350);
        setLayout(null);
        
        
        retryButton=new JButton("Retry");
        retryButton.setBounds(8,5,70,30);
        retryButton.addActionListener(this);
        exitButton=new JButton("Exit");
        exitButton.setBounds(95,5,70,30);
        exitButton.addActionListener(this);
        backButton=new JButton("Back");
        backButton.setBounds(8,45,70,30);
        backButton.addActionListener(this);
        aboutButton=new JButton("About");
        aboutButton.setBounds(95,45,70,30);
        aboutButton.addActionListener(this);
        showPossMovesButton=new JButton("Some Possible Moves");
        showPossMovesButton.setBounds(7,150,160,30);
        showPossMovesButton.addActionListener(this);
        pauseButton=new JButton("Pause");
        pauseButton.setBounds(8,190,70,30);
        pauseButton.addActionListener(this);
        helpButton =new JButton("Help");
        helpButton.setBounds(95,190,70,30);
        helpButton.addActionListener(this);
        
        timerLabel=new JLabel("Timer: ");
        timerLabel.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
        timerLabel.setBounds(13,85,60,30);
        timerValueLabel=new JLabel("0 Seconds");
        timerValueLabel.setFont(new Font("Comic Sans MS",Font.BOLD,13));
        timerValueLabel.setForeground(Color.RED);
        timerValueLabel.setBounds(90,85,80,30);
        scoreLabel=new JLabel("Score: ");
        scoreLabel.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
        scoreLabel.setBounds(13,115,60,30);
        scoreValueLabel=new JLabel("64 Squares Left");
        scoreValueLabel.setFont(new Font("Comic Sans MS",Font.BOLD,13));
        scoreValueLabel.setForeground(Color.RED);
        scoreValueLabel.setBounds(65,115,110,30);
        
                
        add(retryButton);
        add(exitButton);
        add(backButton);
        add(aboutButton);
        add(showPossMovesButton);
        add(pauseButton);
        add(helpButton);
        add(timerLabel);
        add(timerValueLabel);
        add(scoreLabel);
        add(scoreValueLabel);
        
    }
    
    public void paintComponent(Graphics g)
    { 
       super.paintComponent(g);
       Graphics2D g2=(Graphics2D)g;
       
       g2.setFont(new Font("Comic Sans MS",Font.BOLD,14));
       
       g2.drawImage(currentPosImg,8,230,45,44,this);
       g2.drawImage(coveredImg,8,285,45,44,this);
       g2.drawString("Current Position",60,255);
       g2.drawString("Covered Position",60,310);
    }
    public void actionPerformed(ActionEvent e) 
    {
        Object obj=e.getSource();
        
        if(obj==exitButton)
        {
            int response=JOptionPane.showConfirmDialog(null,"Are you sure you want to exir ?","Bondok < TETOSOFT >",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            
            if(response==JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
        
        if(obj==aboutButton)
        {
            AboutDialog aboutDialog=new AboutDialog();                 
        }
        
        if(obj==helpButton)
        {
            HelpDialog helpDialog=new HelpDialog();
        }
        
        if(obj==retryButton)
        {
            int response=JOptionPane.showConfirmDialog(null,"Are you sure want to retray ?","Bondok Game-TETOSOFT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(response==0)
            {
                gamePanel=new BoardGamePanel();
                getContentPane().getComponent(2).setVisible(false);
                getContentPane().add(gamePanel);   
            }
        }
        
        if(obj==backButton)
        {
            gamePanel.BackOneMove();
        }
        if(obj==showPossMovesButton)
        {
            gamePanel.DisplayPossibleMoves();
        }
    }
    
}

}