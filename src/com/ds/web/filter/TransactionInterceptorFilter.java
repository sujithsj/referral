package com.ds.web.filter;

import com.ds.impl.service.ServiceLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author adlakha.vaibhav
 */
public class TransactionInterceptorFilter implements Filter {

  private Logger logger = LoggerFactory.getLogger(TransactionInterceptorFilter.class);

  public void destroy() {
  }

  public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
    TransactionTemplate transactionTemplate = (TransactionTemplate) ServiceLocatorFactory.getService(TransactionTemplate.class);

    try {
      transactionTemplate.execute(new TransactionCallback() {

        public Object doInTransaction(TransactionStatus status) {
          try {
            chain.doFilter(request, response);
          } catch (IOException e) {
            logger.error("IO Error in Transaction Filter", e);
            throw new RuntimeException(e);
          } catch (ServletException e) {
            logger.error("Servlet Exception in Transaction Filter", e.getRootCause());
            throw new RuntimeException(e.getRootCause());
          }
          return null;
        }

      });
    } catch (UnexpectedRollbackException e) {
      if (logger.isDebugEnabled()) {
        logger.debug("Some Operation Got Rolled Back", e);
      }
    }

  }

  public void init(FilterConfig arg0) throws ServletException {

  }
}
