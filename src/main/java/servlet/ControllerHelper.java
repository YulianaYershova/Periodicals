package servlet;


import commands.CommandImpl.CommandHomePageImpl;
import commands.CommandImpl.CommandLogin;
import commands.CommandImpl.CommandMissing;
import commands.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by Julia on 15.08.2018
 */
public class ControllerHelper {
    private static ControllerHelper controllerHelper;
    private HashMap<String, ICommand> commands = new HashMap<>();

    private ControllerHelper() {
        commands.put("home", new CommandHomePageImpl());
        commands.put("login", new CommandLogin());
        commands.put("missing",new CommandMissing());
    }

    public static ControllerHelper getInstance() {
        if (controllerHelper == null) {
            controllerHelper = new ControllerHelper();
        }
        return controllerHelper;
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = new CommandMissing();
        }
        return command;
    }
}
