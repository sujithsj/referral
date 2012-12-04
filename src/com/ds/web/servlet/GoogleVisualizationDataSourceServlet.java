package com.ds.web.servlet;

import com.ds.visualization.datasource.DataTableGeneratorRegistry;
import com.google.visualization.datasource.DataSourceServlet;
import com.google.visualization.datasource.DataTableGenerator;
import com.google.visualization.datasource.base.DataSourceException;
import com.google.visualization.datasource.base.ReasonType;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.query.Query;

import javax.servlet.http.HttpServletRequest;

/**
 * @author adlakha.vaibhav
 */
public class GoogleVisualizationDataSourceServlet extends DataSourceServlet {


    @Override
    public DataTable generateDataTable(Query query, HttpServletRequest request) throws DataSourceException {
        String dsName = request.getParameter("dsName");

        if (DataTableGeneratorRegistry.getDataTableGenerator(dsName) !=null) {
            DataTableGenerator dataTableGenerator = DataTableGeneratorRegistry.getDataTableGenerator(dsName);

            return dataTableGenerator.generateDataTable(query, request);
        }

        throw new DataSourceException(ReasonType.INVALID_REQUEST, "No Such DataSource Registered in system");
    }

    /**
     * NOTE: By default, this function returns true, which means that cross domain requests are rejected. This check is
     * disabled here so examples can be used directly from the address bar of the browser. Bear in mind that this
     * exposes your data source to xsrf attacks. If the only use of the data source url is from your application, that
     * runs on the same domain, it is better to remain in restricted mode.
     */
    @Override
    protected boolean isRestrictedAccessMode() {
        return false;
    }

   
}
