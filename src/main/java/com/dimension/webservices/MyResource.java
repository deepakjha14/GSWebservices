package com.dimension.webservices;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.server.JSONP;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
    */    
    static final List<loginBean> changes = new LinkedList<loginBean>();

    static {
        changes.add(new loginBean("Deepak", "Jha", 2));
        changes.add(new loginBean("Deepak", "Jha", 2));
        changes.add(new loginBean("Deepak", "Jha", 2));
        changes.add(new loginBean("Deepak", "Jha", 2));
        changes.add(new loginBean("Deepak", "Jha", 2));
    }
        
    @GET
    @JSONP(queryParam = "callback")
    @Produces({"application/x-javascript"})
    public List<loginBean> getIt() {
        return changes;
    }
    
    @POST    
    @Path("count")
    //@JSONP(queryParam = "callback")
    @Produces({"application/x-javascript"})
    //@Consumes(MediaType.APPLICATION_JSON)
    public String getPost(@FormParam("username") String usrName, @FormParam("password") String pwd) {
        // This one is to establisht the connection with the database.
        //We have hardcoded this DB details but later we need to develop the-
        //mechanism to read it from the xml property file.
        
        GStoreDBConnect dbConnect = new GStoreDBConnect("com.mysql.jdbc.Driver",3306,"grocerystore_db", "root", "Creator@123");
        Boolean pwdMatch = dbConnect.checkCredentials(usrName, pwd);
        if (pwdMatch.equals(true)){
            return "Success Ho Gaya";
        }else{
            return "Failed Ho Gaya";
        }
    }
}
