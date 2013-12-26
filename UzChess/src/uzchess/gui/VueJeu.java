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
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class VueJeu extends JFrame{
    
     private static final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
     private static final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
     
     private static final int FRAME_WIDTH = 840;
     private static final int FRAME_HEIGHT = 840;
     private static final int INFO_TEXT_HEIGHT = FRAME_HEIGHT / 6;
     private static final int INFO_SCORE_WIDTH = FRAME_WIDTH / 6;
     
     private static final String LABEL_BUTTON_1 = "Nouvelle Partie";
     private static final String LABEL_BUTTON_2 = "Charger Partie";
     private static final String LABEL_BUTTON_3 = "Sauvegarder";
     private static final String LABEL_BUTTON_4 = "Quitter";
     
     private static final Color SQUARES_COLOR_BLACK = new Color(200, 173, 127);
     private static final Color SQUARES_COLOR_WHITE = new Color(245, 245, 220);
     
     private JTextArea jta;
     
     public VueJeu(String titre){
         
        super(titre); 
        this.initialise();
        this.setLocation( (screenWidth - FRAME_WIDTH)/ 2, (screenHeight - FRAME_HEIGHT) / 4);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
     }
     
    private void initialise(){
        
        Container c = getContentPane();
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new BorderLayout());
        layeredPane.add(creerPanelBoard(), "Center");
        layeredPane.add(creerPanelInfoTexte(), "South");
        layeredPane.add(creerPanelInfo(), "East");
        c.add(layeredPane);
        
    }
   
    private JPanel creerPanelBoard(){
        
        JPanel chessBoard = new JPanel();
        chessBoard.setLayout( new GridLayout(8, 8) );
   
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );
            int row = (i / 8) % 2;
            if (row == 0)
            square.setBackground( i % 2 == 0 ? SQUARES_COLOR_BLACK : SQUARES_COLOR_WHITE );
            else
            square.setBackground( i % 2 == 0 ? SQUARES_COLOR_WHITE : SQUARES_COLOR_BLACK );
        }
        return chessBoard;
    }
    
    
    private JPanel creerPanelInfoTexte(){
        
        JPanel infoTexte = new JPanel();
        infoTexte.setLayout(new BorderLayout());
        jta = new JTextArea("\nBienvenue à UzChess ! ");
        infoTexte.add(jta, "Center");
        jta.setEditable(false);
        infoTexte.setPreferredSize(new Dimension(0,INFO_TEXT_HEIGHT));
        infoTexte.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        return infoTexte;
    }
    
    private JPanel creerPanelInfo(){
        
        JPanel info = new JPanel(new BorderLayout());
        JPanel infoScore = new JPanel();
        infoScore.setLayout(new GridLayout(4, 1, 0, 10));
        infoScore.setPreferredSize(new Dimension(INFO_SCORE_WIDTH,0));
        
        infoScore.add(new JButton(LABEL_BUTTON_1));
        infoScore.add(new JButton(LABEL_BUTTON_2));
        infoScore.add(new JButton(LABEL_BUTTON_3));
        infoScore.add(new JButton(LABEL_BUTTON_4));
        
        JPanel infoPartie = new JPanel(); 
        infoScore.setBorder(BorderFactory.createEmptyBorder(2, 2, 0, 2));
        infoScore.setPreferredSize(new Dimension(INFO_SCORE_WIDTH, FRAME_HEIGHT /6 ) );
        
        JLabel jlb1 = new JLabel("Score Joueur 1 : " );
        JLabel jlb2 = new JLabel("Score Joueur 2 : " );
        
        JTextField jtf1 = new JTextField("0");
        JTextField jtf2 = new JTextField("0");
        jtf1.setBorder(null);
        jtf2.setBorder(null);
                
        infoPartie.add( jlb1 );
        infoPartie.add( jtf1 );
        infoPartie.add( jlb2 );
        infoPartie.add( jtf2 );
        
        jtf1.setEditable( false );
        jtf2.setEditable( false );
        
        JPanel panelListe = new JPanel(new GridLayout(1,1));
        JTextArea jListCoups = new JTextArea("\nListe des coups joués : ");
        jListCoups.setEditable(false);
        final JScrollPane jsp = new JScrollPane(jListCoups);
        panelListe.add(jListCoups);
        panelListe.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        info.add( infoScore, "North" );
        info.add( panelListe, "Center" );
        info.add( infoPartie, "South" );
        
                
        infoPartie.setPreferredSize(new Dimension(INFO_SCORE_WIDTH, FRAME_HEIGHT /14 )); 
        return info;
    }
   
}