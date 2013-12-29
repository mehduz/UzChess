/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.events;

import java.util.EventListener;

/**
 *
 * @author user
 */
public interface EchecsListener extends EventListener{
    
    public void echecsChanged(EchecsChangedEvent event);
    
}
