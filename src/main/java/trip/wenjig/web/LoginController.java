package trip.wenjig.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import trip.wenjig.common.local.NewThread;
import trip.wenjig.entity.User;
import trip.wenjig.service.UserService;
import trip.wenjig.util.IsInvalidCheck;
import trip.wenjig.util.RegisterCode;
import trip.wenjig.util.SystemDateFormat;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/a/rePassword")
    public String rePassword() {
        return "html/passport/rePassword.html";
    }

    @RequestMapping("/a/rePassword.set")
    public String setNewPassword() {
        return "html/passport/setNewPassword.html";
    }

    @RequestMapping("/a/rePassword.ok.hint")
    public String rePasswordOkHint() {
        return "html/hint/rePasswordOkHint.html";
    }

    @RequestMapping("/a/login.ing")
    public String logging(@RequestParam("username") String userName, @RequestParam("password") String password) {
        User loginUser = null;
        if (!(IsInvalidCheck.allCheck(userName.trim()) && IsInvalidCheck.allCheck(password.trim()))) {
            loginUser = userService.findByLogin(userName, password);
        } else {
            setCookie("warningMsg", "输入格式不正确！");
            return "redirect:/a/login";
        }
        if (loginUser != null) {
            userService.updateUserLoginNumber(loginUser.getUserId());
            userService.updateUserLastLogin(SystemDateFormat.getSystemPreciseDate(), loginUser.getUserId());
            getSession().setMaxInactiveInterval(-1);
            getSession().setAttribute("isLogin", true);
            getSession().setAttribute("userId", loginUser.getUserId());
            flushSession();
            return "redirect:/index";
        } else {
            setCookie("warningMsg", "用户名或密码输入错误！");
            return "redirect:/a/login";
        }
    }

    @RequestMapping("/a/rePassword.ing")
    public String rePasswordIng(@RequestParam("username") String userName, @RequestParam("email") String email) {
        User rePwdUser = null;
        if (!(IsInvalidCheck.allCheck(userName.trim()) && IsInvalidCheck.allCheck(email.trim()))) {
            rePwdUser = userService.findByUserNameAndUserEmail(userName, email);
        } else {
            setCookie("warningMsg", "输入格式不正确！");
            return "redirect:/a/rePassword";
        }
        if (rePwdUser != null) {
            HttpSession session = getSession();
            String verificationCode = RegisterCode.generaterCode();
            session.setMaxInactiveInterval(60 * 10);
            session.setAttribute("vCode", verificationCode);
            session.setAttribute("updateUserId", rePwdUser.getUserId());
            NewThread.SEND_MAIL.getLocalSendMailThread().sendMail(email, verificationCode);
            // 提示
            setCookie("warningMsg", "已发送验证码邮件，有效期10分钟。");
            return "redirect:/a/rePassword.set";
        } else {
            setCookie("warningMsg", "用户名与邮箱不匹配！");
            return "redirect:/a/rePassword";
        }
    }

    @RequestMapping("/a/rePassword.succeed")
    public String rePasswordSucceed(@RequestParam("password") String password, @RequestParam("re_password") String rePassword, @RequestParam("v") String vCode) {
        if (!(IsInvalidCheck.allCheck(password) && IsInvalidCheck.allCheck(rePassword)) && password.equals(rePassword) && !(IsInvalidCheck.allCheck(vCode.trim()))) {
            if (getSession().getAttribute("vCode") != null) {
                if (getSession().getAttribute("vCode").equals(vCode)) {
                    if (getSession().getAttribute("updateUserId") != null) {
                        long id = (long) getSession().getAttribute("updateUserId");
                        if (userService.updateUserPassword(password, id)) {
                            // 销毁session
                            getSession().invalidate();
                            return "redirect:/a/rePassword.ok.hint";
                        } else {
                            getSession().removeAttribute("vCode");
                            getSession().removeAttribute("updateUser");
                            setCookie("warningMsg", "由于网络原因修改失败！请重试。");
                            return "redirect:/a/rePassword";
                        }
                    } else {
                        setCookie("warningMsg", "验证码已过期！请重新取得验证码。");
                        return "redirect:/a/rePassword";
                    }
                } else {
                    setCookie("warningMsg", "验证码错误！");
                    return "redirect:/a/rePassword.set";
                }
            } else {
                setCookie("warningMsg", "验证码已过期！请重新取得验证码。");
                return "redirect:/a/rePassword";
            }
        } else {
            setCookie("warningMsg", "输入不符合规范！请重新输入。");
            return "redirect:/a/rePassword.set";
        }
    }

    @RequestMapping("/a/logout")
    public String logOut() {
        getSession().invalidate();
        return "redirect:/index";
    }

    /**
     * @param []
     * @Description: 刷新session中的用户信息
     * @Return void
     */
    private void flushSession() {
        User loginUser = userService.findByUserId((long) getSession().getAttribute("userId"));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int exp = loginUser.getExp();
        int expPercentage = exp / 1000 * 100 > 100 ? 100 : exp / 1000 * 100;
        int level = exp / 10000 < 1 ? 1 : exp / 10000 > 5 ? 5 : exp / 10000;
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String sex;
        switch (loginUser.getSex()) {
            case 0:
                sex = "女";
                break;
            case 1:
                sex = "男";
                break;
            case 2:
                sex = "保密";
                break;
            default:
                sex = "保密";
                break;
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String age;
        long day = 0L;
        try {
            day = SystemDateFormat.daySub(loginUser.getJoinDate(), SystemDateFormat.getSystemDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        age = day < 30 ? day + "天" : day % 30 >= 0 ? day / 30 + "个月" + day % 30 + "天" : day / 365 >= 1 && day % 365 < 30 ? day / 365 + "年" + day % 365 + "天" : day / 365 + "年" + day % 365 / 30 + "个月";
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        getSession().setAttribute("username", loginUser.getUserName());
        getSession().setAttribute("userLv", level);
        getSession().setAttribute("userExp", expPercentage);
        getSession().setAttribute("userSign", loginUser.getSignText());
        getSession().setAttribute("userHeadImg", loginUser.getFaceImagePath());
        getSession().setAttribute("userSex", sex);
        getSession().setAttribute("userAge", age);
        getSession().setAttribute("postTopic", loginUser.getTopicNumber());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                >>> HOME <<<                                                    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping("/a/home")
    public String home() {
        flushSession();
        return "html/passport/home.html";
    }

    @RequestMapping("/a/home/uploadHeadImg")
    public String uploadHeadImg(@RequestParam("headImgFile") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                setCookie("warningMsg", "图片不能为空！");
                return "redirect:/a/home";
            }
            if (file.getSize() > 10000000) {
                setCookie("warningMsg", "上传图片最大限制为10MB！");
                return "redirect:/a/home";
            }
            if (IsInvalidCheck.allCheck(file.getOriginalFilename())) {
                setCookie("warningMsg", "图片名称不能含有空格！");
                return "redirect:/a/home";
            }
            String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if (!(suffixName.equals(".jpg") || suffixName.equals(".png"))) {
                setCookie("warningMsg", "图片格式不是支持的格式。");
                return "redirect:/a/home";
            }
            // 项目根目录
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            String saveFileName = getSession().getAttribute("userId") + suffixName;
            String savePath = "static/image/headImg/" + saveFileName;
            File dest = new File(path.getAbsolutePath(), savePath);
            if (!dest.getParentFile().exists()) {
                setCookie("warningMsg", "需要维护，暂时无法上传！请联系管理人员，谢谢。");
                return "redirect:/a/home";
            }
            file.transferTo(dest);
            if (userService.updateUserHeadImage(saveFileName, (long) getSession().getAttribute("userId"))) {
                setCookie("warningMsg", "上传成功！");
                return "redirect:/a/home";
            } else {
                setCookie("warningMsg", "上传失败！");
                return "redirect:/a/home";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setCookie("warningMsg", "出现了预期之外的错误，请重试。");
        return "redirect:/a/home";
    }

    @RequestMapping("/a/home/sign")
    public String updateUserSign(@RequestParam("sign_text") String signText) {
        if (signText.length() > 65) {
            setCookie("warningMsg", "签名长度不能超过65个字符！");
            return "redirect:/a/home";
        }
        if (userService.updateUserSign(signText, (long) getSession().getAttribute("userId"))) {
            setCookie("warningMsg", "保存成功！");
            return "redirect:/a/home";
        } else {
            setCookie("warningMsg", "保存失败！请重试。");
            return "redirect:/a/home";
        }
    }

    @RequestMapping("/a/home/sex")
    public String updateUserSex(@RequestParam("sex") String sex) {
        if (IsInvalidCheck.allCheck(sex)) {
            setCookie("warningMsg", "保存失败！");
            return "redirect:/a/home";
        }
        switch (sex) {
            case "男":
                userService.updateUserSex(1, (long) getSession().getAttribute("userId"));
                setCookie("warningMsg", "保存成功！");
                break;
            case "女":
                userService.updateUserSex(0, (long) getSession().getAttribute("userId"));
                setCookie("warningMsg", "保存成功！");
                break;
            case "保密":
                userService.updateUserSex(2, (long) getSession().getAttribute("userId"));
                setCookie("warningMsg", "保存成功！");
                break;
            default:
                setCookie("warningMsg", "保存失败！");
                break;
        }
        return "redirect:/a/home";
    }

}
