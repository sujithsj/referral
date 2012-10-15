package com.ds.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public class CompositeValidationException extends RuntimeException {

    private List<ValidationException> validationExceptions = new ArrayList<ValidationException>();

    /**
     * @return the validationExceptions
     */
    public List<ValidationException> getValidationExceptions() {
        return validationExceptions;
    }

    /**
     * @param validationExceptions the validationExceptions to set
     */
    public void setValidationExceptions(List<ValidationException> validationExceptions) {
        this.validationExceptions = validationExceptions;
    }

	@Override
	public String toString() {
		StringBuilder response = new StringBuilder();

		for(ValidationException valExc : getValidationExceptions())
			response.append(valExc.toString()).append("\n");

		return response.toString();
	}




}

