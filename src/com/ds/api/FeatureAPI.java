package com.ds.api;

import com.ds.constants.FeatureType;
import com.ds.domain.company.Company;
import com.ds.domain.core.Plan;

/**
 * @author adlakha.vaibhav
 */
public interface FeatureAPI {

  /**
     * Whether company have access to this Feature
     *
     * @param company
     * @param featureType
     * @return true if company has access to this feature. false otherwise
     */
    public void doesCompanyHaveAccessTo(Company company, FeatureType featureType);

    /**
     * Whether company have access to this Feature
     *
     * @param company
     * @param featureType
     * @param count its used for checking like Employee Count or Product Count (means whether this company can have 6
     *            employees)
     * @return true if company has access to this feature. false otherwise
     */
    public void doesCompanyHaveAccessTo(Company company, FeatureType featureType, int count);

    /**
     * Grants a Company Access to the FeatureType
     *
     * @param company
     * @param featureType
     */
    public void grantCompanyAccessTo(Company company, FeatureType featureType);

    /**
     * Grants a Company Access to the FeatureType for the count mentioned
     *
     * @param company
     * @param featureType
     */
    public void grantCompanyAccessTo(Company company, FeatureType featureType, long count);

    /**
     * Grant the Company Access to the Plan
     *
     * @param company
     * @param plan
     */
    public void grantCompanyAccessTo(Company company, Plan plan);

    /**
     * Revokes the Company Access to the Plan
     *
     * @param company
     * @param plan
     */
    public void revokeCompanyAccessFrom(Company company, Plan plan);

    /**
     * Revokes the Company Access to the Plan identified by Plan Name
     *
     * @param company
     * @param planName
     */
    public void revokeCompanyAccessFrom(Company company, String planName);

    /**
     * Grant the Company Access to the Plan identified by Plan Name
     *
     * @param company
     * @param planName
     */
    public void grantCompanyAccessTo(Company company, String planName);

    /**
     * Loads the company identified by the planName
     *
     * @param planName
     * @return Plan or null if no plan found with the given Name
     */
    public Plan getPlan(String planName);

    /**
     * Whether company have access to this Feature
     *
     * @param company
     * @param featureType
     * @return true if company has access to this feature. false otherwise
     */
    public boolean isFeatureAccessible(Company company, FeatureType featureType);

    /**
     * Whether company have access to this Feature
     *
     * @param company
     * @param featureType
     * @param count its used for checking like Employee Count or Product Count (means whether this company can have 6
     *            employees)
     * @return true if company has access to this feature. false otherwise
     */
    public boolean isFeatureAccessible(Company company, FeatureType featureType, int count);
}
