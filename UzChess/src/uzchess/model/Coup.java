/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.model;

import uzchess.core.domain.Case;

/**
 *
 * @author user
 */
public class Coup {

    public Coup(Case caseDep, Case caseArr) {
        this.caseDep = caseDep;
        this.caseArr = caseArr;
    }
    
    private Case caseDep;
    private Case caseArr;
    
    @Override
    public String toString(){
        return caseDep.toString() + "->" + caseArr.toString();
    }
}
    

