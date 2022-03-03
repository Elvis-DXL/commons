package com.elvis.commons.old.utils;

import com.elvis.commons.old.prop.EmailProp;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * @author : Elvis
 * @since : 2020/8/28 17:37
 */
public final class EmailUtils {

    private EmailProp emailProp;

    public EmailUtils(EmailProp emailProp) {
        if (emailProp == null
                || StringUtils.isEmpty(emailProp.getUsername()) || StringUtils.isEmpty(emailProp.getPassword())
                || StringUtils.isEmpty(emailProp.getHost()) || StringUtils.isEmpty(emailProp.getPort())) {
            throw new IllegalArgumentException("Configuration information error[" + emailProp + "]");
        }
        this.emailProp = emailProp;
    }

    /**
     * 发送邮件
     *
     * @param title       标题
     * @param msg         内容
     * @param targetEmail 目标邮箱地址
     */
    public void sendEmail(String title, String msg, String... targetEmail) throws EmailException {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(msg) || null == targetEmail || targetEmail.length == 0) {
            throw new IllegalArgumentException("Missing parameters[title=" + title + ";msg=" + msg + ";targetEmail=" + targetEmail);
        }
        SimpleEmail simpleEmail = new SimpleEmail();
        simpleEmail.setSSLOnConnect(true);
        simpleEmail.setHostName(emailProp.getHost());
        simpleEmail.setSslSmtpPort(emailProp.getPort());
        simpleEmail.setAuthentication(emailProp.getUsername(), emailProp.getPassword());
        simpleEmail.setFrom(emailProp.getUsername(), emailProp.getName());
        simpleEmail.setCharset("UTF-8");
        simpleEmail.addTo(targetEmail);
        simpleEmail.setSubject(title);
        simpleEmail.setMsg(msg);
        simpleEmail.send();
    }

}
