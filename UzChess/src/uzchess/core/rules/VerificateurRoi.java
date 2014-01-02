package uzchess.core.rules;

import uzchess.constantes.Direction;
import uzchess.core.domain.Case;
import uzchess.core.domain.CaseInterUtility;
import uzchess.core.domain.CheckCasesInterUtility;
import uzchess.core.domain.Echiquier;
import uzchess.core.domain.Piece;

public class VerificateurRoi implements Deplacement {
    
    private Echiquier ech;
    private static VerificateurReine vr;

     public VerificateurRoi(){
        vr = new VerificateurReine();
        ech = null;
     }
    
    
    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {

        byte ligCaseDep = dep.getLigne();
        byte colCaseDep = dep.getColonne();
        byte ligCaseArr = arr.getLigne();
        byte colCaseArr = arr.getColonne();

        byte decLigne = (byte) Math.abs(ligCaseDep - ligCaseArr);
        byte decColonne = (byte) Math.abs(colCaseDep - colCaseArr);

        boolean condition = ((vr.verifierDeplacement(dep, arr)) && (decLigne <= 1 && decColonne <= 1));
        boolean condition2 = verifierRoque(dep, arr, decColonne, ligCaseDep, colCaseDep);

        return (condition || condition2);
    }

    private boolean verifierRoque(Case dep, Case arr, byte decColonne, byte ligCaseDep, byte colCaseDep) {

        Direction dir = dep.getDirection(arr);
        Piece p1, p2;
        Case c1, c2;

        boolean condition1 = !(ech.getSr().getRois().get(ech.getCaseRoi(dep.getCouleur()).getPiece())) && (CheckCasesInterUtility.verifCasesInter(CaseInterUtility.getCasesInter(dep, arr)));
        boolean condition2 = (decColonne == 3) && (dir == Direction.O);
        boolean condition3 = (decColonne == 2) && (dir == Direction.E);
        if (!condition1) {
            return false;
        }
        c1 = ech.getCases()[ligCaseDep][colCaseDep - 4];
        c2 = ech.getCases()[ligCaseDep][colCaseDep + 3];
        boolean condition4 = (p1 = c1.getPiece()) != null && !ech.getSt().getTours().get(p1);
        boolean condition5 = (p2 = c2.getPiece()) != null && !ech.getSt().getTours().get(p2);

        return (condition2 && condition4) || (condition3 && condition5); 

    }

    public void setEch(Echiquier ech) {
        this.ech = ech;
    } 
    
}
