/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uzchess.dal;


/**
 *
 * @author mehduz
 */

public abstract class Dao <T>{
    
    public abstract void save( T game ) throws DaoException; 
    public abstract T load(String  fileName) throws DaoException;
    public abstract void delete(Object... criterias) throws DaoException; 
    
}
