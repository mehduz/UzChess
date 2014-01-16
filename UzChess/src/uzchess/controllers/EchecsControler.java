/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;
import uzchess.constantes.Couleur;
import uzchess.constantes.DaoType;
import uzchess.constantes.Pieces;
import uzchess.constantes.PiecesUnicode;
import uzchess.core.domain.Case;
import uzchess.core.domain.Piece;
import uzchess.core.rules.Deplacement;
import uzchess.core.rules.VerificateurCavalier;
import uzchess.core.rules.VerificateurFou;
import uzchess.core.rules.VerificateurReine;
import uzchess.core.rules.VerificateurTour;
import uzchess.dal.*;
import uzchess.gui.EchecsView;
import uzchess.gui.RepPieceUnicode;
import uzchess.gui.VueJeu;
import uzchess.model.JeuEchecsModel;

/**
 *
 * @author user
 */
public class EchecsControler implements Serializable {

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
        VueJeu viewG = ((VueJeu) view);
        viewG.getPanelBoard().setCases(model.getEchiquier().getCases());
        viewG.getPanelInfoSide().getjListCoups().setText(null);
        view.repaint();
    }

    public void notifyLoad(String fileName) {
        try {
            model = (JeuEchecsModel) dao.load(fileName);
            model.cleanListeners();
            model.addEchecsListener(view);
            model.fireEchecsChanged();
        } catch (DaoException ex) {
            LOG.severe("Error loading " + fileName);
        }
        view.repaint();
    }

    public void notifySave(String fileName) {

        model.setNomPartie(fileName);
        try {
            dao.save(model);
            LOG.info("saved");
        } catch (DaoException daoe) {
            LOG.info(daoe.getMessage());
        }
    }

    public void notifyPromote(Case arr, Pieces choix) {
        Piece p = arr.getPiece();
        Deplacement dep = p.getDeplacement();
        String choose = null;
        Couleur c = p.getCouleur();
        if (choix == Pieces.REINE && c == Couleur.BLANC) {
            dep = new VerificateurReine();
            choose = PiecesUnicode.DAME_B;
        }
        if (choix == Pieces.REINE && c == Couleur.NOIR) {
            dep = new VerificateurReine();
            choose = PiecesUnicode.DAME_N;
        } else if (choix == Pieces.TOUR && c == Couleur.BLANC) {
            dep = new VerificateurTour();
            choose = PiecesUnicode.TOUR_B;
        } else if (choix == Pieces.CAVALIER && c == Couleur.BLANC) {
            dep = new VerificateurCavalier();
            choose = PiecesUnicode.CAVALIER_B;
        } else if (choix == Pieces.FOU && c == Couleur.BLANC) {
            dep = new VerificateurFou();
            choose = PiecesUnicode.FOU_B;
        } else if (choix == Pieces.TOUR && c == Couleur.NOIR) {
            dep = new VerificateurTour();
            choose = PiecesUnicode.TOUR_N;
        } else if (choix == Pieces.CAVALIER && c == Couleur.NOIR) {
            dep = new VerificateurCavalier();
            choose = PiecesUnicode.CAVALIER_N;
        } else if (choix == Pieces.FOU && c == Couleur.NOIR) {
            dep = new VerificateurFou();
            choose = PiecesUnicode.FOU_N;
        }

        p.setDeplacement(dep);
        arr.setPiece(p);

        arr.getPiece().setRep(new RepPieceUnicode(choose));

        view.repaint();

    }

}
