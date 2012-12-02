package com.ds.impl.dao.affiliate;

import com.ds.impl.dao.BaseDaoImpl;
import com.ds.pact.dao.affiliate.CompanyAffiliateDao;
import com.ds.pact.dao.affiliate.CompanyAffiliateGroupDao;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.affiliate.CompanyAffiliateGroup;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Dec 2, 2012
 * Time: 12:22:05 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CompanyAffiliateGroupDaoImpl extends BaseDaoImpl implements CompanyAffiliateGroupDao {

	@Override
	public CompanyAffiliateGroup saveCompanyAffiliateGroup(CompanyAffiliateGroup companyAffiliateGroup) {
		return (CompanyAffiliateGroup) save(companyAffiliateGroup);
	}
}
