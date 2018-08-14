package dao.mySqlDAOImpl;

import dao.IUserRole;
import entities.UserRole;

import java.sql.*;

/**
 * Created by Julia on 09.08.2018
 */
public class UserRoleDAO extends AbstractDAO implements IUserRole {

    private static UserRoleDAO userRoleDAO=null;

    private UserRoleDAO() {
    }

    public static UserRoleDAO getInstance() {
        if (userRoleDAO == null) {
            return new UserRoleDAO();
        }
        return userRoleDAO;
    }

    private final String FIND_USER_ROLE_BY_ID = "SELECT * FROM user_role WHERE user_role.id= ?";
    private final String FIND_USER_ROLE_BY_ROLE = "SELECT * FROM user_role WHERE user_role.role= ?";
    private final String INSERT_USER_ROLE = "INSERT INTO user_role (role) VALUES (?)";
    private final String UPDATE_USER_ROLE = "UPDATE user_role SET user_role.role = ? WHERE user_role.id = ?";

    @Override
    public UserRole findUserRoleById(int id) {
        UserRole role = null;
        role = findById(FIND_USER_ROLE_BY_ID, id,
                set -> set != null ? new UserRole(
                        set.getInt(id),
                        set.getString("role")) : null);
        return role;
        /*try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_ROLE_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    role = new UserRole(resultSet.getInt("id"),
                            resultSet.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;*/
    }

    @Override
    public UserRole findUserRoleByRole(String role) {
        UserRole userRole = null;
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_ROLE_BY_ROLE)) {
            statement.setString(1, "role");
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
        try (Connection connection = daoFactory.getConnection();
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
        try (Connection connection = daoFactory.getConnection();
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
       /* try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_ROLE);) {
            statement.setInt(1, role.getId());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;*/
    }
}

