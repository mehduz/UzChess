/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.core.model;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class CheckCasesInterUtility{
    
    public static boolean verifCasesInter(ArrayList<Case> aVerif) {

        for (Case c : aVerif) {
            if (c.getPiece() != null) {
                return false;
            }
        }
        return true;
    }
    
}
