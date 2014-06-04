package org.jscc.client;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;



public class LocalAuthentificationTestCase extends LocalServiceTestCase {



    public final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalUserServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }





}