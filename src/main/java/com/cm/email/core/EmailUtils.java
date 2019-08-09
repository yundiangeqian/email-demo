package com.cm.email.core;

import com.cm.email.constant.FileConstant;
import com.cm.email.entity.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * @description:
 * @author: caomian
 * @data: 2019/8/9 10:30
 */
@Component
public class EmailUtils {
    private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送简易邮件
     *
     * @param email
     * @return
     */
    public boolean sendSimpleMail(Email email) {
        boolean flag = true;
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(email.getSender());
            simpleMailMessage.setTo(email.getRecipient());
            simpleMailMessage.setCc(email.getCc());
            simpleMailMessage.setSubject(email.getSubject());
            simpleMailMessage.setText(email.getContent());
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            logger.error("邮件发送失败：{}", e.getMessage());
            flag = false;
        }
        return flag;
    }

    /**
     * 发送html格式的可带附件的邮件
     * 可多个附件
     *
     * @param email
     */
    public boolean sendHtmlMailWithAttachment(Email email) {
        boolean flag = true;
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setText(email.getContent(), email.isHtml());
            mimeMessageHelper.setFrom(email.getSender());
            mimeMessageHelper.setTo(email.getRecipient());
            if (email.getCc() != null) {
                mimeMessageHelper.setCc(email.getCc());
            }
            if (email.getAttachFiles() != null) {
                FileSystemResource file = null;
                for (Map<String, String> attachment : email.getAttachFiles()) {
                    file = new FileSystemResource(attachment.get(FileConstant.FILE_PATH));
                    mimeMessageHelper.addAttachment(attachment.get(FileConstant.FILE_NAME), file);
                }
            }
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("邮件发送失败：{}", e.getMessage());
            flag = false;
        }
        return flag;
    }

}
