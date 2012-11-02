package com.ds.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;



public class JSONResponseBuilder {

    private Map<String, Object> params;

    public JSONResponseBuilder addField(String fieldName, Object fieldValue) {
        if (params == null) {
            params = new LinkedHashMap<String, Object>();
        }

        params.put(fieldName, fieldValue);

        return this;
    }

    public JSONResponseBuilder addMap(Map<String, Object> data) {
        if (params == null) {
            params = new HashMap<String, Object>();
        }

        params.putAll(data);

        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        com.ds.domain.core.JSONObject.appendJSONMap(params, sb);
        return sb.toString();
    }

}

