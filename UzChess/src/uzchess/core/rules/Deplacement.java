package uzchess.core.rules;

import uzchess.core.domain.Case;

public interface Deplacement {

    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove);

}
