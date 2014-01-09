/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.constantes;

/**
 *
 * @author user
 */
public enum PosLettres {
    A("A"),
    B("B"), 
    C("C"), 
    D("D"), 
    E("E"),
    F("F"),
    G("G"),
    H("H");
    
    private String string;
    
    private PosLettres(String lettre){
        string = lettre;
    }
}
