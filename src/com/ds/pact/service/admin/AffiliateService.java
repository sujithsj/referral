package com.ds.pact.service.admin;

import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.company.Company;
import com.ds.domain.user.UserSettings;
import com.ds.exception.DSException;
import com.ds.web.action.Page;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 22, 2012
 * Time: 12:40:30 AM
 * To change this template use File | Settings | File Templates.
 */
public interface AffiliateService {


	/**
	 * Saves a new Affiliate Record
	 *
	 * @param affiliate
	 */
	public Affiliate saveAffiliate(Affiliate affiliate);


	/**
	 * Finds the company corresponding to the companyShortName
	 *
	 * @return company
	 */
	public Company getCompany(String companyShortName) throws InvalidParameterException;

	/**
	 * Gets the UserSettings corresponding to the username
	 *
	 * @return UserSettings
	 */
	public UserSettings getUserSettings(String username);

	/**
	 * Get a list of all the companies in the system.
	 *
	 * @return
	 */
	public List<Company> getAllCompanies();

	/**
	 * Update any entity, possibly a company or user in context of this service.
	 *
	 * @param entity
	 */
	public void updateEntity(Object entity);

	/**
	 * Returns a affiliate corresponding to the specified affiliateId
	 *
	 * @param affiliateId
	 * @return
	 */
	public Affiliate getAffiliate(Long affiliateId);

	/**
	 * Changes the password of user specified by userid to newPassword from oldPassword
	 *
	 * @param emailId
	 * @param oldPassword
	 * @param newPassword
	 * @return boolean true if updated sucessfully
	 */
	public boolean changePassword(String emailId, String oldPassword, String newPassword) throws DSException;


	/**
	 * Employee Count for a Company Identified by Company Short Name
	 *
	 * @param companyShortName
	 * @return No of Employees of Company
	 */
	public long affiliatesCount(String companyShortName);

	/**
	 * Encrypts the password
	 *
	 * @param username
	 * @param password
	 * @return encrypted password
	 */
	public String getEncryptedPassword(String username, String password);

	public Page searchAffiliate(String login, String email, String companyShortName, int pageNo, int perPage);


	public CompanyAffiliate saveAffiliateCompany(Affiliate affiliate, String companyShortName);

	public void sendWelcomeEmail(Affiliate affiliate);

	public boolean isEmailIdValid(String emailId);

	public boolean isAffiliateLoginTaken(String login);

	public Affiliate getAffiliateByLogin(String login);

    public Page searchAffiliateGroup(String name, String companyShortName, int pageNo, int perPage);
}
