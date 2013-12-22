/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzchess.dal;

import uzchess.constantes.DaoType;

/**
 *
 * @author mehduz
 */
public abstract class AbstractDaoFactory {

    public abstract Dao getJeuEchecsDao();

    public static AbstractDaoFactory getFactory(DaoType type) {
        if (type.equals(type.DAO_FILE)) {
            return new DaoFileFactory();
        }
        return null;
    }
}
 