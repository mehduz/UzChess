package uzchess.core.rules;

import uzchess.constantes.Couleur;

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
