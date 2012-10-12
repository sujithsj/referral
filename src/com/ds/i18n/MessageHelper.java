package com.ds.i18n;

import com.ds.core.web.PropertyResolving;
import com.ds.impl.service.admin.AdminServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author adlakha.vaibhav
 */

/**
 * Message Helper is the single go-to point for all your i18N needs. It forces default messages that are used when no
 * message source has been configured. Specifying null for the locale (or any method that doesn't have a locale) will
 * give you the system's default locale (which under Spring means the non-localized message.properties file).
 *
 * @author adlakha.vaibhav
 */
@Service
public class MessageHelper implements PropertyResolving {

  private static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

  private static MessageSource messageSource;

  /**
   * The message source is meant to be populated by Spring during app context initialization.
   *
   * @param messageSource the message source to use.
   */
  public static void setMessageSource(MessageSource messageSource) {
    MessageHelper.messageSource = messageSource;
  }

  /**
   * The message source is meant to be populated by Spring during app context initialization.
   *
   * @return the message source to use.
   */
  public static MessageSource getMessageSource() {
    return messageSource;
  }

  /**
   * Gets the localized message for a message key using the system locale. This is only to be used for obtaining log
   * messages.
   *
   * @param messageKey the name of the message (in the i18n properties files) that you want localized.
   */
  public static String getMessage(String messageKey) {
    return getMessage(messageKey, null, null, getUserLocale());
  }

  /**
   * Gets the localized message for a message key using the system locale. This is only to be used for obtaining log
   * messages. This method is specifically for the case where you want to see if there is a message, and if there is
   * not, you don't want it to be logged as a warning.
   *
   * @param messageKey    the name of the message (in the i18n properties files) that you want localized.
   * @param ignoreUnknown whether or not to log an unfound message as a warning.
   * @return the message or null if it is not found.
   */
  public static String getMessage(String messageKey, boolean ignoreUnknown) {
    return getMessage(messageKey, null, null, getUserLocale(), ignoreUnknown);
  }

  /**
   * Gets the localized message for a message key, some params for substitution, using the system locale. This is only
   * to be used for obtaining log messages.
   *
   * @param messageKey the name of the message (in the i18n properties files) that you want localized.
   */
  public static String getMessage(String messageKey, Object[] args) {
    return getMessage(messageKey, args, null, getUserLocale());
  }

  /**
   * Gets the localized message for a message key and a specific locale.
   *
   * @param messageKey the name of the message (in the i18n properties files) that you want localized.
   * @param locale     the locale to localize to
   */
  public static String getMessage(String messageKey, Locale locale) {
    return getMessage(messageKey, null, null, locale);
  }

  /**
   * Gets the localized message for a message key, some params for substitution, and a specific locale.
   *
   * @param messageKey the name of the message (in the i18n properties files) that you want localized.
   * @param args       the substitution params, which may be null or zero-length
   * @param locale     the locale to localize to
   */
  public static String getMessage(String messageKey, Object[] args, Locale locale) {
    return getMessage(messageKey, args, null, locale);
  }

  /**
   * Gets the localized message for a message key, some params for substitution, a default message, and a specific
   * locale.
   *
   * @param messageKey     the name of the message (in the i18n properties files) that you want localized.
   * @param args           the substitution params, which may be null or zero-length
   * @param defaultMessage the default message to use if none can be found
   * @param locale         the locale to localize to
   */
  public static String getMessage(String messageKey, Object[] args, String defaultMessage, Locale locale) {
    return getMessage(messageKey, args, defaultMessage, locale, false);
  }

  /**
   * Gets the localized message for a message key, some params for substitution, a default message, and a specific
   * locale.
   *
   * @param messageKey     the name of the message (in the i18n properties files) that you want localized.
   * @param args           the substitution params, which may be null or zero-length
   * @param defaultMessage the default message to use if none can be found
   * @param locale         the locale to localize to
   * @param ignoreUnknown  if true, will not log.warn when the label cannot be found. Allows the Metadata processing to
   *                       not cloy up the log
   * @return
   */
  public static String getMessage(String messageKey, Object[] args, String defaultMessage, Locale locale, boolean ignoreUnknown) {

    if (messageSource == null) {
      logger.warn("The MessageSource is null. This is normal in Unit testing but a problem if running in Production.");
      StringBuffer buffer = null;
      if (defaultMessage != null) {
        if (args != null && args.length > 0) {
          buffer = new StringBuffer(". Message Substitutions:\n");
          for (int i = 0; i < args.length; i++) {
            buffer.append(args[i]).append('\n');
          }
        }
        if (buffer == null) {
          return defaultMessage;
        } else {
          return defaultMessage = buffer.toString();
        }
      }

      // Return the key and the substitutions.
      StringBuffer buf = new StringBuffer("Localized message '" + messageKey + "'");
      if (args != null && args.length > 0) {
        buf.append(" with substitutions: ");
        for (int i = 0; i < args.length; i++) {
          buf.append("'" + args[i] + "'");

          if (i < args.length - 1)
            buf.append(", ");
        }
      }

      return buf.toString();
    }

    // We want to trace messages that may not be found in the message
    // bundle.
    String retVal;
    try {
      retVal = messageSource.getMessage(messageKey, args, defaultMessage, locale);
    } catch (RuntimeException e) {
      logger.error("Error getting message", e);
      retVal = "";
    }
    if (!ignoreUnknown && StringUtils.isBlank(retVal)) {
      StringBuffer buffer = new StringBuffer(100);
      buffer.append("The following i18n MessageKey returned a blank message:");
      buffer.append("\nMessageKey: ").append(messageKey);
      buffer.append("\nDefaultMessage: ").append(defaultMessage);
      buffer.append("\nLocale:").append(locale);
      buffer.append("\nSubstitutions: ");
      logger.warn(printArgs(args, buffer).toString());
      //
      // Return something, otherwise there will be no error message at all
      // on the UI.
      //
      retVal = messageKey;
    }

    return retVal;
  }

  private static StringBuffer printArgs(Object[] args, StringBuffer buf) {
    if (args != null && args.length > 0) {

      for (int i = 0; i < args.length; i++) {
        buf.append("'" + args[i] + "'");

        if (i < args.length - 1)
          buf.append(", ");
      }
    }
    return buf;
  }

  /*
  * (non-Javadoc)
  *
  * @see com.cc.wmx.ui.jsf.PropertyResolving#getPropertyValue(java.lang.Object) Example:
  *      "#{msgHelper['what.message.key.you.want.here']}" or if no periods are used:
  *      "#{msgHelper.what_message_key_you_want_here]}"
  */
  public Object getPropertyValue(Object property) {
    return getMessage((String) property, getUserLocale());
  }

  private static Locale getUserLocale() {
    Locale loc = Locale.getDefault();
    return loc;
  }
}