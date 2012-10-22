package com.ds.impl.service.mail;

import com.ds.core.event.EmailEvent;
import com.ds.domain.core.Properties;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.service.admin.LoadPropertyService;
import com.ds.pact.service.mail.MailService;
import com.ds.utils.SmartSerializationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.jms.*;
import javax.mail.internet.MimeMessage;
import javax.annotation.PostConstruct;


/**
 * @author adlakha.vaibhav
 */
@Service
public class MailServiceImpl implements MailService {

  @Autowired
  private LoadPropertyService loadPropertyService;
  @Autowired
  private MailSender mailSender;

  @Autowired
  private JmsTemplate jmsTemplate;

  @Autowired
  private Destination emailQueue;


  @PostConstruct
  protected void initMailSender() {

    mailSender.setHost(getLoadPropertyService().getProperty(Properties.USERRULES_SMTP_HOST.getPropertyRef()).toString());
    mailSender.setUsername(getLoadPropertyService().getProperty(Properties.USERRULES_SMTP_USER_NAME.getPropertyRef()).toString());
    mailSender.setPassword(getLoadPropertyService().getProperty(Properties.USERRULES_SMTP_PWD.getPropertyRef()).toString());
    mailSender.setPort(Integer.parseInt(getLoadPropertyService().getProperty(Properties.USERRULES_SMTP_PORT.getPropertyRef()).toString()));

    java.util.Properties props = new java.util.Properties();
    props.put("mail.debug", getLoadPropertyService().getProperty(Properties.USERRULES_MAIL_DEBUG.getPropertyRef()).toString());
    props.put("mail.smtp.auth", getLoadPropertyService().getProperty(Properties.USERRULES_MAIL_SMTP_AUTH.getPropertyRef()).toString());
    props.put("mail.smtp.socketFactory.fallback", getLoadPropertyService().getProperty(Properties.USERRULES_MAIL_SMTP_FALLBACK.getPropertyRef()).toString());
    props.put("mail.smtp.starttls.enable", getLoadPropertyService().getProperty("userrules.mail.smtp.starttls.enable").toString());

    props.put("mail.smtp.socketFactory.class", getLoadPropertyService().getProperty("userrules.mail.smtp.socketFactory.class").toString());
    mailSender.setJavaMailProperties(props);

  }

  public LoadPropertyService getLoadPropertyService() {
    return loadPropertyService;
  }

  public void setLoadPropertyService(LoadPropertyService loadPropertyService) {
    this.loadPropertyService = loadPropertyService;
  }

  /**
   * @return the mailSender
   */
  public JavaMailSenderImpl getMailSender() {
    return mailSender;
  }

  /**
   * @param mailSender the mailSender to set
   */
  public void setMailSender(MailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void sendMail(MimeMessage mimeMessage) {
    getMailSender().send(mimeMessage);
  }

  @Override
  public void sendAsyncMail(final EmailEvent mailEvent) {
    getJmsTemplate().send(getEmailQueue(), new MessageCreator() {
      public Message createMessage(Session session) throws JMSException {
        String json = SmartSerializationHelper.getWireRepresentation(mailEvent);
        TextMessage message = session.createTextMessage(json);
        return message;

      }
    });
  }

  /**
   * @return the jmsTemplate
   */
  public JmsTemplate getJmsTemplate() {
    if (this.jmsTemplate == null) {
      this.jmsTemplate = ServiceLocatorFactory.getService(JmsTemplate.class);
    }
    return jmsTemplate;
  }

  /**
   * @param jmsTemplate the jmsTemplate to set
   */
  public void setJmsTemplate(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  /**
   * @return the emailQueue
   */
  public Destination getEmailQueue() {
    if (this.emailQueue == null) {
      this.emailQueue = (Destination) ServiceLocatorFactory.getService("emailQueue");
    }
    return emailQueue;
  }

  /**
   * @param emailQueue the emailQueue to set
   */
  public void setEmailQueue(Destination emailQueue) {
    this.emailQueue = emailQueue;
  }
}
