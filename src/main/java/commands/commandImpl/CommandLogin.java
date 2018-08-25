package commands.commandImpl;


import commands.ICommand;
import logging.LoggerLoader;
import manager.Config;
import manager.Message;
import persistence.entities.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Created by Julia on 15.08.2018
 */
public class CommandLogin implements ICommand {
    Logger logger = LoggerLoader.getLogger(CommandLogin.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        User user = userService.login(login, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            logger.info("Login new user " + user.getLogin());
            return Config.getInstance().getProperty(Config.HOME);
        }
        request.setAttribute("error", Message.getInstance().getProperty(Message.LOGIN_ERROR));
        return Config.getInstance().getProperty(Config.LOGIN);

    }


}
