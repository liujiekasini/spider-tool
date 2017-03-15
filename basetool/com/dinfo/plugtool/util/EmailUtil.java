package com.dinfo.plugtool.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtil
{
	
	static class MyAuthenricator extends Authenticator{  
        String user=null;  
        String pass="";  
        public MyAuthenricator(String user,String pass){  
            this.user=user;  
            this.pass=pass;  
        }  
        protected PasswordAuthentication getPasswordAuthentication() {  
            return new PasswordAuthentication(user,pass);  
        }  
          
    }

	
	
	/**
	 * 
	 * @param sendto 收件人
	 * @param title 邮件标题
	 * @param content	邮件内容
	 * @param img_path	图片位置
	 * @throws Exception
	 */
/*	public static void sendPhotoMail(String sendto,String title,String content,String img_path) throws Exception{
		DBHelper help = new DBHelper();
		
		System.out.println("Sending mail...");
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", help.protocol);
        props.setProperty("mail.smtp.auth", "true"); 
        props.setProperty("mail.host", help.host);
        MyAuthenricator myauth = new MyAuthenricator(help.mailname,help.mailpassword);

        Session mailSession = Session.getDefaultInstance(props, myauth);
        mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject(title);
        message.setFrom(new InternetAddress(help.sendfrom));
        message.addRecipient(Message.RecipientType.TO,
             new InternetAddress(sendto));

        //
        // This HTML mail have to 2 part, the BODY and the embedded image
        //
        MimeMultipart multipart = new MimeMultipart("related");

        // first part  (the html)
        BodyPart messageBodyPart = new MimeBodyPart();
        String htmlText = "<H1>"+content+"</H1><img src=\"cid:image\">";
        messageBodyPart.setContent(htmlText, "text/html");

        // add it
        multipart.addBodyPart(messageBodyPart);

        // second part (the image)
        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource
          (img_path);
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID","<image>");

        // add it
        multipart.addBodyPart(messageBodyPart);

        // put everything together
        message.setContent(multipart);

        transport.connect();
        transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
        transport.close();
        }*/

/*	public static int sendCustFileMail(String subject, String content, String filepath, String filename, List<String> mailList)
	{
		try
		{
			if (mailList == null || mailList.size() <= 0)
				return -1;
			EmailAttachment emailattachment = new EmailAttachment();
			emailattachment.setPath(filepath);
			// emailattachment.setURL(new
			// URL("http://www.blogjava.net/bulktree/picture/bulktree.jpg"));
			emailattachment.setDisposition(EmailAttachment.ATTACHMENT);
			// emailattachment.setDescription("This is Smile picture");
			emailattachment.setName(MimeUtility.encodeText(subject + ".doc"));
			// 创建一个email
			MultiPartEmail multipartemail = new MultiPartEmail();
			multipartemail.setCharset("utf-8");
			multipartemail.setHostName(ServerInfo.getChartCaption("smtp"));
			for (String mailadd : mailList)
			{
				multipartemail.addTo(mailadd);
			}
			multipartemail.setFrom(ServerInfo.getChartCaption("email"));
			multipartemail.setAuthentication(ServerInfo.getChartCaption("email"), ServerInfo.getChartCaption("emailpass"));
			multipartemail.setSubject(subject);
			multipartemail.setMsg(content);
			// 添加附件
			multipartemail.attach(emailattachment);
			// 发送邮件
			multipartemail.send();
			System.out.println("邮件发送完成!");
			return 1;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}*/


	public static void sendCustFileMail(String subject, String content, String filepath, String filename)
	{
		try
		{
			EmailAttachment emailattachment = new EmailAttachment();
			emailattachment.setPath(filepath);
			// emailattachment.setURL(new
			// URL("http://www.blogjava.net/bulktree/picture/bulktree.jpg"));
			emailattachment.setDisposition(EmailAttachment.ATTACHMENT);
			// emailattachment.setDescription("This is Smile picture");
			emailattachment.setName(MimeUtility.encodeText(filename));
			// 创建一个email
			MultiPartEmail multipartemail = new MultiPartEmail();
			multipartemail.setCharset("utf-8");
			multipartemail.setHostName("smtp.163.com");
			multipartemail.addTo("912769841@qq.com");
			multipartemail.setFrom("fengyangchunxx@163.com");
			multipartemail.setAuthentication("fengyangchunxx@163.com", "31415926fyc");
			multipartemail.setSubject(subject);
			multipartemail.setMsg(content);
			// 添加附件
			multipartemail.attach(emailattachment);
			// 发送邮件
			multipartemail.send();
			System.out.println("邮件发送成功!");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/*public static void sendHtmlMail(String subject, String content, List<String> mailList)
	{
		try
		{
			if(mailList==null || mailList.size()<=0)
			return ;
			HtmlEmail email = new HtmlEmail();
			email.setHostName(ServerInfo.getChartCaption("smtp"));
			for (String mailadd : mailList)
			{
				email.addTo(mailadd);
			}
			email.setFrom(ServerInfo.getChartCaption("email"));
			email.setCharset("utf-8");
			email.setSubject(subject);
			email.setMsg(content);
			email.setAuthentication(ServerInfo.getChartCaption("email"), ServerInfo.getChartCaption("emailpass"));
			email.send();
			System.out.println("邮件发送成功!");
		} catch (Exception e)
		{
			// TODO: handle exception
		}
	}*/

	
	public static void main(String[] args) {
		sendHtmlMail("<html><body><h1 align='center'>hello  大家好</h1></body></html>");
	}
	
	public static void sendTextMail(String text)
	{
		try
		{
			SimpleEmail email = new SimpleEmail();
			email.setHostName("smtp.163.com");
			email.addTo("fengyangchun@dinfo.cn", "冯阳春");
			email.addTo("fengxingze@dinfo.cn", "冯星泽");
			email.addTo("xulonglong@dinfo.cn", "徐龙龙");
			email.addTo("wanglei@dinfo.cn", "王磊");
			email.addTo("sunxiaorong@dinfo.cn", "孙晓荣");
			email.addTo("liuwenbo@dinfo.cn", "孙晓荣");
			email.setFrom("fengyangchunxx@163.com", "哈尔滨银行数据监测报告");
			email.setSubject("哈尔滨银行项目检测邮件");
			email.setCharset("utf-8");
			email.setMsg(text);
			email.setAuthentication("fengyangchunxx@163.com", "31415926fyc");
			email.send();
			System.out.println("邮件发送成功!");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void sendHtmlMail(String text){
		try {
			//附件，可以定义多个附件对象
//			EmailAttachment attachment = new EmailAttachment();
			//设置附件信息
			/*attachment.setPath("e:\\1.pdf");
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Picture of John");
			//添加附件
			email.attach(attachment);*/
			HtmlEmail email = new HtmlEmail ();
			email.setCharset("utf-8");
			//smtp host 
			email.setHostName("smtp.163.com");
			//登陆邮件服务器的用户名和密码
			email.setAuthentication("fengyangchunxx@163.com", "31415926fyc");
			//发送人
			email.setFrom("fengyangchunxx@163.com", "哈尔滨银行数据监测报告");
			//邮件标题
			email.setSubject("哈尔滨银行项目检测邮件");
			//接收人
			email.addTo("chenjianming@dinfo.cn", "陈建民");
			email.addTo("fengxingze@dinfo.cn", "冯星泽");
			email.addTo("liuwenbo@dinfo.cn", "刘文博");
			email.addTo("sunxiaorong@dinfo.cn", "孙晓荣");
			email.addTo("fengyangchun@dinfo.cn", "冯阳春");
			email.addTo("yangjunfei@dinfo.cn", "孙晓荣");
			email.addTo("liujie@dinfo.cn", "刘杰");
			/*email.addTo("xulonglong@dinfo.cn", "徐龙龙");*/
			email.addTo("zhaoxinbo@dinfo.cn", "赵新博");
			email.addTo("wanglei@dinfo.cn", "王磊");
		
			
			//邮件内容
			email.setHtmlMsg(text);
			//发送
			email.send();
			System.out.println("发送成功!");
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}
