package com.ds.impl.dao.affiliate;

import com.ds.impl.dao.BaseDaoImpl;
import com.ds.pact.dao.affiliate.AffiliateDAO;
import com.ds.domain.company.Company;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.user.User;
import com.ds.domain.user.UserLoginConfirmationRequest;
import com.ds.domain.user.UserSettings;
import com.ds.domain.core.Plan;

import java.util.List;
import java.security.InvalidParameterException;

import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Nov 18, 2012
 * Time: 4:12:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyAffiliateDaoImpl extends BaseDaoImpl implements AffiliateDAO {

	@Override
	public Company saveCompany(Company company) {
		return (Company) save(company);
	}

	@Override
	public Affiliate saveAffiliate(Affiliate affiliate) {
		return (Affiliate) save(affiliate);
	}

	@Override
	public CompanyAffiliate saveAffiliateCompany(CompanyAffiliate companyAffiliate) {
		return (CompanyAffiliate) save(companyAffiliate);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String emailId) {
		List<User> results = findByQuery("from User where email=?", new Object[]{emailId});
		if (results.isEmpty()) {
			return null;
		}
		return results.get(0);
	}

	public boolean isCompanyShortNameTaken(String shortName) {
		List results = findByQuery("select 1 from Company where shortName=?", new Object[]{shortName});
		return !results.isEmpty();
	}

	public boolean isUserIdTaken(String userEmailId) {
		List results = findByQuery("select 1 from User where email=?", new Object[]{userEmailId});
		return !results.isEmpty();
	}

	@Override
	public Company getCompany(String companyShortName) {
		return get(Company.class, companyShortName);
	}

	@Override
	public boolean existsUser(String username) {
		return getByQuery("from User where username = ?", username) != null;
	}


	@Override
	public void saveUserLoginConfirmationRequest(UserLoginConfirmationRequest userLoginConfirmationRequest) {
		saveOrUpdate(userLoginConfirmationRequest);
	}

	@Override
	public UserLoginConfirmationRequest loadUserLoginConfirmationRequest(long userLoginConfirmationRequestId) {
		return get(UserLoginConfirmationRequest.class, userLoginConfirmationRequestId);
	}

	@Override
	public UserSettings getUserSettings(String username) {
		return getByQuery("from UserSettings where username = ?", username);
	}

	public List<User> getAllEmployees(String companyId) {
		List<User> results = findByQuery("select user from User user left join fetch user.roleNames where user.companyShortName = ?", new Object[]{companyId});
		if (results.isEmpty()) {
			return null;
		}
		return results;
	}

	public List<User> findEmployees(String companyId, int start, int rows, String sortBy, String sortHow) {
		StringBuilder hql = new StringBuilder("select user from User user where user.companyShortName=? ");
		if (StringUtils.isNotBlank(sortBy)) {
			hql.append(" order by ").append(sortBy).append(" ").append(getSortType(sortHow));
		}

		return find(hql.toString(), new Object[]{companyId}, start, rows);

	}

	@Override
	public long affiliatesCount(String companyShortName) {
		return count("select count(*) from User where companyShortName = ? ", companyShortName);

	}

	@Override
	public Plan getPlan(String planName) {
		return getByQuery("from Plan where name = ?", planName);
	}

	@Override
	public boolean isAffiliateLoginTaken(String login){
		List results = findByQuery("select 1 from Affiliate where login=?", new Object[] {login});
		return !results.isEmpty();

	}

	@Override
	public Affiliate getAffiliateByLogin(String login) {
		Affiliate affiliate = (Affiliate) findUnique("select aff from Affiliate aff  where aff.login = ?", new Object[]{login});

		if (affiliate == null) {
			logger.error("No such affiliate found in system: " + login);
			throw new InvalidParameterException("INVALID_AFFILIATE");
		}
		return affiliate;
	}


}
