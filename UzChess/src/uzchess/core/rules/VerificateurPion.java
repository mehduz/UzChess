package uzchess.core.rules;

import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;

public class VerificateurPion implements Deplacement {

 
//penser  dep début, prise diago
    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {
        
       boolean verif=false;
       byte colDep=dep.getColonne();
       byte colArr=arr.getColonne();
       byte ligDep=dep.getLigne();
       byte ligArr=arr.getLigne();
         
       if(arr.getPiece()!=null){
           System.out.println("case occupée");
   
           if(ligArr==ligDep+1)
           {  
               if( ( colArr == colDep+1 ) || ( colDep == colArr-1 ))
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
                   /*if( ligArr==ligDep+2)
                       verif=Echiquier.getInstance().verifCasesInter(dep, arr, 1);*/
               }
               else
                   verif = ligArr==ligDep+1;
           }
  
       }
       return verif;
    }

}