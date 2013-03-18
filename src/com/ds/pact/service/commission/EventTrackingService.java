package com.ds.pact.service.commission;

import com.ds.domain.tracking.EventTracking;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Mar 18, 2013
 * Time: 9:28:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EventTrackingService {

  
  public EventTracking saveEventTracking(EventTracking eventTracking);
}
