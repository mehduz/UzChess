package uzchess.core;

import java.util.HashMap;
import uzchess.constantes.Couleur;
import uzchess.constantes.Pieces;
import uzchess.core.domain.Case;
import uzchess.core.domain.CaseInterUtility;
import uzchess.core.domain.Echiquier;
import uzchess.core.domain.Joueur;
import uzchess.core.domain.Piece;
import uzchess.core.rules.StatutRoi;
import uzchess.core.rules.StatutTour;
import uzchess.core.rules.VerificateurRoi;

public class Initializer {

    public static void initialiserPartie(String njb, String njn, JeuEchecs jeu) {

        Joueur jb = new Joueur(Couleur.BLANC, njb, (byte) 0);
        Joueur jn = new Joueur(Couleur.NOIR, njn, (byte) 0);
        jeu.setJb(jb);
        jeu.setJn(jn);

        Case[][] cases = new Case[8][8];

        byte i, j;
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                cases[i][j] = new Case(i, j, (i % 2 == 0) ? (j % 2 == 0) ? Couleur.BLANC : Couleur.NOIR : (j % 2 == 0) ? Couleur.NOIR : Couleur.BLANC);
            }
        }

        HashMap<Piece, Case> piecesN = new HashMap<>();
        HashMap<Piece, Case> piecesB = new HashMap<>();

        StatutRoi sr = new StatutRoi();
        StatutTour st = new StatutTour();

        for (i = 0; i < 8; i++) {
            cases[1][i].setPiece(PiecesFactory.createPiece(Pieces.PION, Couleur.NOIR));
            cases[6][i].setPiece(PiecesFactory.createPiece(Pieces.PION, Couleur.BLANC));
            piecesN.put(cases[1][i].getPiece(), cases[1][i]);
            piecesB.put(cases[6][i].getPiece(), cases[6][i]);
        }

        cases[0][4].setPiece(PiecesFactory.createPiece(Pieces.ROI, Couleur.NOIR));
        cases[7][4].setPiece(PiecesFactory.createPiece(Pieces.ROI, Couleur.BLANC));

        sr.getRois().put(cases[0][4].getPiece(), false);
        sr.getRois().put(cases[7][4].getPiece(), false);

        piecesN.put(cases[0][4].getPiece(), cases[0][4]);
        piecesB.put(cases[7][4].getPiece(), cases[7][4]);

        cases[0][3].setPiece(PiecesFactory.createPiece(Pieces.REINE, Couleur.NOIR));
        cases[7][3].setPiece(PiecesFactory.createPiece(Pieces.REINE, Couleur.BLANC));

        piecesN.put(cases[0][3].getPiece(), cases[0][3]);
        piecesB.put(cases[7][3].getPiece(), cases[7][3]);

        cases[0][2].setPiece(PiecesFactory.createPiece(Pieces.FOU, Couleur.NOIR));
        cases[7][2].setPiece(PiecesFactory.createPiece(Pieces.FOU, Couleur.BLANC));
        cases[0][5].setPiece(PiecesFactory.createPiece(Pieces.FOU, Couleur.NOIR));
        cases[7][5].setPiece(PiecesFactory.createPiece(Pieces.FOU, Couleur.BLANC));

        piecesN.put(cases[0][2].getPiece(), cases[0][2]);
        piecesN.put(cases[0][5].getPiece(), cases[0][5]);
        piecesB.put(cases[7][2].getPiece(), cases[7][2]);
        piecesB.put(cases[7][5].getPiece(), cases[7][5]);

        cases[0][1].setPiece(PiecesFactory.createPiece(Pieces.CAVALIER, Couleur.NOIR));
        cases[7][1].setPiece(PiecesFactory.createPiece(Pieces.CAVALIER, Couleur.BLANC));
        cases[0][6].setPiece(PiecesFactory.createPiece(Pieces.CAVALIER, Couleur.NOIR));
        cases[7][6].setPiece(PiecesFactory.createPiece(Pieces.CAVALIER, Couleur.BLANC));

        piecesN.put(cases[0][1].getPiece(), cases[0][1]);
        piecesN.put(cases[0][6].getPiece(), cases[0][6]);
        piecesB.put(cases[7][1].getPiece(), cases[7][1]);
        piecesB.put(cases[7][6].getPiece(), cases[7][6]);

        cases[0][0].setPiece(PiecesFactory.createPiece(Pieces.TOUR, Couleur.NOIR));
        cases[7][0].setPiece(PiecesFactory.createPiece(Pieces.TOUR, Couleur.BLANC));
        cases[0][7].setPiece(PiecesFactory.createPiece(Pieces.TOUR, Couleur.NOIR));
        cases[7][7].setPiece(PiecesFactory.createPiece(Pieces.TOUR, Couleur.BLANC));

        st.getTours().put(cases[0][0].getPiece(), false);
        st.getTours().put(cases[7][0].getPiece(), false);
        st.getTours().put(cases[0][7].getPiece(), false);
        st.getTours().put(cases[7][7].getPiece(), false);

        piecesN.put(cases[0][0].getPiece(), cases[0][0]);
        piecesN.put(cases[0][7].getPiece(), cases[0][7]);
        piecesB.put(cases[7][0].getPiece(), cases[7][0]);
        piecesB.put(cases[7][7].getPiece(), cases[7][7]);

        Echiquier ech = new Echiquier(cases, piecesN, piecesB, cases[0][4], cases[7][4], sr, st);
         

        ((VerificateurRoi) cases[0][4].getPiece().getDeplacement()).setEch(ech);
        ((VerificateurRoi) cases[7][4].getPiece().getDeplacement()).setEch(ech);

        CaseInterUtility.setCases(ech.getCases());

        MoteurDeJeu mdj = new MoteurDeJeu(ech, jeu);
        jeu.setEchiquier(ech);
        jeu.setMoteurDeJeu(mdj);

    }
}
