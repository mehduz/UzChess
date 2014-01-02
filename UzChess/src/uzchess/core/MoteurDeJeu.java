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

    public boolean verifierCoup(Case dep, Case arr) {

        Couleur couleur = jeu.getTour();
        if (dep.getPiece() != null && (arr.getPiece() == null || arr.getPiece().getCouleur() != couleur)) {
            return dep.getPiece().getDeplacement().verifierDeplacement(dep, arr ) && simulerNotEchec(dep, arr);
        }
        return false;
    }

    private boolean simulerNotEchec(Case dep, Case arr) {

        Piece a = arr.getPiece();
        ech.deplacer(dep, arr);
        boolean echec = detecterEchec(jeu.getTour());
        ech.deplacer(arr, dep);
        if (a != null) {
            arr.setPiece(a);
            ech.getPieces(a.getCouleur()).put(a, arr);
        }
        return !echec;
    }

    public ArrayList<Case> deplacementPossible(Piece piece) {

        ArrayList<Case> casesP = new ArrayList<>();

        Case caseV, dep;
        dep = ech.getPieces(piece.getCouleur()).get(piece);

        for (byte i = 0; i < 8; i++) {
            for (byte j = 0; j < 8; j++) {
                caseV = ech.getCases()[i][j];
                if (verifierCoup(dep, caseV)) { 
                    casesP.add(caseV);
                }
            }
        }
        return casesP;
    }

    public boolean detecterEchec(Couleur c) {
        
        Case caseRoiAChecker = ech.getCaseRoi(c);
        return !ech.isMenace(caseRoiAChecker).isEmpty();
    }

    public boolean detecterMat() {

        Couleur c = jeu.getTour();
        Case caseRoiAChecker = ech.getCaseRoi(c);

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
        if (casesMenace.isEmpty()) {
            return false;
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
                if (entry.getKey().getDeplacement().verifierDeplacement(entry.getValue(), inter)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean detecterPat() {

        Couleur c = jeu.getTour();
        Case caseRoiAChecker = ech.getCaseRoi(c);

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
