package service;

import persistence.dao.IUser;
import persistence.dao.daoFactory.DAOFactory;
import persistence.entities.User;

/**
 * Created by Julia on 15.08.2018
 */
public class UserService {

    private IUser iUser = DAOFactory.getMySqlDAOFactory().getUserDAO();

    public boolean login(String login, String password) {
        User u = iUser.findUserById(1);
        User user = iUser.findUserByLogin(login);
        if (user != null) {
            if (user.getPassword().equals(password))
                return true;
        }
        return false;
    }

}
