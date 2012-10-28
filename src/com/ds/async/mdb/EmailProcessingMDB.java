package com.ds.async.mdb;

/**
 * @author adlakha.vaibhav
 */

import com.ds.core.event.EmailEvent;
import com.ds.pact.service.mail.EmailTemplateService;
import com.ds.pact.service.mail.MailService;
import com.ds.utils.SmartSerializationHelper;
import com.ds.exception.DSException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailProcessingMDB implements MessageListener {

    private TransactionTemplate  transactionTemplate;

    private MailService          mailService;

    private EmailTemplateService emailTemplateService;

    @Override
    public void onMessage(final Message msg) {
        if (msg instanceof TextMessage) {

            getTransactionTemplate().execute(new TransactionCallback() {

                @Override
                public Object doInTransaction(TransactionStatus status) {

                    TextMessage textMessage = (TextMessage) msg;
                    try {
                        String json = textMessage.getText();
                        EmailEvent emailEvent = (EmailEvent) SmartSerializationHelper.getObjectFromWireRepresentation(json);
                        MimeMessage mimeMessage = getEmailTemplateService().createEmail(emailEvent.getEventType(), emailEvent.getContext());
                        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                        try {
                            emailEvent.setReceivers(messageHelper);
                        } catch (MessagingException e) {
                            throw new DSException("ERROR_IN_SETTING_MAIL_RECIEVER", e);
                        }
                        getMailService().sendMail(messageHelper.getMimeMessage());
                    } catch (JMSException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                }

            });

        }

    }

    /**
     * @return the transactionTemplate
     */
    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    /**
     * @param transactionTemplate the transactionTemplate to set
     */
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * @return the mailService
     */
    public MailService getMailService() {
        return mailService;
    }

    /**
     * @param mailService the mailService to set
     */
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * @return the emailTemplateService
     */
    public EmailTemplateService getEmailTemplateService() {
        return emailTemplateService;
    }

    /**
     * @param emailTemplateService the emailTemplateService to set
     */
    public void setEmailTemplateService(EmailTemplateService emailTemplateService) {
        this.emailTemplateService = emailTemplateService;
    }

}
