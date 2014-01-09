/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class PanelInfoSide extends JPanel {

    private static final String LABEL_BUTTON_1 = "Nouvelle Partie";
    private static final String LABEL_BUTTON_2 = "Charger Partie";
    private static final String LABEL_BUTTON_3 = "Sauvegarder";
    private static final String LABEL_BUTTON_4 = "Quitter";

    private JPanel menu;
    private JPanel infoScore;
    private JLabel jlb1;
    private JLabel jlb2;
    private JTextField jtf1;
    private JTextField jtf2;
    private JPanel listeCoups;
    private JButton buttonNew;
    private JButton buttonLoad;
    private JButton buttonSave;
    private JButton buttonExit;
    private JTextArea jListCoups; 

    public PanelInfoSide(VueJeu vue) {

        super(new BorderLayout());
        menu = new JPanel();
        menu.setLayout(new GridLayout(4, 1, 0, 10));

        this.buttonNew = new JButton(LABEL_BUTTON_1);
        this.buttonLoad = new JButton(LABEL_BUTTON_2);
        this.buttonSave = new JButton(LABEL_BUTTON_3);
        this.buttonExit = new JButton(LABEL_BUTTON_4);

        menu.add(buttonNew);
        menu.add(buttonLoad);
        menu.add(buttonSave); 
        menu.add(buttonExit);

        jlb1 = new JLabel("Score Joueur 1 : ");
        jlb2 = new JLabel("Score Joueur 2 : ");

        jtf1 = new JTextField("0");
        jtf2 = new JTextField("0");
        jtf1.setBorder(null);
        jtf2.setBorder(null);

        infoScore = new JPanel();
        infoScore.add(jlb1);
        infoScore.add(jtf1);
        infoScore.add(jlb2);
        infoScore.add(jtf2);

        jtf1.setEditable(false);
        jtf2.setEditable(false);

        listeCoups = new JPanel(new GridLayout(1, 1));
        jListCoups = new JTextArea("\nListe des coups joués : ");
        jListCoups.setEditable(false);
        final JScrollPane jsp = new JScrollPane(jListCoups);
        listeCoups.add(jListCoups);
        listeCoups.add(new JScrollPane(jListCoups));

        this.add(menu, "North");
        this.add(listeCoups, "Center");
        this.add(infoScore, "South");
        
        buttonExit.addActionListener(vue);

    }

    public JTextArea getjListCoups() {
        return jListCoups;
    }
    
    public JPanel getMenu() {
        return menu;
    }

    public JPanel getInfoScore() {
        return infoScore;
    }

    public JLabel getJlb1() {
        return jlb1;
    }

    public JLabel getJlb2() {
        return jlb2;
    }

    public JTextField getJtf1() {
        return jtf1;
    }

    public JTextField getJtf2() {
        return jtf2;
    }

    public JPanel getListeCoups() {
        return listeCoups;
    }

    public JButton getButtonNew() {
        return buttonNew;
    }

    public JButton getButtonLoad() {
        return buttonLoad;
    }

    public JButton getButtonSave() {
        return buttonSave;
    }

    public JButton getButtonExit() {
        return buttonExit;
    }
    
}
