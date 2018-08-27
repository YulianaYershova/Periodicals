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
import java.util.ArrayList;

/**
 * Created by Julia on 22.08.2018
 */
public class CommandUserPeriodicals implements ICommand {
    private SubscriptionService subscriptionService = new SubscriptionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return Config.getInstance().getProperty(Config.LOGIN);
        }
        ArrayList<Periodical> periodicals = subscriptionService.getUserPeriodicals(user);
        request.getSession().setAttribute("userPeriodicals", periodicals);
        return Config.getInstance().getProperty(Config.USER_PERIODICALS);
    }
}
