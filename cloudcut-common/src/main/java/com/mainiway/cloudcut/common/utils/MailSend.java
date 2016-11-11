package com.mainiway.cloudcut.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * ***************************************************************************
 *模块名 : MailSend
 *文件名 : MailSend.java
 *创建时间 : 2016年5月12日
 *实现功能 :  发送邮件带附件
 *作者 : Administrator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月12日 v0.0.1 Administrator 创建
 ****************************************************************************
 */
public class MailSend {

	/**
	 * 发送邮件
	 * @param to 收件人列表，以","分割
	 * @param subject 标题
	 * @param body  内容
	 * @param filepath 附件列表,无附件传递null
	 * @return
	 * @throws MessagingException
	 * @throws AddressException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean sendMail(String to, String subject, String body, List<String> filepath,MailSenderInfoEntity mailInfo) throws Exception {
		// 参数修饰
		if (body == null) {
			body = "";
		}
		if (subject == null) {
			subject = "无主题";
		}
		// 创建Properties对象
		Properties props = System.getProperties();
		// 创建信件服务器
		props.put("mail.smtp.host", mailInfo.getMailServerHost());
		props.put("mail.smtp.auth", mailInfo.isValidate()); // 通过验证
		// 得到默认的对话对象
		Session session = Session.getDefaultInstance(props, null);
		// 创建一个消息，并初始化该消息的各项元素
		MimeMessage msg = new MimeMessage(session);
		String nick = MimeUtility.encodeText(mailInfo.getSubject());
		msg.setFrom(new InternetAddress(nick + "<" + mailInfo.getFromAddress() + ">"));
		// 创建收件人列表
		if (to != null && to.trim().length() > 0) {
			String[] arr = to.split(",");
			int receiverCount = arr.length;
			if (receiverCount > 0) {
				InternetAddress[] address = new InternetAddress[receiverCount];
				for (int i = 0; i < receiverCount; i++) {
					address[i] = new InternetAddress(arr[i]);
				}
				msg.addRecipients(Message.RecipientType.TO, address);
				msg.setSubject(subject);
				// 后面的BodyPart将加入到此处创建的Multipart中
				Multipart mp = new MimeMultipart();
				// 附件操作
				if (filepath != null && filepath.size() > 0) {
					for (String filename : filepath) {
						MimeBodyPart mbp = new MimeBodyPart();
						// 得到数据源
						FileDataSource fds = new FileDataSource(filename);
						// 得到附件本身并至入BodyPart
						mbp.setDataHandler(new DataHandler(fds));
						// 得到文件名同样至入BodyPart
						mbp.setFileName(fds.getName());
						mp.addBodyPart(mbp);
					}
					MimeBodyPart mbp = new MimeBodyPart();
					mbp.setText(body);
					mp.addBodyPart(mbp);
					// 移走集合中的所有元素
					filepath.clear();
					// Multipart加入到信件
					msg.setContent(mp);
				} else {
					// 设置邮件正文
					msg.setText(body);
				}
				// 设置信件头的发送日期
				msg.setSentDate(new Date());
				msg.saveChanges();
				// 发送信件
				Transport transport = session.getTransport("smtp");
				transport.connect(
						mailInfo.getMailServerHost(), mailInfo.getUserName(),
						mailInfo.getPassword());
				transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
				transport.close();
				return true;
			} else {
				System.out.println("None receiver!");
				return false;
			}
		} else {
			System.out.println("None receiver!");
			return false;
		}
	}
	//带有HTML的
	public static boolean sendMailHtml(String USER_MAIL, String subject, List<String> filepath,MailSenderInfoEntity mainInfo) {
       // 创建Properties对象
 		Properties props = System.getProperties();
 		// 创建信件服务器
 		props.put("mail.smtp.host", mainInfo.getMailServerHost());
 		props.put("mail.smtp.auth", mainInfo.isValidate()); // 通过验证
 		// 得到默认的对话对象
 		Session session = Session.getDefaultInstance(props, null);
 		MimeMessage sendMailMessage = new MimeMessage(session);
        try {
            Address from = new InternetAddress(mainInfo.getFromAddress());
            sendMailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(USER_MAIL);
            sendMailMessage.setRecipient(Message.RecipientType.TO, to);

            // 设置邮件消息的主题
            sendMailMessage.setSubject(subject);
            // 设置邮件消息发送的时间
            sendMailMessage.setSentDate(new Date());

            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(mainInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            sendMailMessage.setContent(mainPart);
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            transport.connect(
            		mainInfo.getMailServerHost(), mainInfo.getUserName(),
            		mainInfo.getPassword());
            transport.sendMessage(sendMailMessage, sendMailMessage.getRecipients(Message.RecipientType.TO));
            transport.close();
            return true;
        } catch (AddressException e) {
            System.out.println("邮件地址有误。。");
            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
