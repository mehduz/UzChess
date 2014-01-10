/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.controllers;

import java.util.ArrayList;
import java.util.logging.Logger;
import uzchess.constantes.DaoType;
import uzchess.core.domain.Case;
import uzchess.dal.*;
import uzchess.gui.EchecsView;
import uzchess.gui.VueJeu;
import uzchess.model.JeuEchecsModel;

/**
 *
 * @author user
 */
public class EchecsControler {

    private EchecsView view;
    private JeuEchecsModel model = null;
    private Case selected = null;
    private Dao dao = DaoFileFactory.getFactory(DaoType.DAO_FILE).getJeuEchecsDao();
    private static final Logger LOG = Logger.getLogger(EchecsControler.class.getName());

    public void displayView() {
        view.display();
    }

    public EchecsView getView() {
        return view;
    }

    public void setView(EchecsView view) {
        this.view = view;
    }

    public void setModel(JeuEchecsModel model) {
        this.model = model;
    }

    public void notifyCaseSelect(Case c) {

        model.setCasesToClean(model.getCasesValides());
        if (c.getPiece() != null && c.getPiece().getCouleur() == model.getTour()) {
            model.setCasesValides(model.getMoteurDeJeu().deplacementPossible(c.getPiece()));
            selected = c;

        } else if (selected != null && selected != c) {
            model.jouer(selected, c);
            model.setCasesValides(new ArrayList<Case>());
            selected = null;
        }
    }
    
    public void notifyNewGame() {
        
        model = new JeuEchecsModel();
        model.initialiser("test", "batard");
        model.addEchecsListener(view);
        VueJeu viewG =  ((VueJeu)view); 
        viewG.getPanelBoard().setCases(model.getEchiquier().getCases());
        viewG.getPanelInfoSide().getjListCoups().setText(null); 
        view.repaint();
    }
    
    public void notifyLoad(String fileName){
        
    }
    
    public void notifySave(String fileName){
        
        model.setNomPartie(fileName);
        try{
            dao.save(model);
             LOG.info("saved");
        }
        catch(DaoException daoe){
            LOG.info(daoe.getMessage());
        }
    }
    
}
