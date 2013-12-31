/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.gui;

import javax.swing.JFrame;
import uzchess.controllers.EchecsControler;
import uzchess.events.EchecsListener;
/**
 *
 * @author mehduz
 */
public abstract class EchecsView extends JFrame implements EchecsListener{
    
    private EchecsControler ctrl;

    public EchecsView(EchecsControler CoreCtrl) {
        
        this.ctrl = CoreCtrl;
    }

    public EchecsControler getCtrl() {
        return ctrl;
    }

    public void setCoreCtrl(EchecsControler ctrl) {
        this.ctrl = ctrl; 
    }
    
    public abstract void display();
    public abstract void close();
    
}
