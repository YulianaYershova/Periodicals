package persistence.dao.mySqlDAOImpl;


import persistence.dao.IUserRole;
import persistence.entities.UserRole;

import java.sql.*;

/**
 * Created by Julia on 09.08.2018
 */
public class UserRoleDAO extends AbstractDAO implements IUserRole {

    private static UserRoleDAO userRoleDAO;
    private final String SELECT_ALL_FROM_USER_ROLE = "SELECT * FROM user_role ";
    private final String INSERT_USER_ROLE = "INSERT INTO user_role (role) VALUES (?)";
    private final String UPDATE_USER_ROLE = "UPDATE user_role SET user_role.role = ? WHERE user_role.id = ?";

    private UserRoleDAO() {
    }

    public static UserRoleDAO getInstance() {
        if (userRoleDAO == null) {
            userRoleDAO = new UserRoleDAO();
        }
        return userRoleDAO;
    }

    @Override
    public UserRole findUserRoleById(int id) {
        UserRole role = null;
        role = findById(SELECT_ALL_FROM_USER_ROLE + "WHERE user_role.id= ?", id,
                set -> set != null ? new UserRole(
                        set.getInt(id),
                        set.getString("role")) : null);
        return role;
    }

    @Override
    public UserRole findUserRoleByRole(String role) {
        UserRole userRole = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_USER_ROLE + "WHERE user_role.role= ?")) {
            statement.setString(1, role);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    userRole = new UserRole(resultSet.getInt("id"),
                            resultSet.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRole;
    }

    @Override
    public boolean insertUserRole(UserRole role) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_ROLE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, role.getRole());
            if (statement.executeUpdate() != 0) {
                role.setId(getGeneratedKey(statement));
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUserRole(UserRole role) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ROLE)) {
            statement.setString(1, role.getRole());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUserRole(UserRole role) {
        String query = "DELETE FROM user_role WHERE user_role.id = " + role.getId();
        if (execute(query) != 0) {
            return true;
        }
        return false;
    }
}

