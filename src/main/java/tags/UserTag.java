package tags;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Julia on 18.08.2018
 */
public class UserTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        String userName = (String) pageContext.getSession().getAttribute("user");
        if (userName != null) {
            String login = "Hello, " + userName + "!";
            JspWriter out = pageContext.getOut();
            try {
                out.write(login);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return SKIP_BODY;
    }
}
