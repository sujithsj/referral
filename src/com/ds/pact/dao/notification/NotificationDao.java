package com.ds.pact.dao.notification;

import com.ds.pact.dao.BaseDao;
import com.ds.domain.company.Company;
import com.ds.domain.user.User;
import com.ds.domain.user.UserLoginConfirmationRequest;
import com.ds.domain.user.UserSettings;
import com.ds.domain.core.Plan;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 22, 2012
 * Time: 12:37:54 AM
 * To change this template use File | Settings | File Templates.
 */
public interface NotificationDao extends BaseDao {

    public long getPendingNotificationForUser(String userId);

}