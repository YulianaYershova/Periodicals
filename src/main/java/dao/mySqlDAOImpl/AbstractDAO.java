package dao.mySqlDAOImpl;

import dao.Mapper;
import dao.daoFactory.DAOFactory;
import dao.daoFactory.MySqlDAOFactory;

import java.sql.*;

/**
 * Created by Julia on 09.08.2018
 */
public abstract class AbstractDAO {

    protected DAOFactory daoFactory = new MySqlDAOFactory();

    protected  <T> T findById(String query,int id, Mapper<T> mapper) {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapper.map(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    protected int execute(String query) {
        try (Connection connection = daoFactory.getConnection();
             Statement statement = connection.createStatement()) {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    protected int getGeneratedKey(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.getGeneratedKeys()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException("Key is not generated");
    }
}
