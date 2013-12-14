package uzchess.core.rules;

import uzchess.core.JeuEchecs;
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

       
        if (ligCaseDep != ligCaseArr && colCaseDep != colCaseArr) {
            return false;
        }

        Echiquier ech = JeuEchecs.getInstance().getEchiquier();
        return ech.verifCasesInter( ech.getCasesInter(dep, arr )); 
    }
}
