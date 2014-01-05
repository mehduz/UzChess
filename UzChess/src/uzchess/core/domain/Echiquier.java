package uzchess.core.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import uzchess.constantes.Couleur;
import uzchess.core.rules.StatutRoi;
import uzchess.core.rules.StatutTour;
import uzchess.core.rules.VerificateurRoi;
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

    public Case[][] getEchiquier() {
        return cases;
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
            caseRoiB = arr;
            sr.getRois().put(p, true);
        }
        if (dep == caseRoiN) {
            caseRoiN = arr;
            sr.getRois().put(p, true);
        }
        if (p.getDeplacement() instanceof VerificateurTour) {
            st.getTours().put(p, true);
        }
        arr.setPiece(p);
        dep.setPiece(null);
    }

    @Override
    public Echiquier clone() {
        Echiquier e = null;
        try {
            e = (Echiquier) super.clone();
            e.cases = new Case[8][8];
            e.piecesN = new HashMap<>();
            e.piecesB = new HashMap<>();
            e.st = new StatutTour();
            e.sr = new StatutRoi();
            for (byte i = 0; i < 8; i++) {
                for (byte j = 0; j < 8; j++) {
                    e.cases[i][j] = this.cases[i][j].clone();
                    Piece p = e.cases[i][j].getPiece();
                    if (p != null) {
                        e.getPieces(p.getCouleur()).put(p, e.cases[i][j]);
                        if (p.getDeplacement() instanceof VerificateurRoi) {
                            e.sr.getRois().put(p, this.sr.getRois().get(this.cases[i][j].getPiece()));
                            ((VerificateurRoi)p.getDeplacement()).setEch(e);
                        } else if (p.getDeplacement() instanceof VerificateurTour) {
                            e.st.getTours().put(p, this.st.getTours().get(this.cases[i][j].getPiece()));
                        }
                    }
                }
            }
            e.caseRoiB = e.cases[this.caseRoiB.getLigne()][this.caseRoiB.getColonne()];
            e.caseRoiN = e.cases[this.caseRoiN.getLigne()][this.caseRoiN.getColonne()];
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return e;
    }

}
