/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.model;

import java.util.ArrayList;
import javax.swing.event.EventListenerList;
import uzchess.core.JeuEchecs;
import uzchess.core.domain.Case;
import uzchess.events.EchecsChangedEvent;
import uzchess.events.EchecsListener;

/**
 *
 * @author usere
 */
public class JeuEchecsModel extends JeuEchecs{
    
    private EventListenerList listeners;
    private ArrayList<Case> casesValides;
  
    public JeuEchecsModel(){
        super();
        this.listeners = new EventListenerList();
        this.casesValides = new ArrayList<>();
    }
    
    public void addEchecsListener(EchecsListener l ){
        this.listeners.add(EchecsListener.class, l);
    }
    
    public void removeEchecsListener( EchecsListener l){
        this.listeners.remove(EchecsListener.class, l);
    }
    
    private void fireEchecsChanged(){
        
        EchecsListener[] listenerList = ( EchecsListener[] )this.listeners.getListeners(EchecsListener.class);
        
        for(EchecsListener el : listenerList){ 
            el.echecsChanged( new EchecsChangedEvent(this)); 
        } 
    }

    public void setCasesValides(ArrayList<Case> casesValides) {
        this.casesValides = casesValides;
        fireEchecsChanged();
    }

    public ArrayList<Case> getCasesValides() {
        return this.casesValides; 
    }
    
}
