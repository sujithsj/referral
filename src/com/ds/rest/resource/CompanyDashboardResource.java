package com.ds.rest.resource;

import org.jboss.resteasy.annotations.cache.NoCache;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;

import com.ds.pact.service.dashboard.CompanyDashboardService;
import com.ds.dto.dashboard.AffiliateByCommissionDto;
import com.ds.security.helper.SecurityHelper;
import com.ds.domain.user.User;
import com.ds.utils.JSONResponseBuilder;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Feb 10, 2013
 * Time: 1:13:08 AM
 * To change this template use File | Settings | File Templates.
 */

@Path("/company/dashboard")
@NoCache
@Component
public class CompanyDashboardResource {

    @Autowired
    private CompanyDashboardService companyDashboardService;

    @GET
    @Path("/ttAffByComm")
    @Produces("application/json")
    public String getTopTenAffiliatesByCommission(@QueryParam("stDt") Date startDate,@QueryParam("edDt") Date endDate){
        String companyShortName = SecurityHelper.getCompanyShortNameForLoggedInUser();
        
        List<AffiliateByCommissionDto> results = getCompanyDashboardService().getTopTenAffiliateByCommission(companyShortName, startDate, endDate);
        return new JSONResponseBuilder().addField("exception", false).addField("data", results).build();
    }



    public CompanyDashboardService getCompanyDashboardService() {
        return companyDashboardService;
    }
}
