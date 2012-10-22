package com.ds.exception;

/**
 * @author adlakha.vaibhav
 */
public class InvalidParameterException extends DSException {

    public InvalidParameterException(String key, Object... messageParams) {
        super(key, messageParams);
    }

}
