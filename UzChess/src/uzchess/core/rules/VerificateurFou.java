package uzchess.core.rules;

import uzchess.core.JeuEchecs;
import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;

public class VerificateurFou implements Deplacement {

    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {

        byte ligCaseDep = dep.getLigne();
        byte colCaseDep = dep.getColonne();
        byte ligCaseArr = arr.getLigne();
        byte colCaseArr = arr.getColonne();

        byte decVer = (byte) Math.abs(ligCaseDep - ligCaseArr);
        byte decHor = (byte) Math.abs(colCaseDep - colCaseArr);

        //Si la case arrivé est sur le même ligne ou la même colonne
        Echiquier ech = JeuEchecs.getInstance().getEchiquier();
        if (decVer != decHor) {
            return false;
        }
        return ech.verifCasesInter(ech.getCasesInter(dep, arr));

    }

}
