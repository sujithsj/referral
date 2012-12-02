package com.ds.impl.service.affiliate;

import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.affiliate.CompanyAffiliateGroup;
import com.ds.dto.affiliate.CompanyAffiliateGroupDTO;
import com.ds.pact.dao.affiliate.CompanyAffiliateGroupDao;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.pact.service.affiliate.CompanyAffiliateGroupService;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
import com.ds.pact.service.core.SearchService;
import com.ds.pact.service.mail.MailService;
import com.ds.search.impl.CompanyAffiliateGroupQuery;
import com.ds.web.action.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Dec 1, 2012
 * Time: 10:54:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CompanyAffiliateGroupServiceImpl implements CompanyAffiliateGroupService {

	private Logger logger = LoggerFactory.getLogger(CompanyAffiliateGroupServiceImpl.class);

	@Autowired
	private MailService mailService;
	@Autowired
	private SearchService searchService;
	@Autowired
	private AffiliateService affiliateService;
	@Autowired
	private CompanyAffiliateService companyAffiliateService;

	@Autowired
	private CompanyAffiliateGroupDao companyAffiliateGroupDao;

	@Override
	public CompanyAffiliateGroup saveCompanyAffiliateGroup(CompanyAffiliateGroup companyAffiliateGroup) {
		return getCompanyAffiliateGroupDao().saveCompanyAffiliateGroup(companyAffiliateGroup);
	}

	@Override
	public CompanyAffiliateGroup getCompanyAffiliateGroup(Long companyAffiliateGroupId) {
		CompanyAffiliateGroup companyAffiliateGroup = (CompanyAffiliateGroup) getCompanyAffiliateGroupDao().load(CompanyAffiliateGroup.class, companyAffiliateGroupId);
		return companyAffiliateGroup;
	}

	@Override
	public List<CompanyAffiliateGroup> getAllCompanyAffiliateGroups(String companyShortName) {
		CompanyAffiliateGroupQuery companyAffiliateGroupQuery = new CompanyAffiliateGroupQuery();
		companyAffiliateGroupQuery.setCompanyShortName(companyShortName);
		return getSearchService().executeSearch(companyAffiliateGroupQuery);
	}

	@Transactional
	@Override
	public CompanyAffiliateGroup createOrUpdateCompanyAffiliateGroup(CompanyAffiliateGroupDTO companyAffiliateGroupDTO, String companyShortName, List<Long> assignedAffiliates) {

		if (companyAffiliateGroupDTO == null) {
			companyAffiliateGroupDTO = new CompanyAffiliateGroupDTO();
		}
		CompanyAffiliateGroup companyAffiliateGroup = null;
		companyAffiliateGroup = companyAffiliateGroupDTO.extractCompanyAffiliateGroup(companyAffiliateGroup);
		List<CompanyAffiliate> companyAffiliateList = new ArrayList<CompanyAffiliate>();

		if (assignedAffiliates != null && assignedAffiliates.size() > 0) {
			for (Long assignedAffiliate : assignedAffiliates) {
				companyAffiliateList.add(getCompanyAffiliateService().getCompanyAffiliate(assignedAffiliate));
			}
		}
		if (companyShortName != null) {
			companyAffiliateGroup.setCompanyShortName(companyShortName);
		}
		if (companyAffiliateGroup.getId() == null){
			companyAffiliateGroup = saveCompanyAffiliateGroup(companyAffiliateGroup);
		}
		if (companyAffiliateList.size() > 0) {
			if(companyAffiliateGroup.getCompanyAffiliates() !=  null && companyAffiliateGroup.getCompanyAffiliates().size() > 0){
				companyAffiliateGroup.getCompanyAffiliates().removeAll(companyAffiliateGroup.getCompanyAffiliates());
			}
			companyAffiliateGroup.getCompanyAffiliates().addAll(companyAffiliateList);
		}

		companyAffiliateGroup = saveCompanyAffiliateGroup(companyAffiliateGroup);
		return companyAffiliateGroup;
	}

	@Override
	public Page searchCompanyAffiliateGroup(String name, String companyShortName, int pageNo, int perPage) {
		CompanyAffiliateGroupQuery companyAffiliateGroupQuery = new CompanyAffiliateGroupQuery();
		companyAffiliateGroupQuery.setName(name).setCompanyShortName(companyShortName).setPageNo(pageNo).setRows(perPage);
		return getSearchService().list(companyAffiliateGroupQuery);
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public AffiliateService getAffiliateService() {
		return affiliateService;
	}

	public void setAffiliateService(AffiliateService affiliateService) {
		this.affiliateService = affiliateService;
	}

	public CompanyAffiliateGroupDao getCompanyAffiliateGroupDao() {
		return companyAffiliateGroupDao;
	}

	public void setCompanyAffiliateGroupDao(CompanyAffiliateGroupDao companyAffiliateGroupDao) {
		this.companyAffiliateGroupDao = companyAffiliateGroupDao;
	}

	public CompanyAffiliateService getCompanyAffiliateService() {
		return companyAffiliateService;
	}

	public void setCompanyAffiliateService(CompanyAffiliateService companyAffiliateService) {
		this.companyAffiliateService = companyAffiliateService;
	}
}
