/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.core.rules;

import uzchess.core.entities.Case;
import uzchess.core.entities.Echiquier;

/**
 *
 * @author 20130317
 */
public class VerificateurRoque implements Deplacement{

    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        Echiquier.getInstance().isRoiBMoved();
    }
    
}
