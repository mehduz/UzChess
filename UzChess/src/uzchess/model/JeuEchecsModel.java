/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.model;

import java.util.ArrayList;
import javax.swing.event.EventListenerList;
import uzchess.constantes.Couleur;
import uzchess.core.JeuEchecs;
import uzchess.core.domain.Case;
import uzchess.core.domain.Joueur;
import uzchess.core.rules.VerificateurPion;
import uzchess.events.EchecsChangedEvent;
import uzchess.events.EchecsListener;

/**
 *
 * @author usere
 */
public class JeuEchecsModel extends JeuEchecs {

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

    private void fireEchecsChanged() {

        EchecsListener[] listenerList = (EchecsListener[]) this.listeners.getListeners(EchecsListener.class);

        for (EchecsListener el : listenerList) {
            el.echecsChanged(new EchecsChangedEvent(this));
        }
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
            echiquier.deplacer(dep, arr);
            invalide = false;
            coupsJoues.add(new Coup(dep, arr));
            tour = (tour == Couleur.BLANC) ? Couleur.NOIR : Couleur.BLANC;    
            echec = moteurDeJeu.detecterEchec(tour);
            this.detecterFin();
            
            Joueur j;
            if (super.tour == Couleur.BLANC && echiquier.getgBlanc() != null) {
                j = jb;
                echiquier.getgBlanc().setGhosted(false);
                echiquier.setgBlanc(null);
            } else if (super.tour == Couleur.NOIR && echiquier.getgNoir() != null) {
                j = jn;
                echiquier.getgNoir().setGhosted(false);
                echiquier.setgNoir(null);
            }

            if (arr.getPiece() != null) {
                //j.setScore((byte) (j.getScore() + arr.getPiece().getValeur()));
            }
        }
        fireEchecsChanged();
    }

}
