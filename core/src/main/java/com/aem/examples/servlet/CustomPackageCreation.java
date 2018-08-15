/**
 * 
 */
package com.aem.examples.servlet;

import java.io.IOException;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.jackrabbit.vault.fs.api.PathFilterSet;
import org.apache.jackrabbit.vault.fs.api.ProgressTrackerListener;
import org.apache.jackrabbit.vault.fs.config.DefaultWorkspaceFilter;
import org.apache.jackrabbit.vault.packaging.JcrPackage;
import org.apache.jackrabbit.vault.packaging.JcrPackageDefinition;
import org.apache.jackrabbit.vault.packaging.JcrPackageManager;
import org.apache.jackrabbit.vault.packaging.PackageException;
import org.apache.jackrabbit.vault.packaging.Packaging;
import org.apache.jackrabbit.vault.util.DefaultProgressListener;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

/**
 * @author DCM
 *
 */
@SlingServlet(
       
        metatype=true,
        paths = { "/bin/custom-package.html" }, 
        methods = { "GET", "POST" }
       
)
public class CustomPackageCreation extends SlingAllMethodsServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	@Reference
    private Packaging packaging;
	
	@Override
	  protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
		 	
		 	 final JcrPackageManager jcrPackageManager = packaging.getPackageManager(request.getResourceResolver().adaptTo(Session.class));
			try {
				JcrPackage pack = jcrPackageManager.create("Dileep", "Dileep-test", "1.0");
				JcrPackageDefinition definition = pack.getDefinition();
				DefaultWorkspaceFilter filter = new DefaultWorkspaceFilter();
					/*nodePaths is the List containing the list of paths*/
					
					    PathFilterSet pathFilterSet = new PathFilterSet();
					    pathFilterSet.setRoot("/apps/test/components/common");
					    filter.add(pathFilterSet);
					    definition.setFilter(filter, true);
					    boolean autoSave = true;
					    ProgressTrackerListener listener = new DefaultProgressListener();
					    jcrPackageManager.assemble(pack,listener);//This method will build the package.  
					    request.getResourceResolver().adaptTo(Session.class).save();
					    response.getWriter().write("Go to package manager to see your package");
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PackageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			   
		 	
	 }

}
