package trip.wenjig.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import trip.wenjig.common.local.NewThread;
import trip.wenjig.entity.TempUser;
import trip.wenjig.entity.User;
import trip.wenjig.service.UserService;
import trip.wenjig.util.*;

@Controller
public class RegisterController extends BaseController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/a/registering")
    public String registering(@RequestParam(name = "username", required = false, defaultValue = "") String username,
                              @RequestParam(name = "password", required = false, defaultValue = "") String password,
                              @RequestParam(name = "re_password", required = false, defaultValue = "") String rePassword,
                              @RequestParam(name = "email", required = false, defaultValue = "") String email) {

        if (IsInvalidCheck.allCheck(username.trim()) || IsInvalidCheck.allCheck(password.trim()) ||
                IsInvalidCheck.allCheck(rePassword.trim()) || IsInvalidCheck.allCheck(email.trim())) {
            return "redirect:/a/register";
        } else if (RegisterCheck.isNormativeString(username) && RegisterCheck.isNormativeStringNumber(username) &&
                ((password.equals(rePassword)) && RegisterCheck.isNormativeStringNumber(password)) &&
                RegisterCheck.isNormativeEmail(email)) {

                TempUser tempUser = userService.findByTempUserName(username);
                User user = userService.findByUserName(username);
                //用户名是否重复
                if (tempUser ==null && user == null) {
                    //添加session并准备发送邮件
                    getSession().setMaxInactiveInterval(300);
                    getSession().setAttribute("sendEmail",email);
                    String verificationCode = RegisterCode.generaterCode();
                    TempUser tempUserDomain = addTempUser(username,password,email,verificationCode);
                    //之后启动一个本地线程，5分钟之后删除temp表中添加的这条数据
                    NewThread.DELETE_TEMP_USER.getLocalDeleteTempUserThread().addThread(tempUserDomain);
                    try {
                        //发送邮件，为了加快响应速度，采用本地线程发送
                        //SendMailCode.sendMail(email,username,verificationCode);
                        NewThread.SEND_MAIL.getLocalSendMailThread().sendMail(email,username,verificationCode);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return "forward:/a/registerSendMail";
                }else {
                    setCookie("warningMsg","用户名已经被注册！");
                    return "redirect:/a/register";
                }
        } else {
            setCookie("warningMsg","输入不能含有空格，且密码最少4位字符。");
            return "redirect:/a/register";
        }
    }

    @RequestMapping("/a/registerSendMail")
    public String registerSendMail() {
        return "/html/passport/registerSendMail.html";
    }

    @RequestMapping("/a/register.ing")
    public String registerIsOk(@RequestParam("verificationCode") String verificationCode,
                               @RequestParam("userName") String userName) {
        try {
            if (addUser(verificationCode, userName)) {
                setCookie("warningMsg","注册成功！已为你跳转至登录页面。");
                return "redirect:/a/login";
            } else {
                setCookie("warningMsg","由于网络原因，注册失败！请重试。");
                return "redirect:/a/register";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setCookie("warningMsg","出现了预期之外的错误，请重试。");
        return "redirect:/a/register";
    }

    private boolean addUser(String verificationCode, String userName) {
        try {
            TempUser TempUser = userService.findByTempUserNameAndTempCode(userName,verificationCode);
            if (TempUser != null) {
                User user = new User();
                user.setUserName(TempUser.getUserName());
                user.setPassword(TempUser.getPassword());
                user.setEmail(TempUser.getMail());
                userService.addUser(user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private TempUser addTempUser(String userName, String password, String mail, String verificationCode) {
        TempUser tempUser = new TempUser();
        try {
            tempUser.setUserName(userName);
            tempUser.setPassword(password);
            tempUser.setMail(mail);
            tempUser.setVerificationCode(verificationCode);
            tempUser.setLoseEfficacyTime(SystemDateFormat.getSystemPreciseDate5m());
            userService.addTempUser(tempUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempUser;
    }

}
