package com.ds.web.servlet;

import com.ds.pact.service.admin.AdminService;
import com.ds.impl.service.ServiceLocatorFactory;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Feb 21, 2013
 * Time: 2:19:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class EmailConfirmServlet extends HttpServlet {

    private AdminService adminService;
    private Logger logger = LoggerFactory.getLogger(FileUploadServlet.class);

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String confId = request.getParameter("confId");
        String confirmationKey = request.getParameter("confKey");

        if (StringUtils.isNotBlank(confId) && StringUtils.isNotBlank(confirmationKey) && StringUtils.isNumeric(confId)) {
            boolean result = getAdminService().confirmUserLoginConfirmationRequest(request, response, Integer.parseInt(confId), confirmationKey);

            // successfully confirmed.
            if (result) {

                try {
                    response.sendRedirect("/pages/emailConfirmResponse.jsp?response=success");
                } catch (Throwable t) {
                    logger.error("Error while redirecting to login page.", t);
                    response.sendError(response.SC_NOT_FOUND);
                }

            } else {
                redirectToFailure(response);

            }
            // already confirmed or could not confirm.
        } else {
            redirectToFailure(response);

        }
    }

    private void redirectToFailure(HttpServletResponse response) throws IOException {
        try {
            response.sendRedirect("/pages/emailConfirmResponse.jsp?response=success");
        } catch (Throwable t) {
            logger.error("Error while redirecting to login page.", t);
            response.sendError(response.SC_NOT_FOUND);
        }
    }

    /**
     * @return the adminService
     */
    public AdminService getAdminService() {
        if (this.adminService == null) {
            this.adminService = ServiceLocatorFactory.getService(AdminService.class);
        }
        return adminService;
    }

}