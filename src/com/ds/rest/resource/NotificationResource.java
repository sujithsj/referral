package com.ds.rest.resource;

import com.ds.domain.notification.Notification;
import com.ds.domain.user.User;
import com.ds.pact.service.notification.NotificationService;
import com.ds.security.helper.SecurityHelper;
import com.ds.utils.JSONResponseBuilder;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Mar 10, 2013
 * Time: 1:25:48 AM
 * To change this template use File | Settings | File Templates.
 */

@Path("/nf")
@NoCache
@Component
public class NotificationResource {


  @Autowired
  private NotificationService notificationService;

  @GET
  @Path("/latest")
  @Produces("application/json")
  public String getLastestUnreadNotificationsForCompany() {
    User user = SecurityHelper.getLoggedInUser();

    List<Notification> notificationList = getNotificationService().getLastestUnreadNotificationsForCompany(user.getCompanyShortName(), 10);

    return new JSONResponseBuilder().addField("data", notificationList).build();


  }

  public NotificationService getNotificationService() {
    return notificationService;
  }
}
