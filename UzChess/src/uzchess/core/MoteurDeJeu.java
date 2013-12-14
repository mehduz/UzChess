package uzchess.core;

import java.util.ArrayList;
import uzchess.constantes.Couleur;
import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;

public class MoteurDeJeu {

    private boolean echec;

    public void verifierCoup(Case dep, Case arr){
        
    }

    public boolean detecterEchec() 
    {
        JeuEchecs jeu = JeuEchecs.getInstance();
        Couleur c = jeu.getTour();
        Echiquier ech = jeu.getEchiquier();
        Case caseRoiAChecker = (c == Couleur.BLANC ) ? ech.getCaseRoiN() : ech.getCaseRoiB();
        return !(ech.isMenace(caseRoiAChecker).isEmpty());
    }
    
    public boolean detecterMat() {
        
        Echiquier ech = JeuEchecs.getInstance().getEchiquier();
        
        Case caseRN=ech.getCaseRoiN();
        Case caseRB=ech.getCaseRoiB();
        Couleur c = JeuEchecs.getInstance().getTour();
        Case caseRoiAChecker = (c == Couleur.BLANC ) ? ech.getCaseRoiB() : ech.getCaseRoiN();
        
        ArrayList<Case> depPossible = ech.deplacementPossible(caseRoiAChecker.getPiece());
        
        if(!depPossible.isEmpty()){
            for(Case ca : depPossible){
                if(ech.isMenace(ca).isEmpty()){
                    return false;
                }
            }
        }
        
        return false;
    }

    public void detecterPat() {
    } 
    
    public boolean isThereEchec() {
        return echec;
    }
    
}
