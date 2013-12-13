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
    
    private Echiquier() {
    }

    public ArrayList<Case> getCasesInter(Case dep, Case arr) {
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
    
    public Direction getDirection(Case dep, Case arr){
        
        int lDep = dep.getLigne();
        int cDep = dep.getColonne();
        int lArr= arr.getLigne();
        int cArr = arr.getColonne();
        
        Direction dir;
        
        if(lArr == lDep){
                dir = ( cArr > cDep) ? Direction.E : Direction.O;
        }else if(cArr == cDep){
                dir = ( lArr > lDep) ? Direction.S : Direction.N;
        }else if (lArr > lDep ){
                dir = ( cArr > cDep) ? Direction.SE : Direction.SO;
        }else{
                dir = ( cArr > cDep) ? Direction.NE : Direction.NO;
        } 
        
        return dir;
    }
    
    
}
