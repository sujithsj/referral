package com.ds.utils;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Modelled after Spring's Collection Utils. The only reason I am creating a clone is because there is no guarantee that
 * the spring class would not be changed in another release. The spring class is actually an equivalent of a internal
 * class.
 *
 * @author adlakha.vaibhav
 */
public class GeneralUtils {
  /**
   * Return <code>false</code> if the supplied <code>Collection</code> is null or empty. Otherwise, return
   * <code>true</code>.
   *
   * @param collection the <code>Collection</code> to check
   */
  public static boolean isNotNullOrEmpty(Collection collection) {
    return (!(collection == null || collection.isEmpty()));
  }

  /**
   * Return <code>false</code> if the supplied <code>Map</code> is null or empty. Otherwise, return <code>true</code>.
   *
   * @param map the <code>Map</code> to check
   */
  public static boolean isNotNullOrEmpty(Map map) {
    return (!(map == null || map.isEmpty()));
  }

  /**
   * Factory method for jibx. There are cases where type element interferes with marshalling. For example Flex Field
   * has a set, which is created as a PersistentSet by Hibernate, if we map this with type=HashSet, jibx will always
   * expect a HashSet and will bomb with ClassCastException in marshalling. We do not need this method if the mapping
   * is only for unmarshalling.
   *
   * @return set
   */
  public static Set hashSetFactory() {
    return new HashSet();
  }

  /**
   * Handy reflection based toString method, can do substrings (if needed) or could ignore proxies (for hibernate) and
   * lots of other such things, add as you need them.
   *
   * @param object
   * @return
   */
  public static String handyToString(Object object) {
    // TODO add the ToStringStyle subclass that would do all you promise in the javadoc
    return ReflectionToStringBuilder.toString(object);

  }

  public static String limitLength(String content, int length, boolean appendMoreIndicator) {
    if (content.length() > length) {
      content = content.substring(0, length);
      if (appendMoreIndicator) {
        return content + " ...";
      }
      return content;
    }
    return content;
  }

  public static String getSEOTitle(String title) {
    String seo = limitLength(title, 45, false);
    return seo.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\b\\s{2,}\\b", " ").trim().replaceAll(" ", "-").toLowerCase();
  }

  static public Date parseToDate(String value, List<DateFormat> allowableDateFormats) throws ParseException {
    for (DateFormat dateFormat : allowableDateFormats) {
      try {
        return dateFormat.parse(value);
      } catch (ParseException e) {
      }
    }

    throw new ParseException("Unable to parse String " + value + " into a Date", 0);
  }

  public static Date parseToDate(String value) throws ParseException {
    List<DateFormat> dateFormats = new ArrayList<DateFormat>();

    // Figure out what time zone to use when the user doesn't give us a
    // time. We'll assume midnight in whatever
    // time zone we have.
    TimeZone noTimeTimeZone = getNoTimeTimeZone(null);

    dateFormats.add(createDateFormat("yyyy-MM-dd", noTimeTimeZone));

    return parseToDate(value, dateFormats);
  }

  static public Date parseToDate(String value, final DateFormat allowableDateFormat) throws ParseException {
    return parseToDate(value, new ArrayList<DateFormat>() {
      {
        add(allowableDateFormat);
      }
    });
  }

  static public String dateAsString(Date date) {
    return dateAsString(date, "yyyy-MM-dd", TimeZone.getDefault());
  }

  static public String dateAsString(Date date, String dateFormat, TimeZone timeZone) {
    if (date == null)
      return null;

    if (timeZone == null) {
      timeZone = TimeZone.getDefault();
    }

    DateFormat format = null;

    // If the time is midnight in whatever time-zone we consider the "no
    // time included with the date time zone",
    // then we should display only the date. Otherwise, we show the date and
    // the time.

    TimeZone noTimeTimeZone = getNoTimeTimeZone(timeZone);
    Calendar calender = noTimeTimeZone == null ? new GregorianCalendar() : new GregorianCalendar(noTimeTimeZone);
    calender.setTime(date);

    if (calender.get(Calendar.HOUR_OF_DAY) == 0 && calender.get(Calendar.MINUTE) == 0 && calender.get(Calendar.SECOND) == 0) {
      format = createDateFormat(dateFormat, getNoTimeTimeZone(timeZone));
    } else {
      format = createDateFormat(dateFormat + " HH:mm:ss", timeZone);
    }

    return format.format(date);
  }

  private static TimeZone getNoTimeTimeZone(TimeZone userTimeZone) {
    TimeZone noTimeTimeZone = TimeZone.getDefault();

    return noTimeTimeZone;
  }

  public static final DateFormat createDateFormat(String dateFormat, TimeZone timeZone) {
    DateFormat simpleUserDateFormat = new SimpleDateFormat(dateFormat);
    /*
    * Never allow a lenient date format. It may assume your date 31/05/2006 is the 31st month of the year, I kid
    * you not.
    */
    simpleUserDateFormat.setLenient(false);
    /*
    * Check for null time zone
    */
    if (timeZone == null) {
      timeZone = TimeZone.getDefault();
    }
    simpleUserDateFormat.setTimeZone(timeZone);
    return simpleUserDateFormat;
  }

  public static final String stringifyDate(Date date) {
    if (date == null)
      return "";

    Calendar cal = Calendar.getInstance();
    long diff = Math.abs((cal.getTimeInMillis() - date.getTime()) / 1000);
    if (diff > 0) {
      long d = diff / (24 * 60 * 60);
      if (d > 0) {
        long y = (d > 365 ? d / 365 : 0);
        String r = "";
        if (y > 0) {
          r = (y + " year" + (y == 1 ? "" : "s"));
          d %= 365;
        }
        return (r + (d > 0 ? (r.isEmpty() ? "" : " & ") + d + " day" + (d == 1 ? "" : "s") : "") + " ago");
      } else {
        long h = diff / (60 * 60);
        if (h > 0) {
          return (h + " hour" + (h == 1 ? "" : "s") + " ago");
        } else {
          long m = diff / 60;
          return (m > 0 ? (m + " minute" + (m == 1 ? "" : "s") + " ago") : "less than a minute ago");
        }
      }
    } else {
      return "less than a minute ago";
    }
  }

  public static String getRandomAlphaNumericString(int length, int... alphaLen) {

    int alphaLength = (alphaLen.length > 0 ? alphaLen[0] : length);
    int numLen = length - alphaLength;

    char alpahbetArr[] = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    int randomNum = (int) Math.floor(Math.random() * Math.pow(10, numLen));
    StringBuffer result = new StringBuffer();

    for (int i = 0; i < alphaLength; i++) {
      result.append(alpahbetArr[doRawRandomNumber(1, 25)]);
    }

    if (randomNum > 0) {
      result.append(Integer.valueOf(randomNum).toString());
    }

    return result.toString();
  }

  public static int doRawRandomNumber(int min, int max) {
    int rawRandomNumber;
    rawRandomNumber = (int) (Math.random() * (max - min + 1)) + min;

    return rawRandomNumber;
  }

  public static String getRefererURL(HttpServletRequest request) {
    return getHeaderElementFromReq(request, "referer");
  }

  @SuppressWarnings("unchecked")
  public static String getHeaderElementFromReq(HttpServletRequest request, String elementName) {
    Enumeration headers = request.getHeaders(elementName);
    if (headers != null && headers.hasMoreElements()) {
      return (String) headers.nextElement();
    }
    return null;
  }
}
