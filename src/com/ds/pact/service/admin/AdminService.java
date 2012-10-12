package com.ds.pact.service.admin;

import com.ds.domain.company.Company;
import com.ds.domain.core.Role;
import com.ds.domain.user.User;
import com.ds.domain.user.UserLoginConfirmationRequest;
import com.ds.domain.user.UserSettings;
import com.ds.domain.visitor.VisitorInfo;
import com.ds.exception.DSException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidParameterException;
import java.util.List;


/**
 * @author adlakha.vaibhav
 */
public interface AdminService {

  /**
   * Register a new company in the system only if it passes all business validations
   *
   * @param company
   * @param user
   */
  public void registerCompany(Company company, User user, Object[] recaptchaParams);

  /**
   * Register a new company in the system only if it passes all business validations
   *
   * @param company
   */
  public void registerCompany(Company company);

  /**
   * Gets all Vendor Companies
   *
   * @param start form where to start
   * @param rows  no of vendors ot be returned
   * @return
   */
  /*public List<Company> getAllVendors(int start, int rows);*/

  /**
   * @return the No of Vendors
   */
  /*public int getNoOfVendors();*/

  /**
   * Register a new employee for the company in the system
   *
   * @param companyShortName
   * @param user
   */
  public void registerEmployee(String companyShortName, User user);

  /**
   * Checks whether shortName is already taken by some company
   *
   * @param shortName
   * @return Boolean whether already booked or available
   */
  public boolean isCompanyShortNameTaken(String shortName);

  /**
   * Checks if the url provided for registration is well formed or not
   *
   * @param url
   * @return Boolean whether the URL given in well formed or not
   */
  public boolean isCompanyURLWellFormed(String url);

  /**
   * The short name and URL need to have some similarity. Like for http://42squares.com 42squares is a valid short
   * name but google is not.
   *
   * @param shortName
   * @param url
   * @return Boolean
   */
  public boolean isCompanyShortNameAndURLRelated(String shortName, String url);

  /**
   * Checks if the user id is already taken or not. Our user ids are going to be emailId only.
   *
   * @param userEmailId
   * @return
   */
  public boolean isUserIdTaken(String userEmailId);

  /**
   * Checks if the user id/emailId(in our case) is a valid emailID or not.
   *
   * @param userEmailId
   * @return
   */
  public boolean isUserIdValid(String userEmailId);

  /**
   * Verifies ownership of company domain. Basically it goes to a URL and downloads a file and then checks it content
   * to verify that it satisfies what was required. If it matches then it activates the company, otherwise it returns
   * false
   *
   * @param companyShortName
   * @return true, if it has been verified, false otherwise
   */
  public boolean verifyCompanyActivation(String companyShortName);

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
   * Add a user for a company.
   *
   * @return
   */
  public void addUser(User user, Role.RoleType[] roleRoleTypes);

  /**
   * Updates the User
   *
   * @param user
   */
  public void updateUser(User user);

  /**
   * Update any entity, possibly a company or user in context of this service.
   *
   * @param entity
   */
  public void updateEntity(Object entity);

  /**
   * Returns a user corresponding to the specified userId
   *
   * @param userId
   * @return
   */
  public User getUser(String userId);

  /* *//**
   * Creates a IssueTracker into the Userrules
   *
   * @param issueTrackerConfig
   *//*
      public void createIssueTracker(IssueTrackerConfig issueTrackerConfig);

      *//**
   * Allows Reconfiguring an IssueTracker so that it can fetch/update details like Issue Create Screen from the host
   * system.
   *
   * @param issueTrackerConfig
   *//*
      public void reconfigure(IssueTrackerConfig issueTrackerConfig);

      *//**
   * Loads an IssueTrackerConfig with given issueTrackerConfigName
   *
   * @param issueTrackerConfigName
   * @return IssueTrackerConfig
   *//*
      public IssueTrackerConfig loadIssueTrackerConfig(String issueTrackerConfigName);

      *//**
   * Loads an IssueTrackerConfig with given issueTrackerId and company Short Name
   *
   * @param companyShortName
   * @param issueTrackerId
   * @return IssueTrackerConfig
   *//*
      public IssueTrackerConfig loadIssueTrackerConfig(String companyShortName, long issueTrackerId);

      *//**
   * Loads an IssueTrackerConfig with given issueTrackerId
   *
   * @param issueTrackerId
   * @return IssueTrackerConfig
   *//*
      public IssueTrackerConfig loadIssueTrackerConfig(long issueTrackerId);

      *//**
   * Gets All Issue Trackers configured for the companyShortName
   *
   * @param companyShortName
   * @return List Of IssueTrackers
   *//*
      public List<IssueTrackerConfig> getIssueTrackers(String companyShortName);

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
      public void saveOrUpdateForm(Form form);
*/

  /**
   * Finds or Creates User if doesnt exist
   *
   * @param user
   */
  public User findOrCreateUser(User user);

   /**//**
   * Finds the form with the given Name which has been saved for IssueTracker
   *
   * @param issueTrackerConfig for which to find form
   * @param formName name of form to search
   * @return Form
   *//*
      public Form findForm(IssueTrackerConfig issueTrackerConfig, String formName);

      *//**
   * Gets the Form which cna be used to generate a UI to Capture Issue Details for Creating an Issue
   *
   * @param issueTrackerConfig
   * @param post for which an issue has to be created
   * @return Form which holds details of fields required for Issue Create
   *//*
      public Form getIssueCreateForm(IssueTrackerConfig issueTrackerConfig, Post post);

      *//**
   * Creates an Issue in the Issue Tracker with the data Passed and associates that to given Post in the System
   *
   * @param issueTrackerConfig
   * @param postId post which has to be related to the issue
   * @param data to be used in creating issue
   *//*
      public void createIssue(IssueTrackerConfig issueTrackerConfig, long postId, Map<String, String> data);

      *//**
   * Checks the Status of Issues on the Issue Tracker represented by IssueTrackerConfig, by using issueIds passed
   *
   * @param issueIds
   * @param issueTrackerConfig
   * @return Map of IssueId as Key and its status as value
   *//*
      public Map<String, String> checkStatus(List<String> issueIds, IssueTrackerConfig issueTrackerConfig);*/

  /**
   * Saves UserLoginConfirmationRequest
   *
   * @param userLoginConfirmationRequest
   */
  public void saveUserLoginConfirmationRequest(UserLoginConfirmationRequest userLoginConfirmationRequest);

  /**
   * Associates User Email to LoginConfirmationRequest This method will also send an email to user to confirm the
   * Request
   *
   * @param
   * @param email
   */
  public UserLoginConfirmationRequest associateEmailToUserLoginConfirmationRequest(final UserLoginConfirmationRequest userLoginConfirmationRequest, String email,
                                                                                   boolean isThirdPartyConfirmation);

  /**
   * Confirms the UserLogin Confirmation Request
   *
   * @param userLoginConfirmationRequestId
   * @param confirmationKey
   * @return true if confirmed, false if could not be confirmed
   */
  public boolean confirmUserLoginConfirmationRequest(HttpServletRequest request, HttpServletResponse response, long userLoginConfirmationRequestId, String confirmationKey);

  /**
   * Loads UserLoginConfirmationRequest
   *
   * @param
   */
  public UserLoginConfirmationRequest loadUserLoginConfirmationRequest(long userLoginConfirmationRequestId);

  /**
   * Configure Google Analytics for the Company
   *
   * @param company
   * @param gaAuthInfo
   */
  /*public void configureGoogleAnalytics(Company company, GAAuthInfo gaAuthInfo);*/

  /**
   * Get information like ip, latitude, longitude etc for the user specified by userName.
   *
   * @param userName
   * @return
   */
  public VisitorInfo getUserInfo(String userName, String postId);

  /**
   * Reset user password to some random value and send an email
   *
   * @param userEmail email id of the user whose password is to be reset.
   */
  public void resetEmployeePassword(String userEmail);

   /**//**
   * Gets Form Field Options identified by fieldName belonging to form identified by formName and belonging to
   * mentioned IssueTrackerConfig
   *
   * @param issueTrackerConfig
   * @param formName
   * @param fieldName
   * @return List of FormFieldOption
   *//*
      public List<FormFieldOption> getFormFieldOptions(IssueTrackerConfig issueTrackerConfig, String formName, String fieldName);
*/

  /**
   * Delete the employee indicated by username.
   *
   * @param username
   */
  public void deleteEmployee(String username);

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
   * @param start            used for pagination which should be first record
   * @param rows             how many records should be returned
   * @param sortBy           which property of Product should be used for sorting
   * @param sortHow          the order of Sorting ASC or DESC
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
   * Encrypts the password
   *
   * @param username
   * @param password
   * @return encrypted password
   */
  public String getEncryptedPassword(String username, String password);

   /**//**
   * Get badge specified by badge name for a particular company.
   *
   * @param badgeName
   * @param companyShortName
   * @return
   *//*
      public Badge getBadgeForCompany(String badgeName, String companyShortName);*/

}
