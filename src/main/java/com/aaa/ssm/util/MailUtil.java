package com.aaa.ssm.util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**
 * className:MailUtil
 * discription:邮件发送工具类
 * author:fhm
 * createTime:2019-01-04 17:38
 */
public class MailUtil {
    /**
     * 发送邮件的方法
     * @param to
     *            :收件人
     * @param code
     *            :激活码
     */
    public static void sendMail(String to, String code) {
        /**
         * 1.获得一个Session对象. 2.创建一个代表邮件的对象Message. 3.发送邮件Transport
         */
        // 1.获得连接对象
        Properties props = new Properties();
        //props.setProperty("smtp.qq.com", "192.168.1.102");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("2268162655@qq.com",
                        "unsquhawfvgqeada");
            }

        });
        // 2.创建邮件对象:
        Message message = new MimeMessage(session);
        // 设置发件人:
        try {
            message.setFrom(new InternetAddress("2268162655@qq.com"));
            // 设置收件人:
            message.addRecipient(RecipientType.TO, new InternetAddress(to));
            // 抄送 CC 密送BCC
            // 设置标题
            message.setSubject("来自随便贷官方邮件");
            // 设置邮件正文:

            // message.setContent("<h1>您的验证码："+code+"</a></h3><br/><h2>请保存好你的验证码，能帮你找回账号密码！</h2>",
            // "text/html;charset=UTF-8");

            message.setContent(
                    "<div style='text-align:center'> <span style='overflow:hidden;padding-left:28px;height:64px;line-height:64px;'><h1>随便贷平台更换邮箱</h1></span><br/>  <p style='font-size:14px;margin-top:20px;'>亲爱的用户，<b>"
                            + to
                            + "</b>，您好：</p> <p style='line-height:25px;font-size:14px;margin-top:28px;'>您正在通过邮箱发送验证码，验证码为<a style='color:red ;font-size:16px'><b>"
                            + code
                            + "</b></a>，请勿将此信息泄漏给他人。"

                            + "<p style='line-height:25px;font-size:12px;color:#5f5f5f;margin-top:20px;'>如有任何疑问，请联系随便贷客服。"
                            + "<br/> 客服邮箱:<a>2268162655@qq.com</a></p><div style='font-size:12px;color:#939393;height:32px;background:#f5f5f5;line-height:32px;text-indent:7px;margin-top:30px;overflow:hidden;'>此邮件为系统自动发送，请勿回复！</div> <div style='font-size:12px;overflow:hidden;border-top:1px solid #ebebeb;padding:10px 0 0 7px;margin-top:20px;line-height:20px;color:#959595;text-align:center;'>随便贷团队<br> www.suibian.com &copy; Copyright 2015-2020</div></div>",
                    "text/html;charset=UTF-8");
            // 3.发送邮件:
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

	public static void main(String[] args) {
		String code = new RandomUtil_code().random();
		sendMail("1557018139@qq.com", code);
	}
}
