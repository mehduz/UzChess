package uzchess.core.rules;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import uzchess.core.domain.Piece;

public class StatutTour implements Cloneable{

   private AbstractMap<Piece, Boolean> tours;
  
    public StatutTour() {
        tours = new HashMap<>();
    }

    public AbstractMap<Piece, Boolean> getTours() {
        return tours;
    }
    
   @Override
    public StatutTour clone(){
        StatutTour st = null;
        try{
            st = (StatutTour)super.clone();
            st.tours = new HashMap<>();
            for(Map.Entry entry : this.tours.entrySet()){
                st.tours.put((Piece)entry.getKey(), (Boolean)entry.getValue());
            }
        }
        catch(CloneNotSupportedException cnse){
            cnse.printStackTrace(System.err);
        }
        return st;
    }
}
