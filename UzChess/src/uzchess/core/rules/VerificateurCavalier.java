package uzchess.core.rules;

import java.io.Serializable;
import uzchess.core.domain.Case;

public class VerificateurCavalier implements Deplacement, Serializable {
    
    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {

        byte colCaseDep = dep.getColonne();
        byte ligCaseDep = dep.getLigne();
        byte colCaseArr = arr.getColonne();
        byte ligCaseArr = arr.getLigne();

        byte depVer = (byte) Math.abs(ligCaseArr - ligCaseDep);
        byte depHor = (byte) Math.abs(colCaseArr - colCaseDep);

        return (depVer == 2) && (depHor == 1) || (depHor == 2 ) && (depVer == 1);
    }

}
