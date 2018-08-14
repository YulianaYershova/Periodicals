package dao.mySqlDAOImpl;

import dao.IUser;
import entities.User;
import entities.UserRole;

import java.sql.*;

/**
 * Created by Julia on 09.08.2018
 */
public class UserDAO extends AbstractDAO implements IUser {

    private static UserDAO userDAO=null;

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (userDAO == null) {
            return new UserDAO();
        }
        return userDAO;
    }

    private final String FIND_USER_BY_ID = "SELECT user.id,user.role as role_id,user_role.role, user.name, " +
            "user.login, user.password\n" +
            "FROM user INNER JOIN user_role ON(user.role = user_role.id)\n" +
            "WHERE user.id= ?";
    private final String FIND_USER_BY_LOGIN = "SELECT user.id,user.role as role_id,user_role.role, user.name, " +
            "user.login, user.password\n" +
            "FROM user INNER JOIN user_role ON(user.role = user_role.id)\n" +
            "WHERE user.login= ?";
    private final String INSERT_USER = "INSERT INTO user (role,name,login,password) VALUES (?,?,?,?)";

    private final String UPDATE_USER = "UPDATE user SET user.role = ?, user.name = ?, user.login = ?, user.password = ?\n" +
            "WHERE user.id = ?";
    // private final String DELETE_USER = "DELETE FROM user WHERE user.id = ?;";


    @Override
    public User findUserById(int id) {
        User user = null;
        user = findById(FIND_USER_BY_ID, id,
                set -> set != null ? new User(
                        set.getInt(id),
                        new UserRole(set.getInt("role_id"), set.getString("role")),
                        set.getString("name"),
                        set.getString("login"),
                        set.getString("password")) : null);
        /*try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User(resultSet.getInt("id"),
                            new UserRole(resultSet.getInt("role_id"), resultSet.getString("role")),
                            resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return user;
    }

    @Override
    public User findUserByLogin(String login) {
        User user = null;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User(resultSet.getInt("id"),
                            new UserRole(resultSet.getInt("role_id"), resultSet.getString("role")),
                            resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean insertUser(User user) {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, user.getRole().getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            if (statement.executeUpdate() != 0) {
                user.setId(getGeneratedKey(statement));
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setInt(1, user.getRole().getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getId());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        String query = "DELETE FROM user WHERE user.id = " + user.getId();

        if (execute(query) != 0) {
            return true;
        }
        return false;
    }
}
