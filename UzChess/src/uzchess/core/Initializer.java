/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.core;

import java.util.HashMap;
import uzchess.constantes.Couleur;
import uzchess.constantes.Pieces;
import uzchess.core.model.Case; 
import uzchess.core.model.Echiquier;
import uzchess.core.model.Joueur;
import uzchess.core.model.Piece;

/**
 *
 * @author user
 */
public class Initializer {
    
    
    
    public static void initialiserPartie(String njb, String njn){
        
        JeuEchecs jeu = null;
        MoteurDeJeu mdj = null;
        Echiquier ech = null;
        
        //creation des joueurs
        Joueur jb = new Joueur(Couleur.BLANC, njb, (byte)0);
        Joueur jn = new Joueur(Couleur.NOIR, njn, (byte)0);
        
        //creation des cases
        Case[][] cases = new Case[8][8];
        for( byte i = 0; i < 8; i++){
            for (byte j = 0; i < 8; i++){
                cases[i][j] = new Case( i , j );
                if( (i % 2 == 0) && (j % 2 == 0) ) {
                    cases[i][j].setCouleur(Couleur.BLANC);
                } else {
                    cases[i][j].setCouleur(Couleur.NOIR);
                }
            }
        }
        
        //creation des pieces
        HashMap<Piece, Case> piecesN = new HashMap<>();
        HashMap<Piece, Case> piecesB = new HashMap<>();
       
        
            //on positionne les pions noies
            for(byte i = 0; i < 7; i++){
       
                Piece p = PiecesFactory.createPiece(Pieces.PION);
                cases[1][i].setPiece(PiecesFactory.createPiece(Pieces.PION));
                cases[6][i].setPiece(PiecesFactory.createPiece(Pieces.PION));
                piecesN.put(cases[6][i].getPiece(), cases[6][i]);
                piecesB.put(cases[1][i].getPiece(), cases[6][i]);
            }
           
            //on positionne les pieces speciales
            Piece roiN , reineN , fouN , cavalierN , tourN, roiB , reineB , fouB, cavalierB , tourB;
            
            cases[0][4].setPiece(PiecesFactory.createPiece(Pieces.ROI));
            cases[7][4].setPiece(PiecesFactory.createPiece(Pieces.ROI));
           
            piecesN.put(cases[0][4].getPiece(), cases[0][4]);
            piecesB.put(cases[7][4].getPiece(), cases[7][4]);
        }
    }
            