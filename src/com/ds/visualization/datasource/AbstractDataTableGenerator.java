package com.ds.visualization.datasource;

import com.google.visualization.datasource.Capabilities;
import com.google.visualization.datasource.DataTableGenerator;
import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * @author adlakha.vaibhav
 */
public abstract class AbstractDataTableGenerator implements DataTableGenerator {

    /**
     * To provide the start date from request or default it to one month from current date if not available in request
     *
     * @param 
     * @return
     */
    protected Date getStartDateFromRequest(HttpServletRequest request) {
        String startDateString = request.getParameter("startDate");
        Date startDate;
        if (StringUtils.isNotEmpty(startDateString))
            startDate = DateUtil.parseDate(startDateString, Arrays.asList("yyyy-MM-dd", "dd-MM-yyyy"));
        else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, -30);
            startDate = cal.getTime();
        }
        return startDate;
    }

    /**
     * To provide the end date from request or default it current date if not available in request
     *
     * @param request
     * @return
     */
    protected Date getEndDateFromRequest(HttpServletRequest request) {
        String endDateString = request.getParameter("endDate");
        Date endDate;
        if (StringUtils.isNotEmpty(endDateString))
            endDate = DateUtil.parseDate(endDateString, Arrays.asList("yyyy-MM-dd", "dd-MM-yyyy"));
        else
            endDate = new Date();
        return endDate;
    }

    @Override
    public Capabilities getCapabilities() {
        return Capabilities.NONE;
    }

}
