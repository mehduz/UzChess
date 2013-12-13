package uzchess.core.rules;

import uzchess.constantes.Direction;
import uzchess.core.entities.Case;
import uzchess.core.entities.Echiquier;

public class VerificateurTour implements Deplacement {
//on arrive dans cette méthode tout est nikel il faut juste verifier que déplacement comforme

    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {

        byte colCaseDep = dep.getColonne();
        byte ligCaseDep = dep.getLigne();
        byte colCaseArr = arr.getColonne();
        byte ligCaseArr = arr.getLigne();

        //typeVefir== pour ligne (0)colonne(1) ou diago(2)
        if (ligCaseDep != ligCaseArr && colCaseDep != colCaseArr) {
            return false;
        }

        Direction direction = (ligCaseDep == ligCaseArr) ? (colCaseDep > colCaseArr) ? Direction.O : Direction.E : (ligCaseDep > ligCaseArr) ? Direction.S : Direction.N;

        Echiquier ech = Echiquier.getInstance();
        return ech.verifCasesInter(dep, arr, direction);
    }
}
