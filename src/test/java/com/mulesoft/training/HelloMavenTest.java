package com.mulesoft.training;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.tck.junit4.rule.DynamicPort;

public class HelloMavenTest extends FunctionalTestCase {

	@Rule
	public DynamicPort dynamicPort = new DynamicPort("http.port");
	
    @Test
    public void mavenFlowReturnsHelloMaven() throws Exception {
    	System.out.println("\n\n\n**** Dynamic HTTP Port used in Junit Test case #1 **** --->" + dynamicPort.getNumber()+ "\n\n\n");
        runFlowAndExpect("mavenFlow", "Hello Maven");
    }

    @Test
    public void retrieveFlightsAddsAppropriateHeader() throws Exception {
      System.out.println("\n\n\n**** Dynamic HTTP Port used in Junit Test case #2 **** --->" + dynamicPort.getNumber()+ "\n\n\n");
      MuleEvent event = runFlow("retrieveFlights");
      String contentType = event.getMessage().getOutboundProperty("Content-Type");
      assertEquals("application/json", contentType);
    }    
    
    @Override
    protected String getConfigFile() {
        return "maven-project.xml";
    }

}
