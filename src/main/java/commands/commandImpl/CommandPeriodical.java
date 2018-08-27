package commands.commandImpl;

import commands.ICommand;
import manager.Config;
import service.PeriodicalService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Julia on 21.08.2018
 */
public class CommandPeriodical implements ICommand {
    PeriodicalService periodicalService = new PeriodicalService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        request.getSession().setAttribute("periodical", periodicalService.getPeriodical(Integer.valueOf(id)));
        return Config.getInstance().getProperty(Config.PERIODICAL);
    }
}
