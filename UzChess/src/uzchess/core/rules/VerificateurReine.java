package uzchess.core.rules;

import java.io.Serializable;
import uzchess.core.domain.Case;

public class VerificateurReine implements Deplacement, Serializable {

    private static final VerificateurFou vf = new VerificateurFou();
    private static final VerificateurTour vt = new VerificateurTour();
            
    @Override
    public boolean verifierDeplacement(Case dep, Case arr ) {
        return vf.verifierDeplacement(dep, arr) || vt.verifierDeplacement(dep, arr);
    }

}
