package io.xplore.assets.rest;

import io.xplore.assets.Consts;
import io.xplore.assets.messages.EntitiesResponse;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.*;
import io.xplore.assets.service.SystemService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
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
     * @param filter Filter by field in the format of field:value (multiple values)
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

            return this.service.find(page, pageSize, filtering, sorting);
        } catch (Exception e) {
            return new QueryResponse<MdaSystem>(e.getMessage());
        }
    }

    /**
     * Search list of systems
     * @param accessToken Access token
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @param filters List of filters by field
     * @return QueryResponse[MdaSystem]
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/search")
    public QueryResponse<MdaSystem> search(@HeaderParam("X-Access-Token") String accessToken,
                                           @QueryParam("sort") @DefaultValue("") String sort,
                                           @QueryParam("page") @DefaultValue("1") int page,
                                           @QueryParam("pageSize") @DefaultValue("0") int pageSize,
                                           List<Filter> filters) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            // Pagination
            page = (page > 0) ? page : 1;
            pageSize = (pageSize > 0) ? pageSize : Consts.DB_PAGE_SIZE;

            QuerySort sorting = QuerySort.create(sort);
            QueryFilter filtering = QueryFilter.createFromFilters(filters);

            return this.service.find(page, pageSize, filtering, sorting);
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
            return this.service.set(system);
        } catch (Exception e) {
            return new EntityResponse<MdaSystem>(e.getMessage());
        }
    }

    // ------------------ System related servers actions ---------------------------------------------------------------

    /**
     * Get system related servers
     * @param accessToken Access token
     * @param systemKey System key
     * @return EntityResponse[MdaServer]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{systemKey}/servers")
    public EntitiesResponse<MdaServer> getServers(@HeaderParam("X-Access-Token") String accessToken, @PathParam("systemKey") int systemKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.getServers(systemKey);
        } catch (Exception e) {
            return new EntitiesResponse<MdaServer>(e.getMessage());
        }
    }

    /**
     * Link servers to system
     * @param accessToken Access token
     * @param systemKey System key
     * @param serversKeys List of keys to link
     * @return EntityResponse[MdaServer]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{systemKey}/servers")
    public EntitiesResponse<MdaServer> linkServers(@HeaderParam("X-Access-Token") String accessToken,
                                                   @PathParam("systemKey") int systemKey,
                                                   int[] serversKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.linkServers(systemKey, serversKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaServer>(e.getMessage());
        }
    }

    /**
     * Unlink servers from system
     * @param accessToken Access token
     * @param systemKey System key
     * @param serversKeys List of keys to unlink
     * @return EntityResponse[MdaServer]
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{systemKey}/servers")
    public EntitiesResponse<MdaServer> unlinkServers(@HeaderParam("X-Access-Token") String accessToken,
                                                     @PathParam("systemKey") int systemKey,
                                                     int[] serversKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.unlinkServers(systemKey, serversKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaServer>(e.getMessage());
        }
    }

    // ------------------ System related entities actions --------------------------------------------------------------

    /**
     * Get system related entities
     * @param accessToken Access token
     * @param systemKey System key
     * @return EntityResponse[MdaBusinessEntity]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{systemKey}/entities")
    public EntitiesResponse<MdaBusinessEntity> getEntities(@HeaderParam("X-Access-Token") String accessToken,
                                                           @PathParam("systemKey") int systemKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.getEntities(systemKey);
        } catch (Exception e) {
            return new EntitiesResponse<MdaBusinessEntity>(e.getMessage());
        }
    }

    /**
     * Link entities to system
     * @param accessToken Access token
     * @param systemKey System key
     * @param entitiesKeys List of keys to link
     * @return EntityResponse[MdaBusinessEntity]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{systemKey}/entities")
    public EntitiesResponse<MdaBusinessEntity> linkEntities(@HeaderParam("X-Access-Token") String accessToken,
                                                            @PathParam("systemKey") int systemKey,
                                                            int[] entitiesKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.linkEntities(systemKey, entitiesKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaBusinessEntity>(e.getMessage());
        }
    }

    /**
     * Unlink entities from system
     * @param accessToken Access token
     * @param systemKey System key
     * @param entitiesKeys List of keys to unlink
     * @return EntityResponse[MdaBusinessEntity]
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{systemKey}/entities")
    public EntitiesResponse<MdaBusinessEntity> unlinkEntities(@HeaderParam("X-Access-Token") String accessToken,
                                                              @PathParam("systemKey") int systemKey,
                                                              int[] entitiesKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.unlinkEntities(systemKey, entitiesKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaBusinessEntity>(e.getMessage());
        }
    }
}
