/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.events;

import java.util.EventObject;

/**
 *
 * @author user
 */
public class EchecsChangedEvent extends EventObject{
    
    public EchecsChangedEvent(Object source ){
        super(source);  
    }
}
