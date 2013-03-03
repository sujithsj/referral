package com.ds.action;

import com.ds.web.action.BaseAction;
import com.ds.pact.service.admin.AdminService;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.DefaultHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Feb 24, 2013
 * Time: 1:22:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ResetPasswordAction extends BaseAction {

    private String userEmail;

    @Autowired
    private AdminService adminService;

    @DefaultHandler
    public Resolution resetPassword() {

        getAdminService().resetEmployeePassword(userEmail);

        return new ForwardResolution("/pages/resetPasswordResponse.jsp");
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
