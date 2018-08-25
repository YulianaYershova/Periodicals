package commands.commandImpl;

import commands.ICommand;
import manager.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Julia on 19.08.2018
 */
public class CommandLocale implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("locale" , request.getParameter("locale"));
        return Config.getInstance().getProperty(Config.HOME);
    }
}