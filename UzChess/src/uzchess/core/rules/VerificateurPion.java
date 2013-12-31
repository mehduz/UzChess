package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.core.domain.Case;

public class VerificateurPion implements Deplacement {

    private StatutPion sp;

    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {

        Couleur c = dep.getCouleur();
        Direction dir = dep.getDirection(arr);
        if (arr.getPiece() != null) {
            return verifAvance(dep, arr, c, dir);
        }
        return new VerificateurPionPrend().verifierDeplacement(dep, arr, noticeMove);
    }

    private boolean verifAvance(Case dep, Case arr, Couleur c, Direction dir) {

        byte ligDep = dep.getLigne();
        byte ligArr = arr.getLigne();
        byte dist = (byte) Math.abs(ligArr - ligDep);
        boolean hasmoved = sp.getPions().get(dep.getPiece());
        boolean condition1 = (c == Couleur.BLANC && dir == Direction.N) || (c == Couleur.NOIR && dir == Direction.S);
        boolean condition2 = dist == 1;
        boolean condition3 = dist == 2 && !hasmoved;

        if (condition1 && (condition2 || condition3)) {
            sp.getPions().put(dep.getPiece(), hasmoved);
            return true;
        }
        return false;
    }

    public void setSp(StatutPion sp) {
        this.sp = sp;
    }
    
    
}
