package mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSendService{
    @Autowired
    private JavaMailSender mailSender;
    @Value("#{feedbackmail['mail.from']}")
    private String fromMail;
    
    public MimeMessage getSendBackMime(String title, String content,
            String toMail) throws MessagingException,
            UnsupportedEncodingException {
    
        Properties props = System.getProperties();
        MimeMessage mimeMessage = new MimeMessage(
                Session.getDefaultInstance(props));
        mimeMessage.setFrom(new InternetAddress(MimeUtility.encodeText("日志系统")
                + "<" + this.fromMail + ">"));
        String[] strs = toMail.split(";|,|\\s");
        for (String str : strs) {
            mimeMessage.addRecipients(Message.RecipientType.TO, str);
        }
        mimeMessage.setSubject("【WARN】" + title, "UTF-8");
        String message = content + "<br/>";
        mimeMessage.setContent(message, "text/html;charset=UTF-8");
        return mimeMessage;
    }
    
    public void sendMail(String title, String content, String toMail)
            throws MailException, UnsupportedEncodingException,
            MessagingException {
    
        mailSender.send(this.getSendBackMime(title, content, toMail));
    }
    
    public static void main(String[] args) {
    
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "spring/app-mail.xml");
        MailSendService mailSendService = (MailSendService) ctx
                .getBean("mailSendService");
        try {
            mailSendService.sendMail("test", "test",
                    "yangxuan@letv.com;yangxuan@letv.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
