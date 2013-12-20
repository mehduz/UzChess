/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.dal;
/**
 *
 * @author user
 */
public interface IDal < T, S> {
    
    public T charger(String nomFichier);
    public void sauvegarder(String nomFichier);
    public S getListePartie();
    
}
