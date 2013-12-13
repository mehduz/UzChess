package uzchess.core;

import java.util.ArrayList;
import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;
import uzchess.core.model.Piece;

public class MoteurDeJeu {

    public void verifierCoup() {
    }

    public void detecterEchec() 
    {
        //utiliser isMenace de Echiquier...
        // trouver case du roi et demander isMeance...
        Echiquier ech = Echiquier.getInstance();
        if(ech.isMenace( ech.getCaseRoiB() ) ) {
           System.out.println("le Roi Blanc est en echec!");
        }
        if(ech.isMenace( ech.getCaseRoiN() ) ) {
           System.out.println("le Roi Noir est en echec!");
        }
    }
    
    public void detecterMat() {
        Echiquier ech = Echiquier.getInstance();
        Case caseRN=ech.getCaseRoiN();
        Case caseRB=ech.getCaseRoiB();
        byte colRN=caseRN.getColonne();
        byte ligRN=caseRN.getLigne();
        byte cmpt=0;
        Case caseV=new Case();
        for(byte i=-1;i<2;i++){
            for(byte j=-1;j<2;j++){
                caseV.setColonne((byte) (colRN+i));
                caseV.setLigne((byte) (ligRN+j));
                if(ech.isMenace(caseV)){
                    cmpt++;
                    System.out.println("case menacée");
                }
                    
            }
        }
        if(cmpt==9)
        {
            //roi peut pas bouger...
        }
        else //pas mat...
        ;
    }

    public void detecterPat() {
    }

    //renvoie la liste des cases accessible par la piece
     public ArrayList<Case> deplacementPossible(Piece piece){
         throw new UnsupportedOperationException();
     }
    
}
