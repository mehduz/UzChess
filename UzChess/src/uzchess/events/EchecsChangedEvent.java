/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.events;

import java.util.EventObject;
import uzchess.core.domain.Echiquier;

/**
 *
 * @author user
 */
public class EchecsChangedEvent extends EventObject{
    
    Echiquier ech;
    
    public EchecsChangedEvent(Object source, Echiquier ech){
        super(source);
        this.ech = ech;    
    }

    public Echiquier getEch() {
        return ech;
    }
   
}
