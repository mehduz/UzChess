package uzchess.core;

public class VerificateurPion extends Deplacement {

 
//penser  dep début, prise diago
    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {
       boolean verif=false;
       int colDep=dep.getColonne();
       int colArr=arr.getColonne();
       int ligDep=dep.getLigne();
       int ligArr=arr.getLigne();
         
       if(arr.getPiece()!=null){
           System.out.println("case occupée");
   
           if(ligArr==ligDep+1)
           {  
               if( colArr == colDep+1 || colDep == colArr-1)
                    verif=true;
           }
           
       }
       else{
           
           System.out.println("case vide");
           if(colArr==colDep)
           {
               if(ligDep==1) {
                   if(ligArr==ligDep+1)
                        verif=true;
                   if( ligArr==ligDep+2)
                       verif=this.moteurDeJeu.getJeuEchecs().getEchiquier().verifierInter(dep, arr, 1);
               }
               else
                   verif = ligArr==ligDep+1;
           }
  
       }
       return verif;
    }

}