package uzchess.core.rules;

import uzchess.constantes.TypeTour;

public class StatutTour {

    private boolean tourBOMoved;
    private boolean tourBEMoved;
    private boolean tourNEMoved;
    private boolean tourNOMoved;

    public void setTourMoved(TypeTour t, boolean val) {
        switch (t) {
            case TBE:
                setTourBEMoved(val);
                break;
            case TBO:
                setTourBOMoved(val);
                break;
            case TNE:
                setTourNEMoved(val);
                break;
            case TNO:
                setTourNOMoved(val);
                break;
            default:
                break;
        }
    }

    private boolean isTourBOMoved() {
        return tourBOMoved;
    }

    private boolean isTourBEMoved() {
        return tourBEMoved;
    }

    private boolean isTourNEMoved() {
        return tourNEMoved;
    }

    private boolean isTourNOMoved() {
        return tourNOMoved;
    }

    private void setTourBOMoved(boolean tourBOMoved) {
        this.tourBOMoved = tourBOMoved;
    }

    private void setTourBEMoved(boolean tourBEMoved) {
        this.tourBEMoved = tourBEMoved;
    }

    private void setTourNEMoved(boolean tourNEMoved) {
        this.tourNEMoved = tourNEMoved;
    }

    private void setTourNOMoved(boolean tourNOMoved) {
        this.tourNOMoved = tourNOMoved;
    }

    public boolean isTourMoved(TypeTour t) {
        boolean ret = false;
        switch (t) {
            case TBE:
                ret = isTourBEMoved();
                break;
            case TBO:
                ret = isTourBOMoved();
                break;
            case TNE:
                ret = isTourNEMoved();
                break;
            case TNO:
                ret = isTourNOMoved();
                break;
            default:
                break;
        }
        return ret;
    }

}
