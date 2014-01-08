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
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import uzchess.controllers.EchecsControler;
import uzchess.core.domain.Case;
import uzchess.events.EchecsChangedEvent;
import uzchess.model.JeuEchecsModel;

/**
 *
 * @author mehduz
 */
public class VueJeu extends EchecsView implements MouseListener, ActionListener {

    private static final String TITRE = "UzChess";

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
        this.panelInfoSide = new PanelInfoSide( this );
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
        
        if(jeu.getCasesValides().isEmpty()){
            panelInfoTexte.getJta().setText("\nLe joueur " + jeu.getJoueur(jeu.getTour()).getPseudo() + "a le trait : ");
            if(jeu.isEchec()){
                  panelInfoTexte.getJta().append("\nEchec !");
            }
            else if(jeu.isMat()){
                 panelInfoTexte.getJta().append("\nEchec et Mat!");
            } 
            else if(jeu.isPat()){
                 panelInfoTexte.getJta().append("\nPat!");
            }
            else if(jeu.isInvalide()){
                 panelInfoTexte.getJta().append("\nCoup invalide ! Veuillez rejouer");
            }
        }
        
        panelInfoTexte.repaint();
        panelBoard.repaint(); 
        panelInfoSide.repaint();
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

        if (((HashMap) panelBoard.getMapPieces()).containsKey((JPanel)e.getSource())) {
            this.getCtrl().notifyCaseSelect(panelBoard.getMapPieces().get((JPanel)e.getSource()));
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
        
        if (e.getSource() == panelInfoSide.getButtonExit()) {
            this.close();
        }
    }
}
