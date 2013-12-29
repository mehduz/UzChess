/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author user
 */
public class PanelBoard extends JPanel {
    
    
     private static final Color SQUARES_COLOR_BLACK = new Color(200, 173, 127);
     private static final Color SQUARES_COLOR_WHITE = new Color(245, 245, 220);
     
     private static final Font PIECE_FONT = new Font("" , Font.BOLD, 40);
    
     public PanelBoard(){
        
        super();
        this.setLayout( new GridLayout(8, 8) );
   
        for (int i = 0; i < 64; i++) {
            
            JPanel square = new JPanel( new BorderLayout() );
            /*JLabel piece = new JLabel("\u2654", SwingConstants.CENTER);
            square.add( piece , BorderLayout.CENTER );
            piece.setFont(PIECE_FONT);*/
            this.add( square );
            int row = (i / 8) % 2;
            if (row == 0)
            square.setBackground( i % 2 == 0 ? SQUARES_COLOR_BLACK : SQUARES_COLOR_WHITE );
            else
            square.setBackground( i % 2 == 0 ? SQUARES_COLOR_WHITE : SQUARES_COLOR_BLACK );
            
        }
    }
}
