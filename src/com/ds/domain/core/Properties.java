package com.ds.domain.core;

/**
 * @author adlakha.vaibhav
 */
public enum Properties {

  SOLR_SERVER_URL("solr.serverurl"),
	SOLR_INDEX_UPDATE_CRON_EXPR("solr.indexUpdate.cronExpr"),
	SOLR_DELTA_UPDATE_URL("solr.deltaUpdateURL"),
	SOLR_FULL_IMPORT_URL("solr.fullImportURL"),
	USERRULES_FROM_EMAIL_ID("userrules.fromEmailId"),
	USERRULES_SMTP_HOST("userrules.smtp.host"),
	USERRULES_SMTP_USER_NAME("userrules.smtp.username"),
	USERRULES_SMTP_PWD("userrules.smtp.password"),
	USERRULES_SMTP_PORT("userrurles.smtp.port"),
	USERRULES_MAIL_DEBUG("userrurles.mail.debug"),
	USERRULES_MAIL_SMTP_AUTH("userrurles.mail.smtp.auth"),
	USERRULES_MAIL_SMTP_FALLBACK("userrurles.mail.smtp.socketFactory.fallback"),
	GA_ACCOUNTS_URL("ga.accounts.url"),
	GA_DATA_URL("ga.data.url"),
	RECAPTCHA_VERIFY_URL("recaptcha.verify.url"),
	RECAPTCH_PRIVATE_KEY("recaptcha.private.key");

	private String propertyRef;
	private Object defaultValue;


	private Properties(String propertyRef) {
		this(propertyRef, null);
	}


	private Properties(String propertyRef, Object defaultValue) {
		this.propertyRef = propertyRef;
		this.defaultValue = defaultValue;
	}


	public String getPropertyRef() {
		return propertyRef;
	}


	public Object getDefaultValue() {
		return defaultValue;
	}
}
