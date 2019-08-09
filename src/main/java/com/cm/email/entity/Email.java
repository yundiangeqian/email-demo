package com.cm.email.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: caomian
 * @data: 2019/8/9 11:16
 */
public class Email implements Serializable {
    private Integer id;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件显示内容
     */
    private String content;
    /**
     * 发送人
     */
    private String sender;
    /**
     * 收件人
     */
    private String[] recipient;
    /**
     * 抄送人
     */
    private String[] cc;
    /**
     * 内容是否是html
     */
    private boolean isHtml;
    /**
     * 附件列表 一个map存储一个附件的名称和路径
     */
    private List<Map<String, String>> attachFiles;

    public Email() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getContent() {
        return this.content;
    }

    public String getSender() {
        return this.sender;
    }

    public String[] getRecipient() {
        return this.recipient;
    }

    public String[] getCc() {
        return this.cc;
    }

    public boolean isHtml() {
        return isHtml;
    }

    public void setHtml(boolean html) {
        isHtml = html;
    }

    public List<Map<String, String>> getAttachFiles() {
        return this.attachFiles;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setRecipient(String[] recipient) {
        this.recipient = recipient;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public void setAttachFiles(List<Map<String, String>> attachFiles) {
        this.attachFiles = attachFiles;
    }

}
