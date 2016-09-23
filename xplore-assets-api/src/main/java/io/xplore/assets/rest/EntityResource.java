package io.xplore.assets.rest;

import io.xplore.assets.Consts;
import io.xplore.assets.messages.EntitiesResponse;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.*;
import io.xplore.assets.service.BusinessEntityService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 * Business entities related services
 */
@Path("/entities")
@RequestScoped
public class EntityResource extends BaseResource {

    @Inject
    private Logger log;

    @Inject
    private BusinessEntityService service;

    // ------------------ Main entity actions --------------------------------------------------------------------------
    /**
     * Get list of business entities
     * @param accessToken Access token
     * @param filter Filter by field in the format of field:value (multiple values)
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @return QueryResponse[MdaBusinessEntity]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaBusinessEntity> find(@HeaderParam("X-Access-Token") String accessToken,
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
            return new QueryResponse<MdaBusinessEntity>(e.getMessage());
        }
    }

    /**
     * Search list of entities
     * @param accessToken Access token
     * @param serverKey Filter by server key
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @param filters List of filters by field
     * @return QueryResponse[MdaBusinessEntity]
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/search")
    public QueryResponse<MdaBusinessEntity> search(@HeaderParam("X-Access-Token") String accessToken,
                                             @QueryParam("server") @DefaultValue("-1") int serverKey,
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
            return new QueryResponse<MdaBusinessEntity>(e.getMessage());
        }
    }

    /**
     * Get specific business entity data
     * @param accessToken Access token
     * @param entityKey Entity key
     * @return EntityResponse[MdaSystem]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{entityKey}")
    public EntityResponse<MdaBusinessEntity> get(@HeaderParam("X-Access-Token") String accessToken, @PathParam("entityKey") int entityKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.get(entityKey);
        } catch (Exception e) {
            return new EntityResponse<MdaBusinessEntity>(e.getMessage());
        }
    }

    /**
     * Update system data (editable fields only)
     * @param accessToken Access token
     * @param entityKey Entity key
     * @param entity Business entity object to update
     * @return EntityResponse[Account]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{entityKey}")
    public EntityResponse<MdaBusinessEntity> set(@HeaderParam("X-Access-Token") String accessToken, @PathParam("entityKey") int entityKey, MdaBusinessEntity entity) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.set(entity);
        } catch (Exception e) {
            return new EntityResponse<MdaBusinessEntity>(e.getMessage());
        }
    }

    // ------------------ Related Systems actions ----------------------------------------------------------------------

    /**
     * Get business entity related systems
     * @param accessToken Access token
     * @param entityKey Entity key
     * @return EntityResponse[MdaSystem]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{entityKey}/systems")
    public EntitiesResponse<MdaSystem> getSystems(@HeaderParam("X-Access-Token") String accessToken, @PathParam("entityKey") int entityKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.getSystems(entityKey);
        } catch (Exception e) {
            return new EntitiesResponse<MdaSystem>(e.getMessage());
        }
    }

    /**
     * Link systems to business entity
     * @param accessToken Access token
     * @param entityKey Entity key
     * @param systemsKeys List of system keys to link
     * @return EntityResponse[MdaSystem]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{entityKey}/systems")
    public EntitiesResponse<MdaSystem> linkSystems(@HeaderParam("X-Access-Token") String accessToken,
                                                   @PathParam("entityKey") int entityKey,
                                                   int[] systemsKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.linkSystems(entityKey, systemsKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaSystem>(e.getMessage());
        }
    }

    /**
     * Unlink systems from business entity
     * @param accessToken Access token
     * @param entityKey Entity key
     * @param systemsKeys List of systems keys to link
     * @return EntityResponse[MdaSystem]
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{entityKey}/systems")
    public EntitiesResponse<MdaSystem> unlinkSystems(@HeaderParam("X-Access-Token") String accessToken,
                                                   @PathParam("entityKey") int entityKey,
                                                   int[] systemsKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.unlinkSystems(entityKey, systemsKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaSystem>(e.getMessage());
        }
    }

    // ------------------ Related Tables actions -----------------------------------------------------------------------

    /**
     * Get business entity related tables
     * @param accessToken Access token
     * @param entityKey Entity key
     * @return EntityResponse[MdaTable]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{entityKey}/tables")
    public EntitiesResponse<MdaTable> getTables(@HeaderParam("X-Access-Token") String accessToken, @PathParam("entityKey") int entityKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.getTables(entityKey);
        } catch (Exception e) {
            return new EntitiesResponse<MdaTable>(e.getMessage());
        }
    }


    /**
     * Link tables to business entity
     * @param accessToken Access token
     * @param entityKey Entity key
     * @param tablesKeys List of tables keys to link
     * @return EntityResponse[MdaTable]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{entityKey}/tables")
    public EntitiesResponse<MdaTable> linkTables(@HeaderParam("X-Access-Token") String accessToken,
                                                   @PathParam("entityKey") int entityKey,
                                                   int[] tablesKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.linkTables(entityKey, tablesKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaTable>(e.getMessage());
        }
    }

    /**
     * Unlink tables from business entity
     * @param accessToken Access token
     * @param entityKey Entity key
     * @param tablesKeys List of tables keys to link
     * @return EntityResponse[MdaSystem]
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{entityKey}/tables")
    public EntitiesResponse<MdaTable> unlinkTables(@HeaderParam("X-Access-Token") String accessToken,
                                                     @PathParam("entityKey") int entityKey,
                                                     int[] tablesKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.unlinkTables(entityKey, tablesKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaTable>(e.getMessage());
        }
    }
}
