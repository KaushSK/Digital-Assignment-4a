import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class DeathStar
{
   private int xCord;
   private int yCord;
   private static int height = 97;		//height of the Deathstar
   private static int width = 97;		//width of the Deathstar
   private boolean shooting;
	
   public DeathStar()
   {
      xCord = 0;				//initial x position
      yCord = 0;				//initial y position
      shooting = false;
   }
	
   public void move(int x, int y) 
   {
      xCord = x;				//updated x position of the Deathstar
      yCord = y;				//updated y position of the Deathstar
   }
	
   public void setShooting(boolean value) 
   {
      shooting = value;
   }
	
   public Boolean getShooting() 
   {
      return Boolean.valueOf(shooting);
   }
	
   public Point getPosition() 
   {
      return new Point(xCord+15, yCord+15);
   }
	
   public void draw(Graphics g, int laserlength) 
   {
      // g.setColor(new Color(105, 105, 105));
   //       g.fillOval(xCord, yCord, 80, 80);		//Deathstar body
   //       g.setColor(Color.black);
   //       g.drawLine(xCord+4, yCord + height, xCord + width +18, yCord + height);
   //       g.setColor(new Color(90, 89, 88));
   //       g.fillOval(xCord + 45 , yCord + 10, 30, 30);		//hole on the Deathstar
   //       g.setColor(new Color(53, 53, 51));
   //       g.fillOval(xCord + 48 , yCord + 10, 25, 25);
   
    ImageIcon l = new ImageIcon("deathstarpic.png");
         g.drawImage(l.getImage(), xCord+15, yCord+15, width, height, null);
         
          g.setColor(Color.green);
         
          if (shooting)
      {
               		//Deathstar's laser beam
               Graphics2D g2 = (Graphics2D) g.create();
               g2.setStroke(new BasicStroke(5)); 
               g2.setColor(Color.green);
               g2.drawLine(xCord + width+5, yCord +38 , laserlength, yCord +38);
               g2.dispose();
               }
      
        
      
      }
         
   }
