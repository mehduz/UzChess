/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.core.utilities;

import java.util.AbstractMap;
import java.util.Map.Entry;

/**
 *
 * @author user
 */
public class MapUtility {

    public static AbstractMap<Object, Object> clone(AbstractMap<Object, Object> map) {
        AbstractMap<Object, Object> ret = null;
        try{
           ret = map.getClass().newInstance();
        }catch(InstantiationException ie){
            ie.printStackTrace(System.err);
        }
        catch(IllegalAccessException iae){
             iae.printStackTrace(System.err);
        }
        for (Entry entry : ret.entrySet()) {
            //ret.put((Piece)entry.getKey(), (Boolean) entry.getValue());
        }
        return null;
    }

}
