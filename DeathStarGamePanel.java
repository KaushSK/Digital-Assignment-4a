import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;


public class DeathStarGamePanel extends JPanel
{
   //fields
   boolean objectsHaveBeenInitialized = false;
   DeathStar dstar = new DeathStar();
   ArrayList<XWings> xwing;
   int score = 0;					
   int lifeleft = 10;				
   int timeleft = 100;				
   int x, y;
   Timer countTimer;				
   Timer xwingTimer;				
   int delay = 1000;
   int delay2 = 90;
   int shootX, shootY;				
   int collideX, collideY;			
   int currentX=0;

   public DeathStarGamePanel(JFrame frame)
   {
   
      SpaceshipListener monitor = new SpaceshipListener();
      xwing = new ArrayList<XWings>();
      
      for(int i=0; i<5; i++){
         xwing.add(new XWings(x,y));
      }
      addMouseMotionListener(monitor);
      addMouseListener(monitor);
      
      
      countTimer = new Timer(delay, new CountdownTimer());
      xwingTimer = new Timer(delay2, new XWingsTimer());
      Timer theTimer = new Timer(20, new listenerTimer());
   
      setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
      setBackground (Color.white);
      setPreferredSize(new Dimension(1200, 900));
      
      countTimer.start();		//Starts the count down timer from 10 seconds
      xwingTimer.start();		//Starts the timer to move the xwings
      theTimer.start();
   }
   
   public void paintComponent(Graphics g)
   {
     
      super.paintComponent(g);
      ImageIcon i = new ImageIcon("StarBackground.gif");
      g.drawImage(i.getImage(), currentX, 0, getWidth(), getHeight(), null);
      {
         i = new ImageIcon("spacebackground.gif");
         g.drawImage(i.getImage(), currentX+getWidth()-1, 0, getWidth(), getHeight(), null);
      }
      
   
      currentX--;
      if(Math.abs(currentX)>=getWidth())
         currentX=0;
         
      //draws the dstar
      dstar.draw(g,800);
      
      
      //draws the xings
      for (XWings a : xwing)
         a.draw(g);
      
      g.setColor(Color.red);
      //prints 'HIT!!!' when the deat collides with an xwing
      if(lifeleft>0 && timeleft>0 && dstar.getPosition().x==0 && dstar.getPosition().y==0)
      {
         g.drawString("HIT!!!", collideX, collideY);
      } 
            
      //prints Score! when the dest destroys an xwing
      
      if(lifeleft>0 && timeleft>0 && dstar.getShooting())
      {
         g.drawString("Score!", shootX, shootY);
      } 
      else {
         g.drawString("", shootX, shootY);
      }
      
      if (lifeleft == 0 )
      {
         g.drawString("Score: " + score, 20, 40);
         g.drawString("Health: " + lifeleft, 20, 60);
         g.drawString("Time Remain: " + timeleft, 20, 80);
         g.drawString("Game Over", 600, 400);
      } 
      else if (timeleft > 0)
      {
         g.drawString("Score: " + score, 20, 40);
         g.drawString("Health: " + lifeleft, 20, 60);
         g.drawString("Time Remain: "+ timeleft, 20, 80);
      }  
      else 
      {
         g.drawString("Score: " + score, 20, 40);
         g.drawString("Health: " + lifeleft, 20, 60);
         g.drawString("Time Remain: " + timeleft, 20, 80);
         g.drawString("Game Over", 600, 400);
      }      
   }
   
   
  
   private class listenerTimer implements ActionListener
   {
      public void actionPerformed (ActionEvent event)
      {
         repaint();
      }
   }

   
   //Timer counting down from 10 seconds by every 1 second. Timer stops when time remaining = 0.
   private class CountdownTimer implements ActionListener
   {
      public void actionPerformed (ActionEvent event)
      {
         if (timeleft > 0){
            timeleft --;   
         } 
         
         if (timeleft==0) {
            countTimer.stop();
            xwingTimer.stop();
            xwing.clear();
            
            
            
            // if(keyCode == 32)
//             
//                countTimer.start();		
//             xwingTimer.start();		
//             theTimer.start();
         }
      }
   }
   
   private class XWingsTimer implements ActionListener
   {
      public void actionPerformed (ActionEvent event)
      {
         for(int i=0; i<5; i++)
         
         {
            xwing.get(i).move();		//moves all 5 xwings
            
            if(xwing.get(i).getPosition().x < 0)
            {
               xwing.remove(i);
               xwing.add(i, new XWings(x,y));		//removes and adds a new xwing when 1 xwing leaves the screen
            }
            if(xwing.get(i).getPosition().x < dstar.getPosition().x+55 
            
            	   && xwing.get(i).getPosition().y + 30 > dstar.getPosition().y
            	   && xwing.get(i).getPosition().y < dstar.getPosition().y + 50)
            {
               collideX = xwing.get(i).getPosition().x;
               collideY = xwing.get(i).getPosition().y;
               xwing.remove(i);				//removes xwing when collides with destar
               dstar.move(0,0);					//moves destar back to (0,0) after it collides with an xwing
               lifeleft--;					//life remaining - 1 after the collision
               xwing.add(i, new XWings(x,y));		//adds a new xwing after the collision 
            }
            if(xwing.get(i).getPosition().y < dstar.getPosition().y + 25 
            	   && xwing.get(i).getPosition().y + 30 > dstar.getPosition().y + 25
            	   && dstar.getShooting())
            {
               shootX = xwing.get(i).getPosition().x;
               shootY = xwing.get(i).getPosition().y;
               
               xwing.remove(i);				//removes xwing after it is shot by the laser
               score = score + 1;			//updates the score when destar destroys an xwing
               xwing.add(i, new XWings(getWidth(),y));		//adds a new xwing after 1 xwing is destroyed
            }
         }
         if (lifeleft == 0)
         {
            xwingTimer.stop();
            countTimer.stop();
            xwing.clear();
            
         }
      }
   }

   private class SpaceshipListener implements MouseListener, MouseMotionListener
   {
      public void mouseMoved(MouseEvent event)
      {
         dstar.move(event.getX(), event.getY());		//gets and updates the destar's position
         
      }
      
      public void mousePressed(MouseEvent event)
      {
         dstar.setShooting(true);					//draws the laser
      }
   
      public void mouseReleased(MouseEvent event)
      {
         dstar.setShooting(false);				
         shootX =0;
         shootY =0;
      }
    //   
//       public void keyTyped(KeyEvent e) 
//       {
//          int keyCode = e.getKeyCode();
//          System.out.println("Key typed: "+keyCode);
//       }
//       public void keyPressed(KeyEvent e) 
//       {
//          int keyCode = e.getKeyCode();
//          System.out.println("Key pressed: "+keyCode);
//          {
//             if(keyCode == 32)
//                dstar.move(0,0);
//          }
//       }
//       public void keyReleased(KeyEvent e) 
//       {
//                //System.out.println("Key released");
//       }
//             
//       private void displayInfo(KeyEvent e, String keyStatus)
//       {
//               
//               //You should only rely on the key char if the event
//               //is a key typed event.
//          int id = e.getID();
//          String keyString;
//          if (id == KeyEvent.KEY_TYPED) {
//             char c = e.getKeyChar();
//             keyString = "key character = '" + c + "'";
//          } 
//          else {
//             int keyCode = e.getKeyCode();
//             keyString = "key code = " + keyCode
//                           + " ("
//                           + KeyEvent.getKeyText(keyCode)
//                           + ")";
//          }
//               
//          System.out.println("output:: "+ keyString);
//       }
   
      public void mouseDragged(MouseEvent event){}
      public void mouseClicked(MouseEvent event) {}
      public void mouseEntered(MouseEvent event) {}
      public void mouseExited(MouseEvent event) {}
   }
}