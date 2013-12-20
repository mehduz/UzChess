package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.Direction;
import uzchess.core.JeuEchecs;
import uzchess.core.StatutPion;
import uzchess.core.model.Case;

public class VerificateurPion implements Deplacement {

//penser  dep début, prise diago
    private JeuEchecs jeu;
    private StatutPion st; 
   
    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {

        Couleur c = dep.getCouleur();
        Direction dir = dep.getDirection(arr);
        if (arr.getPiece() != null) {
            return verifAvance(dep, arr, c, dir);
        }
        return new VerificateurPionPrend().verifierDeplacement(dep, arr, noticeMove);
    }

    private boolean verifAvance(Case dep, Case arr, Couleur c, Direction dir) {

        byte ligDep = dep.getLigne();
        byte ligArr = arr.getLigne();
        byte dist = (byte) Math.abs(ligArr - ligDep);
        byte nbCoups = jeu.getCompteurCoups();
        boolean hasmoved=st.getPions().get(dep.getPiece());
        boolean condition1 = ( c == Couleur.BLANC && dir == Direction.N) || (c == Couleur.NOIR && dir == Direction.S);
        boolean condition2 =  dist == 1;
        //se servir du fait qu'il ai bougé ou pas
        
        boolean condition3 = (dist == 2 && !hasmoved );
        
            if(condition1 && (condition2 || condition3)){ 
                st.getPions().put(dep.getPiece(), true);
                return true;
            }
             // verif dans statutPion.pions à l'indice "piece courante" si true..
        return false;
    }
}
