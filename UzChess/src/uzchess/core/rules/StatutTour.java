package uzchess.core.rules;

import java.util.AbstractMap;
import java.util.HashMap;
import uzchess.core.domain.Piece;

public class StatutTour {

   private AbstractMap<Piece, Boolean> tours;
  
    public StatutTour() {
        tours = new HashMap<>();
    }

    public AbstractMap<Piece, Boolean> getTours() {
        return tours;
    }
}
