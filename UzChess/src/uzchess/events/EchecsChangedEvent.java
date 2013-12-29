/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.events;

import java.util.EventObject;
import uzchess.core.domain.Case;

/**
 *
 * @author user
 */
public class EchecsChangedEvent extends EventObject{
    
    private Case[][] cases;
    
    public EchecsChangedEvent(Object source, Case[][] cases){
        super(source);
        this.cases = cases;    
    }

    public Case[][] getCases() {
        return this.cases;
    }
   
}
