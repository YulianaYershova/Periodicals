package persistence.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Julia on 16.08.2018
 */
public class DataSource {

    private static DataSource dataSource;

    private DataSource() {
    }

    public static DataSource getInstance() {
        if (dataSource == null) {
            dataSource = new DataSource();
        }
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        javax.sql.DataSource dataSource = null;
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (javax.sql.DataSource) initialContext.lookup("java:/comp/env/jdbc/periodicals");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource.getConnection();
    }
}
