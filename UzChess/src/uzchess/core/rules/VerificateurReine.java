package uzchess.core.rules;

import uzchess.core.domain.Case;

public class VerificateurReine implements Deplacement {

    private static final VerificateurFou vf = new VerificateurFou();
    private static final VerificateurTour vt = new VerificateurTour();
            
    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {
        return vf.verifierDeplacement(dep, arr, false) || vt.verifierDeplacement(dep, arr, false);
    }

}
