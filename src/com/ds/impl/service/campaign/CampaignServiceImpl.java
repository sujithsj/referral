package com.ds.impl.service.campaign;

import com.ds.constants.EnumCampaignType;
import com.ds.constants.EnumMarketingMaterialType;
import com.ds.domain.campaign.Campaign;
import com.ds.domain.campaign.CampaignType;
import com.ds.domain.commission.CommissionPlan;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.domain.marketing.MarketingMaterialType;
import com.ds.dto.campaign.CampaignDTO;
import com.ds.dto.commission.CommissionPlanDTO;
import com.ds.exception.InvalidParameterException;
import com.ds.exception.ValidationException;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.campaign.CampaignService;
import com.ds.pact.service.commission.CommissionService;
import com.ds.pact.service.core.SearchService;
import com.ds.pact.service.marketing.MarketingService;
import com.ds.search.impl.CampaignQuery;
import com.ds.web.action.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang.StringUtils;


import java.util.Date;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
@Service
public class CampaignServiceImpl implements CampaignService {

  @Autowired
  private SearchService searchService;
  @Autowired
  private CommissionService commissionService;
  @Autowired
  private MarketingService marketingService;

  @Autowired
  private BaseDao baseDao;


  @Override
  public Campaign getCampaignById(Long campaignId) {
    if (campaignId == null) {
      throw new InvalidParameterException("CAMPAIGN_ID_CANNOT_BE_BLANK");
    }
    return (Campaign) getBaseDao().findUniqueByNamedQueryAndNamedParam("getCampaignById", new String[]{"campaignId"}, new Object[]{campaignId});
  }


  @Override
  public Page searchCampaign(String name, String companyShortName, Date startDate, Date endDate, Long campaignTypeId, boolean active, int pageNo, int perPage) {
    if (EnumCampaignType.ALL.getId().equals(campaignTypeId)) {
      campaignTypeId = null;
    }
    CampaignQuery campaignQuery = new CampaignQuery();
    campaignQuery.setCompanyShortName(companyShortName);
    campaignQuery.setName(name).setStartDate(startDate).setEndDate(endDate).setCampaignType(EnumCampaignType.getById(campaignTypeId));

    campaignQuery.setOrderByField("nm").setPageNo(pageNo).setRows(perPage);

    return getSearchService().list(campaignQuery);
  }

  @Override
  @Transactional
  public Campaign createCampaign(CampaignDTO campaignDTO, CommissionPlanDTO commissionPlanDTO, String companyShortName) {
    Campaign campaign = new Campaign();
    campaign.setCompanyShortName(companyShortName);
    return persistCampaign(campaign, campaignDTO, commissionPlanDTO);
  }

  @Override
  @Transactional
  public Campaign updateCampaign(Long campaignId, CommissionPlanDTO commissionPlanDTO, CampaignDTO campaignDTO) {
    if (campaignId == null) {
      throw new InvalidParameterException("CAMPAIGN_ID_CANNOT_BE_BLANK");
    }

    Campaign campaign = getCampaignById(campaignId);

    if (campaign == null) {
      throw new InvalidParameterException("NO_CAMPAIGN_EXISTS_TO_UPDATE");
    }

    return persistCampaign(campaign, campaignDTO, commissionPlanDTO);
  }


  @Transactional
  private Campaign persistCampaign(Campaign campaign, CampaignDTO campaignDTO, CommissionPlanDTO commissionPlanDTO) {
    if (campaignDTO.getCampaignTypeId() == null) {
      throw new InvalidParameterException("CAMPAIGN_TYPE_CANNOT_BE_NULL");
    }
    campaignDTO.syncToCampaign(campaign);

    if (campaign.getStartDate() != null && campaign.getEndDate() != null && campaign.getEndDate().compareTo(campaign.getStartDate()) == -1) {
      throw new ValidationException("endDate", "End Date cannot be less than start date");
    }
    /**
     * TODO: lot of validations need to be done based on type of plan and startegry and currecny
     */
    if (campaign.isPrivate() == null) {
      campaign.setPrivate(false);
    }
    if (campaign.isActive() == null || (campaign.getStartDate() != null && campaign.getStartDate().compareTo(new Date()) == 1)) {
      campaign.setActive(true);
    }
    if (campaign.isDeleted() == null) {
      campaign.setDeleted(false);
    }

    CommissionPlan commissionPlan = null;
    if (campaign.getId() == null) {
      commissionPlan = getCommissionService().createCommissionPlan(commissionPlanDTO, campaign.getCompanyShortName());
    } else {
      commissionPlan = getCommissionService().updateCommissionPlan(campaign.getCommissionPlan().getId(), commissionPlanDTO);
    }

    if (commissionPlan == null) {
      throw new ValidationException("commisiionPlan", "no commission plan is associated with campaign");
    }
    CampaignType campaignType = getCampaignTypeById(campaignDTO.getCampaignTypeId());
    campaign.setCampaignType(campaignType);
    campaign.setCommissionPlan(commissionPlan);

    campaign = (Campaign) getBaseDao().save(campaign);

    if (EnumCampaignType.SOCIAL_REFERRAL.getId().equals(campaignDTO.getCampaignTypeId())) {
      if (StringUtils.isBlank(campaignDTO.getLandingPage())) {
        throw new InvalidParameterException("LANDING_PAGE_NULL_FOR_SOCIAL_CAMPAIGN");
      }

      MarketingMaterial marketingMaterial = new MarketingMaterial();
      MarketingMaterialType marketingMaterialType = EnumMarketingMaterialType.getById(EnumMarketingMaterialType.ReferalAd.getId()).asMarketingMaterialType();

      marketingMaterial.setTitle(campaign.getName());
      marketingMaterial.setCampaign(campaign);
      marketingMaterial.setMarketingMaterialType(marketingMaterialType);
      marketingMaterial.setCompanyShortName(campaign.getCompanyShortName());
      marketingMaterial.setLandingPageUrl(campaignDTO.getLandingPage());


      getMarketingService().saveMarketingMaterial(marketingMaterial);
    }

    return campaign;
  }


  private CampaignType getCampaignTypeById(Long campaignTypeId) {
    if (campaignTypeId == null) {
      throw new InvalidParameterException("CAMPAIGN_TYPE_ID_CANNOT_BE_BLANK");
    }
    return (CampaignType) getBaseDao().findUniqueByNamedQueryAndNamedParam("getCampaignTypeById", new String[]{"campaignTypeId"}, new Object[]{campaignTypeId});
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Campaign> getAllCampaigns(String companyShortName) {
    return getBaseDao().findByNamedQueryAndNamedParam("getAllCampaignsForCompany", new String[]{"companyShortName"}, new Object[]{companyShortName});
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Campaign> getAllCampaignsEligibleForAd(String companyShortName) {
    return getBaseDao().findByNamedQueryAndNamedParam("getAllCampaignsEligibleForAdForCompany", new String[]{"companyShortName"}, new Object[]{companyShortName});
  }

  @Override
  public Page getCampaignsVisibleToAffiliate(String companyShortName, int pageNo, int perPage) {
    CampaignQuery campaignQuery = new CampaignQuery();
    campaignQuery.setCompanyShortName(companyShortName).setQueryForAffiliate(true).setPrivate(false).setActive(true);
    campaignQuery.setOrderByField("nm").setPageNo(pageNo).setRows(perPage);
    return getSearchService().list(campaignQuery);
  }

  public SearchService getSearchService() {
    return searchService;
  }

  public BaseDao getBaseDao() {
    return baseDao;
  }

  public CommissionService getCommissionService() {
    return commissionService;
  }

  public MarketingService getMarketingService() {
    return marketingService;
  }
}
