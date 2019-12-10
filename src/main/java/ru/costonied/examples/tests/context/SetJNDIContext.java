package ru.costonied.examples.tests.context;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

/**
 * Example show how to add JNDI resource in context for using while testing.
 * In the example we use org.springframework.mock.jndi.SimpleNamingContextBuilder to add JNDI to InitialContext.
 *
 * Note:
 * org.springframework.mock.jndi.SimpleNamingContextBuilder was deprecated from org.springframework:spring-test:5.2
 * and was recommended to use third-party solution like a https://github.com/h-thurow/Simple-JNDI
 */
public class SetJNDIContext {

    private static final String JNDI_ENVIRONMENT_INFO = "our/own/jndi";

    @BeforeMethod
    public void setUp() {
        try {
            // Add our JNDI in context
            // You have two possibility init SimpleNamingContextBuilder:
            //   1. use emptyActivatedContextBuilder() if you want reuse context in other places and not recreate it
            //   2. use new SimpleNamingContextBuilder() and do builder.activate() if you create context just in one place
            // read documentation for details
            SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            builder.bind(JNDI_ENVIRONMENT_INFO, "PRIVATE/IFT/CORE/p1/p1");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printJNDI() {
        try {
            String jndiValue = InitialContext.doLookup(JNDI_ENVIRONMENT_INFO);
            System.out.println(String.format("JNDI [%s] has value [%s]", JNDI_ENVIRONMENT_INFO, jndiValue));
        } catch (NamingException e) {
            System.out.println(String.format("JNDI [%s] not found!", JNDI_ENVIRONMENT_INFO));
        }
    }

}
