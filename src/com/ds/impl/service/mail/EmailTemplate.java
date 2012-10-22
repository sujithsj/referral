package com.ds.impl.service.mail;

import java.io.Serializable;

/**
 * @author adlakha.vaibhav
 */
public class EmailTemplate implements Serializable {

    private String  subject;
    private String  from;
    // body actually refers to Velocity Template for body
    private String  bodyTemplateName;

    private boolean html;

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the bodyTemplateName
     */
    public String getBodyTemplateName() {
        return bodyTemplateName;
    }

    /**
     * @param bodyTemplateName the bodyTemplateName to set
     */
    public void setBodyTemplateName(String bodyTemplateName) {
        this.bodyTemplateName = bodyTemplateName;
    }

    /**
     * @return the html
     */
    public boolean isHtml() {
        return html;
    }

    /**
     * @param html the html to set
     */
    public void setHtml(boolean html) {
        this.html = html;
    }

}
