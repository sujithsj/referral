package com.hk.util.json;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.hk.domain.core.JSONObject;

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
        JSONObject.appendJSONMap(params, sb);
        return sb.toString();
    }

}

