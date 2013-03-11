package com.ds.pact.dao.affiliate;

import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.company.Company;
import com.ds.pact.dao.BaseDao;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 22, 2012
 * Time: 12:37:54 AM
 * To change this template use File | Settings | File Templates.
 */
public interface AffiliateDao extends BaseDao {


    public Affiliate saveAffiliate(Affiliate affiliate);
    
    /**
     * Affiliates Count for a Company Identified by Company Short Name
     *
     * @param companyShortName
     * @return No of Affiliates of Company
     */
    public long affiliatesCount(String companyShortName);

    /**
     * @param login
     * @return
     */
    public Affiliate getAffiliateByLogin(String login);

	/**
	 *
	 * @param login
	 * @return
	 */
	public List<Company> getAllCompaniesForAffiliate(String login);
}