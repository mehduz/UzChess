/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.core.rules;

import uzchess.constantes.Couleur;

/**
 *
 * @author user
 */
public class StatutRoi {

    private boolean roiBMoved;
    private boolean roiNMoved;

    public void setRoiMoved(Couleur color) {
        if (color == Couleur.BLANC) {
            setRoiBMoved(true);
            return;
        }
        setRoiNMoved(true);
    }

    private void setRoiBMoved(boolean roiBMoved) {
        this.roiBMoved = roiBMoved;
    }
    
    private void setRoiNMoved(boolean roiNMoved) {
        this.roiNMoved = roiNMoved;
    }

    public boolean isRoiMoved(Couleur c) {
        if (c == Couleur.BLANC) {
            return isRoiBMoved();
        }
        return isRoiNMoved();
    }

    private boolean isRoiBMoved() {
        return roiBMoved;
    }

    private boolean isRoiNMoved() {
        return roiNMoved;
    }
}
