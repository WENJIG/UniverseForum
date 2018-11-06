package trip.wenjig.util;

import org.springframework.stereotype.Component;

@Component
public class RegisterCheck {

    /**
     * @param [str]
     * @Description: 字符串是否只包含中文或英文
     * @Return boolean
     */
    public static boolean isNormativeString(String str) {
        if (str.matches("^[a-zA-Z0-9\u4E00-\u9FA5]+$")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param [str]
     * @Description: 字符串长度是否规范(4<=str<=20)
     * @Return boolean
     */
    public static boolean isNormativeStringNumber(String str) {
        if (str.length() >= 4 && str.length() <= 20) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param [email]
     * @Description: 字符串是否为符合规范的邮箱格式
     * @Return boolean
     */
    public static boolean isNormativeEmail(String email) {
        if (email.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")
                && email.length() < 90) {
            return true;
        } else {
            return false;
        }
    }

}
