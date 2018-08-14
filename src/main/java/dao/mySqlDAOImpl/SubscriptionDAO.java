package dao.mySqlDAOImpl;

import dao.ISubscription;
import entities.Subscription;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Julia on 09.08.2018
 */
public class SubscriptionDAO extends AbstractDAO implements ISubscription {

    private static SubscriptionDAO subscriptionDAO = null;

    private SubscriptionDAO() {
    }

    public static SubscriptionDAO getInstance() {
        if (subscriptionDAO == null) {
            return new SubscriptionDAO();
        }
        return subscriptionDAO;
    }

    private final String FIND_SUBSCRIPTION_BY_ID = "SELECT subscription.id, subscription.user_id, " +
            "subscription.periodical_id, " +
            "subscription.payment_id, subscription.expiration_date " +
            "FROM subscription " +
            "WHERE subscription.id = ?";

    private final String FIND_ALL_SUBSCRIPTIONS = "SELECT subscription.id, subscription.user_id, " +
            "subscription.periodical_id, subscription.payment_id, subscription.expiration_date " +
            "From subscription ; ";

    private final String INSERT_SUBSCRIPTION = "INSERT INTO subscription (user_id, periodical_id, payment_id, expiration_date) " +
            "VALUES (?,?,?,?)";

    private final String UPDATE_SUBSCRIPTION = "UPDATE subscription SET subscription.user_id = ?, subscription.periodical_id = ?," +
            "subscription.payment_id = ?, subscription.expiration_date = ? " +
            "WHERE subscription.id = ?";


    //!!!
    @Override
    public Subscription findSubscriptionById(int id) {
        Subscription subscription = null;
        subscription = findById(FIND_SUBSCRIPTION_BY_ID, id,
                set -> set != null ? new Subscription(
                        set.getInt(id),
                        daoFactory.getUserDAO().findUserById(set.getInt("user_id")),
                        daoFactory.getPeriodicalDAO().findPeriodicalById(set.getInt("periodical_id")),
                        daoFactory.getPayment().findPaymentById(set.getInt("payment_id")),
                        set.getTimestamp("expiration_date")) : null);
        return subscription;
    }

    @Override
    public ArrayList<Subscription> getAllSubscription() {
        ArrayList<Subscription> subscriptions = null;
        try (Connection connection = daoFactory.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL_SUBSCRIPTIONS)) {
                while (resultSet.next()) {
                    Subscription subscription = new Subscription(resultSet.getInt("id"),
                            daoFactory.getUserDAO().findUserById(resultSet.getInt("user_id")),
                            daoFactory.getPeriodicalDAO().findPeriodicalById(resultSet.getInt("periodical_id")),
                            daoFactory.getPayment().findPaymentById(resultSet.getInt("payment_id")),
                            resultSet.getTimestamp("expiration_date"));
                    subscriptions.add(subscription);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriptions;
    }

    @Override
    public boolean insertSubscription(Subscription subscription) {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SUBSCRIPTION, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, subscription.getUser().getId());
            statement.setInt(2, subscription.getPeriodical().getId());
            statement.setInt(3, subscription.getPayment().getId());
            statement.setTimestamp(4, subscription.getExpiration_date());

            if (statement.executeUpdate() != 0) {
                subscription.setId(getGeneratedKey(statement));
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateSubscription(Subscription subscription) {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SUBSCRIPTION)) {
            statement.setInt(1, subscription.getUser().getId());
            statement.setInt(2, subscription.getPeriodical().getId());
            statement.setInt(3, subscription.getPayment().getId());
            statement.setTimestamp(4, subscription.getExpiration_date());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteSubscription(Subscription subscription) {
        String query = "DELETE FROM subscription WHERE subscription.id = " + subscription.getId();
        if (execute(query) != 0) {
            return true;
        }
        return false;
    }

}
