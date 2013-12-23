/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class BoardPanel extends JPanel {

    private int XXX = 0; //coordonnées de depart
    private int YYY = 0;
    
    
    public BoardPanel(){
        super();
    }
    
    public BoardPanel(int x, int y){
        this.XXX = x;
        this.YYY = y;
    }

    @Override
    public void paintComponent(Graphics g) {
        BoardPanel frame = new BoardPanel();

        int nb = 8;
        int w = getWidth()/8+1; //taille des cases
        int h = getHeight()/8+1;
        int co_x, co_y = YYY;
        boolean flag = false;
        Graphics2D g2 = (Graphics2D) g;
        
        setBackground(new Color(80, 40, 40));
        super.paintComponent(g);
        
        for (int j = 0; j < nb; j++) {
            if (j % 2 == 0) {
                flag = false;
            } else if (j % 2 == 1) {
                flag = true;
            }
            co_x = XXX;
            for (int i = 0; i < nb; i++) {
                if (flag == true) {
                    g2.setPaint(Color.WHITE);
                    g2.fill(new Rectangle2D.Double(co_x, co_y, w, h));
                    flag = false;
                } else {
                    g2.setPaint(Color.BLACK);
                    g2.fill(new Rectangle2D.Double(co_x, co_y, w, h));
                    flag = true;
                }
                co_x += w;
            }
            co_y += h;
        }
    }
}
