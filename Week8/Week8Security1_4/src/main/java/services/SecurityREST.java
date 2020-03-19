package services;

import entities.Users;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Runi
 */
//@Stateless
@Path("demo")
public class SecurityREST extends AbstractFacade<Users> {

     @Context
    private UriInfo context;
    
    @Context
    SecurityContext securityContext;
    
    @PersistenceContext(unitName = "com.mycompany_Week8Security_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public SecurityREST() {
        super(Users.class);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return String.format("{\"msg\":\"Hello World\" }" );
    }
    
    @GET
    @Path("/user")
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public String user() {
        return "Hello user";
    }
    
    @GET
    @RolesAllowed("admin")
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON)
    public String admin() {
        return "Hello admin";
    }
    
    

//    @POST
//    @Override
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void create(Users entity) {
//        super.create(entity);
//    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") String id, Users entity) {
//        super.edit(entity);
//    }
//
//    @DELETE
//    @Path("{id}")
//    public void remove(@PathParam("id") String id) {
//        super.remove(super.find(id));
//    }
//
//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Users find(@PathParam("id") String id) {
//        return super.find(id);
//    }
//
//    @GET
//    @Override
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Users> findAll() {
//        return super.findAll();
//    }
//
//    @GET
//    @Path("{from}/{to}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }
//
//    @GET
//    @Path("count")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String countREST() {
//        return String.valueOf(super.count());
//    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
