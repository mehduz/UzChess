/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.gui;

import javax.swing.JFrame;
import uzchess.controllers.CoreControler;
import uzchess.controllers.DalControler;
import uzchess.events.EchecsListener;
/**
 *
 * @author mehduz
 */
public abstract class EchecsView extends JFrame implements EchecsListener{
    
    private CoreControler CoreCtrl;
    private DalControler DalCtrl;

    public EchecsView(CoreControler CoreCtrl, DalControler DalCtrl) {
        
        this.CoreCtrl = CoreCtrl;
        this.DalCtrl = DalCtrl;
        
    }

    public CoreControler getCoreCtrl() {
        return CoreCtrl;
    }

    public void setCoreCtrl(CoreControler CoreCtrl) {
        this.CoreCtrl = CoreCtrl;
    }

    public DalControler getDalCtrl() {
        return DalCtrl;
    }

    public void setDalCtrl(DalControler DalCtrl) {
        this.DalCtrl = DalCtrl;
    }
    
    public abstract void display();
    public abstract void close();
    
}
