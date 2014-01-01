package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.core.domain.Case;
import uzchess.core.domain.CaseInterUtility;
import uzchess.core.domain.CheckCasesInterUtility;
import uzchess.core.domain.Echiquier;
import uzchess.core.domain.Piece;

public class VerificateurRoi implements Deplacement {

    private StatutTour st;
    private StatutRoi sr;
    private Echiquier ech;
    private static VerificateurReine vr = new VerificateurReine();

    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {

        byte ligCaseDep = dep.getLigne();
        byte colCaseDep = dep.getColonne();
        byte ligCaseArr = arr.getLigne();
        byte colCaseArr = arr.getColonne();

        byte decLigne = (byte) Math.abs(ligCaseDep - ligCaseArr);
        byte decColonne = (byte) Math.abs(colCaseDep - colCaseArr);

        boolean condition = ((vr.verifierDeplacement(dep, arr, false)) && (decLigne <= 1 && decColonne <= 1));
        boolean condition2 = verifierRoque(dep, arr, decColonne, ligCaseDep, colCaseDep);

        if (condition || condition2) {
            if (noticeMove) {
                sr.setRoiMoved(dep.getPiece().getCouleur());
                if (dep.getPiece().getCouleur() == Couleur.BLANC) {
                    ech.setCaseRoiB(arr);
                } else {
                    ech.setCaseRoiN(arr);
                }
                if (condition2) {
                    Case rDep, rArr;
                    if (decColonne == 3) {
                        rDep = ech.getCases()[ligCaseDep][colCaseDep - 4];
                        rArr = ech.getCases()[ligCaseDep][colCaseDep - 2];
                    } else {
                        rDep = ech.getCases()[ligCaseDep][colCaseDep + 3];
                        rArr = ech.getCases()[ligCaseDep][colCaseDep + 1];
                    }
                    ech.deplacer(rDep, rArr);
                    st.getTours().put(rDep.getPiece(), true);
                }
            }
            return true;
        }
        return false;
    }

    private boolean verifierRoque(Case dep, Case arr, byte decColonne, byte ligCaseDep, byte colCaseDep) {

        Direction dir = dep.getDirection(arr);
        Piece p1, p2;
        Case c1, c2;

        boolean condition1 = !(sr.isRoiMoved(dep.getPiece().getCouleur())) && (CheckCasesInterUtility.verifCasesInter(CaseInterUtility.getCasesInter(dep, arr)));
        boolean condition2 = (decColonne == 3) && (dir == Direction.O);
        boolean condition3 = (decColonne == 2) && (dir == Direction.E);
        if (!condition1) {
            return false;
        }
        boolean condition4 = (c1 = ech.getCases()[ligCaseDep][colCaseDep - 4]) != null && (p1 = c1.getPiece()) != null && !st.getTours().get(p1);
        boolean condition5 = (c2 = ech.getCases()[ligCaseDep][colCaseDep + 3]) != null && (p2 = c2.getPiece()) != null && !st.getTours().get(p2);

        return (condition2 && condition4) || (condition3 && condition5);

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