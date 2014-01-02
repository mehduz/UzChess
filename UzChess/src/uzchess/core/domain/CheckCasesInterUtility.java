package uzchess.core.domain;

import java.util.List;

public class CheckCasesInterUtility {

    public static boolean verifCasesInter(List<Case> aVerif) {

        for (Case c : aVerif) {
            if (c.getPiece() != null) {
                return false;
            }
        } 
        return true;
    }

}
