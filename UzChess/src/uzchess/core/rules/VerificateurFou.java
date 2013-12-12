package uzchess.core.rules;

import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;

public class VerificateurFou implements Deplacement {

    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {
        
        byte ligneCaseDep = dep.getLigne();
        byte colonneCaseDep = dep.getColonne();
        byte ligneCaseArr = arr.getLigne();
        byte colonneCaseArr = arr.getColonne();

        byte decLigne = (byte) Math.abs(ligneCaseDep - ligneCaseArr);
        byte decColonne = (byte) Math.abs(colonneCaseDep - colonneCaseArr);

        //Si la case arrivé est sur le même ligne ou la même colonne
        Echiquier ech = Echiquier.getInstance();
        return (decLigne == decColonne) && (ech.verifCasesInter(dep, arr));

    }

}
