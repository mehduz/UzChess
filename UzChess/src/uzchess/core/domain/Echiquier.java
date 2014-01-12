package uzchess.core.domain;

import java.util.HashMap;
import uzchess.constantes.Couleur;
import uzchess.core.rules.StatutRoi;
import uzchess.core.rules.StatutTour;
import uzchess.core.rules.VerificateurPion;
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

    private Case gBlanc;
    private Case gNoir;

    public Echiquier(Case[][] cases, HashMap<Piece, Case> piecesN, HashMap<Piece, Case> piecesB, Case caseRoiN, Case caseRoiB, StatutRoi sr, StatutTour st) {

        this.cases = cases;
        this.piecesN = piecesN;
        this.piecesB = piecesB;
        this.caseRoiB = caseRoiB;
        this.caseRoiN = caseRoiN;
        this.st = st;
        this.sr = sr;
        this.gBlanc = null;
        this.gNoir = null;
    }

    public Case getgBlanc() {
        return gBlanc;
    }

    public Case getgNoir() {
        return gNoir;
    }


    public Case getGhost(Couleur c)
    {
        if(c == Couleur.BLANC)
            return gBlanc;
        return gNoir;
    }
    
    public void setGhost(Couleur c, Case ca)
    {
        if(c == Couleur.BLANC)
            gBlanc = ca;
        else
            gNoir = ca;
    }
    
    public void setgBlanc(Case gBlanc) {
        this.gBlanc = gBlanc;
    }

    

    public void setgNoir(Case gNoir) {
        this.gNoir = gNoir;
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
            if (decal == 2 && caseRoiB.getColonne() == 4) {
                deplacer(cases[7][7], cases[7][5]);
            } else if (decal == -2 && caseRoiB.getColonne() == 4) {
                deplacer(cases[7][0], cases[7][3]);
            }
            caseRoiB = arr;
        }
        if (dep == caseRoiN) {
            sr.getRois().put(p, true);
            byte decal = (byte) (arr.getColonne() - dep.getColonne());
            if (decal == 2 && caseRoiN.getColonne() == 4) {
                deplacer(cases[0][7], cases[0][5]);
            } else if (decal == -2 && caseRoiN.getColonne() == 4) {
                deplacer(cases[0][0], cases[0][3]);
            }
            caseRoiN = arr;
        }

        if (p.getDeplacement() instanceof VerificateurTour) {
            st.getTours().put(p, true);
        }
        
        if (dep.getPiece().getDeplacement() instanceof VerificateurPion) {

                //On se déplace de deux et on ghost la putain de case
                byte decal = (byte) (arr.getLigne() - dep.getLigne());
                if (decal == 2) {
                    gNoir = cases[2][dep.getColonne()];
                    gNoir.setGhosted(true);
                } else if (decal == -2) {
                    gBlanc = cases[5][dep.getColonne()];
                    gBlanc.setGhosted(true);
                }
                
                //La case est ghoster on tejj le pion UZ
                if (arr.isGhosted()) {
                    Case ghost;
                    if (dep.getPiece().getCouleur() == Couleur.BLANC) {
                        ghost = cases[arr.getLigne() + 1][arr.getColonne()];
                        piecesN.remove(ghost.getPiece());  
                    }
                    else {
                        ghost = cases[arr.getLigne() - 1][arr.getColonne()];
                        piecesB.remove(ghost.getPiece());
                    }
                    ghost.setPiece(null);
                }
            }
        
        arr.setPiece(p);
        dep.setPiece(null);
    }

}
