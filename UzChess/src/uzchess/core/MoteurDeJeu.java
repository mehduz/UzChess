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
import uzchess.core.rules.VerificateurRoi;
import uzchess.core.rules.VerificateurTour;

public class MoteurDeJeu {

    private Echiquier ech;
    private JeuEchecs jeu;

    public MoteurDeJeu(Echiquier ech, JeuEchecs jeu) {
        this.ech = ech;
        this.jeu = jeu;
    }

    public boolean verifierCoup(Case dep, Case arr) {

        Couleur couleur = jeu.getTour();
        if (dep != null && arr!= null && dep.getPiece() != null && (arr.getPiece() == null || arr.getPiece().getCouleur() != couleur)) {
            if (dep.getPiece().getDeplacement().verifierDeplacement(dep, arr)) {
                return simulerCoup(dep, arr);
            }
            return false;
        }
        return false;
    }

    private boolean simulerCoup(Case dep, Case arr) {

        boolean ret;
        Piece parr = arr.getPiece();
        Piece pdep = dep.getPiece();
        boolean moved;
        boolean king = pdep.getDeplacement() instanceof VerificateurRoi;
        boolean tower = pdep.getDeplacement() instanceof VerificateurTour;
        moved = (king) ? ech.getSr().getRois().get(dep.getPiece()) : (tower) ? ech.getSt().getTours().get(dep.getPiece()) : false;
        ech.deplacer(dep, arr);
        ret = ech.detecterEchec(arr.getPiece().getCouleur());
        ech.deplacer(arr, dep);
        arr.setPiece((parr != null) ? parr : null);
        if (king) {
            cancelRoque(dep, arr);
            ech.getSr().getRois().put(pdep, moved);
        } else if (tower) {
            ech.getSt().getTours().put(pdep, moved);
        }
        return !ret;
    }
 
    private void cancelRoque(Case dep, Case arr){
        
        byte decal = (byte) (arr.getColonne() - dep.getColonne());
        Piece pdep = dep.getPiece();
            if (decal == 2 && pdep.getCouleur() == Couleur.BLANC) {
                ech.deplacer(ech.getCases()[7][5], ech.getCases()[7][7]);
            } else if (decal == -2 && pdep.getCouleur() == Couleur.BLANC) {
               ech.deplacer(ech.getCases()[7][3], ech.getCases()[7][0]);
            } else if (decal == 2 && pdep.getCouleur() == Couleur.NOIR) {
                ech.deplacer(ech.getCases()[0][5], ech.getCases()[0][7]);
            } else if (decal == -2 && pdep.getCouleur() == Couleur.NOIR) {
                ech.deplacer(ech.getCases()[0][3], ech.getCases()[0][0]);
            }
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
        ArrayList<Case> casesInterception = (!(caseMenace.getPiece().getDeplacement() instanceof VerificateurCavalier)) ? CaseInterUtility.getCasesInter(caseRoiAChecker, caseMenace) : new ArrayList<Case>();
        casesInterception.add(caseMenace);

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
        HashMap<Piece, Case> allies = ech.getPieces(c);
        for (Entry<Piece, Case> entry : allies.entrySet()) {
            if (!deplacementPossible(entry.getKey()).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
