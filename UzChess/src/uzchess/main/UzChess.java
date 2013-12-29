package uzchess.main;

import uzchess.gui.VueJeu;
import uzchess.model.JeuEchecsModel;

public class UzChess {

    private static String DEF_NAME1 = "Joueur 1 ";
    private static String DEF_NAME2 = "Joueur 2 ";

    private UzChess() {
    }

    public static void main(String[] args) {

        JeuEchecsModel jeu = new JeuEchecsModel();
        jeu.initialiser( DEF_NAME1, DEF_NAME2 );
        new VueJeu(null, null, jeu.getEchiquier().getCases());
    }

}
