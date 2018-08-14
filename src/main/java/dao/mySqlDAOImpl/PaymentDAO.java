package dao.mySqlDAOImpl;

import dao.IPayment;
import entities.Payment;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Julia on 14.08.2018
 */
public class PaymentDAO extends AbstractDAO implements IPayment {

    private static PaymentDAO paymentDAO = null;

    private PaymentDAO() {
    }

    public static PaymentDAO getInstance() {
        if (paymentDAO == null) {
            return new PaymentDAO();
        }
        return paymentDAO;
    }

    private final String FIND_PAYMENT_BY_ID = "SELECT payment.id, payment.date, payment.price " +
            "FROM payment WHERE payment.id = ?";

    private final String FIND_ALL_PAYMENTS = "SELECT * FROM payment";

    private final String INSERT_PAYMENT = "INSERT INTO payment (date, price) " +
            "VALUES (?,?)";

    private final String UPDATE_PERIODICAL = "UPDATE payment SET payment.date = ?, payment.price = ?" +
            "WHERE payment.id = ?";


    @Override
    public Payment findPaymentById(int id) {
        Payment payment = null;
        payment = findById(FIND_PAYMENT_BY_ID, id,
                set -> set != null ? new Payment(
                        set.getInt(id),
                        set.getTimestamp("date"),
                        set.getBigDecimal("price")) : null);
        return payment;
    }

    @Override
    public ArrayList<Payment> getAllPayments() {
        ArrayList<Payment> payments = null;
        try (Connection connection = daoFactory.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL_PAYMENTS)) {
                while (resultSet.next()) {
                    Payment payment = new Payment(resultSet.getInt("id"),
                            resultSet.getTimestamp("date"),
                            resultSet.getBigDecimal("price"));
                    payments.add(payment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public boolean insertPayment(Payment payment) {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PAYMENT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, payment.getDate());
            statement.setBigDecimal(2, payment.getPrice());
            if (statement.executeUpdate() != 0) {
                payment.setId(getGeneratedKey(statement));
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePayment(Payment payment) {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PERIODICAL)) {
            statement.setTimestamp(1, payment.getDate());
            statement.setBigDecimal(2, payment.getPrice());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePayment(Payment payment) {
        String query = "DELETE FROM payment WHERE payment.id = " + payment.getId();
        if (execute(query) != 0) {
            return true;
        }
        return false;
    }
}
