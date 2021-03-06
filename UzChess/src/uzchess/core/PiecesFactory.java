package uzchess.core;

import uzchess.constantes.Couleur;
import uzchess.constantes.Pieces;
import uzchess.constantes.PiecesUnicode;
import uzchess.core.domain.Piece;
import uzchess.core.rules.*;
import uzchess.gui.RepPieceUnicode;

public class PiecesFactory {

    public static Piece createPiece(Pieces p, Couleur c) {

        Piece piece = new Piece();
        if (p == Pieces.PION) {
            piece.setDeplacement(new VerificateurPion());
            piece.setCouleur(c);
            piece.setRep( new RepPieceUnicode(( c == Couleur.BLANC )? PiecesUnicode.PION_B : PiecesUnicode.PION_N));   
            piece.setValue((byte)1);
            return piece;
        }

        if (p == Pieces.FOU) {

            piece.setDeplacement(new VerificateurFou());
            piece.setRep( new RepPieceUnicode(( c == Couleur.BLANC )? PiecesUnicode.FOU_B : PiecesUnicode.FOU_N));  
            piece.setCouleur(c);
            piece.setValue((byte)3);
            return piece;
        }

        if (p == Pieces.TOUR) {

            piece.setDeplacement(new VerificateurTour());
            piece.setCouleur(c);
            piece.setRep( new RepPieceUnicode(( c == Couleur.BLANC )? PiecesUnicode.TOUR_B : PiecesUnicode.TOUR_N));
            piece.setValue((byte)4);
            return piece;
            
        }

        if (p == Pieces.CAVALIER) {
            piece.setRep( new RepPieceUnicode(( c == Couleur.BLANC )? PiecesUnicode.CAVALIER_B : PiecesUnicode.CAVALIER_N));
            piece.setCouleur(c);
            piece.setDeplacement(new VerificateurCavalier());
            piece.setValue((byte)5);
            return piece;
        }

        if (p == Pieces.ROI) {
            piece.setDeplacement(new VerificateurRoi());
            piece.setCouleur(c);
            piece.setRep( new RepPieceUnicode(( c == Couleur.BLANC )? PiecesUnicode.ROI_B : PiecesUnicode.ROI_N));   
            return piece;
        }

        if (p == Pieces.REINE) {
            piece.setDeplacement(new VerificateurReine());
            piece.setCouleur(c);
            piece.setRep( new RepPieceUnicode(( c == Couleur.BLANC )? PiecesUnicode.DAME_B : PiecesUnicode.DAME_N));
            piece.setValue((byte)8);
            return piece;
        }

        return null;
    }
}
