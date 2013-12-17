package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.core.JeuEchecs;
import uzchess.core.model.Case;

public class VerificateurPion implements Deplacement {

//penser  dep début, prise diago
    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {

        Couleur c = dep.getCouleur();
        Direction dir = JeuEchecs.getInstance().getEchiquier().getDirection(dep, arr);
        if (arr.getPiece() != null) {
            return verifAvance(dep, arr, c, dir);
        }
        return verifPrend(dep, arr, c, dir);
    }

    private boolean verifAvance(Case dep, Case arr, Couleur c, Direction dir) {

        byte ligDep = dep.getLigne();
        byte ligArr = arr.getLigne();
        byte dist = (byte) Math.abs(ligArr - ligDep);
        byte nbCoups = JeuEchecs.getInstance().getCompteurCoups();
        
        boolean condition1 = (( c == Couleur.BLANC && dir == Direction.N) || (c == Couleur.NOIR && dir == Direction.S));
        boolean condition2 = (dist == 1 || (dist == 2 && (( nbCoups == 0  && c == Couleur.BLANC) || ( (nbCoups == 1) && (c == Couleur.NOIR)))));
        
        return condition1 && condition2;
           
    }

    private boolean verifPrend(Case dep, Case arr, Couleur c, Direction dir) {

        byte ligDep = dep.getLigne();
        byte ligArr = arr.getLigne();

        boolean condition1 = (c == Couleur.BLANC && ( dir == Direction.NE || dir == Direction.NO ));
        boolean condition2 = (c == Couleur.NOIR && ( dir == Direction.SE || dir == Direction.SO ));
        boolean condition3 = (Math.abs(ligArr - ligDep) == 1);
           
        return condition1 && condition2 && condition3;
    }
}
