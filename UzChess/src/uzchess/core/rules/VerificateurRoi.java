package uzchess.core.rules;

import java.io.Serializable;
import uzchess.constantes.Direction;
import uzchess.core.domain.Case;
import uzchess.core.domain.CaseInterUtility;
import uzchess.core.domain.CheckCasesInterUtility;
import uzchess.core.domain.Echiquier;
import uzchess.core.domain.Piece;

public class VerificateurRoi implements Deplacement, Serializable {
    
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

        byte decLigne = (byte) Math.abs(ligCaseArr - ligCaseDep);
        byte decColonne = (byte) Math.abs(colCaseArr - colCaseDep);

        boolean condition1 = ((vr.verifierDeplacement(dep, arr)) && (decLigne <= 1 && decColonne <= 1));
        boolean condition2 = verifierRoque(dep, arr, decColonne, ligCaseDep, colCaseDep);

        return (condition1 || condition2);
    }

    private boolean verifierRoque(Case dep, Case arr, byte decColonne, byte ligCaseDep, byte colCaseDep) {

        Direction dir = dep.getDirection(arr);
        Piece p1, p2;
        Case c1, c2;

        boolean condition1 = !(ech.getSr().getRois().get(dep.getPiece())) && (CheckCasesInterUtility.verifCasesInter(CaseInterUtility.getCasesInter(dep, arr)));
        if (!condition1) {
            return false;
        }
        c1 = ech.getCases()[ligCaseDep][colCaseDep - 4];
        c2 = ech.getCases()[ligCaseDep][colCaseDep + 3];
        boolean condition4 = (p1 = c1.getPiece()) != null && !ech.getSt().getTours().get(p1);
        boolean condition5 = (p2 = c2.getPiece()) != null && !ech.getSt().getTours().get(p2);

        return decColonne == 2 && ( dir == Direction.O && condition4 ||  dir == Direction.E  && condition5);
    }

    public void setEch(Echiquier ech) {
        this.ech = ech;
    } 
    
}
