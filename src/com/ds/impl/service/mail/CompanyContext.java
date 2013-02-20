package com.ds.impl.service.mail;

import com.ds.pact.service.mail.EmailContext;
import com.ds.pact.service.admin.AdminService;
import com.ds.domain.company.Company;
import com.ds.impl.service.ServiceLocatorFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Feb 20, 2013
 * Time: 11:27:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyContext extends EmailContext {

    private static final String COMPANY_CONTEXT_COMPANY_SHORT_NAME = "CompanyContext.Company.shortName";
    private Company company;

    // default constructor to make it serializable
    public CompanyContext() {

    }

    /**
     * Creates an Email Context with company
     *
     * @param company
     */
    public CompanyContext(Company company) {
        this.company = company;
    }

    /**
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    @Override
    public Map<String, String> getWireRepresentation() {
        Map<String, String> data = new HashMap<String, String>();

        // lets store company short name to load company later if complany is not null
        if (getCompany() != null) {
            data.put(COMPANY_CONTEXT_COMPANY_SHORT_NAME, getCompany().getShortName());
        }

        return data;
    }

    @Override
    public void prepareFromWireRepresentation(Map<String, String> data) {
        // if company short name is in data then lets load Company object
        if (data.containsKey(COMPANY_CONTEXT_COMPANY_SHORT_NAME)) {
            this.company = ServiceLocatorFactory.getService(AdminService.class).getCompany(data.get(COMPANY_CONTEXT_COMPANY_SHORT_NAME));
        }
    }


}
