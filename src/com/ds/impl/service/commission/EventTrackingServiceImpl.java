package com.ds.impl.service.commission;

import com.ds.domain.tracking.EventTracking;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.commission.EventTrackingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Mar 16, 2013
 * Time: 2:40:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class EventTrackingServiceImpl implements EventTrackingService {

  @Autowired
  private BaseDao baseDao;

  @Override
  @Transactional
  public EventTracking saveEventTracking(EventTracking eventTracking) {
    eventTracking = (EventTracking) getBaseDao().save(eventTracking);
    //TODO: send email, add notification
    return eventTracking;
  }

  public BaseDao getBaseDao() {
    return baseDao;
  }
}
