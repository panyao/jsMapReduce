package org.jscc.common.server;


import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import com.google.inject.Singleton;


@Singleton
public final class PMF {
 
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");
    
    private PMF() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }

}

