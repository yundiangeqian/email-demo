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
        //解析模板
        String content = templateEngine.process("email", context);

        Email email = new Email();
        email.setSender(env.getProperty("spring.mail.username"));
        email.setRecipient(new String[]{"收件人"});
        email.setCc(new String[]{"抄送人"});
        email.setSubject("HTML格式的测试邮件");
        email.setContent(content );
        email.setHtml(true);
        List<Map<String, String>> maps = new ArrayList<>();
        Map<String, String> map1 = new TreeMap<>();
        map1.put(FileConstant.FILE_NAME, "名称1");
        map1.put(FileConstant.FILE_PATH, "路径1");
        maps.add(map1);
        Map<String, String> map2 = new TreeMap<>();
        map2.put(FileConstant.FILE_NAME, "名称2t");
        map2.put(FileConstant.FILE_PATH, "路径2");
        maps.add(map2);
        Map<String, String> map3 = new TreeMap<>();
        map3.put(FileConstant.FILE_NAME, "名称3");
        map3.put(FileConstant.FILE_PATH, "路径3");
        maps.add(map3);
        email.setAttachFiles(maps);
        boolean isSend = emailUtils.sendHtmlMailWithAttachment(email);
        logger.info("邮件发送：{}", isSend);
    }


    @Test
    public void sendHtmlMailWithAttachment() {
        Email email = new Email();
        email.setSender(env.getProperty("spring.mail.username"));
        email.setRecipient(new String[]{"收件人"});
        email.setCc(new String[]{"抄送人"});
        email.setSubject("HTML格式的测试邮件");
        email.setContent("<h3><a href='http://www.baidu.com'>百度一下，你就知道</a></h3>");
        email.setHtml(true);
        List<Map<String, String>> maps = new ArrayList<>();
        Map<String, String> map1 = new TreeMap<>();
        map1.put(FileConstant.FILE_NAME, "名称1");
        map1.put(FileConstant.FILE_PATH, "路径1");
        maps.add(map1);
        Map<String, String> map2 = new TreeMap<>();
        map2.put(FileConstant.FILE_NAME, "名称2t");
        map2.put(FileConstant.FILE_PATH, "路径2");
        maps.add(map2);
        Map<String, String> map3 = new TreeMap<>();
        map3.put(FileConstant.FILE_NAME, "名称3");
        map3.put(FileConstant.FILE_PATH, "路径3");
        maps.add(map3);
        email.setAttachFiles(maps);
        boolean isSend = emailUtils.sendHtmlMailWithAttachment(email);
        logger.info("邮件发送：{}", isSend);
    }

    @Test
    public void sendSimpleMail() {
        Email email = new Email();
        email.setSender(env.getProperty("spring.mail.username"));
        email.setRecipient(new String[]{"收件人"});
        email.setCc(new String[]{"抄送人"});
        email.setSubject("主题");
        email.setContent("内容");
        boolean isSend = emailUtils.sendSimpleMail(email);
        logger.info("邮件发送：{}", isSend);
    }
}