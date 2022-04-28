import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class XWings extends JPanel

{
   private int xCord;
   private int yCord;
   private static int height = 30;		//height 
   private static int width = 35;		//width 	
   public XWings(int x, int y)
   {
      Random r = new Random();
      xCord = 800 + r.nextInt(50);		//initial x position 
      yCord = 50 + r.nextInt(700);		//initial y position 
   }
	
   public void move() {
      xCord = xCord - 15;				//move the xwing to the left by 15
   }
	
   public void draw(Graphics g) {
   	// g.setColor(Color.gray);
   // 		g.fillOval(xCord, yCord, width, height);	//draws the xwing
      ImageIcon l = new ImageIcon("Xwing3.gif");
      g.drawImage(l.getImage(), xCord, yCord, width, height, null);
   }
	
   public Point getPosition() {
      return new Point(xCord, yCord);		
   }
}
