package uzchess.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import uzchess.constantes.Couleur;
import uzchess.core.domain.Case;
import uzchess.core.domain.CaseInterUtility;
import uzchess.core.domain.Echiquier;
import uzchess.core.domain.Piece;
import uzchess.core.rules.VerificateurCavalier;

public class MoteurDeJeu {

    private boolean echec;

    private Echiquier ech;
    private JeuEchecs jeu;

    public MoteurDeJeu(Echiquier ech, JeuEchecs jeu) {
        echec = false;
        this.ech = ech;
        this.jeu = jeu;
    }

    public boolean verifierCoup(Case dep, Case arr, boolean notify) {
        
        Couleur couleur = jeu.getTour();
        if (dep.getPiece() != null && (arr.getPiece() == null || arr.getPiece().getCouleur() != couleur)) {
            return dep.getPiece().getDeplacement().verifierDeplacement(dep, arr, notify);
        }
        return false;
    }

    
    public ArrayList<Case> deplacementPossible(Piece piece) {

        ArrayList<Case> casesP = new ArrayList<>();

        Case caseV, dep;
        dep = ech.getPieces(piece.getCouleur()).get(piece);

        for (byte i = 0; i < 8; i++) {
            for (byte j = 0; j < 8; j++) {
                caseV = ech.getCases()[i][j];
                if ( verifierCoup(dep, caseV, false)) {
                    casesP.add(caseV);
                }
            }
        }
        return casesP;
    }
    
    
    public boolean detecterEchec() {

        Couleur c = jeu.getTour();
        Case caseRoiAChecker = (c == Couleur.BLANC) ? ech.getCaseRoiN() : ech.getCaseRoiB();
        echec = (ech.isMenace(caseRoiAChecker).isEmpty());
        return echec;
    }

    public boolean detecterMat() {

        Couleur c = jeu.getTour();
        Case caseRoiAChecker = (c == Couleur.BLANC) ? ech.getCaseRoiB() : ech.getCaseRoiN();

        ArrayList<Case> depPossible = deplacementPossible(caseRoiAChecker.getPiece());
        if (!depPossible.isEmpty()) {
            for (Case ca : depPossible) {
                if (ech.isMenace(ca).isEmpty()) {
                    return false;
                }
            }
        }

        ArrayList<Case> casesMenace = ech.isMenace(caseRoiAChecker);
        if (casesMenace.size() > 1) {
            return true;
        }

        Case caseMenace = casesMenace.listIterator().next();
        ArrayList<Case> casesInterception = new ArrayList<>();

        if (caseMenace.getPiece().getDeplacement() instanceof VerificateurCavalier) {
            casesInterception.add(caseMenace);
        } else {
            casesInterception = CaseInterUtility.getCasesInter(caseRoiAChecker, caseMenace);
        }

        HashMap<Piece, Case> allies = ech.getPieces(c);
        for (Entry<Piece, Case> entry : allies.entrySet()) {
            for (Case inter : casesInterception) {
                if (entry.getKey().getDeplacement().verifierDeplacement(entry.getValue(), inter, false)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean detecterPat() {

        Couleur c = jeu.getTour();
        Case caseRoiAChecker = (c == Couleur.BLANC) ? ech.getCaseRoiB() : ech.getCaseRoiN();

        ArrayList<Case> depPossible = deplacementPossible(caseRoiAChecker.getPiece());

        if (!depPossible.isEmpty()) {
            for (Case ca : depPossible) {
                if (ech.isMenace(ca).isEmpty()) {
                    return false;
                }
            }
        }

        HashMap<Piece, Case> allies = ech.getPieces(c);
        for (Entry<Piece, Case> entry : allies.entrySet()) {
            if (!deplacementPossible(entry.getKey()).isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    public void setEchec(boolean echec) {
        this.echec = echec;
    }
}
