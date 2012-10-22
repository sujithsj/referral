package com.ds.impl.service.mail;


import com.ds.exception.DSException;
import com.ds.exception.InvalidParameterException;
import com.ds.pact.service.mail.EmailContext;
import com.ds.pact.service.mail.EmailTemplateService;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

  private Logger logger = LoggerFactory.getLogger(EmailTemplateServiceImpl.class);

  /*private Map<String, EmailTemplate> emailTemplates = new HashMap<String, EmailTemplate>();*/
  @Autowired
  private JavaMailSenderImpl mailSender;
  @Autowired
  private VelocityEngine velocityEngine;

  @Override
  public MimeMessage createEmail(EmailTemplateService.EmailEventType eventType, EmailContext context) {
    if (eventType == null || context == null) {
      throw new InvalidParameterException("EVENTTYPE_OR_CONTEXT_IS_NULL");
    }

    /**
     * We do not use a template in case this was a post reply, since reply already has subject, to , from and message body, so no template required.
     */
    /*if(EmailTemplateService.EmailEventType.FeedbackAnswered.equals(eventType)){

      try {
        MimeMessage message = getMailSender().createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        FeedbackAnswerContext feedbackAnsweredContext = (FeedbackAnswerContext)context;
        Answer answer = feedbackAnsweredContext.getAnswer();

        mimeMessageHelper.setFrom(answer.getAnsweringUser().getEmail());
        mimeMessageHelper.setSubject(answer.getPost().getTitle());
        mimeMessageHelper.setText(new String(answer.getContent()));

        return mimeMessageHelper.getMimeMessage();

      }catch (Exception ex) {
        LOGGER.error("Error occured in Creating MimeMessage in EmailTemplateServiceImpl", ex);
        throw new UserrulesException("EXCEPTION_IN_CREATING_EMAIL_MESSAGE", ex);
      }
    }*/

    if (EmailTemplateFactory.getEmailTemplate(eventType.toString()) == null) {
      throw new InvalidParameterException("NO_EMAIL_TEMPLATE_FOUND");
    }

    EmailTemplate emailTemplate = EmailTemplateFactory.getEmailTemplate(eventType.toString());

    try {
      MimeMessage message = getMailSender().createMimeMessage();
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

      /*if(context instanceof CompanyContext){
        CompanyContext companyContext = (CompanyContext)context;
        mimeMessageHelper.setFrom(companyContext.getCompany().getFromEmail() != null ? companyContext.getCompany().getFromEmail() : "support@userrules.com");
      }
      else{*/
      mimeMessageHelper.setFrom("adlakha.vaibhav@gmail.com");
      //}

      Map<String, Object> vc = new HashMap<String, Object>();
      vc.put("context", context);

      String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailTemplate.getBodyTemplateName(), vc);

      mimeMessageHelper.setText(content, emailTemplate.isHtml());

      StringWriter subject = new StringWriter();

      velocityEngine.evaluate(new VelocityContext(vc), subject, "Template Subject", emailTemplate.getSubject());

      mimeMessageHelper.setSubject(subject.toString());

      return mimeMessageHelper.getMimeMessage();
    } catch (Exception ex) {
      logger.error("Error occured in Creating MimeMessage in EmailTemplateServiceImpl", ex);
      throw new DSException("EXCEPTION_IN_CREATING_EMAIL_MESSAGE", ex);
    }
  }

//    private MimeMessage createEmail(EmailEventType eventType, MimeMessageHelper mimeMessageHelper){
//
//    	if (!emailTemplates.containsKey(eventType.toString())) {
//            throw new InvalidParameterException("NO_EMAIL_TEMPLATE_FOUND");
//        }
//
//    	 EmailTemplate emailTemplate = emailTemplates.get(eventType.toString());
//
//    	  try {
//
//    		Map<String, Object> vc = new HashMap<String, Object>();
//            vc.put("context", context);
//
//            String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailTemplate.getBodyTemplateName(), vc);
//
//            mimeMessageHelper.setText(content, emailTemplate.isHtml());
//
//            StringWriter subject = new StringWriter();
//
//            velocityEngine.evaluate(new VelocityContext(vc), subject, "Template Subject", emailTemplate.getSubject());
//
//            mimeMessageHelper.setSubject(subject.toString());
//
//            return mimeMessageHelper.getMimeMessage();
//        } catch (Exception ex) {
//            LOGGER.error("Error occured in Creating MimeMessage in EmailTemplateServiceImpl", ex);
//            throw new UserrulesException("EXCEPTION_IN_CREATING_EMAIL_MESSAGE", ex);
//        }
//    }

  /**//**
   * @return the emailTemplates
   *//*
  public Map<String, EmailTemplate> getEmailTemplates() {
    return emailTemplates;
  }

  *//**
   * @param emailTemplates the emailTemplates to set
   *//*
  public void setEmailTemplates(Map<String, EmailTemplate> emailTemplates) {
    this.emailTemplates = emailTemplates;
  }*/

  public JavaMailSenderImpl getMailSender() {
    return mailSender;
  }

  public void setMailSender(JavaMailSenderImpl mailSender) {
    this.mailSender = mailSender;
  }

  /**
   * @return the velocityEngine
   */
  public VelocityEngine getVelocityEngine() {
    return velocityEngine;
  }

  /**
   * @param velocityEngine the velocityEngine to set
   */
  public void setVelocityEngine(VelocityEngine velocityEngine) {
    this.velocityEngine = velocityEngine;
  }

}