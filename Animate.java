import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/* class Animate creates the starting frame of the game with the animation of 4 
   rectangles on it */
class Animate extends JPanel implements ActionListener
{
  Point p1,p2,p3,p4;
  int x,y;
  JFrame frame;
  JButton b1,b2;
  JLabel l1,l;
  JPanel pa,p;
  ImageIcon i;
  /* creates the GUI of the frame with animation */
  Animate()
  {  
     frame =new JFrame(" Tic Tac Toe 4 2");
     frame.addWindowListener(new Start(this));
     frame.setSize(400,400);
     frame.setLocationRelativeTo(null);
     frame.setResizable(false);
     b1= new JButton("Single Player");
     b2= new JButton("Double Player");
     b1.addActionListener(this);
     b2.addActionListener(this);
     l1= new JLabel("CLICK TO PLAY "); 
     pa =new JPanel();
     frame.add(pa,BorderLayout.SOUTH);
     pa.add(l1);
     pa.add(b1);
     pa.add(b2);
     i= new ImageIcon("ttt1.png");
     p =new JPanel();
     p.setLayout(new BorderLayout());
     p.setBackground(Color.BLACK);
     frame.add(p,BorderLayout.CENTER);	
     l=new JLabel();
     l.setIcon(i);
     p.add(l,BorderLayout.CENTER);
     l.setLayout(new BorderLayout());
     frame.setVisible(true);
     p1=new Point(10,20);
     p2=new Point(30,40);
     p3=new Point(10,310);
     p4=new Point(360,40);
     x=2;
     y=1;
     Timer t=new Timer(50,this);
     t.start();
   }
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.setColor(Color.WHITE);
      g.fillRect(p1.x,p1.y,300,5);
      g.setColor(Color.WHITE);
      g.fillRect(p2.x,p2.y,5,250);
      g.setColor(Color.WHITE);
      g.fillRect(p3.x,p3.y,300,5);
      g.setColor(Color.WHITE);
      g.fillRect(p4.x,p4.y,5,250);

   } 
   public static void main(String...s)
   {
     Animate c= new Animate();
     c.setOpaque(false);
     c.l.add(c,BorderLayout.CENTER);   
   }
   /* This function defines the action to be perfrmed on button click 
      and repaints the graphics on the frame in accordance with the timer */
    public void actionPerformed(ActionEvent e)
   { 
      
      if(e.getSource()== b1)
      {  
        frame.dispose();
        new TTTSD(1);
      }
      else if(e.getSource()== b2)
      {
        frame.dispose();
        new TTTSD(2);
      }
      else
      { 
        p1.x+=x;
        p2.y+=y;
        p3.x+=x;
        p4.y+=y;
        if(p1.x<=0||p1.x+300>=getWidth()||p3.x<=0||p3.x+300>=getWidth())
        x=-x;
        if(p2.y<=0||p2.y+250>=getHeight()||p4.y<=0||p4.y+250>=getHeight())
        y=-y;
        repaint();
      }
   }
}
/* Start class defines the functionality of closing button of the frame */
class Start extends WindowAdapter
{
   Animate a;
   Start(Animate b)
   {  
     a=b;
   }
   public void windowClosing(WindowEvent e)
   {
     int c = JOptionPane.showConfirmDialog(null," Are you sure you want to exit ?");
     if(c== JOptionPane.YES_OPTION)
     {  
       System.exit(0);
     } 
     else if(c== JOptionPane.NO_OPTION||c== JOptionPane.CANCEL_OPTION)
     {
       a.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);      
     } 
   }
}
