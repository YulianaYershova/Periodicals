package persistence.dao;

import persistence.entities.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * IAccountDAO interface provides methods for manipulation of account table
 * Created by Julia on 28.08.2018
 */
public interface IAccountDAO {
    /**
     * Account's selection by id
     *
     * @param id - account id
     * @return - account or null
     * @throws SQLException
     */
    Account findAccountById(int id) throws SQLException;

    /**
     * Selection all accounts in the database
     *
     * @return - list of accounts
     * @throws SQLException
     */
    ArrayList<Account> findAllAccounts() throws SQLException;

    /**
     * Insert new account
     *
     * @param account - account to be inserted to the database
     * @throws SQLException
     */
    void insertAccount(Account account,Connection connection) throws SQLException;

    /**
     * Update account info
     *
     * @param account    - account info to be updated in the database
     * @param connection - connection with setAutoCommit(false)
     * @throws SQLException
     */
    void updateAccount(Account account, Connection connection) throws SQLException;

    /**
     * Delete account
     *
     * @param account - account to be deleted from the database
     * @throws SQLException
     */
    void deleteAccount(Account account) throws SQLException;
}
