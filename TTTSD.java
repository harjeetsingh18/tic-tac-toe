import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/* class TTTSD creates the GUI and Functionality of the game. */
class TTTSD extends WindowAdapter implements ActionListener
{
  JFrame f;
  JButton b1;
  JButton b[][];
  JLabel l;
  JPanel p;
  int current= 0;
  Color c;
  int m,n,players;
  /* creates the GUI of the game.*/
  TTTSD(int x)
  {  
     players=x;
     c= new Color(0,139,139);
     f=new JFrame("Tic Tac Toe 4 2");
     f.setSize(400,400);
     f.setLocationRelativeTo(null);
     f.setResizable(false);
     f.addWindowListener(this);
     b1=new  JButton("Instructions - How to play");
     b1.addActionListener(new Instructions());
     f.add(b1,BorderLayout.NORTH);
     l=new JLabel(" Player 1 (x) and Player 2 (o)");
     f.add(l,BorderLayout.SOUTH);
     p=new JPanel();
     f.add(p,BorderLayout.CENTER);
     p.setLayout(new GridLayout(3,3,5,5));
     p.setBackground(Color.BLACK);
     b=new JButton[3][3];
     for(int i=0;i<3;i++)
     {  for(int j=0;j<3;j++)
        {  
           b[i][j]=new JButton();
           b[i][j].setBackground(new Color(173,216,230));
           b[i][j].setFont(new Font("Arial",Font.BOLD,50));
           b[i][j].addActionListener(this);
           p.add(b[i][j]);
        }
     }
     f.setVisible(true);
   }
   /* Defines the closing button functionality of the frame.*/
   public void windowClosing(WindowEvent e)
   {
     int c = JOptionPane.showConfirmDialog(null," Are you sure you want to exit ?");
     if(c== JOptionPane.YES_OPTION)
     {  
       System.exit(0);
     } 
     else if(c== JOptionPane.NO_OPTION||c== JOptionPane.CANCEL_OPTION)
     {
       f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);      
     } 
   }
   /* when players == 1,it defines action to be performed (i.e X) when the button of the grid is 
      clicked by user and counter move of computer.when players == 2,it defines action to be
      performed (i.e. X or O) when the button of the grid is clicked by one of the player. It 
      also checks for the winner of the game or the game is a draw. */
   public void actionPerformed(ActionEvent e)
   {   
     if(players==1)
     {
      for(int i=0;i<3;i++)
      { 
       for(int j=0;j<3;j++)
       {
          if(e.getSource()==b[i][j])
          {
             if(b[i][j].getText()=="X"||b[i][j].getText()=="O")
             {
                JOptionPane.showMessageDialog(null," WRONG MOVE !!");             
             }
             else
             {
               if(current==0 || current==1)
               {
                 b[i][j].setText("X");
                 m=i;
                 n=j;
                 current=2;
               }
             }
           }
         }
        }
        if(current==2)
        {
           winMove();
           defend();
           computer(m,n);
        }
      }
      else if(players==2)
      {
        for(int i=0;i<3;i++)
        { 
         for(int j=0;j<3;j++)
         {
          if(e.getSource()==b[i][j])
          {
             if(b[i][j].getText()=="X"||b[i][j].getText()=="O")
             {
                JOptionPane.showMessageDialog(null," WRONG MOVE !!");             
             }
             else
             {
               if(current==0 || current==1)
               {
                 b[i][j].setText("X");
                 current=2;
               }
               else if(current==2)
               {
                 b[i][j].setText("O");
                 current=1;
               }
             }
           }
         }
       }
      }
       if(checkWin1())
        { 
          int c = JOptionPane.showConfirmDialog(null," Player 1 wins the game :) \n Do you want to play again ?");
          if(c== JOptionPane.YES_OPTION)
          {  
             f.dispose();
             new TTTSD(players);
          }  
          else if(c== JOptionPane.NO_OPTION)
          {
             System.exit(0);
          }
        } 
        else if(checkWin2())
        {
          int c = JOptionPane.showConfirmDialog(null," Player 2 Wins the game :) \n Do you want to play again ?");
          if(c== JOptionPane.YES_OPTION)
          {  
             f.dispose();
             new TTTSD(players);
          }  
          else if(c== JOptionPane.NO_OPTION)
          {
             System.exit(0);
          }
        }
   
        else if(draw())
        {
           int c = JOptionPane.showConfirmDialog(null," ITS A DRAW :) \n Do you want to play again ?");
          if(c== JOptionPane.YES_OPTION)
          {  
             f.dispose();
             new TTTSD(players);
          }  
          else if(c== JOptionPane.NO_OPTION)
          {
             System.exit(0);
          }     
        }
     }  
     /* This function returns true if player 1(X) wins. */   
     boolean checkWin1()
     {
       /* checks for 3 Xs in a row or a column */
       for(int i=0;i<3;i++)
       {  
          if(b[i][0].getText()=="X" && b[i][1].getText()=="X" && b[i][2].getText()=="X")
          {
             b[i][0].setBackground(c);
             b[i][1].setBackground(c);
             b[i][2].setBackground(c);
             return true;
          }
          else if( b[0][i].getText()=="X" && b[1][i].getText()=="X" && b[2][i].getText()=="X")
          {
             b[0][i].setBackground(c);
             b[1][i].setBackground(c);
             b[2][i].setBackground(c);
             return true;
          }
        }
       /* Checks for 3 Xs in the two diagonals. */
       if(b[0][0].getText()=="X" && b[1][1].getText()=="X" && b[2][2].getText()=="X" )
       {
         b[0][0].setBackground(c);
         b[1][1].setBackground(c);
         b[2][2].setBackground(c);
         return true;
       }
       else if(b[0][2].getText()=="X" && b[1][1].getText()=="X" && b[2][0].getText()=="X")
       {
          b[0][2].setBackground(c);
          b[1][1].setBackground(c);
          b[2][0].setBackground(c);
          return true;
        }
        else
          return false;
     }
     /* This function returns true if player 2(O) wins. */
     boolean checkWin2()
     {
       /* checks for 3 Os in a row or a column */
       for(int i=0;i<3;i++)
       {  
          if(b[i][0].getText()=="O" && b[i][1].getText()=="O" && b[i][2].getText()=="O")
          {
             b[i][0].setBackground(c);
             b[i][1].setBackground(c);
             b[i][2].setBackground(c);
             return true;
          }
          else if( b[0][i].getText()=="O" && b[1][i].getText()=="O" && b[2][i].getText()=="O")
          {
             b[0][i].setBackground(c);
             b[1][i].setBackground(c);
             b[2][i].setBackground(c);
             return true;
          }
        }
       /* Checks for 3 Os in the two diagonals. */
       if(b[0][0].getText()=="O" && b[1][1].getText()=="O" && b[2][2].getText()=="O" )
       {
         b[0][0].setBackground(c);
         b[1][1].setBackground(c);
         b[2][2].setBackground(c);
         return true;
       }
       else if(b[0][2].getText()=="O" && b[1][1].getText()=="O" && b[2][0].getText()=="O")
       {
          b[0][2].setBackground(c);
          b[1][1].setBackground(c);
          b[2][0].setBackground(c);
          return true;
        }
        else
          return false;
     }  
     /* This function returns true if it is a draw */
     boolean draw()
     {
        int k=0;
        for(int i=0;i<3;i++)
        {
          for(int j=0;j<3;j++)
          {
             if(b[i][j].getText().isEmpty())
             { 
               k=1;
               break;
             }
          }
        }
        if(k==0)
        {
          return true;
        }
        else
           return false;
     }
     /* This function stops the user from winning by checking for two Xs and one
        empty box in a row,column or the two diagonals.*/
     void defend()
     {
       /* Checks for two Xs and one empty box in a row */
       for(int i=0;i<3;i++)
       {  
          if((b[i][0].getText()=="X" && b[i][1].getText()=="X"||
             b[i][0].getText()=="X" && b[i][2].getText()=="X"||
             b[i][1].getText()=="X" && b[i][2].getText()=="X") && current==2)
          { 
             if(b[i][0].getText().isEmpty())
             { b[i][0].setText("O");  current=1;  break; }
             else if(b[i][1].getText().isEmpty())
             { b[i][1].setText("O");  current=1;  break;  }
             else if(b[i][2].getText().isEmpty())
             { b[i][2].setText("O");  current=1;  break;}
          }
       }
       /* Checks for two Xs and one empty box in a column */
       for(int i=0;i<3;i++)
       {
            if((b[0][i].getText()=="X" && b[1][i].getText()=="X"||
             b[0][i].getText()=="X" && b[2][i].getText()=="X"||
             b[1][i].getText()=="X" && b[2][i].getText()=="X") && current==2)
          {  
             if(b[0][i].getText().isEmpty())
             { b[0][i].setText("O"); current=1;  break; }
             else if(b[1][i].getText().isEmpty())
             { b[1][i].setText("O"); current=1;   break; }
             else if(b[2][i].getText().isEmpty())
             { b[2][i].setText("O");  current=1;  break; }
             
          }
       }
       /* Checks for two Xs and one empty box in the main diagonal.*/
       if((b[0][0].getText()=="X" && b[1][1].getText()=="X"|| 
          b[0][0].getText()=="X" && b[2][2].getText()=="X"||
          b[1][1].getText()=="X" && b[2][2].getText()=="X") && current==2)
       {
          if(b[0][0].getText().isEmpty())
          { b[0][0].setText("O"); current=1;   }
          else if(b[1][1].getText().isEmpty())
          { b[1][1].setText("O"); current=1;   }
          else if(b[2][2].getText().isEmpty())
          { b[2][2].setText("O");  current=1;  }             
       }
       /* Checks for two Xs and one empty box in the other diagonal.*/
       else if((b[0][2].getText()=="X" && b[1][1].getText()=="X"||
               b[0][2].getText()=="X" && b[2][0].getText()=="X"||
               b[1][1].getText()=="X" && b[2][0].getText()=="X") && current==2)
       {
          if(b[0][2].getText().isEmpty())
          { b[0][2].setText("O"); current=1;   }
          else if(b[1][1].getText().isEmpty())
          { b[1][1].setText("O"); current=1;   }
          else if(b[2][0].getText().isEmpty())
          { b[2][0].setText("O");  current=1;  } 
       }
     }
     /* This function checks if the computer is winning by checking for two Os and
        one empty box in a row,column or the two diagonals.*/
     void winMove()
     {
       /* Checks for two Os and one empty box in a row */
       for(int i=0;i<3;i++)
       {  
          if((b[i][0].getText()=="O" && b[i][1].getText()=="O"||
             b[i][0].getText()=="O" && b[i][2].getText()=="O"||
             b[i][1].getText()=="O" && b[i][2].getText()=="O") && current==2)
          { 
             if(b[i][0].getText().isEmpty())
             { b[i][0].setText("O");  current=1;  break; }
             else if(b[i][1].getText().isEmpty())
             { b[i][1].setText("O");  current=1;  break;  }
             else if(b[i][2].getText().isEmpty())
             { b[i][2].setText("O");  current=1;  break;}
          }
       }
       /* Checks for two Os and one empty box in a column */
       for(int i=0;i<3;i++)
       {
          if((b[0][i].getText()=="O" && b[1][i].getText()=="O"||
          b[0][i].getText()=="O" && b[2][i].getText()=="O"||
          b[1][i].getText()=="O" && b[2][i].getText()=="O") && current==2)
          { 
               if(b[0][i].getText().isEmpty())
               { b[0][i].setText("O"); current=1;  break; }
               else if(b[1][i].getText().isEmpty())
               { b[1][i].setText("O"); current=1;  break; }
               else if(b[2][i].getText().isEmpty())
               { b[2][i].setText("O");  current=1;  break; }
          }
       }
       /* Checks for two Os and one empty box in the main diagonal.*/
       if((b[0][0].getText()=="O" && b[1][1].getText()=="O"|| 
          b[0][0].getText()=="O" && b[2][2].getText()=="O"||
          b[1][1].getText()=="O" && b[2][2].getText()=="O") && current==2)
       {
          if(b[0][0].getText().isEmpty())
          { b[0][0].setText("O"); current=1;   }
          else if(b[1][1].getText().isEmpty())
          { b[1][1].setText("O"); current=1;   }
          else if(b[2][2].getText().isEmpty())
          { b[2][2].setText("O");  current=1;  }             
       }
       /* Checks for two Os and one empty box in the other diagonal.*/
       else if((b[0][2].getText()=="O" && b[1][1].getText()=="O"||
               b[0][2].getText()=="O" && b[2][0].getText()=="O"||
               b[1][1].getText()=="O" && b[2][0].getText()=="O") && current==2)
       {
          if(b[0][2].getText().isEmpty())
          { b[0][2].setText("O"); current=1;   }
          else if(b[1][1].getText().isEmpty())
          { b[1][1].setText("O"); current=1;   }
          else if(b[2][0].getText().isEmpty())
          { b[2][0].setText("O");  current=1;  } 
       }
     }
     /* This function plays the move of computer such that the user cannot use the 
        tricks */
     void computer(int m,int n)
     {
         /* Move of computer if the user places a X at a corner of the grid */
         if((m==0 && n==0||m==0 && n==2||m==2 && n==0||m==2 && n==2)&& 
             (b[1][1].getText().isEmpty()||b[1][1].getText()=="O")&& current==2)
          { 
             if(b[1][1].getText().isEmpty())
             { b[1][1].setText("O");  current=1;  }
             else if(b[1][0].getText().isEmpty())
             { b[1][0].setText("O"); current=1;   }
             else if(b[0][1].getText().isEmpty())
             { b[0][1].setText("O");  current=1;  }
             else if(b[2][1].getText().isEmpty())
             { b[2][1].setText("O"); current=1;   }
             else if(b[1][2].getText().isEmpty())
             { b[1][2].setText("O"); current=1;   } 
             else if(b[0][0].getText().isEmpty())
             { b[0][0].setText("O"); current=1;   }
             else if(b[0][2].getText().isEmpty())
             { b[0][2].setText("O");  current=1;  }
             else if(b[2][2].getText().isEmpty())
             { b[2][2].setText("O"); current=1;   }
             else if(b[2][0].getText().isEmpty())
             { b[2][0].setText("O"); current=1;   }
             
          }
          else if((m==0 && n==0||m==0 && n==2||m==2 && n==0||m==2 && n==2)&& 
                  b[1][1].getText()=="X" && current==2)
          {
             if(b[0][0].getText().isEmpty())
             { b[0][0].setText("O"); current=1;   }
             else if(b[0][2].getText().isEmpty())
             { b[0][2].setText("O");  current=1;  }
             else if(b[2][2].getText().isEmpty())
             { b[2][2].setText("O"); current=1;   }
             else if(b[2][0].getText().isEmpty())
             { b[2][0].setText("O"); current=1;   }
             else if(b[1][0].getText().isEmpty())
             { b[1][0].setText("O"); current=1;   }
             else if(b[0][1].getText().isEmpty())
             { b[0][1].setText("O");  current=1;  }
             else if(b[2][1].getText().isEmpty())
             { b[2][1].setText("O"); current=1;   }
             else if(b[1][2].getText().isEmpty())
             { b[1][2].setText("O"); current=1;   } 
          }
          /* Move of computer if the user places a X at the center of the grid */
          else if((m==1 && n==1)&& current==2)
          {
            if(b[0][0].getText().isEmpty())
            { b[0][0].setText("O"); current=1;   }
            else if(b[0][2].getText().isEmpty())
            { b[0][2].setText("O");  current=1;  }
            else if(b[2][2].getText().isEmpty())
            { b[2][2].setText("O"); current=1;   }
            else if(b[2][0].getText().isEmpty())
            { b[2][0].setText("O"); current=1;   } 
            else if(b[0][1].getText().isEmpty())
            { b[0][1].setText("O"); current=1;   }
            else if(b[1][2].getText().isEmpty())
            { b[1][2].setText("O");  current=1;  }
            else if(b[2][1].getText().isEmpty())
            { b[2][1].setText("O"); current=1;   }
            else if(b[1][0].getText().isEmpty())
            { b[1][0].setText("O"); current=1;   } 
          }
          /* Move of computer if the user places a X at an edge of the grid */
          else if((m==0 && n==1||m==1 && n==2||m==2 && n==1||
                  m==1 && n==0)&& current==2)
          {
            if(b[1][1].getText().isEmpty())
            { b[1][1].setText("O");  current=1;  }
            else if(b[1][1].getText()=="O"&& (b[0][1].getText().isEmpty() &&
                    b[2][1].getText().isEmpty()))
            {  
               b[0][1].setText("O");  current=1;  
            }
            else if(b[1][1].getText()=="O"&& (b[1][0].getText().isEmpty() &&
                    b[1][2].getText().isEmpty()))
            { 
              
               b[1][2].setText("O"); current=1;   
            }
            else
            { 
              
              if(b[0][0].getText().isEmpty())
              { b[0][0].setText("O"); current=1;   }
              else if(b[0][2].getText().isEmpty())
              { b[0][2].setText("O");  current=1;  }
              else if(b[2][2].getText().isEmpty())
              { b[2][2].setText("O"); current=1;   }
              else if(b[2][0].getText().isEmpty())
              { b[2][0].setText("O"); current=1;   } 
            }
        }       
     } 
}

     