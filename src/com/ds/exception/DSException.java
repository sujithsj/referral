package com.ds.exception;

import com.ds.i18n.I18NMessage;
import com.ds.i18n.MessageHelper;

import java.util.Locale;

/**
 * @author adlakha.vaibhav
 */
public class DSException extends RuntimeException {
    public I18NMessage i18nMessage;

    public DSException(String key, Exception ex, Object... messageParams) {
        super(MessageHelper.getMessage(key, messageParams, Locale.ENGLISH), ex);
        this.i18nMessage = new I18NMessage(key, messageParams);
    }

    public DSException(String key, Object... messageParams) {
        super(MessageHelper.getMessage(key, messageParams, Locale.ENGLISH));
        this.i18nMessage = new I18NMessage(key, messageParams);
    }

    /**
     * @return the i18nMessage
     */
    public I18NMessage getI18nMessage() {
        return i18nMessage;
    }

}
