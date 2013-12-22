/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.dal;

/**
 *
 * @author user
 */
public class DaoException extends Exception{

    DaoException(String string) {
        super(string);
    }
    
    DaoException( String message, Throwable cause ){
        super(message, cause);
    }
    
}
