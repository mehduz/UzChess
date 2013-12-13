package uzchess.core.rules;

import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;

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

        Echiquier ech = Echiquier.getInstance();
        return ech.verifCasesInter( ech.getCasesInter(dep, arr ));
    }
}
