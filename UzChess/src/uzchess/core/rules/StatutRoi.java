package uzchess.core.rules;

import java.util.AbstractMap;
import java.util.HashMap;
import uzchess.core.domain.Piece;

public class StatutRoi {

    private AbstractMap<Piece, Boolean> rois;

    public StatutRoi(){
        rois = new HashMap<>();
    }
    
    public AbstractMap<Piece, Boolean> getRois() {
        return rois;
    }
}
