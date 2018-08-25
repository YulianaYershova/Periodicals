package service;

import persistence.dao.ISubscription;
import persistence.dao.IUser;
import persistence.dao.daoFactory.DAOFactory;
import persistence.entities.Periodical;
import persistence.entities.Subscription;
import persistence.entities.User;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Julia on 15.08.2018
 */
public class SubscriptionService {
    private ISubscription iSubscription = DAOFactory.getMySqlDAOFactory().getSubscriptionDAO();
    private IUser iUser = DAOFactory.getMySqlDAOFactory().getUserDAO();

    public boolean subscribe(User user, Periodical periodical) {
        Subscription subscription = new Subscription(user, periodical, null, new Timestamp(System.currentTimeMillis()));
        if (iSubscription.insertSubscription(subscription))
            return true;
        return false;
    }

    public ArrayList<Periodical> getUserPeriodicals(User user) {
        ArrayList<Subscription> subscriptions = iSubscription.findSubscriptionsByUser(user.getId());
        ArrayList<Periodical> periodicals = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            periodicals.add(subscription.getPeriodical());
        }
        return periodicals;
    }
}