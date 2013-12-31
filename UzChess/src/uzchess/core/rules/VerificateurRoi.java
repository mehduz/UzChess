package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.constantes.TypeTour;
import uzchess.core.domain.Case;
import uzchess.core.domain.CaseInterUtility;
import uzchess.core.domain.CheckCasesInterUtility;
import uzchess.core.domain.Echiquier;

public class VerificateurRoi implements Deplacement { 

    private StatutTour st;
    private StatutRoi sr;
    private Echiquier ech;

   
    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {

        byte ligCaseDep = dep.getLigne();
        byte colCaseDep = dep.getColonne();
        byte ligCaseArr = arr.getLigne();
        byte colCaseArr = arr.getColonne();

        byte decLigne = (byte) Math.abs(ligCaseDep - ligCaseArr);
        byte decColonne = (byte) Math.abs(colCaseDep - colCaseArr);

        boolean condition1 = ech.isMenace(arr).isEmpty();
        boolean condition2 = ((new VerificateurReine().verifierDeplacement(dep, arr, false)) && (decLigne <= 1 && decColonne <= 1)) || verifierRoque(dep, arr, decLigne, noticeMove);

        if (condition1 && condition2) {
            if (noticeMove) {
                sr.setRoiMoved(dep.getPiece().getCouleur());
            }
            return true;
        }
        return false;
    }

    private boolean verifierRoque(Case dep, Case arr, byte decLigne, boolean noticeMove) {

        Couleur col = dep.getPiece().getCouleur();
        Direction dir = dep.getDirection(arr);

        boolean condition0 = dir == Direction.O;
        boolean condition1 = dir == Direction.E;
        boolean condition2 = !(sr.isRoiMoved(col)) && (CheckCasesInterUtility.verifCasesInter(CaseInterUtility.getCasesInter(dep, arr)));
        boolean condition3 = (decLigne == 3) && (dir == Direction.O);
        boolean condition4 = (decLigne == 2) && (dir == Direction.E);
        boolean condition5 = (condition3 && !st.isTourMoved(TypeTour.TBO)) || (condition4 && !st.isTourMoved(TypeTour.TBE));
        boolean condition6 = (condition3 && !st.isTourMoved(TypeTour.TNO)) || (condition4 && !st.isTourMoved(TypeTour.TNE));
        boolean condition7 = (col == Couleur.BLANC) ? condition5 : condition6;

        if ((condition0 || condition1) && condition2 && condition7) {
            if (noticeMove) {
                if (dep.getPiece().getCouleur() == Couleur.BLANC && dir == Direction.O) { 

                    st.setTourMoved(TypeTour.TBO, true);

                } else if (dep.getPiece().getCouleur() == Couleur.BLANC && dir == Direction.E) {

                    st.setTourMoved(TypeTour.TBE, true);

                } else if (dep.getPiece().getCouleur() == Couleur.NOIR && dir == Direction.O) {

                    st.setTourMoved(TypeTour.TNO, true);

                } else {

                    st.setTourMoved(TypeTour.TNE, true);
                }
            }
            return true;
        }

        return false;
    }

    public void setEch(Echiquier ech) {
        this.ech = ech;
    }

    public void setSt(StatutTour st) {
        this.st = st;
    }

    public void setSr(StatutRoi sr) {
        this.sr = sr;
    }
    
    
    
}
