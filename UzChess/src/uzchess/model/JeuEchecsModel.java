/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.event.EventListenerList;
import uzchess.constantes.Couleur;
import uzchess.core.JeuEchecs;
import uzchess.core.domain.Case;
import uzchess.core.domain.Joueur;
import uzchess.core.rules.VerificateurPion;
import uzchess.events.EchecsChangedEvent;
import uzchess.events.EchecsListener;
import uzchess.events.PromotEvent;

/**
 *
 * @author usere
 */
public class JeuEchecsModel extends JeuEchecs implements Serializable{

    private EventListenerList listeners;
    private ArrayList<Case> casesValides;
    private ArrayList<Case> casesToClean;
    private ArrayList<Coup> coupsJoues;

    public JeuEchecsModel() {
        super();
        this.listeners = new EventListenerList();
        this.casesValides = new ArrayList<>();
        this.casesToClean = new ArrayList<>();
        this.coupsJoues = new ArrayList<>();
    }

    public void addEchecsListener(EchecsListener l) {
        this.listeners.add(EchecsListener.class, l);
    }

    public void removeEchecsListener(EchecsListener l) {
        this.listeners.remove(EchecsListener.class, l);
    }

    public void fireEchecsChanged() {

        EchecsListener[] listenerList = (EchecsListener[]) this.listeners.getListeners(EchecsListener.class);

        for (EchecsListener el : listenerList) {
            el.echecsChanged(new EchecsChangedEvent(this));
        }
    }
    
    public void cleanListeners(){
        this.listeners = new EventListenerList();
    }

    public void setCasesValides(ArrayList<Case> casesValides) {
        this.casesValides = casesValides;
        fireEchecsChanged();
    }

    public void setCasesToClean(ArrayList<Case> casesToClean) {
        this.casesToClean = casesToClean;
    }

    public ArrayList<Case> getCasesValides() {
        return this.casesValides;
    }

    public ArrayList<Case> getCasesToClean() {
        return casesToClean;
    }

    public ArrayList<Coup> getCoupsJoues() {
        return coupsJoues;
    }

    /*we can't use super.jouer() here : it won't works*/
    @Override
    public void jouer(Case dep, Case arr) {
        invalide = true;
        if (casesToClean.contains(arr)) {

            compteurCoups = (dep.getPiece().getDeplacement() instanceof VerificateurPion || arr.getPiece() != null) ? 0 : (byte) (compteurCoups + 1);
            if (arr.getPiece() != null || arr.isGhosted()) {
                if( arr.isGhosted()){
                    this.getJoueur(tour).setScore((byte)(this.getJoueur(tour).getScore() + 1));
                }
                else
                {
                    this.getJoueur(tour).setScore((byte)(this.getJoueur(tour).getScore() + arr.getPiece().getValue()));
                }
            }
            echiquier.deplacer(dep, arr);
            invalide = false;
            
            
            if(arr.getPiece().getDeplacement() instanceof VerificateurPion)
            {
                if(tour == Couleur.BLANC && arr.getLigne() == 0 )
                {
                    //envoyer a la vue event promotion
                    //class event promotion herite eventechec
                    firePromotion(arr);
                      
                }
                else if(tour == Couleur.NOIR && arr.getLigne() == 7 )
                {
                       firePromotion(arr);   
                }
            }
            
            coupsJoues.add(new Coup(dep, arr));
            tour = (tour == Couleur.BLANC) ? Couleur.NOIR : Couleur.BLANC;
            
            Joueur j = null;
            if (super.tour == Couleur.BLANC && echiquier.getgBlanc() != null) {
                j = jb;
                echiquier.getgBlanc().setGhosted(false);
                echiquier.setgBlanc(null);
            } else if (super.tour == Couleur.NOIR && echiquier.getgNoir() != null) {
                j = jn;
                echiquier.getgNoir().setGhosted(false);
                echiquier.setgNoir(null);
            }
            
            echec = moteurDeJeu.detecterEchec(tour);
            detecterFin();
            
        }
        fireEchecsChanged();
    }
    
    private void firePromotion(Case arr) {

        EchecsListener[] listenerList = (EchecsListener[]) this.listeners.getListeners(EchecsListener.class);

        for (EchecsListener el : listenerList) {
            el.onPromote(new PromotEvent(arr));
        }
    }

}
