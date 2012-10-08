package com.ds.core.web;


/**
 * This interface can be implemented by any object that wants to control how EL expressions resolve their properties
 * when they're referenced like "#{msgHelper['what.message.key.you.want.here']}" or if no periods are used:
 * "#{msgHelper.what_message_key_you_want_here]}"
 *
 * @author adlakha.vaibhav
 */
public interface PropertyResolving {
    /**
     * @param property the property to obtain from this object.
     * @return the appropriate value.
     */
    public Object getPropertyValue(Object property);
}

