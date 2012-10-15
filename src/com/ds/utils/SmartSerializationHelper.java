package com.ds.utils;

import com.ds.core.event.SmartSerializable;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Map;


/**
 * @author adlakha.vaibhav
 */
public class SmartSerializationHelper {

  public static String getWireRepresentation(SmartSerializable smartSerializable) {
        Map<String, String> eventData = smartSerializable.getWireRepresentation();
        eventData.put("ClassName", smartSerializable.getClass().getName());
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(eventData);
            return json;
        } catch (JsonGenerationException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SmartSerializable getObjectFromWireRepresentation(String json) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String, String> data = mapper.readValue(json, Map.class);
            String eventClassName = data.get("ClassName");
            Class eventClass = Class.forName(eventClassName);
            SmartSerializable smartSerializable = (SmartSerializable) eventClass.newInstance();

            smartSerializable.prepareFromWireRepresentation(data);
            return smartSerializable;

        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
