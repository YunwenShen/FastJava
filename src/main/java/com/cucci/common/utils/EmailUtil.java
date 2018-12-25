package com.cucci.common.utils;

import lombok.Data;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件发送工具类
 */
public class EmailUtil {

    public static void send(EmailEntity emailEntity) throws MessagingException {
        // 配置发送邮件的环境属性
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", emailEntity.getHost());
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.debug", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.user", emailEntity.getFrom());
        props.put("mail.password", emailEntity.getPassword());
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String userName = emailEntity.getFrom();
                String password = emailEntity.getPassword();
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(emailEntity.getFrom());
        message.setFrom(form);
        // 设置收件人
        InternetAddress to = new InternetAddress(emailEntity.getTo());
        message.setRecipient(RecipientType.TO, to);
        message.setSubject(emailEntity.getSubject());
        // 设置邮件的内容体
        message.setContent(emailEntity.getContent(), "text/plain;charset=UTF-8");
        // 发送邮件
        Transport.send(message);
    }

    public static void main(String[] args) throws MessagingException {

        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setHost("smtp.qiye.163.com");
        emailEntity.setPort(25);
        emailEntity.setFrom("发送邮箱的账号");
        emailEntity.setPassword("邮箱授权码");
        emailEntity.setSubject("数据共享平台接口异常");
        emailEntity.setUsername("接口监测");
        emailEntity.setTo("shenyunwen520@qq.com");
        emailEntity.setContent("你好");
        send(emailEntity);

    }

}

@Data
class EmailEntity {
    /**
     * 邮件服务器地址
     * 网易企业邮箱 smtp.qiye.163.com
     * QQ邮箱 smtp.qq.com
     */
    private String host;

    /**
     * 邮箱服务器端口 常用端口25
     */
    private int port = 25;

    /**
     * 发送人邮件地址
     */
    private String from;

    /**
     * 收件人邮件地址
     */
    private String to;

    /**
     * 附件地址
     */
    private String attachAddr;

    /**
     * 附件名称
     */
    private String attachName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 授权码
     */
    private String password;

    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;
}