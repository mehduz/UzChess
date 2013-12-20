package uzchess.core;

import uzchess.constantes.Pieces;
import uzchess.core.model.Piece;
import uzchess.core.rules.VerificateurCavalier;
import uzchess.core.rules.VerificateurFou;
import uzchess.core.rules.VerificateurPion;
import uzchess.core.rules.VerificateurReine;
import uzchess.core.rules.VerificateurRoi;
import uzchess.core.rules.VerificateurTour;

public class PiecesFactory {

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

            piece.setDeplacement(new VerificateurTour());
            return piece;
        }

        if (p == Pieces.CAVALIER) {
            piece.setDeplacement(new VerificateurCavalier());
            return piece;
        }

        if (p == Pieces.ROI) {
            piece.setDeplacement(new VerificateurRoi());
            return piece;
        }

        if (p == Pieces.REINE) {
            piece.setDeplacement(new VerificateurReine());
            return piece;
        }

        return null;
    }
}
