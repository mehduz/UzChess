/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.gui;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author user
 */
public class VueJeu extends JFrame{
    
     public int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
     public int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
     private static final int FRAME_WIDTH = 860;
     private static final int FRAME_HEIGHT = 860;
    
    
    public VueJeu(String title){
         
         this.setSize(FRAME_WIDTH, FRAME_HEIGHT);    
         this.setLocation( (screenWidth - FRAME_WIDTH)/ 2, (screenHeight - FRAME_HEIGHT) / 2);
         this.setResizable(true);
         this.setTitle(title);
         this.setUndecorated(false);
         this.setResizable(true);
         this.setVisible(true);
         this.getContentPane().add(new BoardPanel());
    }
   
}
