/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.AbstractMap;
import java.util.HashMap;
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
     
    
     private static final Color SQUARES_COLOR_BLACK = new Color(200, 173, 127 );
     private static final Color SQUARES_COLOR_WHITE = new Color(245, 245, 220 );
     private static final Font PIECE_FONT = new Font("" , Font.BOLD, 50);
     
     private VueJeu vue;
     private Case[][] cases;
     private JPanel[][] squares;
     private AbstractMap<JPanel, Case> mapPieces;
     
     public PanelBoard(Case[][] cases, VueJeu vue){
        
        super();
        mapPieces = new HashMap<>();
        this.cases = cases;
        this.vue = vue;
        this.squares = new JPanel[8][8];
        this.setLayout( new GridLayout(8, 8) );
        
         for (byte i = 0; i < 8; i ++){
            for ( byte j = 0; j < 8; j ++){
                squares[i][j] = new JPanel(new BorderLayout());
                this.add(squares[i][j]);
                squares[i][j].setBackground(( cases[i][j].getCouleur() == Couleur.NOIR ) ? SQUARES_COLOR_BLACK : SQUARES_COLOR_WHITE );
                JLabel piece = new JLabel();
                piece.setHorizontalAlignment(SwingConstants.CENTER);
                piece.setFont( PIECE_FONT );
                squares[i][j].add(piece);
                squares[i][j].addMouseListener(this.vue);
            }
         }
         
         this.repaint();
     } 
        
    @Override
    protected void paintComponent(Graphics g){
        
        this.mapPieces = new HashMap<>();
        for (byte i = 0; i < 8; i ++){
            for ( byte j = 0; j < 8; j ++){
                Piece p = cases[i][j].getPiece();
                ((JLabel)squares[i][j].getComponent(0)).setText((p != null  )?(String)p.getRep().dessinerPiece(): null);
                mapPieces.put(squares[i][j], cases[i][j]);
            }
        } 
    } 
    
    public AbstractMap<JPanel, Case> getMapPieces() {
        return mapPieces; 
    }
  
    public void setCases(Case[][] cases) {
        this.cases = cases;
    }

    public Case[][] getCases() {
        return cases;
    }

    public JPanel[][] getSquares() {
        return squares;
    }
    
}
