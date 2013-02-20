package com.ds.core.event.listener;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ds.core.event.EventListener;
import com.ds.core.event.Event;
import com.ds.core.event.UserLoginEmailConfirmationRequestEvent;
import com.ds.core.event.EmailEvent;
import com.ds.pact.service.mail.MailService;
import com.ds.pact.service.mail.EmailTemplateService;
import com.ds.impl.service.mail.UserAuthEmailContext;
import com.ds.utils.SystemSettings;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Feb 20, 2013
 * Time: 11:56:25 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class UserLoginEmailConfirmationRequestListener implements EventListener {

	private Logger logger = LoggerFactory.getLogger(UserLoginEmailConfirmationRequestListener.class);

    @Autowired
	private MailService mailService;

	@Override
	public void handleEvent(Event event) {
		UserLoginEmailConfirmationRequestEvent requestEvent = (UserLoginEmailConfirmationRequestEvent) event;
		EmailTemplateService.EmailEventType emailEventType = requestEvent.getEmailEventType();

		UserAuthEmailContext context = new UserAuthEmailContext();

		context.setFullName(requestEvent.getUserLoginConfirmationRequest().getName());
		context.setPreferredUserName(requestEvent.getUserLoginConfirmationRequest().getPreferredUsername());
		context.setProviderName(requestEvent.getUserLoginConfirmationRequest().getProviderName());
		context.setUserEmail(requestEvent.getUserLoginConfirmationRequest().getVerifiedEmail());

		String confirmationLink = SystemSettings.getBaseUrl() + "tpEmailConfirm?confId=" + requestEvent.getUserLoginConfirmationRequest().getId() + "&confKey="
			+ requestEvent.getUserLoginConfirmationRequest().getConfirmationKey();

		context.setConfirmationLink(confirmationLink);

		//getMailService().sendAsyncMail(new EmailEvent(EmailEventType.UserLoggedInThirdPartyEmailConfirmation, context));
		getMailService().sendAsyncMail(new EmailEvent(emailEventType, context));
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

}
