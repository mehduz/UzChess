package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.core.domain.Case;
import uzchess.core.domain.CaseInterUtility;
import uzchess.core.domain.CheckCasesInterUtility;

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
            boolean condition2 = dist == 2 && !hasmoved;

            return (condition1 && (dist == 1 || condition2)) && CheckCasesInterUtility.verifCasesInter(CaseInterUtility.getCasesInter(dep, arr));
        }

        boolean condition1 = c == Couleur.BLANC && (dir == Direction.NE || dir == Direction.NO);
        boolean condition2 = c == Couleur.NOIR && (dir == Direction.SE || dir == Direction.SO);
        boolean condition3 = dist == 1 && Math.abs(arr.getColonne() - dep.getColonne()) == 1;

        return ( ( condition1 || condition2) && condition3);
    }

  

}
