/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.gui;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class RepPieceUnicode implements RepPieceGraphique, Serializable{

    private String symbol;

    public RepPieceUnicode(String symbol) {
        this.symbol = symbol;
    } 
    
    @Override
    public String dessinerPiece() {
        return symbol;
    }
    
}
