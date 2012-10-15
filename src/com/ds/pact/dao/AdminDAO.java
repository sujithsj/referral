package com.ds.pact.dao;

import com.ds.domain.company.Company;
import com.ds.domain.core.Plan;
import com.ds.domain.user.User;
import com.ds.domain.user.UserLoginConfirmationRequest;
import com.ds.domain.user.UserSettings;

import java.util.List;


/**
 * @author adlakha.vaibhav
 */
public interface AdminDAO extends BaseDao{

  /**
      * Saves a new Company Record
      *
      * @param company
      */
     public Company saveCompany(Company company);

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

     /**//**
      * Creates a IssueTracker into the Userrules
      *
      * @param issueTrackerConfig
      *//*
     public void createIssueTracker(IssueTrackerConfig issueTrackerConfig);

     *//**
      * Loads an IssueTrackerConfig with given issueTrackerConfigName
      *
      * @param issueTrackerConfigName
      * @return IssueTrackerConfig
      *//*
     public IssueTrackerConfig loadIssueTrackerConfig(String issueTrackerConfigName);

     *//**
      * Updates a IssueTracker into the Userrules
      *
      * @param issueTrackerConfig
      *//*
     public void updateIssueTracker(IssueTrackerConfig issueTrackerConfig);

     *//**
      * Allows Creation of a Form
      *
      * @param form
      *//*
     public void createForm(Form form);

     *//**
      * Allows updation of a Form
      *
      * @param form
      *//*
     public void saveOrUpdateForm(Form form);*/

     /**//**
      * Loads an IssueTrackerConfig with given issueTrackerId
      *
      * @param issueTrackerId
      * @return IssueTrackerConfig
      *//*
     public IssueTrackerConfig loadIssueTrackerConfig(long issueTrackerId);

     *//**
      * Loads an IssueTrackerConfig with given issueTrackerId and company Short Name
      *
      * @param companyShortName
      * @param issueTrackerId
      * @return IssueTrackerConfig
      *//*
     public IssueTrackerConfig loadIssueTrackerConfig(String companyShortName, long issueTrackerId);

     *//**
      * Checks where Issue Tracker is configured with the given name for the company.
      *
      * @param companyShortName
      * @param issueTrackerName
      * @return true if Issue Tracker Already Exists/False Otherwise
      *//*
     public boolean existsIssueTracker(String companyShortName, String issueTrackerName);*/

    /* *//**
      * Gets All Issue Trackers configured for the companyShortName
      *
      * @param companyShortName
      * @return List Of IssueTrackers
      *//*
     public List<IssueTrackerConfig> getIssueTrackers(String companyShortName);*/

     /**
      * Checks where user exists with given username
      *
      * @param username
      * @return true if User lready exists with given username false otherwise
      */
     public boolean existsUser(String username);

     /**//**
      * Finds the form with the given Name which has been saved for IssueTracker
      *
      * @param issueTrackerConfig for which to find form
      * @param formName name of form to search
      * @return Form
      *//*
     public Form findForm(IssueTrackerConfig issueTrackerConfig, String formName);*/

     /**
      * Saves UserLoginConfirmationRequest
      *
      * @param userLoginConfirmationRequest
      */
     public void saveUserLoginConfirmationRequest(UserLoginConfirmationRequest userLoginConfirmationRequest);

     /**
      * Loads UserLoginConfirmationRequest
      *
      * 
      */
     public UserLoginConfirmationRequest loadUserLoginConfirmationRequest(long userLoginConfirmationRequestId);

     /**
      * Gets Form Field Options identified by fieldName belonging to form identified by formName and belonging to
      * mentioned IssueTrackerConfig
      *
      * @param issueTrackerConfig
      * @param formName
      * @param fieldName
      * @return List of FormFieldOption
      *//*
     public List<FormFieldOption> getFormFieldOptions(IssueTrackerConfig issueTrackerConfig, String formName, String fieldName);*/

     /**
      * Gets the UserSettings corresponding to the userName
      *
      * @return UserSettings
      */
     public UserSettings getUserSettings(String username);

     /**
      * Get all employees registered for a company.
      *
      * @param companyShortName
      * @return
      */
     public List<User> getAllEmployees(String companyShortName);

     /**
      * Get a list of all the employees added for a particular company corresponding to company short name.
      *
      * @param companyShortName
      * @param start used for pagination which should be first record
      * @param rows how many records should be returned
      * @param sortBy which property of Product should be used for sorting
      * @param sortHow the order of Sorting ASC or DESC
      * @return List of Users
      */
     public List<User> findEmployees(String companyShortName, int start, int rows, String sortBy, String sortHow);

     /**
      * Employee Count for a Company Identified by Company Short Name
      *
      * @param companyShortName
      * @return No of Employees of Company
      */
     public long employeesCount(String companyShortName);

     /**
      * Gets a Plan form DB with planName
      *
      * @param planName
      * @return Plan or null if not found
      */
     public Plan getPlan(String planName);

     /**//**
      * Get badge specified by badge name for a particular company.
      *
      * @param badgeName
      * @param companyShortName
      * @return
      *//*
     public Badge getBadgeForCompany(String badgeName, String companyShortName);*/

     /**//**
      * Gets all Vendor Companies
      *
      * @param start form where to start
      * @param rows no of vendors ot be returned
      * @return
      *//*
     public List<Company> getAllVendors(int start, int rows);

     *//**
      * @return the No of Vendors
      *//*
     public int getNoOfVendors();*/
  

}
