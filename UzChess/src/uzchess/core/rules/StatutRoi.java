package uzchess.core.rules;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map.Entry;
import uzchess.core.domain.Piece;

public class StatutRoi implements Cloneable{

    private AbstractMap<Piece, Boolean> rois;

    public StatutRoi(){
        rois = new HashMap<>();
    }
    
    public AbstractMap<Piece, Boolean> getRois() {
        return rois;
    }
    
    @Override
     public StatutRoi clone(){
        StatutRoi sr = null;
        try{
            sr = (StatutRoi)super.clone();
            sr.rois = new HashMap<>();
            for(Entry entry : this.rois.entrySet()){
                sr.rois.put((Piece)entry.getKey(), (Boolean)entry.getValue());
            }
        }
        catch(CloneNotSupportedException cnse){
            cnse.printStackTrace(System.err);
        }
        return sr;
    }
}
