  import javax.swing.JFrame;
    public class DriverDeathStar
   {
       public static void main(String[] args)
      {
		   int maxX = 1200; 
			int maxY = 900;
			
         JFrame frame = new JFrame("Death Star");
         frame.setSize(maxX, maxY);
         frame.setLocation(0,0);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new DeathStarGamePanel(frame));
         frame.setVisible(true);
      }
   }