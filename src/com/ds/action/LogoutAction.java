package com.ds.action;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import com.ds.web.action.BaseAction;
import com.ds.security.helper.SecurityHelper;

/**
 * User: Rahul
 * Date: Feb 9, 2013
 * Time: 4:38:45 PM
 */

public class LogoutAction extends BaseAction {

	public Resolution pre() {
		SecurityHelper.logoutUser();
		return new RedirectResolution(LoginAction.class);

	}
}
