package trip.wenjig.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

    @RequestMapping("/index")
    public String index() { return "html/index.html"; }

    @RequestMapping("/a/login")
    public String login() {return "html/passport/login.html"; }

    @RequestMapping("/a/register")
    public String register() {
        return "html/passport/register.html";
    }

}
