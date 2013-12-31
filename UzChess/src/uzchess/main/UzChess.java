package uzchess.main;

import uzchess.controllers.EchecsControler;
import uzchess.gui.VueJeu;
import uzchess.model.JeuEchecsModel;

public class UzChess {

    private static String DEF_NAME1 = "Joueur 1 ";
    private static String DEF_NAME2 = "Joueur 2 ";
    private static String DEF_MESSAGE = "Bienvenue à Uzchess";

    private UzChess() {
    }

    public static void main(String[] args) {

        JeuEchecsModel jeu = new JeuEchecsModel();
        jeu.initialiser( DEF_NAME1, DEF_NAME2 );
        EchecsControler controler = new EchecsControler();
        VueJeu view = new VueJeu(controler, jeu.getEchiquier().getCases());
        controler.setModel(jeu);
        controler.setView(view);
        jeu.addEchecsListener(view);
        view.display();
    }

}
