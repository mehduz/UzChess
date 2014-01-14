package uzchess.dal;

import uzchess.model.JeuEchecsModel;


public class DaoFileFactory extends AbstractDaoFactory{

    @Override
    public Dao<JeuEchecsModel> getJeuEchecsDao() {
       return new JeuEchecsDaoFile(); 
    }
    
}