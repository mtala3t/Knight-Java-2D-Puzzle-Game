import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
/*
 * AboutDialog.java
 *
 * Created on 21 ãÇíæ, 2007, 05:05 ã
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Mohammed
 */
public class AboutDialog extends JDialog implements ActionListener
{
    
    /**
     * Creates a new instance of AboutDialog
     */
    Toolkit kit=Toolkit.getDefaultToolkit();
    Dimension d=kit.getScreenSize();
    JLabel gameNameLabel;
    JLabel developerNameLabel;
    JLabel facultyNameLabel;
    JLabel locationLabel;
    JLabel developerMail;
    
    JButton okButton;
    
    public AboutDialog() 
    {
        setIconImage(kit.getImage("Images/bn.gif"));
        setTitle("Bondok - TETOSOFT - About");
        setBounds((int)d.getWidth()/2-220,(int)d.getHeight()/2-200,300,250);
        getContentPane().setBackground(Color.YELLOW);
        
        
        gameNameLabel=new JLabel("Bondok-TETOSOFT-Ver 1.0");
        gameNameLabel.setBounds(10,10,200,20);
        developerNameLabel=new JLabel("Made by-> Mohamed Talaat Saad");
        developerNameLabel.setBounds(10,40,200,20);
        facultyNameLabel=new JLabel("Faculty of Computers and Information");
        facultyNameLabel.setBounds(10,70,250,20);
        locationLabel=new JLabel("Cairo, Egypt");
        locationLabel.setBounds(10,100,200,20);
        developerMail=new JLabel("Mail-> teto_soft@yahoo.com");
        developerMail.setBounds(10,130,200,20);
        
        okButton=new JButton("OK");
        okButton.addActionListener(this);
        okButton.setBounds(115,170,70,30);
        
        add(gameNameLabel);
        add(developerNameLabel);
        add(facultyNameLabel);
        add(locationLabel);
        add(developerMail);
        add(okButton);
        add(new JLabel(""));
        setVisible(true);
        toFront();
    }

    public void actionPerformed(ActionEvent e) 
    {
        Object obj=e.getSource();
        
        if(obj==okButton)
        {
            setVisible(false);
            dispose();
        }
    }
    
}
