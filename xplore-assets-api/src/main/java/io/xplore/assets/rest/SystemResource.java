package io.xplore.assets.rest;

import io.xplore.assets.Consts;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaSystem;
import io.xplore.assets.service.SystemService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * System related services
 */
@Path("/systems")
@RequestScoped
public class SystemResource extends BaseResource {

    @Inject
    private Logger log;

    @Inject
    private SystemService service;

    // ------------------ Main entity actions --------------------------------------------------------------------------

    /**
     * Get list of systems
     * @param accessToken Access token
     * @param filter Filter by name
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @return QueryResponse[MdaSystem]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaSystem> find(@HeaderParam("X-Access-Token") String accessToken,
                                         @QueryParam("filter") @DefaultValue("") String filter,
                                         @QueryParam("sort") @DefaultValue("") String sort,
                                         @QueryParam("page") @DefaultValue("1") int page,
                                         @QueryParam("pageSize") @DefaultValue("0") int pageSize) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            // Pagination
            page = (page > 0) ? page : 1;
            pageSize = (pageSize > 0) ? pageSize : Consts.DB_PAGE_SIZE;

            return this.service.find(page, pageSize);
        } catch (Exception e) {
            return new QueryResponse<MdaSystem>(e.getMessage());
        }
    }

    /**
     * Get specific system data
     * @param accessToken Access token
     * @param systemKey System key
     * @return EntityResponse[MdaSystem]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{systemKey}")
    public EntityResponse<MdaSystem> get(@HeaderParam("X-Access-Token") String accessToken, @PathParam("systemKey") int systemKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.get(systemKey);
        } catch (Exception e) {
            return new EntityResponse<MdaSystem>(e.getMessage());
        }
    }

    /**
     * Update system data (editable fields only)
     * @param accessToken Access token
     * @param systemKey System key
     * @param system System object to update
     * @return EntityResponse[MdaSystem]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{systemKey}")
    public EntityResponse<MdaSystem> set(@HeaderParam("X-Access-Token") String accessToken, @PathParam("systemKey") int systemKey, MdaSystem system) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            throw new NotImplementedException();
        } catch (Exception e) {
            return new EntityResponse<MdaSystem>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
