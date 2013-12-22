package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.core.domain.Case;

public class VerificateurPionPrend implements Deplacement{

    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {
        
        byte ligDep = dep.getLigne();
        byte ligArr = arr.getLigne();
                        
        Couleur c = dep.getCouleur();
        Direction dir = dep.getDirection(arr);
        
        boolean condition1 = c == Couleur.BLANC && ( dir == Direction.NE || dir == Direction.NO );
        boolean condition2 = c == Couleur.NOIR && ( dir == Direction.SE || dir == Direction.SO );
        boolean condition3 = Math.abs(ligArr - ligDep) == 1;
           
        return condition1 && condition2 && condition3;
    }
    
}
