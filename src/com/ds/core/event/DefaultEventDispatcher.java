package com.ds.core.event;

import com.ds.utils.SmartSerializationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * @author adlakha.vaibhav
 */
public class DefaultEventDispatcher implements EventDispatcher{

  private Logger logger = LoggerFactory.getLogger(DefaultEventDispatcher.class);

    private Map<String, List<EventListener>> syncEventListenerRegistry  = new LinkedHashMap<String, List<EventListener>>();

    private Map<String, List<EventListener>> asyncEventListenerRegistry = new LinkedHashMap<String, List<EventListener>>();

    private JmsTemplate                      jmsTemplate;

    private Destination eventQueue;

    @Override
    public void dispatchEvent(Event event) {

        if (syncEventListenerRegistry.containsKey(event.getClass().getSimpleName())) {
            for (EventListener eventListener : syncEventListenerRegistry.get(event.getClass().getSimpleName())) {
                eventListener.handleEvent(event);
            }
        }
        if (event instanceof AsyncEvent) {
            if (asyncEventListenerRegistry.containsKey(event.getClass().getSimpleName())) {
                dispatchEventForAsycnProcessing((AsyncEvent) event);
            }
        }
    }

    @Override
    public void invokeAsyncEventListeners(AsyncEvent event) {

        if (asyncEventListenerRegistry.containsKey(event.getClass().getSimpleName())) {
            for (EventListener eventListener : asyncEventListenerRegistry.get(event.getClass().getSimpleName())) {
                eventListener.handleEvent(event);
            }
        }
    }

    private void dispatchEventForAsycnProcessing(final AsyncEvent event) {

        jmsTemplate.send(eventQueue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                String json = SmartSerializationHelper.getWireRepresentation(event);
                TextMessage message = session.createTextMessage(json);
                return message;
            }
        });
    }

    /**
     * @return the syncEventListenerRegistry
     */
    public Map<String, List<EventListener>> getSyncEventListenerRegistry() {
        return syncEventListenerRegistry;
    }

    /**
     * @param syncEventListenerRegistry the syncEventListenerRegistry to set
     */
    public void setSyncEventListenerRegistry(Map<String, List<EventListener>> syncEventListenerRegistry) {

        this.syncEventListenerRegistry = syncEventListenerRegistry;
    }

    /**
     * @return the asyncEventListenerRegistry
     */
    public Map<String, List<EventListener>> getAsyncEventListenerRegistry() {
        return asyncEventListenerRegistry;
    }

    /**
     * @param asyncEventListenerRegistry the asyncEventListenerRegistry to set
     */
    public void setAsyncEventListenerRegistry(Map<String, List<EventListener>> asyncEventListenerRegistry) {
        this.asyncEventListenerRegistry = asyncEventListenerRegistry;
    }

    /**
     * @return the jmsTemplate
     */
    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    /**
     * @param jmsTemplate the jmsTemplate to set
     */
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * @return the eventQueue
     */
    public Destination getEventQueue() {
        return eventQueue;
    }

    /**
     * @param eventQueue the eventQueue to set
     */
    public void setEventQueue(Destination eventQueue) {
        this.eventQueue = eventQueue;
    }

}
