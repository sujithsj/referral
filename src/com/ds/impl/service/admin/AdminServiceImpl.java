package com.ds.impl.service.admin;

import com.ds.domain.company.Company;
import com.ds.domain.core.Role;
import com.ds.domain.user.User;
import com.ds.exception.CompositeValidationException;
import com.ds.exception.ValidationException;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.dao.AdminDAO;
import com.ds.pact.service.admin.AdminService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import java.util.List;

/**
 * @author adlakha.vaibhav
 */
@Service
public class AdminServiceImpl implements AdminService {

  private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

  private AdminDAO adminDAO;

  @Override
  public void registerCompany(Company company, final User user, Object[] recaptchaParams) {
    CompositeValidationException compositeValidationException = new CompositeValidationException();

    validateCompany(company, compositeValidationException);
    validateUser(user, compositeValidationException, recaptchaParams);

    company.setVerificationToken(String.valueOf(RandomUtils.nextInt()));

    if (!compositeValidationException.getValidationExceptions().isEmpty()) {
      logger.error("Exception registing company", compositeValidationException);
      throw compositeValidationException;
    }

    getAdminDAO().saveCompany(company);

    getFeatureAPI().grantCompanyAccessTo(company, "ENTERPRISE");

    user.setCompanyShortName(company.getShortName());
    addUser(user, new Role.Type[]{Role.Type.admin});
  }


  ublic

  void addUser(final User user, Role.Type[] roleTypes) {
    CompositeValidationException compositeValidationException = new CompositeValidationException();

    validateUser(user, compositeValidationException, null);

    if (!compositeValidationException.getValidationExceptions().isEmpty()) {
      logger.error(compositeValidationException);
      throw compositeValidationException;
    }

    UserKarmaProfile karmaProfile = new UserKarmaProfile();
    karmaProfile.setUserName(user.getUsername());
    karmaProfile.setKarmaPoints(new Long(0));
    getAdminDAO().save(karmaProfile);

    UserSettings userSettings = new UserSettings();
    userSettings.setCreatedDate(new Date());
    userSettings.setCreatedBy(user.getUsername());
    userSettings.setUsername(user.getUsername());
    userSettings.setSendEmailOnAssignedPost(true);
    userSettings.setSendEmailOnPost(true);
    getAdminDAO().save(userSettings);

    user.setKarmaProfileId(karmaProfile.getId());
    user.setUserSettingsId(userSettings.getId());

    ServiceLocatorFactory.getService(RequiresNewTemplate.class).executeInNewTransaction(new TransactionCallback() {

      @Override
      public Object doInTransaction(TransactionStatus status) {
        user.setPassword(getMessageDigestPasswordEncoder().encodePassword(user.getPassword(), user.getUsername()));

        getAdminDAO().save(user);
        return null;
      }
    });

    getSecurityAPI().grantRolesToUser(user, roleTypes);

    UserLoginConfirmationRequest userLoginConfirmationRequest = new UserLoginConfirmationRequest();
    userLoginConfirmationRequest.setConfirmationKey(GeneralUtils.getRandomAlphaNumericString(20, 5));
    userLoginConfirmationRequest.setProviderName("userrules");
    userLoginConfirmationRequest.setName(user.getFullName());
    userLoginConfirmationRequest.setAuthInfoJson("NA");
    userLoginConfirmationRequest.setIdentifier(userLoginConfirmationRequest.getConfirmationKey());
    userLoginConfirmationRequest.setConfirmed(false);

    associateEmailToUserLoginConfirmationRequest(userLoginConfirmationRequest, user.getEmail(), false);

  }

  private void validateUser(User user, CompositeValidationException compositeValidationException, Object[] recaptchaParams) {

    if (!isUserIdValid(user.getEmail())) {
      compositeValidationException.getValidationExceptions().add(new ValidationException("email", "not a valid username"));
    } else if (isUserIdTaken(user.getEmail())) {
      compositeValidationException.getValidationExceptions().add(new ValidationException("email", "username has already been taken"));
    }

    if (recaptchaParams != null) {
      String remoteip = recaptchaParams.length >= 0 ? (String) recaptchaParams[0] : null;
      String recaptchaChallengeField = recaptchaParams.length > 0 ? (String) recaptchaParams[1] : null;
      String recaptchaResponsefield = recaptchaParams.length > 1 ? (String) recaptchaParams[2] : null;
      boolean validateCaptcha = true;

      if (remoteip == null || StringUtils.isEmpty(remoteip)) {
        compositeValidationException.getValidationExceptions().add(new ValidationException("remoteip", "User ip cannot be blank"));
        validateCaptcha = false;
      }
      if (recaptchaChallengeField == null || StringUtils.isEmpty(recaptchaChallengeField)) {
        compositeValidationException.getValidationExceptions().add(new ValidationException("recaptchaChallengeField", "reCaptcha challenge can not be left blank."));
        validateCaptcha = false;
      }
      if (recaptchaResponsefield == null || StringUtils.isEmpty(recaptchaResponsefield)) {
        compositeValidationException.getValidationExceptions().add(new ValidationException("recaptchaResponsefield", "reCaptcha response can not be left blank."));
        validateCaptcha = false;
      }
      if (validateCaptcha) {
        List<String> captchaResponse = getRecaptchaService().validateRecatchaResponse(remoteip, recaptchaChallengeField, recaptchaResponsefield);
        String isResponseValid = captchaResponse.get(0);
        boolean isValid = false;
        if (isResponseValid != null && StringUtils.isNotEmpty(isResponseValid))
          isValid = Boolean.parseBoolean(isResponseValid);

        if (!isValid) {
          String errorMsg = captchaResponse.get(1);
          compositeValidationException.getValidationExceptions().add(
              new ValidationException("recaptchaValidation", "reCaptcha response does not match, please try again."));
        }

      }

    }
  }

  private void validateCompany(Company company, CompositeValidationException compositeValidationException) {

    if (isCompanyShortNameTaken(company.getShortName())) {
      compositeValidationException.getValidationExceptions().add(new ValidationException("shortName", "shortname has already been taken"));
    } else if (!isCompanyShortNameAndURLRelated(company.getShortName(), company.getUrl())) {
      compositeValidationException.getValidationExceptions().add(new ValidationException("shortName", "shortname does not look similar to url"));
    }

    if (!isCompanyURLWellFormed(company.getUrl())) {
      compositeValidationException.getValidationExceptions().add(new ValidationException("url", "should look like an url"));
    }
  }

  @Override
  public boolean isCompanyShortNameTaken(String shortName) {
    return getAdminDAO().isCompanyShortNameTaken(shortName);
  }

  @Override
  public boolean isCompanyURLWellFormed(String url) {
    Pattern pattern = Pattern.compile("^(http)://[^/]+\\.[a-z]+");
    Matcher matcher = pattern.matcher(url);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }


  public AdminDAO getAdminDAO() {
    return adminDAO;
  }
}
