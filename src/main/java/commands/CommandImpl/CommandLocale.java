package commands.CommandImpl;

import commands.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Julia on 19.08.2018
 */
public class CommandLocale implements ICommand {
    private final String EN = "en";
    private final String RU = "ru";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("locale");
        if (lang.equals(EN)) {
            req.getSession().setAttribute("locale", EN);
        }
        if (lang.equals(RU)) {
            req.getSession().setAttribute("locale", RU);
        }
        return req.getRequestURI();
    }
}
