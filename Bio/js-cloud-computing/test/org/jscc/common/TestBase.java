package org.jscc.common;


import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;

public class TestBase extends TestCase {

	
	
	  private final LocalServiceTestHelper helper =
          new LocalServiceTestHelper(
                          new LocalDatastoreServiceTestConfig(),
                          new LocalMemcacheServiceTestConfig(),
                          new LocalTaskQueueTestConfig())
	  						.setEnvIsAdmin(false).setEnvIsLoggedIn(true)
	  						.setEnvEmail("me@test.com")
	  						.setEnvAuthDomain("domain");;
    
    
    @Before
    public void setUp() {
            this.helper.setUp();
    }
    
    
    @After
    public void tearDown() {
            this.helper.tearDown();
    }
    
    
    public void testDummy() {}
}
