package uzchess.core;


public class VerificateurTour extends Deplacement {
//on arrive dans cette méthode tout est nikel il faut juste verifier que déplacement comforme
    
    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {
        
        int colCaseDep = dep.getColonne();
        int ligCaseDep = dep.getLigne();
        int colCaseArr = arr.getColonne();
        int ligCaseArr = arr.getLigne(); 
        
       //typeVefir== pour ligne (0)colonne(1) ou diago(2)
        if ( ligCaseDep !=  ligCaseArr && colCaseDep != colCaseArr )
            return false;
        int verifType = (ligCaseDep == ligCaseArr) ? 0 : 1;
        
        Echiquier ech = moteurDeJeu.getJeuEchecs().getEchiquier();
        return ech.verifierInter(dep, arr, verifType);
       
    }

 

}