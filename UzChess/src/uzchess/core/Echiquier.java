package uzchess.core;

public class Echiquier {

    private Case[][] cases;

    private boolean verifInterLigne(int ligneCaseDep, int ligneCaseArr, int colonneCaseDep)
    {
        int i;
        if(ligneCaseDep > ligneCaseArr)
        {
            int tmp = ligneCaseDep;
            ligneCaseDep = ligneCaseArr;
            ligneCaseArr = tmp;
        }
        
        i = ligneCaseDep;
        while( i < ligneCaseArr && cases[i][colonneCaseDep].getPiece()== null ){
            i++;
        }
        if (i < ligneCaseArr)
            return false; 
        return true;
    }
    
    private boolean verifInterColonne(int colonneCaseDep, int colonneCaseArr, int ligneCaseDep)
    {
       int i;
        if(colonneCaseDep > colonneCaseArr)
        {
            int tmp = colonneCaseDep;
            colonneCaseDep = colonneCaseArr;
            colonneCaseArr = tmp;
        }
        
        i = colonneCaseDep;
        while( i < colonneCaseArr && cases[ligneCaseDep][i].getPiece()== null ){
            i++;
        }
        if (i < colonneCaseArr)
            return false; 
        return true;
    }
   
    private boolean verifDiagonale(int ligneCaseDep, int ligneCaseArr, int colonneCaseDep, int colonneCaseArr)
    {
        int i, j;
        
        if(ligneCaseDep < ligneCaseArr && colonneCaseDep < colonneCaseArr)
                {
                    for(i=ligneCaseDep; i > ligneCaseArr; i--)
                        for(j=colonneCaseDep; j > colonneCaseArr; j--)
                        {
                            if(cases[i][j] != null)
                            {
                                return false;
                            }
                        }
                    return true;
                    
                }
                if(ligneCaseDep > ligneCaseArr && colonneCaseDep > colonneCaseArr)
                {
                    for(i=ligneCaseDep; i < ligneCaseArr; i++)
                        for(j=colonneCaseDep; j < colonneCaseArr; j++)
                        {
                            if(cases[i][j] != null)
                            {
                                return false;
                            }
                        }
                    return true;
                    
                }
                if(ligneCaseDep < ligneCaseArr && colonneCaseDep > colonneCaseArr)
                {
                    for(i=ligneCaseDep; i < ligneCaseArr; i++)
                        for(j=colonneCaseDep; j > colonneCaseArr; j--)
                        {
                            if(cases[i][j] != null)
                            {
                                return false;
                            }
                        }
                    return true;
                }
                if(ligneCaseDep > ligneCaseArr && colonneCaseDep < colonneCaseArr)
                {
                    for(i=ligneCaseDep; i > ligneCaseArr; i--)
                        for(j=colonneCaseDep; j < colonneCaseArr; j++)
                        {
                            if(cases[i][j] != null)
                            {
                                return false;
                            }
                        }
                    return true;
                    
                }
                return true;
        
    }
    
    public boolean verifierInter(Case dep, Case arr, int typeVerif) 
    {
        int ligneCaseDep = dep.getLigne();
        int colonneCaseDep = dep.getColonne();
        int ligneCaseArr = arr.getLigne();
        int colonneCaseArr = arr.getColonne();
      
        switch (typeVerif)
        {
            
            //LIGNE
            case 0 : verifInterLigne(ligneCaseDep, ligneCaseArr, colonneCaseDep); break;
            
            //COLONNE
            case 1 : verifInterColonne(colonneCaseDep, colonneCaseArr, ligneCaseDep); break;
            
            //DIAGONALE
            case 2 : verifDiagonale(ligneCaseDep, ligneCaseArr, colonneCaseDep, colonneCaseArr); break;
            default : break;
                
        }
      return false;
    }

    public Case[][] getEchiquier() {
        return cases;
    }

    public void setEchiquier(Case[][] echiquier) {
        this.cases = echiquier;
    }

    public Case[][] getCases() {
        return cases;
    }

    public void setCases(Case[][] cases) {
        this.cases = cases;
    }

}
