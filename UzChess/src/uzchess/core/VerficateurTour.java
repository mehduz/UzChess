package uzchess.core;


public class VerificateurTour extends Deplacement {
//on arrive dans cette méthode tout est nikel il faut juste verifier que déplacement comforme
    
    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {
        boolean verif=false;
       //typeVefir== pour ligne (0)colonne(1) ou diago(2)
        if ( dep.getLigne()== arr.getLigne() && this.moteurDeJeu.getJeuEchecs().getEchiquier().verifierInter(dep, arr, 0) )
            verif=true;
        
                
        if (dep.getColonne() == arr.getColonne() && this.moteurDeJeu.getJeuEchecs().getEchiquier().verifierInter(dep, arr, 1)  )
            verif=true;
        return verif;
           
    }

 

}