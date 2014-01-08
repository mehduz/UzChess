
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author user
 */
public class PanelInfoTexte extends JPanel{
    
    private JTextArea jta;
    private static final String DEFAULT_MSG = "\nBienvenue à UzChess !";
    private String text = "";
    
    public PanelInfoTexte(){
        super();
        this.setLayout(new BorderLayout());
        jta = new JTextArea(DEFAULT_MSG);
        this.add(jta, "Center");
        jta.setEditable(false);
        this.add(new JScrollPane(jta));
    }

    public JTextArea getJta() {
        return jta;
    }
    
}
