package uzchess.core;

import java.util.HashMap;
import uzchess.constantes.Couleur;
import uzchess.constantes.Pieces;
import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;
import uzchess.core.model.Joueur;
import uzchess.core.model.Piece;

public class Initializer {

    public static void initialiserPartie(String njb, String njn, MoteurDeJeu mdj, Echiquier ech, JeuEchecs jeu) {

        Joueur jb = new Joueur(Couleur.BLANC, njb, (byte) 0);
        Joueur jn = new Joueur(Couleur.NOIR, njn, (byte) 0);

        Case[][] cases = new Case[8][8];
        for (byte i = 0; i < 8; i++) {
            for (byte j = 0; i < 8; i++) {
                cases[i][j] = new Case(i, j);
                if ((i % 2 == 0) && (j % 2 == 0)) {
                    cases[i][j].setCouleur(Couleur.BLANC);
                } else {
                    cases[i][j].setCouleur(Couleur.NOIR);
                }
            }
        }

        HashMap<Piece, Case> piecesN = new HashMap<>();
        HashMap<Piece, Case> piecesB = new HashMap<>();

        for (byte i = 0; i < 7; i++) {

            Piece p = PiecesFactory.createPiece(Pieces.PION);
            cases[1][i].setPiece(PiecesFactory.createPiece(Pieces.PION));
            cases[6][i].setPiece(PiecesFactory.createPiece(Pieces.PION));
            piecesN.put(cases[1][i].getPiece(), cases[6][i]);
            piecesB.put(cases[6][i].getPiece(), cases[6][i]);
        }

        cases[0][4].setPiece(PiecesFactory.createPiece(Pieces.ROI));
        cases[7][4].setPiece(PiecesFactory.createPiece(Pieces.ROI));

        piecesN.put(cases[0][4].getPiece(), cases[0][4]);
        piecesB.put(cases[7][4].getPiece(), cases[7][4]);

        cases[0][3].setPiece(PiecesFactory.createPiece(Pieces.REINE));
        cases[7][3].setPiece(PiecesFactory.createPiece(Pieces.REINE));

        piecesN.put(cases[0][3].getPiece(), cases[0][3]);
        piecesB.put(cases[7][3].getPiece(), cases[7][3]);

        cases[0][2].setPiece(PiecesFactory.createPiece(Pieces.FOU));
        cases[7][2].setPiece(PiecesFactory.createPiece(Pieces.FOU));
        cases[0][5].setPiece(PiecesFactory.createPiece(Pieces.FOU));
        cases[7][5].setPiece(PiecesFactory.createPiece(Pieces.FOU));

        piecesN.put(cases[0][3].getPiece(), cases[0][3]);
        piecesN.put(cases[0][5].getPiece(), cases[0][5]);
        piecesB.put(cases[7][3].getPiece(), cases[7][3]);
        piecesB.put(cases[7][5].getPiece(), cases[7][5]);

        cases[0][1].setPiece(PiecesFactory.createPiece(Pieces.CAVALIER));
        cases[7][1].setPiece(PiecesFactory.createPiece(Pieces.CAVALIER));
        cases[0][6].setPiece(PiecesFactory.createPiece(Pieces.CAVALIER));
        cases[7][6].setPiece(PiecesFactory.createPiece(Pieces.CAVALIER));

        piecesN.put(cases[0][1].getPiece(), cases[0][1]);
        piecesN.put(cases[0][6].getPiece(), cases[0][6]);
        piecesB.put(cases[7][1].getPiece(), cases[7][1]);
        piecesB.put(cases[7][6].getPiece(), cases[7][6]);

        cases[0][0].setPiece(PiecesFactory.createPiece(Pieces.TOUR));
        cases[7][0].setPiece(PiecesFactory.createPiece(Pieces.TOUR));
        cases[0][7].setPiece(PiecesFactory.createPiece(Pieces.TOUR));
        cases[7][7].setPiece(PiecesFactory.createPiece(Pieces.TOUR));

        piecesN.put(cases[0][0].getPiece(), cases[0][0]);
        piecesN.put(cases[0][7].getPiece(), cases[0][7]);
        piecesB.put(cases[7][0].getPiece(), cases[7][0]);
        piecesB.put(cases[7][7].getPiece(), cases[7][7]);

        for (Piece p : piecesN.keySet()) {
            p.setCouleur(Couleur.NOIR);
        }
        for (Piece p : piecesN.keySet()) {
            p.setCouleur(Couleur.BLANC);
        }

        ech = new Echiquier(cases, piecesN, piecesB, cases[0][4], cases[7][4]);
        mdj = new MoteurDeJeu(ech, jeu);
       
    }
}
