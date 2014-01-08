/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.controllers;

import java.util.ArrayList;
import uzchess.core.domain.Case;
import uzchess.gui.EchecsView;
import uzchess.model.JeuEchecsModel;

/**
 *
 * @author user
 */
public class EchecsControler {

    private EchecsView view;
    private JeuEchecsModel model = null;
    private Case selected = null;

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

}
