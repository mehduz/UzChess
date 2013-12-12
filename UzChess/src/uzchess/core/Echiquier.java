package uzchess.core;

public class Echiquier {

    private Case[][] cases;

    private boolean verifLigne(int ligneCaseDep, int ligneCaseArr, int colonneCaseDep)
    {
        int i;
        
        if(ligneCaseDep < ligneCaseArr)
                {
                    i = ligneCaseDep;
                    while( i < ligneCaseArr && cases[i][colonneCaseDep].getPiece()== null ){
                        i++;
                    }
                    if (i < ligneCaseArr)
                        return false;
                }
        
        return true;
    }
    
    private boolean verifColonne(int colonneCaseDep, int colonneCaseArr, int ligneCaseDep)
    {
        int i;
        if(colonneCaseDep < colonneCaseArr)
                {
                    for(i=colonneCaseDep; i < colonneCaseArr; i++)
                    {
                        if(cases[ligneCaseDep][i] != null)
                        {
                            return false;
                        }
                    }
                    return true;
                }
                if(colonneCaseDep > colonneCaseArr)
                {
                    for(i=colonneCaseDep; i > colonneCaseArr; i--)
                    {
                        if(cases[ligneCaseDep][i] != null)
                        {
                            return false;
                        }
                    }
                    return true;
                }
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
        int i,j;
        
        switch (typeVerif)
        {
            
            //LIGNE
            case 0 : verifLigne(ligneCaseDep, ligneCaseArr, colonneCaseDep); break;
            
            //COLONNE
            case 1 : verifColonne(colonneCaseDep, colonneCaseArr, ligneCaseDep); break;
            
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
