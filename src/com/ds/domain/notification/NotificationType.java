package com.ds.domain.notification;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * User: Rahul
 * Date: Jan 7, 2013
 * Time: 12:43:55 PM
 */

@Entity
@Table(name = "notification_type")
public class NotificationType {

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "TYPE", nullable = false, length = 100)
	private String type;

	@Column(name = "PRIORITY", nullable = false)
	private Long priority;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}
}
