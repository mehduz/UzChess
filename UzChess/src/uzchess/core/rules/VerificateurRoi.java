package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.core.domain.Case;
import uzchess.core.domain.CaseInterUtility;
import uzchess.core.domain.CheckCasesInterUtility;
import uzchess.core.domain.Echiquier;

public class VerificateurRoi implements Deplacement { 

    private StatutTour st;
    private StatutRoi sr;
    private Echiquier ech;
    private static VerificateurReine vr = new  VerificateurReine();
   
    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {

        byte ligCaseDep = dep.getLigne();
        byte colCaseDep = dep.getColonne();
        byte ligCaseArr = arr.getLigne();
        byte colCaseArr = arr.getColonne();

        byte decLigne = (byte) Math.abs(ligCaseDep - ligCaseArr);
        byte decColonne = (byte) Math.abs(colCaseDep - colCaseArr);
        
        boolean condition = ((vr.verifierDeplacement(dep, arr, false)) && (decLigne <= 1 && decColonne <= 1));
        boolean condition2 = verifierRoque(dep, arr, decColonne);

        if (condition || condition2) {
            if (noticeMove) {
                sr.setRoiMoved(dep.getPiece().getCouleur());
                if(condition2){
                    Case rDep, rArr;
                    if(decColonne == 3){
                        rDep = ech.getCases()[ligCaseDep][colCaseDep - 4];
                        rArr = ech.getCases()[ligCaseDep][colCaseDep - 2];
                    }
                    else
                    {
                        rDep = ech.getCases()[ligCaseDep][colCaseDep + 3];
                        rArr = ech.getCases()[ligCaseDep][colCaseDep + 1];
                    }
                    ech.deplacer(rDep, rArr);
                }
            }
            return true;
        }
        return false;
    }

    private boolean verifierRoque(Case dep, Case arr, byte decColonne) {

        Couleur col = dep.getPiece().getCouleur();
        Direction dir = dep.getDirection(arr);

        boolean condition1 = !(sr.isRoiMoved(col)) && (CheckCasesInterUtility.verifCasesInter(CaseInterUtility.getCasesInter(dep, arr)));
        boolean condition2 = (decColonne == 3) && (dir == Direction.O);
        boolean condition3 = (decColonne == 2) && (dir == Direction.E);
      
        return condition1 && ( condition2 || condition3 );

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
