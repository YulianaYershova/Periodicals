package persistence.dao.mySqlDAOImpl;

import persistence.dao.IPeriodical;
import persistence.entities.Periodical;
import persistence.entities.PeriodicalCategory;
import persistence.entities.PeriodicalType;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Julia on 09.08.2018
 */
public class PeriodicalDAO extends AbstractDAO implements IPeriodical {

    private static PeriodicalDAO periodicalDAO;
    private final String FIND_PERIODICAL_BY_ID = "SELECT periodical.id, periodical.type AS type_id," +
            "periodical_type.type, periodical.category AS category_id, periodical_category.category, " +
            "periodical.period, periodical.price, periodical.description " +
            "FROM periodical INNER JOIN periodical_type ON(periodical.type = periodical_type.id) " +
            "INNER JOIN periodical_category ON (periodical.category = periodical_category.id)" +
            "WHERE periodical.id = ?";

    private final String FIND_ALL_PERIODICALS = "SELECT periodical.id, periodical.type AS type_id, periodical_type.type," +
            "periodical.category AS category_id, periodical_category.category, periodical.period, " +
            "periodical.price, periodical.description " +
            "FROM periodical INNER JOIN periodical_type ON(periodical.type = periodical_type.id) " +
            "INNER JOIN periodical_category ON (periodical.category = periodical_category.id)";

    private final String INSERT_PERIODICAL = "INSERT INTO periodical (type, category, period, price, description) " +
            "VALUES (?,?,?,?,?)";

    private final String UPDATE_PERIODICAL = "UPDATE periodical SET periodical.type = ?, periodical.category = ?," +
            "periodical.period = ?, periodical.price = ?, periodical.description =? " +
            "WHERE periodical.id = ?";

    private PeriodicalDAO() {
    }

    public static PeriodicalDAO getInstance() {
        if (periodicalDAO == null) {
            periodicalDAO = new PeriodicalDAO();
        }
        return periodicalDAO;
    }


    @Override
    public Periodical findPeriodicalById(int id) {
        Periodical periodical = null;
        periodical = findById(FIND_PERIODICAL_BY_ID, id,
                set -> set != null ? new Periodical(
                        set.getInt(id),
                        new PeriodicalType(set.getInt("type_id"), set.getString("type")),
                        new PeriodicalCategory(set.getInt("category_id"), set.getString("category")),
                        set.getString("period"),
                        set.getBigDecimal("price"),
                        set.getString("description")) : null);
        return periodical;
    }

    @Override
    public ArrayList<Periodical> getAllPeriodicals() {
        ArrayList<Periodical> periodicals = null;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL_PERIODICALS)) {
                while (resultSet.next()) {
                    Periodical periodical = new Periodical(resultSet.getInt("id"),
                            new PeriodicalType(resultSet.getInt("type_id"), resultSet.getString("type")),
                            new PeriodicalCategory(resultSet.getInt("category_id"), resultSet.getString("category")),
                            resultSet.getString("period"),
                            resultSet.getBigDecimal("price"),
                            resultSet.getString("description"));
                    periodicals.add(periodical);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return periodicals;
    }

    @Override
    public boolean insertPeriodical(Periodical periodical) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PERIODICAL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, periodical.getType().getId());
            statement.setInt(2, periodical.getCategory().getId());
            statement.setString(3, periodical.getPeriod());
            statement.setBigDecimal(4, periodical.getPrice());
            statement.setString(5, periodical.getDescription());

            if (statement.executeUpdate() != 0) {
                periodical.setId(getGeneratedKey(statement));
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePeriodical(Periodical periodical) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PERIODICAL)) {
            statement.setInt(1, periodical.getType().getId());
            statement.setInt(2, periodical.getCategory().getId());
            statement.setString(3, periodical.getPeriod());
            statement.setBigDecimal(4, periodical.getPrice());
            statement.setString(5, periodical.getDescription());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePeriodical(Periodical periodical) {
        String query = "DELETE FROM periodical WHERE periodical.id = " + periodical.getId();
        if (execute(query) != 0) {
            return true;
        }
        return false;
    }
}
