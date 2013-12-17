package uzchess.core.rules;

import uzchess.core.model.Case;

public interface Deplacement {

    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove);

} 
