import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
/*
 * HelpDialog.java
 *
 * Created on 21 ãÇíæ, 2007, 11:34 ã
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Mohammed
 */
public class HelpDialog extends JDialog implements ActionListener
{
    
    /** Creates a new instance of HelpDialog */
    JLabel helpTextLabel;
    JButton okButton;
    Toolkit kit=Toolkit.getDefaultToolkit();
    Dimension d=kit.getScreenSize();
    public HelpDialog() 
    {
        setIconImage(kit.getImage("Images/bn.gif"));
        setTitle("Bondok - TETOSOFT - About");
        setBounds((int)d.getWidth()/2-220,(int)d.getHeight()/2-200,250,130);
        getContentPane().setBackground(Color.YELLOW);
        setLayout(null);
        helpTextLabel=new JLabel("Bondok-TETOSOFT-Ver 1.0");
        
        helpTextLabel.setBounds(30,10,160,30);
        
        okButton=new JButton("OK");
        okButton.setBounds(70,50,70,30);
        okButton.addActionListener(this);
        
        add(helpTextLabel);
        add(okButton);
        
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
