package uzchess.core;

import uzchess.constantes.Pieces;
import uzchess.core.domain.Piece;
import uzchess.core.rules.*;

public class PiecesFactory {

    private PiecesFactory() {
    }
    
    public static Piece createPiece(Pieces p) {

        Piece piece = new Piece();
        if (p == Pieces.PION) {
            piece.setDeplacement(new VerificateurPion());
            return piece;
        }

        if (p == Pieces.FOU) {

            piece.setDeplacement(new VerificateurFou());
            return piece;
        }

        if (p == Pieces.TOUR) {

            piece.setDeplacement(new VerificateurTour(new StatutTour()));
            return piece;
        }

        if (p == Pieces.CAVALIER) {
            piece.setDeplacement(new VerificateurCavalier());
            return piece;
        }

        if (p == Pieces.ROI) {
            piece.setDeplacement(new VerificateurRoi(new StatutTour(), new StatutRoi()));
            return piece;
        }

        if (p == Pieces.REINE) {
            piece.setDeplacement(new VerificateurReine());
            return piece;
        }

        return null;
    }
}
