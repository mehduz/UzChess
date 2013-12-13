/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.core.rules;

import uzchess.core.model.Case;

/**
 *
 * @author 20130317
 */
public class VerificateurReine implements Deplacement {

    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {
        return new VerificateurFou().verifierDeplacement(dep, arr) || new VerificateurTour().verifierDeplacement(dep, arr);
    }

}
