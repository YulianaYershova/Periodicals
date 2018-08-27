package commands.commandImpl;

import commands.ICommand;
import manager.Config;
import persistence.entities.Periodical;
import persistence.entities.User;
import service.SubscriptionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Julia on 22.08.2018
 */
public class CommandSubscription implements ICommand {
    SubscriptionService subscriptionService = new SubscriptionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("user") == null) {
            return Config.getInstance().getProperty(Config.LOGIN);
        } else {
            User user = (User) request.getSession().getAttribute("user");
            Periodical periodical = (Periodical) request.getSession().getAttribute("periodical");
            if (subscriptionService.subscribe(user, periodical)) {
                return Config.getInstance().getProperty(Config.HOME);
            }
        }
        return Config.getInstance().getProperty(Config.HOME);
    }
}
