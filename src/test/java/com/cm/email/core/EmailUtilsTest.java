package com.cm.email.core;

import com.cm.email.constant.FileConstant;
import com.cm.email.entity.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(EmailUtilsTest.class);
    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    private Environment env;
    @Autowired
    private TemplateEngine templateEngine;


    @Test
    public void sendFullMailWithTemplate() {
        Context context = new Context();
        context.setVariable("href", "http://www.baidu.com");
        String content = templateEngine.process("email", context);

        Email email = new Email();
        email.setSender(env.getProperty("spring.mail.username"));
        email.setRecipient(new String[]{"987726878@qq.com"});
        email.setCc(new String[]{"1317955420@qq.com"});
        email.setSubject("基于themyleaf模板的测试邮件");
        email.setContent(content);
        email.setHtml(true);
        List<Map<String, String>> maps = new ArrayList<>();
        Map<String, String> map1 = new TreeMap<>();
        map1.put(FileConstant.FILE_NAME, "1.png");
        map1.put(FileConstant.FILE_PATH, "C:\\Users\\caomian\\Desktop\\CSDN\\linux-web部署\\1.png");
        maps.add(map1);
        Map<String, String> map2 = new TreeMap<>();
        map2.put(FileConstant.FILE_NAME, "呵呵.txt");
        map2.put(FileConstant.FILE_PATH, "C:\\Users\\caomian\\Desktop\\呵呵.txt");
        maps.add(map2);
        Map<String, String> map3 = new TreeMap<>();
        map3.put(FileConstant.FILE_NAME, "test-email.docx");
        map3.put(FileConstant.FILE_PATH, "C:\\Users\\caomian\\Desktop\\test-email.docx");
        maps.add(map3);
        email.setAttachFiles(maps);
        boolean isSend = emailUtils.sendHtmlMailWithAttachment(email);
        logger.info("邮件发送：{}", isSend);
    }


    @Test
    public void sendHtmlMailWithAttachment() {
        Email email = new Email();
        email.setSender(env.getProperty("spring.mail.username"));
        email.setRecipient(new String[]{"987726878@qq.com"});
        email.setCc(new String[]{"1317955420@qq.com"});
        email.setSubject("HTML格式的测试邮件");
        email.setContent("<h3><a href='http://www.baidu.com'>百度一下，你就知道</a></h3>");
        email.setHtml(true);
        List<Map<String, String>> maps = new ArrayList<>();
        Map<String, String> map1 = new TreeMap<>();
        map1.put(FileConstant.FILE_NAME, "1.png");
        map1.put(FileConstant.FILE_PATH, "C:\\Users\\caomian\\Desktop\\CSDN\\linux-web部署\\1.png");
        maps.add(map1);
        Map<String, String> map2 = new TreeMap<>();
        map2.put(FileConstant.FILE_NAME, "呵呵.txt");
        map2.put(FileConstant.FILE_PATH, "C:\\Users\\caomian\\Desktop\\呵呵.txt");
        maps.add(map2);
        Map<String, String> map3 = new TreeMap<>();
        map3.put(FileConstant.FILE_NAME, "test-email.docx");
        map3.put(FileConstant.FILE_PATH, "C:\\Users\\caomian\\Desktop\\test-email.docx");
        maps.add(map3);
        email.setAttachFiles(maps);
        boolean isSend = emailUtils.sendHtmlMailWithAttachment(email);
        logger.info("邮件发送：{}", isSend);
    }

    @Test
    public void sendSimpleMail() {
        Email email = new Email();
        email.setSender(env.getProperty("spring.mail.username"));
        email.setRecipient(new String[]{"987726878@qq.com"});
        email.setCc(new String[]{"1317955420@qq.com"});
        email.setSubject("简易邮件的测试邮件");
        email.setContent("今天开心吗");
        boolean isSend = emailUtils.sendSimpleMail(email);
        logger.info("邮件发送：{}", isSend);
    }
}