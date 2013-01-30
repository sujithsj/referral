package com.ds.impl.dao.affiliate;

import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.company.Company;
import com.ds.domain.core.Plan;
import com.ds.domain.user.User;
import com.ds.domain.user.UserLoginConfirmationRequest;
import com.ds.domain.user.UserSettings;
import com.ds.pact.dao.affiliate.AffiliateDao;
import com.ds.impl.dao.BaseDaoImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.security.InvalidParameterException;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 22, 2012
 * Time: 12:37:35 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class AffiliateDaoImpl extends BaseDaoImpl implements AffiliateDao {

    @Override
    public Affiliate saveAffiliate(Affiliate affiliate) {
        return (Affiliate) save(affiliate);
    }

    @Override
    public long affiliatesCount(String companyShortName) {
        return count("select count(*) from User where companyShortName = ? ", companyShortName);

    }

    @Override
    public Affiliate getAffiliateByLogin(String login) {
        Affiliate affiliate = (Affiliate) findUnique("select aff from Affiliate aff  where aff.login = ?", new Object[]{login});

        if (affiliate == null) {
            logger.error("No such affiliate found in system: " + login);
            //throw new InvalidParameterException("INVALID_AFFILIATE");
        }
        return affiliate;
    }


}
