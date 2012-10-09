package com.ds.core.transaction;

import org.springframework.transaction.support.TransactionCallback;

/**
 * @author adlakha.vaibhav
 */
public interface RequiresNewTemplate {

  public Object executeInNewTransaction(TransactionCallback transactionCallback) {
        return transactionCallback.doInTransaction(null);
    }
}
