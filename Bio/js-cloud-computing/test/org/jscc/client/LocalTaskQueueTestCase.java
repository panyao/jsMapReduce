package org.jscc.client;


import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;

public class LocalTaskQueueTestCase extends LocalServiceTestCase {


    private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalTaskQueueTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

}