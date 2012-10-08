package com.ds.pact.dao;

import com.ds.domain.company.Company;

import javax.xml.registry.infomodel.User;

/**
 * @author adlakha.vaibhav
 */
public interface AdminDAO {

/**
     * Saves a new Company Record
     *
     * @param company
     */
    public void saveCompany(Company company);

    /**
     * Checks whether shortName is already taken by some company
     *
     * @param shortName
     * @return Boolean whether already booked or available
     */
    public boolean isCompanyShortNameTaken(String shortName);

    /**
     * Checks if the user id is already taken or not. Our user ids are going to be emailId only.
     *
     * @param userEmailId
     * @return
     */
    public boolean isUserIdTaken(String userEmailId);

    /**
     * Finds the company corresponding to the companyShortName
     *
     * @return company
     */
    public Company getCompany(String companyShortName);

    /**
     * Find a user based on emailId.
     *
     * @return user
     */
    public User getUser(String emailId);
  
}
