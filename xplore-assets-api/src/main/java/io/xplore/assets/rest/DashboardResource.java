package io.xplore.assets.rest;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.Statistics;
import io.xplore.assets.service.DashboardService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * User related services
 */
@Path("/dashboard")
@RequestScoped
public class DashboardResource extends BaseResource {

    @Inject
    private Logger log;

    @Inject
    private DashboardService service;

    // ------------------ Main entity actions --------------------------------------------------------------------------

    /**
     * Get dashboard statistics
     * @param accessToken Access token
     * @return EntityResponse[Statistics]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public EntityResponse<Statistics> get(@HeaderParam("X-Access-Token") String accessToken) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.get();
        } catch (Exception e) {
            return new EntityResponse<Statistics>(e.getMessage());
        }
    }

}
