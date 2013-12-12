package uzchess.core;

public abstract class Deplacement{
    
  protected MoteurDeJeu moteurDeJeu;  

    public MoteurDeJeu getMoteurDeJeu() {
        return moteurDeJeu;
    }

    public void setMoteurDeJeu(MoteurDeJeu moteurDeJeu) {
        this.moteurDeJeu = moteurDeJeu;
    }
  public abstract boolean verifierDeplacement(Case dep, Case arr);
  
  //Uz
}