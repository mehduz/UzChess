package uzchess.core.model;

import java.util.HashMap;
import uzchess.constantes.Direction;

public class Echiquier {

    private Case[][] cases;
    
    private HashMap<Piece, Case> piecesN;
    private HashMap<Piece, Case> piecesB;
    private Case caseRoiB;
    private Case caseRoiN;
    
    private Echiquier() {
    }

    private static class SingletonHolder {

        private final static Echiquier instance = new Echiquier();
    }

    public static Echiquier getInstance() {
        return SingletonHolder.instance;
    }

    public boolean verifCasesInter(Case caseDep, Case caseArr, Direction type) {

        boolean verif = false;
        switch (type) {

            case O:
                verif = verifO(caseDep, caseArr);
                break;
            case NO:
                verif = verifNO(caseDep, caseArr);
                break;
            case N:
                verif = verifN(caseDep, caseArr);
                break;
            case NE:
                verif = verifNE(caseDep, caseArr);
                break;
            case E:
                verif = verifE(caseDep, caseArr);
                break;
            case SE:
                verif = verifSE(caseDep, caseArr);
                break;
            case S:
                verif = verifS(caseDep, caseArr);
                break;
            case SO:
                verif = verifSO(caseDep, caseArr);
            default:
                break;
        }
        return verif;
    }

    private boolean verifO(Case caseDep, Case caseArr) {
        byte iCol = caseDep.getColonne();
        while(cases[caseDep.getLigne()][iCol] != null && iCol < caseArr.getColonne())
            iCol--;
        return iCol == caseArr.getColonne();
    }

    private boolean verifNO(Case caseDep, Case caseArr) {
        byte iCol = caseDep.getColonne();
        byte iLig = caseDep.getLigne();
        while(cases[iLig][iCol] != null && iCol < caseArr.getColonne() && iLig < caseArr.getLigne())
        {
            iLig--;
            iCol--;
        }
        return iCol == caseArr.getColonne() && iLig >= caseArr.getColonne();
    }

    private boolean verifN(Case caseDep, Case caseArr) {
        byte iLig = caseDep.getLigne();
        while(cases[iLig][caseDep.getColonne()] != null && iLig < caseArr.getColonne())
            iLig--;
        return iLig == caseArr.getLigne();
    }

    private boolean verifNE(Case caseDep, Case caseArr) {
        byte iCol = caseDep.getColonne();
        byte iLig = caseDep.getLigne();
        while(cases[iLig][iCol] != null && iCol < caseArr.getColonne() && iLig < caseArr.getLigne())
        {
            iLig--;
            iCol++;
        }
        return iCol == caseArr.getColonne() && iLig == caseArr.getColonne();
    }

    private boolean verifE(Case caseDep, Case caseArr) {
        byte iCol = caseDep.getColonne();
        while(cases[caseDep.getLigne()][iCol] != null && iCol < caseArr.getColonne())
            iCol++;
        return iCol == caseArr.getColonne();
    }

    private boolean verifSE(Case caseDep, Case caseArr) {
        byte iCol = caseDep.getColonne();
        byte iLig = caseDep.getLigne();
        while(cases[iLig][iCol] != null && iCol < caseArr.getColonne() && iLig < caseArr.getLigne())
        {
            iLig++;
            iCol++;
        }
        return iCol == caseArr.getColonne() && iLig == caseArr.getColonne();
    }
    
    private boolean verifSO(Case caseDep, Case caseArr) {
        byte iCol = caseDep.getColonne();
        byte iLig = caseDep.getLigne();
        while(cases[iLig][iCol] != null && iCol < caseArr.getColonne() && iLig < caseArr.getLigne())
        {
            iLig++;
            iCol--;
        }
        return iCol == caseArr.getColonne() && iLig == caseArr.getColonne();
    }
    private boolean verifS(Case caseDep, Case caseArr) {
        byte iLig = caseDep.getLigne();
        while(cases[iLig][caseDep.getColonne()] != null && iLig < caseArr.getColonne())
            iLig++;
        return iLig == caseArr.getLigne();
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
    
}
