package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;

public class VerificateurPion implements Deplacement {

//penser  dep début, prise diago
    
    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {

        Couleur c = dep.getCouleur();
        if (arr.getPiece() != null) {
            return verifAvance(dep , arr, c);
        }
        return verifPrend(dep, arr, c);
    }

    private boolean verifAvance(Case dep, Case arr, Couleur c) {
        
        byte ligDep = dep.getLigne();
        byte ligArr = arr.getLigne();
        
        Direction dir = Echiquier.getInstance().getDirection(dep, arr);
        if ( c == Couleur.BLANC && dir != Direction.N )
            return false;
        if ( c == Couleur.NOIR && dir != Direction.S )
            return false;
        if( Math.abs(ligArr - ligDep) != 1)
            return false;
        return true;
    }
    
    private boolean verifPrend(Case dep, Case arr, Couleur c){
        
        byte colDep = dep.getColonne();
        byte colArr = arr.getColonne();
        byte ligDep = dep.getLigne();
        byte ligArr = arr.getLigne();
        
        Direction dir = Echiquier.getInstance().getDirection(dep, arr);
        if ( c == Couleur.BLANC && dir != Direction.NE && dir != Direction.NO )
            return false;
        if ( c == Couleur.NOIR && dir != Direction.SE  && dir != Direction.SO )
            return false;
        if( Math.abs(ligArr - ligDep) != 1 ||  Math.abs(ligArr - ligDep) != Math.abs(colArr - colDep))
            return false;
        return true;
    }
}
