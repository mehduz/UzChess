package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.core.domain.Case;

public class VerificateurPion implements Deplacement {

    @Override
    public boolean verifierDeplacement(Case dep, Case arr ) {

        Couleur c = dep.getPiece().getCouleur();
        Direction dir = dep.getDirection(arr);
        byte ligDep = dep.getLigne();
        byte ligArr = arr.getLigne();
        byte dist = (byte) Math.abs(ligArr - ligDep);

        if (arr.getPiece() == null) {

            boolean hasmoved = (ligDep != 6 && ligDep != 1);
            boolean condition1 = (c == Couleur.BLANC && dir == Direction.N) || (c == Couleur.NOIR && dir == Direction.S);
            boolean condition2 = dist == 1;
            boolean condition3 = dist == 2 && !hasmoved;

            return (condition1 && (condition2 || condition3));
        }

        boolean condition1 = c == Couleur.BLANC && (dir == Direction.NE || dir == Direction.NO);
        boolean condition2 = c == Couleur.NOIR && (dir == Direction.SE || dir == Direction.SO);
        boolean condition3 = dist == 1;

        return ( ( condition1 || condition2) && condition3);
    }

  

}
