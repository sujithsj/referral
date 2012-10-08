package com.ds.domain.core.event;

import java.io.Serializable;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
public interface SmartSerializable extends Serializable {

    /**
     * Map which represents Object State
     *
     * @return
     */
    public Map<String, String> getWireRepresentation();

    /**
     * Resconstruct Object from Map
     *
     * @param values
     */
    public void prepareFromWireRepresentation(Map<String, String> values);

}
