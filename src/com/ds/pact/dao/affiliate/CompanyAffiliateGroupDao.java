package com.ds.pact.dao.affiliate;

import com.ds.pact.dao.BaseDao;
import com.ds.domain.company.Company;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.affiliate.CompanyAffiliateGroup;
import com.ds.domain.user.User;
import com.ds.domain.user.UserLoginConfirmationRequest;
import com.ds.domain.user.UserSettings;
import com.ds.domain.core.Plan;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Dec 1, 2012
 * Time: 11:02:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyAffiliateGroupDao extends BaseDao {

	public CompanyAffiliateGroup saveCompanyAffiliateGroup(CompanyAffiliateGroup companyAffiliateGroup);

}

