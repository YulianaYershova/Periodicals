package manager;

import java.util.ResourceBundle;

/**
 * Created by Julia on 15.08.2018
 */
public class Message {
    private static Message message;
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "messages";
    public static final String SERVLET_EXECPTION = "SERVLET_EXCEPTION";
    public static final String IO_EXCEPTION = "IO_EXCEPTION";
    public static final String LOGIN_ERROR = "LOGIN_ERROR";

    public static Message getInstance() {
        if (message == null) {
            message = new Message();
            message.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return message;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
