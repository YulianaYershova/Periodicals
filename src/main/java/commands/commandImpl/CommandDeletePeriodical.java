package commands.commandImpl;

import commands.ICommand;
import manager.Config;
import manager.Info;
import persistence.entities.Periodical;
import service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Julia on 31.08.2018
 */
public class CommandDeletePeriodical implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Periodical periodical = (Periodical) request.getSession().getAttribute("periodical");
        if (!PeriodicalService.deletePeriodical(periodical)) {
            request.setAttribute("info", Info.getInstance().getProperty(Info.ERROR));
            return Config.getInstance().getProperty(Config.PERIODICAL_INFO);
        }
        return Config.getInstance().getProperty(Config.HOME);
    }
}
