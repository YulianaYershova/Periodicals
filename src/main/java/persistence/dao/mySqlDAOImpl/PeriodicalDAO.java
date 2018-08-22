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

    private final String SELECT_ALL_FROM_PERIODICAL = "SELECT periodical.id, periodical.title, periodical.periodical_type," +
            "periodical_type.type, periodical.periodical_category, periodical_category.category, " +
            "periodical.period, periodical.price, periodical.description FROM periodical INNER JOIN periodical_type " +
            "ON(periodical.periodical_type = periodical_type.id) " +
            "INNER JOIN periodical_category ON (periodical.periodical_category = periodical_category.id) ";

    private final String INSERT_PERIODICAL = "INSERT INTO periodical (title,periodical_type, periodical_category, " +
            "period, price, description) " +
            "VALUES (?,?,?,?,?,?)";

    private final String UPDATE_PERIODICAL = "UPDATE periodical SET periodical.title=?, periodical.periodical_type = ?, " +
            "periodical_category = ?, periodical.period = ?, periodical.price = ?, periodical.description =? " +
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
        periodical = findById(SELECT_ALL_FROM_PERIODICAL + "WHERE periodical.id = ?;", id,
                set -> set != null ? new Periodical(
                        set.getInt("id"),
                        set.getString("title"),
                        new PeriodicalType(set.getInt("periodical_type"), set.getString("type")),
                        new PeriodicalCategory(set.getInt("periodical_category"), set.getString("category")),
                        set.getString("period"),
                        set.getBigDecimal("price"),
                        set.getString("description")) : null);
        return periodical;
    }

    @Override
    public ArrayList<Periodical> findAllPeriodicals() {
        ArrayList<Periodical> periodicals = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_PERIODICAL)) {
                while (resultSet.next()) {
                    Periodical periodical = new Periodical(resultSet.getInt("id"),
                            resultSet.getString("title"),
                            new PeriodicalType(resultSet.getInt("periodical_type"), resultSet.getString("type")),
                            new PeriodicalCategory(resultSet.getInt("periodical_category"), resultSet.getString("category")),
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
            statement.setString(1, periodical.getTitle());
            statement.setInt(2, periodical.getPeriodicalType().getId());
            statement.setInt(3, periodical.getPeriodicalCategory().getId());
            statement.setString(4, periodical.getPeriod());
            statement.setBigDecimal(5, periodical.getPrice());
            statement.setString(6, periodical.getDescription());

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
            statement.setString(1, periodical.getTitle());
            statement.setInt(2, periodical.getPeriodicalType().getId());
            statement.setInt(3, periodical.getPeriodicalCategory().getId());
            statement.setString(4, periodical.getPeriod());
            statement.setBigDecimal(5, periodical.getPrice());
            statement.setString(6, periodical.getDescription());
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