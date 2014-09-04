package mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMail {
	public ApplicationContext ctx = null;

	public SendMail() {
		// 获取上下文
		ctx = new ClassPathXmlApplicationContext("mail.xml");
	}

	public void send() {
		// 获取JavaMailSender bean
		JavaMailSender mailSender = (JavaMailSender) ctx.getBean("mailSender");
		SimpleMailMessage mimeMessage = new SimpleMailMessage(); // <SPAN
		try {
			mimeMessage.setTo("");// 接受者
			mimeMessage.setFrom("");// 发送者,这里还可以另起Email别名，不用和xml里的username一致
			mimeMessage.setSubject("spring mail test!");// 主题
			mimeMessage.setText("springMail的简单发送测试");// 邮件内容
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 public static MimeMessage copyFromCrmMailQueueItem() throws MessagingException, UnsupportedEncodingException {
	        Properties props = System.getProperties();
	        MimeMessage mimeMessage = new MimeMessage(Session.getDefaultInstance(props));
	        mimeMessage.setFrom(new InternetAddress(MimeUtility.encodeText("asdfasf") + "<" + "xuan.yang@qunar.com" + ">"));
	        mimeMessage.addRecipients(Message.RecipientType.TO, "");
	        mimeMessage.setSubject("测试", "UTF-8");
	        mimeMessage.setContent("测试", "text/html;charset=UTF-8");

	        return mimeMessage;
	    }
	
	public void send0() {
		// 获取JavaMailSender bean
		JavaMailSender sender = (JavaMailSender) ctx.getBean("mailSender");
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// 设置utf-8或GBK编码，否则邮件会有乱码
		try {
//			MimeMessageHelper messageHelper = new MimeMessageHelper(
//					mailMessage, true, "utf-8");
//
//			messageHelper.setTo(new InternetAddress(MimeUtility.encodeText("Qunar目的地") + "<" +"xuan.yang@qunar.com"+ ">"));// 接受者
//			messageHelper.setFrom("xuan.yang@qunar.com");// 发送者
//			messageHelper.setSubject("测试邮件");// 主题
//			// 邮件内容，注意加参数true，表示启用html格式
//			messageHelper
//					.setText(
//							"<html><head></head><body><h1>测试邮件</h1></body></html>",
//							true);
			sender.send(copyFromCrmMailQueueItem());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SendMail sm = new SendMail();
		sm.send0();
	}

}
