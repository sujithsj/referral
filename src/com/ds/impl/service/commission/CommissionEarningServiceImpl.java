package com.ds.impl.service.commission;

import com.ds.constants.EnumCampaignType;
import com.ds.constants.EnumCommissionEarningStatus;
import com.ds.domain.commission.CommissionEarning;
import com.ds.domain.commission.CommissionEarningStatus;
import com.ds.exception.InvalidParameterException;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.commission.CommissionEarningService;
import com.ds.pact.service.core.SearchService;
import com.ds.search.impl.CommissionEarningQuery;
import com.ds.web.action.Page;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
@Service
public class CommissionEarningServiceImpl implements CommissionEarningService {

  @Autowired
  private SearchService searchService;

  @Autowired
  private BaseDao baseDao;


  @Override
  @Transactional
  public CommissionEarning saveCommissionEarning(CommissionEarning commissionEarning) {
    commissionEarning = (CommissionEarning) getBaseDao().save(commissionEarning);
    //TODO: add notification, send email;

    return commissionEarning;
  }

  @Override
  public Page searchCommissionEarning(Long affiliateId, String companyShortName, Date startDate, Date endDate, Long commissionEarningStatusId, String customer, int pageNo, int perPage) {

    if (EnumCampaignType.ALL.getId().equals(commissionEarningStatusId)) {
      commissionEarningStatusId = null;
    }
    CommissionEarningQuery commissionEarningQuery = new CommissionEarningQuery();
    commissionEarningQuery.setCompanyShortName(companyShortName);
    commissionEarningQuery.setAffiliateId(affiliateId).setStartDate(startDate).setEndDate(endDate).setCommissionEarningStatusId(commissionEarningStatusId).setCustomer(customer);

    commissionEarningQuery.setOrderByField("ed").setPageNo(pageNo).setRows(perPage);

    return getSearchService().list(commissionEarningQuery);

  }

  @Transactional
  public void changeCommissionEarningStatus(Long commissionEarningId, Long newStatusId) {
    //TODO: add acted by and acted on
    if (commissionEarningId == null || newStatusId == null) {
      throw new InvalidParameterException("NULL_CE_ID");
    }
    getBaseDao().executeNativeSql("update commission_earning set commission_earning_status_id = ? where id = ?", newStatusId, commissionEarningId);
  }


  @Transactional
  public void bulkChangeCommissionEarningStatus(List<Long> earningIds, Long newStatusId) {
    //TODO: add acted by and acted on
    if (earningIds == null || newStatusId == null) {
      throw new InvalidParameterException("NULL_CE_IDS");
    }

    CommissionEarningStatus commissionEarningStatus = EnumCommissionEarningStatus.getById(newStatusId).asCommissionEarningStatus();

    Query query = getBaseDao().createQuery("update CommissionEarning set commissionEarningStatus = :newStatus where id in (:earningIds)");

    query.setParameter("newStatus", commissionEarningStatus);
    query.setParameterList("earningIds", earningIds);

    query.executeUpdate();
  }

  public SearchService getSearchService() {
    return searchService;
  }

  public BaseDao getBaseDao() {
    return baseDao;
  }
}
