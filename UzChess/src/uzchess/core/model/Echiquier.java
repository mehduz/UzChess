package uzchess.core.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.core.rules.Deplacement;

public class Echiquier {

    private Case[][] cases;
    
    private HashMap<Piece, Case> piecesN;
    private HashMap<Piece, Case> piecesB;
    private Case caseRoiB;
    private Case caseRoiN;
    private boolean roiBMoved;
    private boolean roiNMoved;
    
    private Echiquier() {
    }

    public boolean getCasesInter(Case dep, Case arr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class SingletonHolder {

        private final static Echiquier instance = new Echiquier();
    }

    public static Echiquier getInstance() {
        return SingletonHolder.instance;
    }
    
    public ArrayList<Case> getCasesInter(Case caseDep, Case caseArr, Direction type){
         throw new UnsupportedOperationException();
    }

    public boolean verifCasesInter(ArrayList<Case> aVerif) {

        throw new UnsupportedOperationException();
        /*boolean verif = false;
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
        */
    }

    /*private boolean verifO(Case caseDep, Case caseArr) {
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
    }*/

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
    
    public boolean isRoiMoved(Couleur c){
        if( c == Couleur.BLANC )
            return isRoiBMoved();
        return isRoiNMoved();
    }

    private boolean isRoiBMoved() {
        return roiBMoved;
    }

    private boolean isRoiNMoved() {
        return roiNMoved;
    }
    
    public void setRoiMoved(Couleur color) {
        if(color == Couleur.BLANC){
            setRoiBMoved( true );
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
    
    public ArrayList<Case> isMenace(Case maCase)
    {
        Collection <Case> casesAdverses;
        if( maCase.getCouleur() == Couleur.BLANC )
            casesAdverses = piecesB.values();
        else
            casesAdverses = piecesN.values();
        
        ArrayList<Case> maListMenace = new ArrayList<>();
                
        for( Case c : casesAdverses ){
            Piece p = c.getPiece();
            boolean check = p.getDeplacement().verifierDeplacement(c, maCase);
            if( check == true )
                maListMenace.add(c);
        }
        return maListMenace;
    }
    
    
}
