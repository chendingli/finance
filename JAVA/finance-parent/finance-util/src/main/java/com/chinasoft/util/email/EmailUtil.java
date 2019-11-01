package com.chinasoft.util.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * @Author: lizhi
 * @Date: 2018/9/4 11:08
 */
public class EmailUtil {

    private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    private JavaMailSenderImpl javaMailSenderImpl;
    private String from;
    private String host;
    private String userName;
    private String password;
    private String defaultEncoding;
    private int port;


    public void setJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setDefaultEncoding(this.defaultEncoding);
        javaMailSender.setHost(this.host);
        javaMailSender.setPassword(this.password);
        javaMailSender.setUsername(this.userName);
        javaMailSender.setPort(this.port);
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(new Properties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName,password);
            }
        });
        javaMailSender.setSession(session);
        this.javaMailSenderImpl = javaMailSender;
    }

    /**
     * 发送文本邮件
     * @param to
     * @param subject
     * @param content
     */
    
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message =  new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            javaMailSenderImpl.send(message);
            logger.info("简单邮件已经发送。");
        } catch (MailException e) {
            logger.error("发送邮件时出现异常",e);
        }
    }

    /**
     * 发送HTML邮件
     * @param to
     * @param subject
     * @param content
     */
    
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = javaMailSenderImpl.createMimeMessage();
        try {
            MimeMessageHelper helper= new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(content,true);

            javaMailSenderImpl.send(message);
            logger.info("html邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发送邮件发生异常",e);
        }

    }

    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = javaMailSenderImpl.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));

            helper.addAttachment(fileName,file);

            javaMailSenderImpl.send(message);
            logger.info("带附件的邮件已经发送。");

        } catch (MessagingException e) {
            logger.error("发送邮件时发生异常",e);
        }
    }

    /**
     * 发送邮件中带有静态资源（图片）的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */

    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = javaMailSenderImpl.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);

            FileSystemResource file = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId,file);

            javaMailSenderImpl.send(message);
            logger.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送邮件时发生异常",e);
        }
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
