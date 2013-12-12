package uzchess.core;

public class VerificateurCavalier extends Deplacement {
   
    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {
        
        int colCaseDep = dep.getColonne();
        int ligCaseDep = dep.getLigne();
        int colCaseArr = arr.getColonne();
        int ligCaseArr = arr.getLigne();
        
        int depVertical = Math.abs(ligCaseArr - ligCaseDep);
        int depHorizontal = Math.abs(colCaseArr - colCaseDep);
        
        return depVertical <= 2 && depHorizontal <= 2 && depHorizontal != depVertical;
    }
 
} 