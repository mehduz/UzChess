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
import uzchess.constantes.Couleur;
import uzchess.core.domain.Case;
import uzchess.core.domain.Piece;

/**
 *
 * @author user
 */
public class PanelBoard extends JPanel {
     
    
     private static final Color SQUARES_COLOR_BLACK = new Color(200, 173, 127);
     private static final Color SQUARES_COLOR_WHITE = new Color(245, 245, 220);
     private static final Font PIECE_FONT = new Font("" , Font.BOLD, 50);
     
     private Case[][] cases;
     
     public PanelBoard(Case[][] cases){
        
        super();
        this.cases = cases;
        this.setLayout( new GridLayout(8, 8) );
        for (byte i = 0; i < 8; i ++){
            for ( byte j = 0; j < 8; j ++){
                JPanel square = new JPanel(new BorderLayout());
                Piece p = cases[i][j].getPiece();
                JLabel piece = new JLabel( (p != null && p.getRep() != null )?(String)p.getRep().dessinerPiece(): null );
                piece.setHorizontalAlignment(SwingConstants.CENTER);
                piece.setFont( PIECE_FONT );
                square.add(piece);
                this.add(square);
                square.setBackground(( cases[i][j].getCouleur() == Couleur.NOIR ) ? SQUARES_COLOR_BLACK : SQUARES_COLOR_WHITE );
            }
        }
     }
     
    public void setCases(Case[][] cases) {
        this.cases = cases;
    }
     
     
}
