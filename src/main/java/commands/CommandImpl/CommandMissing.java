package commands.CommandImpl;

import commands.ICommand;
import manager.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Julia on 15.08.2018
 */
public class CommandMissing implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* if (request.getRequestURI().equals("/login") || request.getRequestURI().equals("/error")) {
            if (request.getSession().getAttribute("access") != null
                    && request.getSession().getAttribute("access").equals("true")) {
                return Config.getInstance().getProperty(Config.HOME);
            }
        }*/
        return "/WEB-INF/pages" + request.getRequestURI() + ".jsp";
    }
}
