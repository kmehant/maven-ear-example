package com.example.rest;

import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
import static javax.naming.Context.PROVIDER_URL;
import static javax.naming.Context.URL_PKG_PREFIXES;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.example.service.ExampleService;

import java.util.ArrayList;
import java.util.List;

import java.util.Hashtable;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("/members")
@RequestScoped
public class MemberResourceRESTService {


   @GET
   public String listAllMembers() {

      Helpers h = new Helpers();
		// Connection to Wildfly Server instance
		String host = "127.0.0.1";
		String port = "8080"; // Wildfly HTTP port

		Context remotingContext;
		try {
			remotingContext = h.createRemoteEjbContext(host, port);
		} catch (NamingException e) {
			System.err.println("Error setting up remoting context");
			e.printStackTrace();
			return "";
		}
		// Syntax: ejb:${appName}/${moduleName}/${beanName}!${remoteView}
		// appName = name of EAR deployment (or empty for single EJB/WAR
		// deployments)
		// moduleName = name of EJB/WAR deployment
		// beanName = name of the EJB (Simple name of EJB class)
		// remoteView = fully qualified remote interface class
		String ejbUrl = "ejb:/module-ejb/ExampleServiceBean!com.example.service.ExampleService";

      ExampleService service;
		try {
			service = h.createEjbProxy(remotingContext, ejbUrl, ExampleService.class);
		} catch (NamingException e) {
			System.err.println("Error resolving bean");
			e.printStackTrace();
			return "";
		} catch (ClassCastException e) {
			System.err.println("Resolved EJB is of wrong type");
			e.printStackTrace();
			return "";
		}

		String exampleResult;
		try {
			exampleResult = service.whoAmI();
		} catch (Exception e) {
			System.err.println("Error accessing remote bean");
			e.printStackTrace();
			return "";
		}
      return ("ejb call: " + exampleResult);
   }

}
