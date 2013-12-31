package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.core.domain.Case;

public class VerificateurPion implements Deplacement {

    private StatutPion sp;

    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {

        Couleur c = dep.getPiece().getCouleur();
        Direction dir = dep.getDirection(arr);
        byte ligDep = dep.getLigne();
        byte ligArr = arr.getLigne();
        byte dist = (byte) Math.abs(ligArr - ligDep);

        if (arr.getPiece() == null) {

            boolean hasmoved = sp.getPions().get(dep.getPiece());
            boolean condition1 = (c == Couleur.BLANC && dir == Direction.N) || (c == Couleur.NOIR && dir == Direction.S);
            boolean condition2 = dist == 1;
            boolean condition3 = dist == 2 && !hasmoved;

            if (condition1 && (condition2 || condition3)) {
                if (noticeMove) {
                    sp.getPions().put(dep.getPiece(), true);
                }
                return true;
            }
            return false;
        }

        boolean condition1 = c == Couleur.BLANC && (dir == Direction.NE || dir == Direction.NO);
        boolean condition2 = c == Couleur.NOIR && (dir == Direction.SE || dir == Direction.SO);
        boolean condition3 = dist == 1;

        if ((condition1 || condition2) && condition3) {
            if (noticeMove) {
                sp.getPions().put(dep.getPiece(), true);
            }
            return true;
        }
        return false;
    }

    public void setSp(StatutPion sp) {
        this.sp = sp;
    }

}
