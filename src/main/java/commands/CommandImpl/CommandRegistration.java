package commands.CommandImpl;

import commands.ICommand;
import manager.Config;
import manager.Message;
import persistence.entities.Periodical;
import persistence.entities.User;
import persistence.entities.UserRole;
import service.PeriodicalService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Julia on 16.08.2018
 */
public class CommandRegistration implements ICommand {
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    UserService userService = new UserService();
    PeriodicalService periodicalService = new PeriodicalService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String name = request.getParameter(NAME);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD);

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", Message.getInstance().getProperty(Message.INCORRECT_PASSWORD));
            return Config.getInstance().getProperty(Config.REGISTRATION);
        }

        if (userService.getUserByLogin(login) != null) {
            request.setAttribute("error", Message.getInstance().getProperty(Message.INCORRECT_LOGIN));
            page = Config.getInstance().getProperty(Config.REGISTRATION);
            return page;
        }

        UserRole userRole = userService.getUserRole("reader");
        User user = new User(userRole, name, login, password);
        if (!userService.register(user)) {
            request.setAttribute("error", Message.getInstance().getProperty(Message.LOGIN_ERROR));
            page = Config.getInstance().getProperty(Config.REGISTRATION);
            return page;
        }
        request.getSession().setAttribute("user", login);
        request.getSession().setAttribute("role", user.getUserRole().getRole());
        request.getSession().setAttribute("access", true);
/*
        request.getSession().setAttribute("periodicals", periodicalService.getPeriodicals());
*/
        return Config.getInstance().getProperty(Config.HOME);
        /* return new CommandLogin().execute(request, response);*/
    }

}
