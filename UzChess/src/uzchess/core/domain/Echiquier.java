package uzchess.core.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import uzchess.constantes.Couleur;
import uzchess.core.rules.StatutRoi;
import uzchess.core.rules.StatutTour;
import uzchess.core.rules.VerificateurTour;

public class Echiquier implements Cloneable {

    private Case[][] cases;
    private HashMap<Piece, Case> piecesN;
    private HashMap<Piece, Case> piecesB;

    @SuppressWarnings("FieldMayBeFinal")
    private Case caseRoiB;
    @SuppressWarnings("FieldMayBeFinal")
    private Case caseRoiN;

    private StatutTour st;
    private StatutRoi sr;

    public Echiquier(Case[][] cases, HashMap<Piece, Case> piecesN, HashMap<Piece, Case> piecesB, Case caseRoiN, Case caseRoiB, StatutRoi sr, StatutTour st) {

        this.cases = cases;
        this.piecesN = piecesN;
        this.piecesB = piecesB;
        this.caseRoiB = caseRoiB;
        this.caseRoiN = caseRoiN;
        this.st = st;
        this.sr = sr;
    }
    
    public Case[][] getCases() {
        return cases;
    }

    public HashMap<Piece, Case> getPieces(Couleur c) {
        if (c == Couleur.BLANC) {
            return piecesB;
        }
        return piecesN;
    }

    public Case getCaseRoi(Couleur c) {
        if (c == Couleur.BLANC) {
            return caseRoiB;
        }
        return caseRoiN;
    }

    public void setCaseRoi(Couleur c, Case val) {

        if (c == Couleur.BLANC) {
            caseRoiB = val;
        } else {
            caseRoiN = val;
        }
    }

    public StatutTour getSt() {
        return st;
    }

    public StatutRoi getSr() {
        return sr;
    }

    public boolean detecterEchec(Couleur c) {

        Case caseRoiAChecker = getCaseRoi(c);
        return !(isMenace(caseRoiAChecker).isEmpty());
    }

    public ArrayList<Case> isMenace(Case maCase) {

        Collection<Case> casesAdverses;
        casesAdverses = (maCase.getPiece().getCouleur() == Couleur.BLANC) ? piecesN.values() : piecesB.values();

        ArrayList<Case> maListMenace = new ArrayList<>();

        for (Case c : casesAdverses) {
            Piece p = c.getPiece();
            if (p.getDeplacement().verifierDeplacement(c, maCase)) {
                maListMenace.add(c);
            }
        }
        return maListMenace;
    }

    public void deplacer(Case dep, Case arr) {

        HashMap<Piece, Case> hm;
        HashMap<Piece, Case> hmAdv;
        Piece p = dep.getPiece();
        
        if (p.getCouleur() == Couleur.BLANC) {
            hm = piecesB;
            hmAdv = piecesN;
        } else {
            hm = piecesN;
            hmAdv = piecesB;
        }
        hm.put(p, arr);
        if (arr.getPiece() != null) {
            hmAdv.remove(arr.getPiece());
        }
        if (dep == caseRoiB) {
            sr.getRois().put(p, true);
            byte decal = (byte) (arr.getColonne() - dep.getColonne());
            Piece pdep = dep.getPiece();
            if ( decal == 2 ) {
               deplacer(cases[7][7], cases[7][5]);
            } else if (decal == -2) {
               deplacer(cases[7][0], cases[7][3]);
            }
            caseRoiB = arr;
        }
        if (dep == caseRoiN) {
            sr.getRois().put(p, true);
            byte decal = (byte) (arr.getColonne() - dep.getColonne());
            Piece pdep = dep.getPiece();
            if (decal == 2 ) {
                deplacer(cases[7][7], cases[7][5]);
            } else if (decal == -2 ) {
               deplacer(cases[7][0], cases[7][3]);
            }
            caseRoiN = arr;
        }
        if (p.getDeplacement() instanceof VerificateurTour) {
            st.getTours().put(p, true);
        }
        arr.setPiece(p);
        dep.setPiece(null);
    }
}
