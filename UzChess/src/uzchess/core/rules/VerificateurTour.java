package uzchess.core.rules;

import uzchess.constantes.Couleur;
import uzchess.constantes.TypeTour;
import uzchess.core.StatutTour;
import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;

public class VerificateurTour implements Deplacement {
//on arrive dans cette méthode tout est nikel il faut juste verifier que déplacement comforme

    Echiquier ech;
    StatutTour st;
    
    @Override
    public boolean verifierDeplacement(Case dep, Case arr, boolean noticeMove) {
        
        byte colCaseDep = dep.getColonne();
        byte ligCaseDep = dep.getLigne();
        byte colCaseArr = arr.getColonne();
        byte ligCaseArr = arr.getLigne();

       
        if (ligCaseDep != ligCaseArr && colCaseDep != colCaseArr) {
            return false;
        }
        
        if(ech.verifCasesInter( ech.getCasesInter(dep, arr ))){
            if(noticeMove){
                Couleur c = dep.getPiece().getCouleur();
                byte col  = dep.getColonne();
                TypeTour typeTour;
                typeTour = (c == Couleur.BLANC)?( dep.getColonne() == 0)? TypeTour.TBO : TypeTour.TBE : ( dep.getColonne() == 0)? TypeTour.TNO : TypeTour.TNE ;
                st.setTourMoved(typeTour, noticeMove);
            }
        }
        
        return true;
    }
}
