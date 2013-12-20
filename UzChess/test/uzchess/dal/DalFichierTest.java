/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.dal;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import uzchess.core.JeuEchecs;

/**
 *
 * @author 20130334
 */
public class DalFichierTest {
    
    public DalFichierTest() {
    }

    /**
     * Test of charger method, of class DalFichier.
     */
    @Test
    public void testCharger() {
        System.out.println("charger");
        String nomFichier = "Test.dat";
        DalFichier instance = new DalFichier();
        JeuEchecs expResult = null;
        JeuEchecs result = instance.charger(nomFichier);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of sauvegarder method, of class DalFichier.
     */
    @Test
    public void testSauvegarder() {
        System.out.println("sauvegarder");
        String nomFichier = "TestNull";
        DalFichier instance = new DalFichier();
        instance.sauvegarder(nomFichier);
    }

    /**
     * Test of getListePartie method, of class DalFichier.
     */
    @Test
    public void testGetListePartie() {
        System.out.println("getListePartie");
        DalFichier instance = new DalFichier();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getListePartie();
        assertEquals(expResult, result);
    }
   
    
}
