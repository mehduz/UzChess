package uzchess.dal;

import uzchess.core.JeuEchecs;


public class DaoFileFactory extends AbstractDaoFactory{

    @Override
    public Dao<JeuEchecs> getJeuEchecsDao() {
       return new JeuEchecsDaoFile(); 
    }
    
}