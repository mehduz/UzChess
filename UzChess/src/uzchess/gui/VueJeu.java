/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author mehduz
 */
public class VueJeu extends JFrame {

    private static final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

    private static final int FRAME_WIDTH = 840;
    private static final int FRAME_HEIGHT = 840;
    private static final int INFO_TEXT_HEIGHT = FRAME_HEIGHT / 7;
    private static final int INFO_SCORE_WIDTH = FRAME_WIDTH / 6;

    private PanelBoard panelBoard;
    private PanelInfoSide panelInfoSide;
    private PanelInfoTexte panelInfoTexte;

    public VueJeu(String titre) {

        super(titre);
        this.setLocation((screenWidth - FRAME_WIDTH) / 2, (screenHeight - FRAME_HEIGHT) / 4);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panelBoard = new PanelBoard();
        this.panelInfoSide = new PanelInfoSide();
        this.panelInfoTexte = new PanelInfoTexte();
        this.initialise();
        this.setVisible(true);

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
}
