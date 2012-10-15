package com.ds.api;

import com.ds.domain.company.Company;
import com.ds.domain.user.User;

import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public interface AdminAPI {
  /**
   * Finds the company corresponding to the companyShortName
   *
   * @return company
   */
  public Company getCompany(String companyShortName);

  /**
   * Returns a user corresponding to the specified userId
   *
   * @param userId
   * @return
   */
  public User getUser(String userId);

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
}
