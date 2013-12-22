package uzchess.core.domain;

import java.util.ArrayList;

public class CheckCasesInterUtility {

    public static boolean verifCasesInter(ArrayList<Case> aVerif) {

        for (Case c : aVerif) {
            if (c.getPiece() != null) {
                return false;
            }
        }
        return true;
    }

}
