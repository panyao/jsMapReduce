package org.jscc.common.server;
//package com.seethebig.common.server.guice;
//
//import javax.jdo.JDOHelper;
//import javax.jdo.PersistenceManagerFactory;
//
//import com.google.inject.Binder;
//import com.google.inject.Module;
//
//public class GuiceModule implements Module {
//
//	@Override
//	public void configure(Binder binder) {
//
//		PersistenceManagerFactory pmFactory = JDOHelper
//				.getPersistenceManagerFactory("transactions-optional");
//		
//		binder.bind(PersistenceManagerFactory.class).toInstance(pmFactory);
//
//	}
//
//}