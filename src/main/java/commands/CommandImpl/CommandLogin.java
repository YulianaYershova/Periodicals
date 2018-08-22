package commands.CommandImpl;


import commands.ICommand;
import logging.LoggerLoader;
import manager.Config;
import manager.Message;
import persistence.entities.User;
import service.PeriodicalService;
import service.UserService;

import javax.servlet.http.Cookie;
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
    private PeriodicalService periodicalService = new PeriodicalService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        User user;
        if ((user = userService.login(login, password)) != null) {
            request.getSession().setAttribute("user", login);
            request.getSession().setAttribute("role", user.getUserRole().getRole());
            request.getSession().setAttribute("access", "true");
            page = Config.getInstance().getProperty(Config.HOME);
            /*request.getSession().setAttribute("periodicals", periodicalService.getPeriodicals());*/
            logger.info("Login new user " + user.getLogin());
            /*if(request.getParameter("remember")!=null){
                response.addCookie(new Cookie("userLogin",user.getLogin()));
            }*/
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.LOGIN_ERROR));
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }


}
