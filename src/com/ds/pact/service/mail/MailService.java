package com.ds.pact.service.mail;

import com.ds.core.event.EmailEvent;

import javax.mail.internet.MimeMessage;

/**
 * @author adlakha.vaibhav
 */
public interface MailService {

  /**
     * Sends the Email
     *
     * @param message
     */
    public void sendMail(MimeMessage message);

    /**
     * Send a email asynchronously.
     * @param mailEvent
     */
    public void sendAsyncMail(final EmailEvent mailEvent);
}
