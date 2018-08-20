package servlet;


import commands.CommandImpl.*;
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
        commands.put("home", new CommandHomePage());
        commands.put("register", new CommandRegistration());
        commands.put("login", new CommandLogin());
        commands.put("missing", new CommandMissing());
        commands.put("locale", new CommandLocale());
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
            //command = new CommandMissing();
            return null;
        }
        return command;
    }
}
