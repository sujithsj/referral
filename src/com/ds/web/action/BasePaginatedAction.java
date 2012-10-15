package com.ds.web.action;

import net.sourceforge.stripes.action.After;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author adlakha.vaibhav
 */
@Component
public abstract class BasePaginatedAction extends BaseAction {


  protected int pageNo = 1;
  protected int perPage = Page.DEFAULT_PAGE_SIZE;

  public int getPerPageDefault() {
    return perPage;
  }

  public abstract int getPageCount();

  public abstract int getResultCount();

  public abstract Set<String> getParamSet();

  /**
   * When one is viewing sent messages and clicks on any of
   * the pagination links, the default handler is invoked.
   * <p/>
   * This parameter is being used to get around that.
   */
  private String eventParam;

  public int getPageDefault() {
    return 1;
  }

  public int getPageNo() {
    return pageNo <= 0 ? 1 : pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPerPage() {
    return perPage <= 0 ? getPerPageDefault() : perPage;
  }

  public void setPerPage(int perPage) {
    this.perPage = perPage;
  }

  public String getEventParam() {
    return eventParam;
  }

  /**
   * Setting the event name to a param so that pagination can render the even correctly
   */
  @After(stages = LifecycleStage.EventHandling)
  public void rememberEvent() {
    eventParam = getContext().getEventName();
  }

  protected int getStartIdxForPage() {
    return (getPageNo() - 1) * getPerPage();
  }

  protected int getEndIdxForPage(int maxSize) {
    return (getPageNo() * getPerPage()) >= maxSize ? maxSize : (getPageNo() * getPerPage());
  }
}

