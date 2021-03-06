/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import uzchess.constantes.Couleur;
import uzchess.constantes.Pieces;
import uzchess.controllers.EchecsControler;
import uzchess.core.domain.Case;
import uzchess.core.utilities.CollectionUtility;
import uzchess.events.EchecsChangedEvent;
import uzchess.events.PromotEvent;
import uzchess.model.JeuEchecsModel;

/**
 *
 * @author mehduz
 */
public class VueJeu extends EchecsView implements MouseListener, ActionListener {

    private static final String TITRE = "UzChess";
    private static String SAVE_FOLDER = "UzChessSaves";

    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    private static final int FRAME_WIDTH = 840;
    private static final int FRAME_HEIGHT = 840;
    private static final int INFO_TEXT_HEIGHT = FRAME_HEIGHT / 9;
    private static final int INFO_SCORE_WIDTH = FRAME_WIDTH / 6;

    private PanelBoard panelBoard;
    private PanelInfoSide panelInfoSide;
    private PanelInfoTexte panelInfoTexte;

    public VueJeu(EchecsControler cctrl, Case[][] cases) {

        super(cctrl);
        this.setTitle(TITRE);
        this.setLocation((SCREEN_WIDTH - FRAME_WIDTH) / 2, (SCREEN_HEIGHT - FRAME_HEIGHT) / 4);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panelBoard = new PanelBoard(cases, this);
        this.panelInfoSide = new PanelInfoSide(this);
        this.panelInfoTexte = new PanelInfoTexte();
        this.initialise();
    }

    private void initialise() {

        panelInfoTexte.setPreferredSize(new Dimension(0, INFO_TEXT_HEIGHT));
        panelInfoSide.getInfoScore().setPreferredSize(new Dimension(INFO_SCORE_WIDTH, FRAME_HEIGHT / 14));
        Container c = getContentPane();
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new BorderLayout());
        layeredPane.add(panelBoard, "Center");
        layeredPane.add(panelInfoTexte, "South");
        layeredPane.add(panelInfoSide, "East");
        panelInfoSide.getListeCoups().setBorder(BorderFactory.createEmptyBorder(5, 2, 5, 2));
        c.add(layeredPane);
    }

    @Override
    public void echecsChanged(EchecsChangedEvent event) {

        JeuEchecsModel jeu = (JeuEchecsModel) event.getSource();
        this.panelBoard.setCases(jeu.getEchiquier().getCases());

        for (Case c : jeu.getCasesToClean()) {
            byte lig = c.getLigne();
            byte col = c.getColonne();
            panelBoard.getSquares()[lig][col].setBorder(null);
        }

        for (Case c : jeu.getCasesValides()) {
            byte lig = c.getLigne();
            byte col = c.getColonne();
            panelBoard.getSquares()[lig][col].setBorder(BorderFactory.createLineBorder(Color.green, 3));
        }

        if (jeu.getCasesValides().isEmpty()) {
            panelInfoTexte.getJta().setText("\nLe joueur " + jeu.getJoueur(jeu.getTour()).getPseudo() + "a le trait");
            if (jeu.isEchec()) {
                panelInfoTexte.getJta().append("\nEchec !");
                if (jeu.isMat()) {
                    panelInfoTexte.getJta().append(" et Mat!");
                }
            } else if (jeu.isPat()) {
                panelInfoTexte.getJta().append("\nPat!");
            } else if (jeu.isInvalide()) {
                panelInfoTexte.getJta().append("\nCoup invalide ! Veuillez rejouer");
            }
        }
        panelInfoSide.getjListCoups().setText(CollectionUtility.toStringCollection(jeu.getCoupsJoues(), "\n"));
        panelInfoSide.getJlb1().setText("Score " + jeu.getJoueur(Couleur.BLANC).getPseudo() + " : ");
        panelInfoSide.getJlb2().setText("Score " + jeu.getJoueur(Couleur.NOIR).getPseudo() + " : ");
        panelInfoSide.getJtf1().setText(new Byte(jeu.getJoueur(Couleur.BLANC).getScore()).toString());
        panelInfoSide.getJtf2().setText(new Byte(jeu.getJoueur(Couleur.NOIR).getScore()).toString());
        panelInfoTexte.repaint();
        panelBoard.repaint();
        panelInfoSide.repaint();
    }

    public PanelBoard getPanelBoard() {
        return panelBoard;
    }

    public PanelInfoSide getPanelInfoSide() {
        return panelInfoSide;
    }

    @Override
    public void display() {
        this.setVisible(true);
    }

    @Override
    public void close() {
        this.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (((HashMap) panelBoard.getMapPieces()).containsKey((JPanel) e.getSource())) {
            this.getCtrl().notifyCaseSelect(panelBoard.getMapPieces().get((JPanel) e.getSource()));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == panelInfoSide.getButtonExit()) {
            this.close();
            return;
        }

        if (source == panelInfoSide.getButtonNew()) {
            this.getCtrl().notifyNewGame();
            return;
        }

        if (source == panelInfoSide.getButtonLoad()) {

            JFileChooser jfc = new JFileChooser(SAVE_FOLDER);
            jfc.setDialogTitle("Charger Partie");
            jfc.showOpenDialog(null);
            String fileName = jfc.getName(jfc.getSelectedFile());
            this.getCtrl().notifyLoad(fileName);
            return;
        }

        if (source == panelInfoSide.getButtonSave()) {
            String fileName = (String) JOptionPane.showInputDialog(
                    this,
                    "Nom de sauvegarde : \n",
                    "Sauvegarde",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    null
            );
            this.getCtrl().notifySave(fileName);
        }
    }

    public void onPromote(PromotEvent e) {
        Case c = (Case) e.getSource();
        Pieces[] choix = {Pieces.REINE, Pieces.TOUR, Pieces.CAVALIER, Pieces.FOU};
        JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
        int rang = JOptionPane.showOptionDialog(null, "Veuillez choisir votre piece promue :", "UzChess Promotion !",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[3]);
        JOptionPane.showMessageDialog(null, "Votre nouvelle piece est " + choix[rang], "UzChess Promotion!", JOptionPane.INFORMATION_MESSAGE);

        this.getCtrl().notifyPromote(c, choix[rang]);

    }
}
