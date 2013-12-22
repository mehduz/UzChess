package uzchess.core.rules;

import uzchess.core.domain.Case;

public interface Deplacement {

    boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove);

}
 