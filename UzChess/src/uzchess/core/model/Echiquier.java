package uzchess.core.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;

public class Echiquier {

    private Case[][] cases;

    private HashMap<Piece, Case> piecesN;
    private HashMap<Piece, Case> piecesB;
    private Case caseRoiB;
    private Case caseRoiN;
    private boolean roiBMoved;
    private boolean roiNMoved;

    
    public ArrayList<Case> getCasesInter(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        Direction maDir;
        maDir = getDirection(caseDep, caseArr);
        switch (maDir) {

            case O:
                maListInter = getO(caseDep, caseArr);
                break;
            case NO:
                maListInter = getNO(caseDep, caseArr);
                break;
            case N:
                maListInter = getN(caseDep, caseArr);
                break;
            case NE:
                maListInter = getNE(caseDep, caseArr);
                break;
            case E:
                maListInter = getE(caseDep, caseArr);
                break;
            case SE:
                maListInter = getSE(caseDep, caseArr);
                break;
            case S:
                maListInter = getS(caseDep, caseArr);
                break;
            case SO:
                maListInter = getSO(caseDep, caseArr);
            default:
                break;
        }
        return maListInter;
    }

    private ArrayList<Case> getO(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iCol = caseDep.getColonne();
        while (cases[caseDep.getLigne()][iCol] != null && iCol < caseArr.getColonne()) {
            maListInter.add(cases[caseDep.getLigne()][iCol]);
            iCol--;
        }
        return maListInter;
    }

    private ArrayList<Case> getNO(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iCol = caseDep.getColonne();
        byte iLig = caseDep.getLigne();
        while (cases[iLig][iCol] != null && iCol < caseArr.getColonne() && iLig < caseArr.getLigne()) {
            maListInter.add(cases[iLig][iCol]);
            iLig--;
            iCol--;
        }
        return maListInter;
    }

    private ArrayList<Case> getN(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iLig = caseDep.getLigne();
        while (cases[iLig][caseDep.getColonne()] != null && iLig < caseArr.getColonne()) {
            maListInter.add(cases[iLig][caseDep.getColonne()]);
            iLig--;
        }
        return maListInter;
    }

    private ArrayList<Case> getNE(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iCol = caseDep.getColonne();
        byte iLig = caseDep.getLigne();
        while (cases[iLig][iCol] != null && iCol < caseArr.getColonne() && iLig < caseArr.getLigne()) {
            maListInter.add(cases[iLig][iCol]);
            iLig--;
            iCol++;
        }
        return maListInter;
    }

    private ArrayList<Case> getE(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iCol = caseDep.getColonne();
        while (cases[caseDep.getLigne()][iCol] != null && iCol < caseArr.getColonne()) {
            maListInter.add(cases[caseDep.getLigne()][iCol]);
            iCol++;
        }
        return maListInter;
    }

    private ArrayList<Case> getSE(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iCol = caseDep.getColonne();
        byte iLig = caseDep.getLigne();
        while (cases[iLig][iCol] != null && iCol < caseArr.getColonne() && iLig < caseArr.getLigne()) {
            maListInter.add(cases[iLig][iCol]);
            iLig++;
            iCol++;
        }
        return maListInter;
    }

    private ArrayList<Case> getSO(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iCol = caseDep.getColonne();
        byte iLig = caseDep.getLigne();
        while (cases[iLig][iCol] != null && iCol < caseArr.getColonne() && iLig < caseArr.getLigne()) {
            maListInter.add(cases[iLig][iCol]);
            iLig++;
            iCol--;
        }
        return maListInter;
    }

    private ArrayList<Case> getS(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iLig = caseDep.getLigne();
        while (cases[iLig][caseDep.getColonne()] != null && iLig < caseArr.getColonne()) {
            maListInter.add(cases[iLig][caseDep.getColonne()]);
            iLig++;
        }
        return maListInter;
    }

    public boolean verifCasesInter(ArrayList<Case> aVerif) {

        for (Case c : aVerif) {
            if (c.getPiece() != null) {
                return false;
            }
        }
        return true;
    }

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

    public HashMap<Piece, Case> getPiecesN() {
        return piecesN;
    }

    public HashMap<Piece, Case> getPiecesB() {
        return piecesB;
    }

    public Case getCaseRoiB() {
        return caseRoiB;
    }

    public Case getCaseRoiN() {
        return caseRoiN;
    }

    public boolean isRoiMoved(Couleur c) {
        if (c == Couleur.BLANC) {
            return isRoiBMoved();
        }
        return isRoiNMoved();
    }

    private boolean isRoiBMoved() {
        return roiBMoved;
    }

    private boolean isRoiNMoved() {
        return roiNMoved;
    }

    public void setRoiMoved(Couleur color) {
        if (color == Couleur.BLANC) {
            setRoiBMoved(true);
            return;
        }
        setRoiNMoved(true);
    }

    private void setRoiBMoved(boolean roiBMoved) {
        this.roiBMoved = roiBMoved;
    }

    private void setRoiNMoved(boolean roiNMoved) {
        this.roiNMoved = roiNMoved;
    }

    public ArrayList<Case> isMenace(Case maCase) {

        Collection<Case> casesAdverses;
        casesAdverses = (maCase.getCouleur() == Couleur.BLANC) ? piecesB.values() : piecesN.values();

        ArrayList<Case> maListMenace = new ArrayList<>();

        for (Case c : casesAdverses) {
            Piece p = c.getPiece();
            if (p.getDeplacement().verifierDeplacement(c, maCase)) {
                maListMenace.add(c);
            }
        }
        return maListMenace;
    }

    public Direction getDirection(Case dep, Case arr) {

        int lDep = dep.getLigne();
        int cDep = dep.getColonne();
        int lArr = arr.getLigne();
        int cArr = arr.getColonne();

        Direction dir;

        if (lArr == lDep) {
            dir = (cArr > cDep) ? Direction.E : Direction.O;
        } else if (cArr == cDep) {
            dir = (lArr > lDep) ? Direction.S : Direction.N;
        } else if (lArr > lDep) {
            dir = (cArr > cDep) ? Direction.SE : Direction.SO;
        } else {
            dir = (cArr > cDep) ? Direction.NE : Direction.NO;
        }

        return dir;
    }
    
    public ArrayList<Case> deplacementPossible(Piece piece){
         
         ArrayList<Case> casesP =new ArrayList<>();
    
         Case caseV, dep;
         dep = (piece.getCouleur()==Couleur.BLANC)?getPiecesB().get(piece):getPiecesN().get(piece);
        
         for(byte i=0; i<8 ; i++){
            for( byte j=0; j<8; j++){
                caseV = getCases()[i][j];
                if(piece.getDeplacement().verifierDeplacement(dep, caseV))
                    casesP.add(caseV);
            }
        }
        return casesP;
     }

}
