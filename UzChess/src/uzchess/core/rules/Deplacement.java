package uzchess.core.rules;

import uzchess.core.entities.Case;

public interface Deplacement {

    public boolean verifierDeplacement(Case dep, Case arr);

}
