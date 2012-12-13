package com.ds.web.locale;

/**
 * @author adlakha.vaibhav
 */
public class AffiliateLocaleContextHolder {

private static final ThreadLocal<AffiliateLocaleContext> localeContextHolder = new ThreadLocal<AffiliateLocaleContext>();

    /**
     * Reset the LocaleContext for the current thread.
     */
    public static void resetLocaleContext() {
        localeContextHolder.remove();
    }

    /**
     * Associate the given LocaleContext with the current thread.
     *
     * @param localeContext the current LocaleContext, or <code>null</code> to reset the thread-bound context
     */
    public static void setAffiliateLocaleContext(AffiliateLocaleContext localeContext) {
        localeContextHolder.set(localeContext);
    }

    /**
     * Return the LocaleContext associated with the current thread, if any.
     *
     * @return the current LocaleContext, or <code>null</code> if none
     */
    public static AffiliateLocaleContext getAffiliateLocaleContext() {
      AffiliateLocaleContext affiliateLocaleContext =   (AffiliateLocaleContext) localeContextHolder.get();

      if(affiliateLocaleContext ==null){
        affiliateLocaleContext = new AffiliateLocaleContext();
      }

      return affiliateLocaleContext;
    }

    
}
