package uzchess.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import uzchess.constantes.Couleur;
import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;
import uzchess.core.model.Piece;
import uzchess.core.rules.VerificateurCavalier;

public class MoteurDeJeu {

    private boolean echec = false;

    public void verifierCoup(Case dep, Case arr) {

    }

    public boolean detecterEchec() {
        JeuEchecs jeu = JeuEchecs.getInstance();
        Couleur c = jeu.getTour();
        Echiquier ech = jeu.getEchiquier();
        Case caseRoiAChecker = (c == Couleur.BLANC) ? ech.getCaseRoiN() : ech.getCaseRoiB();
        return (echec = (ech.isMenace(caseRoiAChecker).isEmpty()));
    }

    public boolean detecterMat() {

        Echiquier ech = JeuEchecs.getInstance().getEchiquier();
        Couleur c = JeuEchecs.getInstance().getTour();
        Case caseRoiAChecker = (c == Couleur.BLANC) ? ech.getCaseRoiB() : ech.getCaseRoiN();

        //on recupere les possibilités de déplacement du Roi
        ArrayList<Case> depPossible = ech.deplacementPossible(caseRoiAChecker.getPiece());

        //Si il y en a, alors on les check, et si y'en a au moins une non menacée, il n'y a pas mat et on retourne false dans ta face !
        if (!depPossible.isEmpty()) {
            for (Case ca : depPossible) {
                if (ech.isMenace(ca).isEmpty()) {
                    return false;
                }
            }
        }
        //là le roi ne peut pas bouger, aux autres pieces d'essayer de rompre l'echecs
        //si plusieus pieces adverses menacent notre roi, on perd car une piece à nous ne peut pas bloquer 2 directions en même temps
        ArrayList<Case> casesMenace = ech.isMenace(caseRoiAChecker);
        if (casesMenace.size() > 1) {
            return true;
        }
        Case caseMenace = casesMenace.listIterator().next();

        //On recupere les cases où on peut intercepter
        ArrayList<Case> casesInterception = new ArrayList<>();
        //le cavalier n'est pas interceptable, il faut le prendre ou on perd la partie
        if (caseMenace.getPiece().getDeplacement() instanceof VerificateurCavalier) {
            casesInterception.add(caseMenace);
        } else {
            casesInterception = ech.getCasesInter(caseRoiAChecker, caseMenace);
        }
        //On recupere les pieces alliés pour vérifier les interceptions possibles
        HashMap<Piece, Case> allies = ech.getPieces(c);

        //on teste les interceptions possibles
        for (Entry<Piece, Case> entry : allies.entrySet()) {
            for (Case inter : casesInterception) {
                if (entry.getKey().getDeplacement().verifierDeplacement(entry.getValue(), inter)) {
                    return false;
                }
            }
        }
        //le roi ne peut pas se déplacer et aucune piece ne peut le sauver, donc il y'a mat
        return true;
    }
    

    public void detecterPat() {
        
    }

    public boolean isThereEchec() {
        return echec;
    }

}
