package com.ds.constants;

import com.ds.domain.notification.NotificationType;

import java.util.List;
import java.util.Arrays;

/**
 * User: Rahul
 * Date: Jan 7, 2013
 * Time: 12:44:27 PM
 */
public enum EnumNotificationType {

	COMPANY_AFFILIATE_APPROVAL_PENDING(10L, NotificationMessages.COMPANY_AFFILIATE_APPROVAL_PENDING, 1L),
	COMPANY_SALE_VIA_AFFILIATE(20L, NotificationMessages.COMPANY_SALE_VIA_AFFILIATE, 2L);

	private String type;
	private Long id;
	private Long priority;


	EnumNotificationType(Long id, String type, Long priority) {
		this.id = id;
		this.type = type;
		this.priority = priority;
	}

	public String getType() {
		return type;
	}

	public Long getId() {
		return id;
	}

	public Long getPriority() {
		return priority;
	}

	public static List<EnumNotificationType> getAllNotificationTypes() {
		return Arrays.asList(
				EnumNotificationType.COMPANY_AFFILIATE_APPROVAL_PENDING
				);

	}


	public NotificationType asNotificationType() {
	  NotificationType notificationType  = new NotificationType();
	  notificationType.setId(this.getId());
	  notificationType.setType(this.getType());
	  return notificationType;
	}

	public static EnumNotificationType getById(Long typeId){
	  for(EnumNotificationType enumNotificationType : EnumNotificationType.values()){
	    if(enumNotificationType.getId().equals(typeId)){
	      return enumNotificationType;
	    }
	  }
	  return null;
	}


}
