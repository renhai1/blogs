package functionClass;
/**
 * @className:sendEmail
 * @description:发送邮箱验证码工具类
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class sendEmail {

    public  String myEmailAccount = "renhai0923@dabiantai.xyz";
    public  String myEmailPassword = "DREAMmo010923**";
    public  String myEmailSMTPHost = "smtp.exmail.qq.com";
    public String send(String receiveMailAccount) throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        int code=0;
        for(int i=0;i<6;i++)
        {
            code=code*10+(int)(Math.random()*10);
        }
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount,code);
        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        return String.valueOf(code);
    }

    /**
     *
     *
     * @param session
     * @param sendMail
     * @param receiveMail
     * @return
     * @throws Exception
     */
    public MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,int code) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "人海", "GBK"));
        message.setRecipient(MimeMessage.RecipientType.TO,
                new InternetAddress(receiveMail, "用户", "GBK"));
        message.setSubject("账号激活验证码", "GBK");
        message.setContent("您的验证码是"+code, "text/html;charset=GBK");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
}


