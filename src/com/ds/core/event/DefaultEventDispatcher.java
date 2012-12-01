package com.ds.core.event;

import com.ds.utils.SmartSerializationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * @author adlakha.vaibhav
 */
@Service
public class DefaultEventDispatcher implements EventDispatcher {

  private Logger logger = LoggerFactory.getLogger(DefaultEventDispatcher.class);

  //private Map<String, List<EventListener>> syncEventListenerRegistry = new LinkedHashMap<String, List<EventListener>>();

  //private Map<String, List<EventListener>> asyncEventListenerRegistry = new LinkedHashMap<String, List<EventListener>>();

  private Set<String> syncEvents = new HashSet<String>();
  private Set<String> asyncEvents = new HashSet<String>();

  private JmsTemplate jmsTemplate;

  private Destination eventQueue;

  @PostConstruct
  public void initEvents(){
    asyncEvents.add(MarketingMaterialServeEvent.class.getSimpleName());
  }

  @Override
  public void dispatchEvent(Event event) {

    if (syncEvents.contains(event.getClass().getSimpleName())) {
      /*for (EventListener eventListener : syncEventListenerRegistry.get(event.getClass().getSimpleName())) {
        eventListener.handleEvent(event);
      }*/

      /**
       * TODO: need to handle this
       */
    }
    if (event instanceof AsyncEvent) {
      if (asyncEvents.contains(event.getClass().getSimpleName())) {
        dispatchEventForAsycnProcessing((AsyncEvent) event);
      }
    }
  }

  @Override
  public void invokeAsyncEventListeners(AsyncEvent event) {

    if (asyncEvents.contains(event.getClass().getSimpleName())) {
      for (EventListener eventListener : AsyncEventListenerRegistry.getEventListeners(event.getClass().getSimpleName())) {
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
