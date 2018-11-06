package trip.wenjig.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by Adyina on 2018/8/13.
 */
@Component
public class RegisterCode {

    private static final String tempStr = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateRandomCode() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 41; i++) {
            int number = random.nextInt(62);
            sb.append(tempStr.charAt(number));
        }
        return sb.toString();
    }

    public static String generaterCode() {
        return generateRandomCode();
    }

}
