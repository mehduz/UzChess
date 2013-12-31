package uzchess.core.rules;

import java.util.AbstractMap;
import java.util.HashMap;
import uzchess.core.domain.Piece;

public class StatutPion {

    private AbstractMap<Piece, Boolean> pions;
    private boolean pionMoved;

    public StatutPion() {
        this.pions = new HashMap<>();
    }

    
    public AbstractMap<Piece, Boolean> getPions() {
        return pions;
    }

    public void setPions(AbstractMap<Piece, Boolean> pions) {
        this.pions = pions;
    }

    public boolean isPionMoved() {
        return pionMoved; 
    }

    public void setPionMoved(boolean pionMoved) {
        this.pionMoved = pionMoved;
    }

}
