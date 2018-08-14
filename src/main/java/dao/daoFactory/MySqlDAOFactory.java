package dao.daoFactory;

import dao.*;
import dao.mySqlDAOImpl.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Julia on 09.08.2018
 */
public class MySqlDAOFactory extends DAOFactory {

    @Override
    public Connection getConnection() throws SQLException {
        DataSource dataSource = null;
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/periodicals");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource.getConnection();
    }

    @Override
    public IPayment getPayment() {
        return PaymentDAO.getInstance();
    }

    @Override
    public IPeriodical getPeriodicalDAO() {
        return PeriodicalDAO.getInstance();
    }

    @Override
    public IPeriodicalCategory getPeriodicalCategoryDAO() {
        return PeriodicalCategoryDAO.getInstance();
    }

    @Override
    public IPeriodicalType getPeriodicalType() {
        return PeriodicalTypeDAO.getInstance();
    }

    @Override
    public ISubscription getSubscriptionDAO() {
        return SubscriptionDAO.getInstance();
    }

    @Override
    public IUser getUserDAO() {
        return UserDAO.getInstance();
    }

    @Override
    public IUserRole getUserTypeDAO() {
        return UserRoleDAO.getInstance();
    }
}
