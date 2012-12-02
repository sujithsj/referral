package com.ds.pact.service.affiliate;

import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliateGroup;
import com.ds.web.action.Page;
import com.ds.dto.affiliate.CompanyAffiliateDTO;
import com.ds.dto.affiliate.AffiliateDTO;
import com.ds.dto.affiliate.CompanyAffiliateGroupDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Dec 1, 2012
 * Time: 10:48:08 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyAffiliateGroupService {



	public CompanyAffiliateGroup saveCompanyAffiliateGroup(CompanyAffiliateGroup companyAffiliateGroup);

	public CompanyAffiliateGroup getCompanyAffiliateGroup(Long companyAffiliateGroupId);

	public List<CompanyAffiliateGroup> getAllCompanyAffiliateGroups (String companyShortName);

	public CompanyAffiliateGroup createOrUpdateCompanyAffiliateGroup(CompanyAffiliateGroupDTO companyAffiliateGroupDTO, String companyShortName, List<Long> assignedAffiliates);

	public Page searchCompanyAffiliateGroup(String name, String companyShortName, int pageNo, int perPage);



}
