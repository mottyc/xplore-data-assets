package io.xplore.assets.rest;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaTable;
import io.xplore.assets.model.MdaUser;
import io.xplore.assets.service.UserService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * User related services
 */
@Path("/users")
@RequestScoped
public class UserResource extends BaseResource {

    @Inject
    private Logger log;

    @Inject
    private UserService service;

    // ------------------ Main entity actions --------------------------------------------------------------------------

    /**
     * Get list of users
     * @param accessToken Access token
     * @return QueryResponse[MdaUser]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaUser> find(@HeaderParam("X-Access-Token") String accessToken) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            throw new NotImplementedException();
            //return this.service.findSystems(token);
        } catch (Exception e) {
            return new QueryResponse<MdaUser>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
