/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.dal;

import java.util.Collection;
import uzchess.core.JeuEchecs;

/**
 *
 * @author user
 */
public interface IDal {
    
    public void charger();
    public void sauvegarder();
    public Collection<String> getListePartie();
    
}
