package io.xplore.assets.rest;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaServer;
import io.xplore.assets.model.MdaSystem;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Server related services
 */
@Path("/servers")
@RequestScoped
public class ServerResource extends BaseResource {

    @Inject
    private Logger log;

    // ------------------ Main entity actions --------------------------------------------------------------------------

    /**
     * Get list of servers
     * @param accessToken Access token
     * @return QueryResponse[MdaServer]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaServer> find(@HeaderParam("X-Access-Token") String accessToken) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            throw new NotImplementedException();
            //return this.service.findSystems(token);
        } catch (Exception e) {
            return new QueryResponse<MdaServer>(e.getMessage());
        }
    }

    /**
     * Get specific server data
     * @param accessToken Access token
     * @param serverKey Server key
     * @return EntityResponse[MdaServer]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{serverKey}")
    public EntityResponse<MdaServer> get(@HeaderParam("X-Access-Token") String accessToken, @PathParam("serverKey") int serverKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            throw new NotImplementedException();
            //return this.service.findSystems(token);
        } catch (Exception e) {
            return new EntityResponse<MdaServer>(e.getMessage());
        }
    }

    /**
     * Update system data (editable fields only)
     * @param accessToken Access token
     * @param serverKey Server key
     * @param server Server object to update
     * @return EntityResponse[MdaServer]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{serverKey}")
    public EntityResponse<MdaServer> set(@HeaderParam("X-Access-Token") String accessToken, @PathParam("serverKey") int serverKey, MdaServer server) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            throw new NotImplementedException();
        } catch (Exception e) {
            return new EntityResponse<MdaServer>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
