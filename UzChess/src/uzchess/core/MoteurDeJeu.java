package uzchess.core;

public class MoteurDeJeu {

  private  JeuEchecs jeuEchecs;

    public JeuEchecs getJeuEchecs() {
        return jeuEchecs;
    }

    public void setJeuEchecs(JeuEchecs jeuEchecs) {
        this.jeuEchecs = jeuEchecs;
    }
  
  public void verifierCoup(){
  }

  public void detecterMat() {
  }

  public void detecterPat(){
  }
  
  public boolean verifierInter( Case dep, Case arr, int typeVerif){
      return jeuEchecs.getEchiquier().verifierInter(dep, arr, typeVerif);
  } 

}