package uzchess.core.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import uzchess.constantes.Couleur;

public class Echiquier {

    private Case[][] cases;

    private HashMap<Piece, Case> piecesN;
    private HashMap<Piece, Case> piecesB;
    
    private Case caseRoiB;
    private Case caseRoiN;

    
    public Case[][] getEchiquier(Case c) {
        return cases;
    }

    public void setEchiquier(Case[][] echiquier) {
        this.cases = echiquier;
    }

    public Case[][] getCases() {
        return cases;
    }

    public void setCases(Case[][] cases) {
        this.cases = cases;
    }

    public HashMap<Piece, Case> getPieces(Couleur c) {
        if (c == Couleur.BLANC) {
            return piecesB;
        }
        return piecesN;
    }

    public Case getCaseRoiB() {
        return caseRoiB;
    }

    public Case getCaseRoiN() {
        return caseRoiN;
    }

    public HashMap<Piece, Case> getPiecesN() {
        return piecesN;
    }

    public HashMap<Piece, Case> getPiecesB() {
        return piecesB;
    }
    
    public void setPiecesN(HashMap<Piece, Case> piecesN) {
        this.piecesN = piecesN;
    }

    public void setPiecesB(HashMap<Piece, Case> piecesB) {
        this.piecesB = piecesB;
    }

    public void setCaseRoiB(Case caseRoiB) {
        this.caseRoiB = caseRoiB;
    }

    public void setCaseRoiN(Case caseRoiN) {
        this.caseRoiN = caseRoiN;
    }
    
    public ArrayList<Case> isMenace(Case maCase) {

        Collection<Case> casesAdverses;
        casesAdverses = (maCase.getCouleur() == Couleur.BLANC) ? piecesB.values() : piecesN.values();

        ArrayList<Case> maListMenace = new ArrayList<>();

        for (Case c : casesAdverses) {
            Piece p = c.getPiece();
            if (p.getDeplacement().verifierDeplacement(c, maCase, false)) {
                maListMenace.add(c);
            }
        }
        return maListMenace;
    }

    public ArrayList<Case> deplacementPossible(Piece piece) {

        ArrayList<Case> casesP = new ArrayList<>();

        Case caseV, dep;
        dep = getPieces(piece.getCouleur()).get(piece);

        for (byte i = 0; i < 8; i++) {
            for (byte j = 0; j < 8; j++) {
                caseV = getCases()[i][j];
                if (piece.getDeplacement().verifierDeplacement(dep, caseV, false)) {
                    casesP.add(caseV);
                }
            }
        }
        return casesP;
    }
}
