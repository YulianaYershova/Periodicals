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
    public String execute(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        return Config.getInstance().getProperty(Config.LOGIN);
    }
}
