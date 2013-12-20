package uzchess.core.rules;

import uzchess.core.model.Case;

public class VerificateurReine implements Deplacement {

    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {
        return new VerificateurFou().verifierDeplacement(dep, arr, false) || new VerificateurTour().verifierDeplacement(dep, arr, false);
    }

}
