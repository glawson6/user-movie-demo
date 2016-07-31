package com.taptech.ttis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by tap on 7/30/16.
 */
public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Value("${agora.ns.show-json-pretty:false}")
    boolean showJSONPretty;

    private static final ObjectMapper prettyPrintMapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper;
    }

    /**
     * We will try to convert to pretty print json
     * @param object
     * @return
     */
    protected String createPrettyJSON(Object object){
        String output = "";
        if ((null != object) & showJSONPretty) {
            StringWriter sw = new StringWriter();
            try {
                prettyPrintMapper.writeValue(sw, object);
                output = sw.toString();
            } catch (IOException e) {
                logger.warn("Could not convert object to JSON",e);
                output = object.toString();
            }
        } else if (null != object){
            output = object.toString();
        }
        return output;
    }
}
