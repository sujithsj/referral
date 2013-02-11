package com.ds.domain.notification;

import com.ds.domain.marketing.MarketingMaterialType;
import com.ds.domain.core.FileAttachment;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.user.User;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * User: Rahul
 * Date: Jan 7, 2013
 * Time: 12:43:43 PM
 */

@Entity
@Table(name = "notification")
@NamedQueries({
    @NamedQuery(name = "getNotificationById", query = "select nf from Notification nf where nf.id = :nfId")
})
public class Notification {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NOTIFICATION_TYPE_ID", nullable = false)
	private NotificationType notificationType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AFFILIATE_ID")
	private Affiliate affiliate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPANY_AFFILIATE_ID")
	private CompanyAffiliate companyAffiliate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "MESSAGE")
	private String message;

	@Column(name = "COMPANY_SHORTNAME", nullable = false, length = 50)
	private String companyShortName;

	@Column(name = "NOTIFIED", nullable = false)
	private Boolean notified = false;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	public Affiliate getAffiliate() {
		return affiliate;
	}

	public void setAffiliate(Affiliate affiliate) {
		this.affiliate = affiliate;
	}

	public CompanyAffiliate getCompanyAffiliate() {
		return companyAffiliate;
	}

	public void setCompanyAffiliate(CompanyAffiliate companyAffiliate) {
		this.companyAffiliate = companyAffiliate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCompanyShortName() {
		return companyShortName;
	}

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}
}
