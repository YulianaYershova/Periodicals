package persistence.dao.mySqlDAOImpl;

import persistence.dao.IPeriodicalCategory;
import persistence.entities.PeriodicalCategory;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Julia on 09.08.2018
 */
public class PeriodicalCategoryDAO extends AbstractDAO implements IPeriodicalCategory {

    private static PeriodicalCategoryDAO periodicalCategoryDAO;
    private final String FIND_CATEGORY_BY_ID = "SELECT * FROM periodical_category WHERE periodical_category.id= ?";
    private final String FIND_ALL_CATEGORIES = "SELECT * FROM periodical_category";
    private final String FIND_CATEGORY_BY_PERIODICAL_CATEGORY = "SELECT * FROM periodical_category " +
            "WHERE periodical_category.category= ?";
    private final String INSERT_CATEGORY = "INSERT INTO periodical_category (category) VALUES (?)";
    private final String UPDATE_CATEGORY = "UPDATE periodical_category SET periodical_category.category = ? " +
            "WHERE periodical_category.id = ?";

    private PeriodicalCategoryDAO() {
    }

    public static PeriodicalCategoryDAO getInstance() {
        if (periodicalCategoryDAO == null) {
            periodicalCategoryDAO = new PeriodicalCategoryDAO();
        }
        return periodicalCategoryDAO;
    }


    @Override
    public PeriodicalCategory findCategoryById(int id) {
        PeriodicalCategory category = null;

        category = findById(FIND_CATEGORY_BY_ID, id,
                set -> set != null ? new PeriodicalCategory(
                        set.getInt(id),
                        set.getString("category")) : null);

        return category;
    }

    @Override
    public ArrayList<PeriodicalCategory> getAllCategories() {
        ArrayList<PeriodicalCategory> categories = null;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            try (ResultSet resultSet = statement.executeQuery(FIND_ALL_CATEGORIES)) {
                while (resultSet.next()) {
                    PeriodicalCategory category = new PeriodicalCategory(resultSet.getInt("id"),
                            resultSet.getString("category"));
                    categories.add(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public boolean insertCategory(PeriodicalCategory category) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getCategory());
            if (statement.executeUpdate() != 0) {
                category.setId(getGeneratedKey(statement));
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCategory(PeriodicalCategory category) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY)) {
            statement.setString(1, category.getCategory());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCategory(PeriodicalCategory category) {
        String query = "DELETE FROM periodical_category WHERE periodical_category.id = " + category.getId();
        if (execute(query) != 0) {
            return true;
        }
        return false;
    }
}
