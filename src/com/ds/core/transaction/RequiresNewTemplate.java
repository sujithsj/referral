package com.ds.core.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionCallback;

/**
 * @author adlakha.vaibhav
 */
@Service
public class RequiresNewTemplate {

  public Object executeInNewTransaction(TransactionCallback transactionCallback) {
        return transactionCallback.doInTransaction(null);
    }
}
