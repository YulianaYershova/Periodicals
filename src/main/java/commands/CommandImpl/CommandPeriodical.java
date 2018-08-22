package commands.CommandImpl;

import commands.ICommand;
import manager.Config;
import persistence.entities.Periodical;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = (String)request.getParameter("id");
        int Pid = Integer.valueOf(id);
        Periodical periodical = periodicalService.getPeriodical(Pid);
        request.getSession().setAttribute("periodical", periodical);
        return Config.getInstance().getProperty(Config.PERIODICAL);
    }
}
