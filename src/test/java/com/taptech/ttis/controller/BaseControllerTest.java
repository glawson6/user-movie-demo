package com.taptech.ttis.controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.UUID;

/**
 * Created by tap on 5/19/16.
 */

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@ActiveProfiles("test")
//@DatabaseSetup({"/protocols.xml","/eventType.xml","/endpoints.xml","/templateContent.xml","/eventTypeProtocol.xml","/subscriptions.xml","/event.xml"})
public class BaseControllerTest {

    //@Autowired
    //protected final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    protected ResourceLoader resourceLoader = new DefaultResourceLoader();

    protected static final String CONTENT_TYPE_STR = "content-type";
    protected static final String CONTENT_TYPE_JSON = "application/json";


    protected static String getUUID(){
        return UUID.randomUUID().toString();
    }
}
