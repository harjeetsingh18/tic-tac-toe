import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/* Instruction class creates a frame displaying instruction about the game play,
   when instruction button is clicked.  */
class Instructions implements ActionListener
{  
   public void actionPerformed(ActionEvent e)
   {
      JFrame f= new JFrame(" Instructions ");
      f.setSize(400,400);
      f.setLocationRelativeTo(null);
      f.setResizable(false);
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      JLabel l= new JLabel("<html><hr/>Tic Tac Toe - Game Rules<hr/></html>");
      l.setFont(new Font("Comic Sans MS",Font.BOLD,26));
      l.setHorizontalAlignment(SwingConstants.CENTER);
      f.add(l,BorderLayout.NORTH);
      JTextPane l1= new JTextPane();
      l1.setContentType("text/html");
      l1.setText("<html><font face=Segoe Print,size=5><b>Tic Tac Toe</b> is a simple game which,if  played"+
       " optimally by both players, will always result in a tie. However, many"+
       " players still enjoy it.<br/><br/><b> Players -</b><br/> 2 Players<br/><br/>"+
       " <b>Goal -</b><br/>The goal of Tic Tac Toe is to be first player to get"+
       " three Xs or Os in  a row,column or diagonal on a 3x3 grid.<br/><br/><b>Playing Tic Tac Toe"+
       " on a 3x3 board</b><br/>X always goes first.<br/>Players alternate placing Xs"+
       " and Os on the board until either.<br/>(a) one player has three Xs or Os in a row,"+
       " horizontally,vertically or diagonally;or<br/>(b) all nine squares are filled"+
       " <br/><br/>If a player is able to draw three Xs or Os in a row,column or diagonal"+
       " ,that player wins.<br/><br/> If all nine squares are filled and neither player"+
       " has three Xs or Os in a row, horizontally, vertically or diagonally,the game is a draw"+
       " </font></html>");
      l1.setEditable(false);
      JScrollPane js= new JScrollPane(l1);
      js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      f.add(js,BorderLayout.CENTER);
      f.setVisible(true);
   }
}
   