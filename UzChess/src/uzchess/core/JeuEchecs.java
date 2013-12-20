package uzchess.core;

import uzchess.core.model.Echiquier;
import uzchess.constantes.Couleur;
import uzchess.constantes.RetourJouer;
import uzchess.core.model.Case;

public class JeuEchecs {

    private Couleur tour;
    private byte compteurCoups;
    private MoteurDeJeu moteurDeJeu;
    private Echiquier echiquier;
    
    private JeuEchecs() {
    }
    
    public void setCompteurCoups(byte compteurCoups) {
        this.compteurCoups = compteurCoups;
    }

    public void incrementerCompteurCoups() {
        this.compteurCoups ++;
    }
    private static class SingletonHolder {

        private final static JeuEchecs INSTANCE = new JeuEchecs();
    }

    public static JeuEchecs getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initialiser() {
          throw new UnsupportedOperationException();
    }

    public void quitter() {
          throw new UnsupportedOperationException();
    }

    public void abandonner() {
          throw new UnsupportedOperationException();
    }

    public RetourJouer detecterFin() 
    {
        
        if ( moteurDeJeu.detecterMat() )
            return RetourJouer.ISMAT;
        
        if ( moteurDeJeu.detecterPat() )
            return RetourJouer.ISPAT;
        
        if ( moteurDeJeu.detecterNul () )
            return RetourJouer.ISNUL;
        
        return RetourJouer.ISPOSSIBLE;
    }


    public RetourJouer jouer(Case dep, Case arr) {
       
       echiquier = JeuEchecs.getInstance().getEchiquier();
       RetourJouer retour = detecterFin();
       
       if( retour == RetourJouer.ISPOSSIBLE)
       {
            if( moteurDeJeu.verifierCoup(dep, arr) ){
               dep.getPiece().deplacer(dep, arr);
               return RetourJouer.ISPOSSIBLE;
           }   
       }
       return retour;
    }

    public Couleur getTour() {
        return tour;
    } 

    public byte getCompteurCoups() {
        return compteurCoups;
    }

    public MoteurDeJeu getMoteurDeJeu() {
        return moteurDeJeu;
    }

    public Echiquier getEchiquier() {
        return echiquier;
    }
}
