package dao.daoFactory;

import dao.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Julia on 09.08.2018
 */
public abstract class DAOFactory {

    public static MySqlDAOFactory getMySqlDAOFactory() {
        return new MySqlDAOFactory();
    }

    public abstract Connection getConnection() throws SQLException;

    public abstract IPayment getPayment();

    public abstract IPeriodical getPeriodicalDAO();

    public abstract IPeriodicalCategory getPeriodicalCategoryDAO();

    public abstract IPeriodicalType getPeriodicalType();

    public abstract ISubscription getSubscriptionDAO();

    public abstract IUser getUserDAO();

    public abstract IUserRole getUserTypeDAO();
}
