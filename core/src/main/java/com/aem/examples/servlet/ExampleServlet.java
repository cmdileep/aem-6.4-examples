package com.aem.examples.servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//The path has to be configured under "Apache Sling Servlet/Script Resolver" 
//in OSGI Config as it starts with "/services",other than default 
//names like /apps, /libs, /bin
@SlingServlet(
        label = "Samples - Sling All Methods Servlet",
        description = "Sample implementation of a Sling All Methods Servlet.",
        metatype=true,
        paths = { "/services/all-sample" }, 
        methods = { "GET", "POST" }, 
        resourceTypes = { }, // Ignored if paths is set
        selectors = { "print.a4" }, // Ignored if paths is set
        extensions = { "html", "htm" }  // Ignored if paths is set
)
public class ExampleServlet extends SlingAllMethodsServlet {
	
	  private static final Logger log = LoggerFactory.getLogger(ExampleServlet.class);

	 @Override
	  protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
		 	response.getWriter().write("Your servlet is ready for execution from GET");
	 }
	 @Override
	  protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
		 	response.getWriter().write("Your servlet is ready for execution from Post");
	 }

}

/*
 * http://www.aemcq5tutorials.com/tutorials/sling-servlet-in-aem/
 * 
 */
