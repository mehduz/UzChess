package uzchess.core.rules;

import java.util.HashMap;
import uzchess.core.domain.Piece;

public class StatutPion {

    private HashMap<Piece, Boolean> pions;
    private boolean pionMoved;

    public HashMap<Piece, Boolean> getPions() {
        return pions;
    }

    public void setPions(HashMap<Piece, Boolean> pions) {
        this.pions = pions;
    }

    public boolean isPionMoved() {
        return pionMoved;
    }

    public void setPionMoved(boolean pionMoved) {
        this.pionMoved = pionMoved;
    }

}
