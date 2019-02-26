package trip.wenjig.util;

import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Component
public class SendMailCode {

    private static final String SEND_EMAIL_ACCOUNT = "q1581900362@163.com";
    private static final String SEND_EMAIL_PASSWORD = "smtp1138736299";
    private static final String SEND_EMAIL_SMTP_HOST = "smtp.163.com";
    private static final String LOCALPATH = "http://localhost:8080";

    public static void sendMail(String email, String userName, String verificationCode) throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", SEND_EMAIL_SMTP_HOST);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        Message message = createMimeMessage(session, SEND_EMAIL_ACCOUNT, email, userName, verificationCode);
        Transport transport = session.getTransport();
        transport.connect(SEND_EMAIL_ACCOUNT, SEND_EMAIL_PASSWORD);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public static void sendMail(String email, String verificationCode) throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", SEND_EMAIL_SMTP_HOST);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        Message message = createMimeMessage(session, SEND_EMAIL_ACCOUNT, email, verificationCode);
        Transport transport = session.getTransport();
        transport.connect(SEND_EMAIL_ACCOUNT, SEND_EMAIL_PASSWORD);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    private static Message createMimeMessage(Session session, String sendMail, String receiveMail, String userName, String verificationCode) throws Exception {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail));
        message.setSubject("UniverseForum_SYS_ADMIN : 您的注册邮件已到达。");
        message.setText("请点击此链接完成注册最后一步： " + LOCALPATH + "/a/register.ing?userName=" + userName + "&verificationCode="
                + verificationCode);
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

    private static Message createMimeMessage(Session session, String sendMail, String receiveMail, String verificationCode) throws Exception {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail));
        message.setSubject("UniverseForum_SYS_ADMIN : 验证码已发送。");
        message.setText("你的验证码为: " + verificationCode);
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
}
