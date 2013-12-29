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
    
    private static JTextArea jta;
    
    public PanelInfoTexte(){
        super();
        this.setLayout(new BorderLayout());
        jta = new JTextArea("\nBienvenue à UzChess ! ");
        this.add(jta, "Center");
        jta.setEditable(false);
        this.add(new JScrollPane(jta));
    }

    public static JTextArea getJta() {
        return jta;
    }
   
}
