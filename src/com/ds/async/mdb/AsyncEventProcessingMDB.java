package com.ds.async.mdb;

/**
 * @author adlakha.vaibhav
 */

import com.ds.core.event.AsyncEvent;
import com.ds.core.event.EventDispatcher;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.utils.SmartSerializationHelper;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.jms.*;

public class AsyncEventProcessingMDB implements MessageListener {

    private EventDispatcher     eventDispatcher;

    private TransactionTemplate transactionTemplate;

    @Override
    public void onMessage(final Message msg) {
        if (msg instanceof TextMessage) {

            getTransactionTemplate().execute(new TransactionCallback() {

                @Override
                public Object doInTransaction(TransactionStatus status) {

                    TextMessage textMessage = (TextMessage) msg;
                    try {
                        String json = textMessage.getText();
                        AsyncEvent asyncEvent = (AsyncEvent) SmartSerializationHelper.getObjectFromWireRepresentation(json);

                        getEventDispatcher().invokeAsyncEventListeners(asyncEvent);

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
     * @return the eventDispatcher
     */
    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    /**
     * @param eventDispatcher the eventDispatcher to set
     */
    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:spring/spring*.xml");
        JmsTemplate jmsTemplate = ServiceLocatorFactory.getService(JmsTemplate.class);

        jmsTemplate.send((Destination) appContext.getBean("eventQueue"), new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {

                TextMessage message = session.createTextMessage("Test Message");
                message.setStringProperty("next", "foo");
                return message;
            }
        });
    }
}