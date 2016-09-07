package io.xplore.assets.rest;

import io.xplore.assets.Consts;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaServer;
import io.xplore.assets.model.MdaSystem;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;
import io.xplore.assets.service.ServerService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 * Server related services
 */
@Path("/servers")
@RequestScoped
public class ServerResource extends BaseResource {

    @Inject
    private Logger log;

    @Inject
    private ServerService service;

    // ------------------ Main entity actions --------------------------------------------------------------------------

    /**
     * Get list of servers
     * @param accessToken Access token
     * @param type Filter by server type
     * @param filter Filter by field in the format of field:value (multiple values)
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @return QueryResponse[MdaServer]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaServer> find(@HeaderParam("X-Access-Token") String accessToken,
                                         @QueryParam("type") @DefaultValue("") String type,
                                         @QueryParam("filter") @DefaultValue("") List<String> filter,
                                         @QueryParam("sort") @DefaultValue("") String sort,
                                         @QueryParam("page") @DefaultValue("1") int page,
                                         @QueryParam("pageSize") @DefaultValue("0") int pageSize) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            // Pagination
            page = (page > 0) ? page : 1;
            pageSize = (pageSize > 0) ? pageSize : Consts.DB_PAGE_SIZE;

            QuerySort sorting = QuerySort.create(sort);
            QueryFilter filtering = QueryFilter.createFromList(filter);

            return this.service.find(type, page, pageSize, filtering, sorting);
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

            return this.service.get(serverKey);
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
            return this.service.set(server);
        } catch (Exception e) {
            return new EntityResponse<MdaServer>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
