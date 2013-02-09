package com.ds.action.campaign;

import com.ds.web.action.BaseAction;
import com.ds.pact.service.campaign.CampaignService;
import com.ds.domain.campaign.Campaign;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.DefaultHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Feb 9, 2013
 * Time: 12:56:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CampaignIntegrateAction extends BaseAction {


    private Long campaignId;
    private String companyShortName;

    /*private Campaign campaign;*/


    @Autowired
    private CampaignService campaignService;

    @DefaultHandler
    public Resolution integrate() {

        //TODO: check campaign Id is valid
        getCampaignService().getCampaignById(campaignId);

        return new ForwardResolution("/pages/campaign/campaignIntegrate.jsp");
    }


    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public CampaignService getCampaignService() {
        return campaignService;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }
}
