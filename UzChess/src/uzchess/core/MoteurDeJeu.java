package uzchess.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import uzchess.constantes.Couleur;
import uzchess.core.domain.Case;
import uzchess.core.domain.CaseInterUtility;
import uzchess.core.domain.Echiquier;
import uzchess.core.domain.Piece;
import uzchess.core.rules.VerificateurCavalier;
import uzchess.core.rules.VerificateurPion;
import uzchess.core.rules.VerificateurRoi;
import uzchess.core.rules.VerificateurTour;

public class MoteurDeJeu implements Serializable{

    private Echiquier ech;
    private JeuEchecs jeu;

    public MoteurDeJeu(Echiquier ech, JeuEchecs jeu) {
        this.ech = ech;
        this.jeu = jeu;
    }

    public boolean verifierCoup(Case dep, Case arr) {

        Couleur couleur = jeu.getTour();
        if (dep != null && arr != null && dep.getPiece() != null && (arr.getPiece() == null || arr.getPiece().getCouleur() != couleur)) {
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
        moved = (king) ? ech.getSr().getRois().get(dep.getPiece()) : (tower)?(ech.getSt().getTours().containsKey(dep.getPiece()))? ech.getSt().getTours().get(dep.getPiece()):false : false;
       
        //Si la case visé est goshted
       if ( pdep.getDeplacement() instanceof VerificateurPion && arr.isGhosted()){
           if (jeu.getTour() == Couleur.BLANC) 
                parr = ech.getCases()[arr.getLigne() + 1][arr.getColonne()].getPiece(); //On défini la pièce visé comme celle du dessous
           else
                parr = ech.getCases()[arr.getLigne() - 1][arr.getColonne()].getPiece(); //On défini la pièce visé comme celle du dessous
       }
       
        ech.deplacer(dep, arr);//On effectue le déplacement de la pièce de départ vers la case d'arrivée
        ret = detecterEchec(pdep.getCouleur());

      
        dep.setPiece(pdep);//On annule le déplacement de la pièce de départ en remettant la pièce de départ sur la case de départ
        ech.getPieces(pdep.getCouleur()).put(pdep, dep);//On met à jour la hashmap
      
        if (ech.getGhost(jeu.getTour()) != null) {//Si on est dans le cas d'un ghost
            ech.getGhost(jeu.getTour()).setGhosted(false);//On passe ce ghost à false
            ech.setGhost(jeu.getTour(), null); //On met le statut ghost à null
        }
            if (parr != null &&  pdep.getDeplacement() instanceof VerificateurPion && arr.isGhosted()) {
//Si on a une pièce arrivée et un Pion et que la case visée est ghoster
             if (jeu.getTour() == Couleur.BLANC) {
                ech.getCases()[arr.getLigne() + 1][arr.getColonne()].setPiece(parr);//On remet la pièce à sa place
                ech.getPieces(Couleur.NOIR).put(parr , ech.getCases()[arr.getLigne() + 1][arr.getColonne()]);//On remet la hashmap à jour
                arr.setPiece(null);//La case d'arrivée est null
                
            } else {
                ech.getPieces(Couleur.BLANC).put(parr, ech.getCases()[arr.getLigne() + 1][arr.getColonne()]);
                ech.getCases()[arr.getLigne() - 1][arr.getColonne()].setPiece(parr);
                arr.setPiece(null);
            }
        }
        else if(parr != null){
            arr.setPiece(parr);
            ech.getPieces(parr.getCouleur()).put(parr, arr);
        }
        else
        {
            arr.setPiece(null);
        }
       
        if (king) {
            ech.setCaseRoi(jeu.tour , dep);
            byte decal = (byte) (arr.getColonne() - dep.getColonne());
            if (decal == 2 && pdep.getCouleur() == Couleur.BLANC) {
                ech.deplacer(ech.getCases()[7][5], ech.getCases()[7][7]);
            } else if (decal == -2 && pdep.getCouleur() == Couleur.BLANC) {
                ech.deplacer(ech.getCases()[7][3], ech.getCases()[7][0]);
            } else if (decal == 2 && pdep.getCouleur() == Couleur.NOIR) {
                ech.deplacer(ech.getCases()[0][5], ech.getCases()[0][7]);
            } else if (decal == -2 && pdep.getCouleur() == Couleur.NOIR) {
                ech.deplacer(ech.getCases()[0][3], ech.getCases()[0][0]);
            }
            ech.getSr().getRois().put(pdep, moved);
        } else if (tower) {
            ech.getSt().getTours().put(pdep, moved);
        }

        return !ret;
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
                if (isMenace(ca).isEmpty()) {
                    return false;
                }
            }
        }

        ArrayList<Case> casesMenace = isMenace(caseRoiAChecker);
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

    public ArrayList<Case> isMenace(Case maCase) {

        Collection<Case> casesAdverses;
        Couleur couleur = (jeu.getTour() == Couleur.BLANC) ? Couleur.NOIR : Couleur.BLANC;
        casesAdverses = ech.getPieces(couleur).values();

        ArrayList<Case> maListMenace = new ArrayList<>();

        for (Case c : casesAdverses) {
            Piece p = c.getPiece();
            if (p.getDeplacement().verifierDeplacement(c, maCase)) {
                maListMenace.add(c);
            }
        }
        return maListMenace;
    }

    public boolean detecterEchec(Couleur c) {

        Case caseRoiAChecker = ech.getCaseRoi(c);
        return !(isMenace(caseRoiAChecker).isEmpty());
    }

}
