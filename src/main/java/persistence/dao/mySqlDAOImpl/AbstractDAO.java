package persistence.dao.mySqlDAOImpl;


import logging.LoggerLoader;
import org.apache.log4j.Logger;
import persistence.dao.DataSource;
import persistence.dao.Mapper;

import java.sql.*;

/**
 * Created by Julia on 09.08.2018
 */
public abstract class AbstractDAO {
    private static final Logger logger = LoggerLoader.getLogger(AbstractDAO.class);

    protected DataSource dataSource = DataSource.getInstance();


    protected <T> T findById(String query, int id, Mapper<T> mapper) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapper.map(resultSet);
            }
        } catch (SQLException e) {
            logger.error("findById ", e);
        }
        return null;
    }

    protected int execute(String query) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("execute ", e);

        }
        return 0;
    }

    protected int getGeneratedKey(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.getGeneratedKeys()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("getGeneratedKey ", e);

        }
        throw new SQLException("Key is not generated");
    }
}
