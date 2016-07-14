/*
 * BoardGamePanel.java
 *
 * Created on 28 ›»—«Ì—, 2006, 04:24 „
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;  
import javax.swing.*; 
import java.awt.*;
import java.awt.Image;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class BoardGamePanel extends JPanel
{
    public Toolkit kit=Toolkit.getDefaultToolkit();
    public Image Bondok=kit.getImage("Images/bn.gif");
    public Bondok bond=new Bondok();
   
    public boolean gameStarted;
    
public BoardGamePanel()
    {
       filled=0;
       gameStarted=false;
       move=true;
       bond.mypic=Bondok;
       bond.xposition=0;
       bond.yposition=0;
       bond.row=0;
       bond.column=0;
       action squarePressed=new action();
       addMouseListener(squarePressed);
       
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void paintComponent(Graphics g)
    { 
       super.paintComponent(g);
       Graphics2D g2=(Graphics2D)g;
       Rectangle2D rec;
       setLocation(40,60);
       setBackground(Color.WHITE);
       setSize(320,320);
       this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
// Drawing the Main Board
       
        for (int i=0; i<8; i=i+2)
          {
	      for (int j=0; j<8; j=j+2)
		{
		    g2.setColor(new Color(0,115,0));
		    rec=new Rectangle2D.Double(j*40,(1+i)*40,40,40);
		    g2.fill(rec);
		    rec=new Rectangle2D.Double((1+j)*40,i*40,40,40);
		    g2.fill(rec);
		}
	} 
       
// Drawing The borders of the Main Board
       
       for(int i=0;i<9;i++)
        {
            g2.setPaint(Color.BLACK);
            g2.draw(new Line2D.Double(40*i,0,i*40,320));
            g2.draw(new Line2D.Double(0,i*40,320,i*40));
        }
      
       if(filled!=0)
           g2.drawImage(bond.mypic,bond.xposition,bond.yposition,40,40 ,this);
      
       
// Drawing the filled Squared
       
       for(int i=1;i<filled;i++)
       {
           g2.setPaint(new Color(120,0,0));
           g2.fillOval(prevCol[i]*40+5,prevRow[i]*40+5,30,30);
       }
       
       if(help==true)
       {
         for(int i=0;i<8;i++)
         {
            if(possible_not_fillCol[i]==true&&possible_not_fillRow[i]==true&&hintCol[i]!=prevCol[filled-1]&&hintRow[i]!=prevRow[filled-1])
            {
                g2.setPaint(new Color(0,0,255));
                g2.fillOval(hintCol[i]*40+5,hintRow[i]*40+5,30,30);
            }
          
         }
       }
   }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
// MouseListener
   
private class action implements MouseListener
{
    public void mouseClicked(MouseEvent e) {}
    
     public void mousePressed(MouseEvent e)
     {
        
         X=e.getX();
         Y=e.getY();
         if(X<=320&&Y<=320)
         {
             if(gameStarted==true)
             {
                 Fill_Moves();
                 Move(X,Y);
                 
                 help=false;
                 
                 if(move)
                 {
                    prevRow[filled]=bond.row;
                    prevCol[filled]=bond.column;
                    filled++;       
                    repaint();
                    Find_Row_Col(X,Y);
                    Show_Possible_Moves(bond.row,bond.column);
                    move=true;
                    if(filled==64)
                    {
                      JOptionPane.showConfirmDialog(null,"Congartulation you are won!","Bondok Game-TETOSOFT",JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE);  
                    }
                 }            
             }
             else
             {
                 int response=JOptionPane.showConfirmDialog(null,"Are you sure you want to start from this position?","Bondok Game-TETOSOFT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

                 if(response==0)  //yes
                 {
                     Find_Row_Col(X,Y);
                     bond.xposition=(bond.column*40);
                     bond.yposition=(bond.row*40);
                
                     prevRow[filled]=Y/40;
                     prevCol[filled]=X/40;
                              
                     Show_Possible_Moves(bond.row,bond.column);
                     repaint();   
                     filled++;    
                     gameStarted=true; 
                      
                 }
               
             }
         }
         
     }
        
    public void mouseReleased(MouseEvent e){}
    
    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

 }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class Bondok  
{           
	int row;
	int column;
	public int xposition;
	public int yposition;
	public Image mypic;
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void Find_Row_Col(int x ,int y)
{
    bond.row=y/40;
    bond.column=x/40;   
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void Show_Possible_Moves(int r,int c)
{
    hintRow[0]=r-2;
    hintCol[0]=c+1;
    
    hintRow[1]=r-2;
    hintCol[1]=c-1;
      
    hintRow[2]=r+2;
    hintCol[2]=c+1;
     
    hintRow[3]=r+2;
    hintCol[3]=c-1;
      
    hintRow[4]=r-1;
    hintCol[4]=c+2;
      
    hintRow[5]=r-1;
    hintCol[5]=c-2;
      
    hintRow[6]=r+1;
    hintCol[6]=c+2;
      
    hintRow[7]=r+1;
    hintCol[7]=c-2;
   
    for(int i=0;i<8;i++)
    {
        valid[i]=true;
        if(hintRow[i]>7||hintRow[i]<0||hintCol[i]>7||hintCol[i]<0)
            valid[i]=false;      
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void Fill_Moves()
{
    boolean flag=true;
    for(int i=0;i<8;i++)
    {
        for(int j=0;j<filled;j++)
            if(hintRow[i]==prevRow[j]&&hintCol[i]==prevCol[j])
            {
                flag=false;
                //break;
            }
        
        possible_not_fillCol[i]=flag;
        possible_not_fillRow[i]=flag;
        flag=true;
      
    }

}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void Move(int a,int b)
{  
    boolean live=false; 
    
    for(int i=0;i<8;i++)
    {
        if(hintRow[i]==(b/40)&&hintCol[i]==(a/40)&&possible_not_fillRow[i]==true&&possible_not_fillCol[i]==true&&valid[i]==true)
        {
           bond.xposition=((a/40)*40);
           bond.yposition=((b/40)*40);
           live=true;
           move=true;
           break;
        }       
    }   
    
    if(live==false)
    {
        move=false;
        JOptionPane.showConfirmDialog(null," Invalid Move ","Bondok Game.",JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE);         
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void DisplayPossibleMoves()
{
    help=true;
    repaint();
}
///////////////////////////////////////////////////////////////////////////////////////////
public void BackOneMove()
{
    if(filled>1)
    {
        filled--;
    
        bond.row=prevRow[filled];
        bond.column=prevCol[filled];
    
        bond.xposition=(bond.column*40);
        bond.yposition=(bond.row*40);
    
        Show_Possible_Moves(bond.row,bond.column);
    
        repaint();
    }
    else
    {
        JOptionPane.showConfirmDialog(null,"You can not back more!","Bondok Game-TETOOSFT",JOptionPane.OK_OPTION);
    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

int hintRow[]=new int[8];
int hintCol[]=new int[8];

int prevRow[]=new int[100];
int prevCol[]=new int[100];

boolean possible_not_fillRow[]=new boolean[8];
boolean possible_not_fillCol[]=new boolean[8];

boolean valid []=new boolean[8];

private int filled;

private boolean help=false;
int X;
int Y;

private boolean move;

}