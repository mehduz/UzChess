package uzchess.core;

public class VerificateurFou extends Deplacement {

    @Override
    public boolean verifierDeplacement(Case dep, Case arr)
    {     
        int ligneCaseDep = dep.getLigne();
        int colonneCaseDep = dep.getColonne();
        int ligneCaseArr = arr.getLigne();
        int colonneCaseArr = arr.getColonne();
        
        int decLigne = Math.abs(ligneCaseDep - ligneCaseArr);
        int decColonne = Math.abs(colonneCaseDep - colonneCaseArr);
        
        //Si la case arrivé est sur le même ligne ou la même colonne
        Echiquier ech = moteurDeJeu.getJeuEchecs().getEchiquier();
        return ech.verifierInter(dep, arr, 2) && decLigne == decColonne; 
           
    }

}