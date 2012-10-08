package com.ds.i18n;

/**
 * @author adlakha.vaibhav
 */
public class I18NMessage {
    private String   key;
    private String   enLocaleMessage; // This message will be in 'en' format
    private Object[] messageParams;

    public I18NMessage(String key, Object... messageParams) {
        this.key = key;
        this.messageParams = messageParams;
        this.enLocaleMessage = MessageHelper.getMessage(key, messageParams, Locale.ENGLISH);
    }

    public String getKey() {
        return key;
    }

    /**
     * @return the enLocaleMessage
     */
    public String getEnLocaleMessage() {
        return enLocaleMessage;
    }

    public Object[] getMessageParams() {
        return messageParams;
    }
}
