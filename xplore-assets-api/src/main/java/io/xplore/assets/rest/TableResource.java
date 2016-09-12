package io.xplore.assets.rest;

import io.xplore.assets.Consts;
import io.xplore.assets.messages.EntitiesResponse;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.*;
import io.xplore.assets.service.TableService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 * Table related services
 */
@Path("/tables")
@RequestScoped
public class TableResource extends BaseResource {

    @Inject
    private Logger log;

    @Inject
    private TableService service;

    // ------------------ Main entity actions --------------------------------------------------------------------------

    /**
     * Get list of tables
     * @param accessToken Access token
     * @param schemaKey Filter by schema
     * @param filter Filter by field in the format of field:value (multiple values)
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @return QueryResponse[MdaTable]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaTable> find(@HeaderParam("X-Access-Token") String accessToken,
                                        @QueryParam("schema") @DefaultValue("-1") int schemaKey,
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

            return this.service.find(schemaKey, page, pageSize, filtering, sorting);
        } catch (Exception e) {
            return new QueryResponse<MdaTable>(e.getMessage());
        }
    }

    /**
     * Search list of tables
     * @param accessToken Access token
     * @param schemaKey Filter by schema
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @param filters List of filters by field
     * @return QueryResponse[MdaTable]
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/search")
    public QueryResponse<MdaTable> search(@HeaderParam("X-Access-Token") String accessToken,
                                          @QueryParam("schema") @DefaultValue("-1") int schemaKey,
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

            return this.service.find(schemaKey, page, pageSize, filtering, sorting);
        } catch (Exception e) {
            return new QueryResponse<MdaTable>(e.getMessage());
        }
    }

    /**
     * Get specific table data
     * @param accessToken Access token
     * @param tableKey Table key
     * @return EntityResponse[MdaTable]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{tableKey}")
    public EntityResponse<MdaTable> get(@HeaderParam("X-Access-Token") String accessToken, @PathParam("tableKey") int tableKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.get(tableKey);
        } catch (Exception e) {
            return new EntityResponse<MdaTable>(e.getMessage());
        }
    }

    /**
     * Update table data (editable fields only)
     * @param accessToken Access token
     * @param tableKey Table key
     * @param table Table object to update
     * @return EntityResponse[MdaTable]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{tableKey}")
    public EntityResponse<MdaTable> set(@HeaderParam("X-Access-Token") String accessToken, @PathParam("tableKey") int tableKey, MdaTable table) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.set(table);
        } catch (Exception e) {
            return new EntityResponse<MdaTable>(e.getMessage());
        }
    }

    // ------------------ Systems related actions ----------------------------------------------------------------------

    /**
     * Get table related systems
     * @param accessToken Access token
     * @param tableKey Table key
     * @return EntityResponse[MdaSystem]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{tableKey}/systems")
    public EntitiesResponse<MdaSystem> getSystems(@HeaderParam("X-Access-Token") String accessToken, @PathParam("tableKey") int tableKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.getSystems(tableKey);
        } catch (Exception e) {
            return new EntitiesResponse<MdaSystem>(e.getMessage());
        }
    }

    /**
     * Link systems to table
     * @param accessToken Access token
     * @param tableKey Table key
     * @param systemsKeys List of keys to link
     * @return EntityResponse[MdaSystem]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{tableKey}/systems")
    public EntitiesResponse<MdaSystem> linkSystems(@HeaderParam("X-Access-Token") String accessToken, @PathParam("tableKey") int tableKey, int[] systemsKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.linkSystems(tableKey, systemsKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaSystem>(e.getMessage());
        }
    }

    /**
     * Unlink systems from table
     * @param accessToken Access token
     * @param tableKey Table key
     * @param systemsKeys List of keys to unlink
     * @return EntityResponse[MdaSystem]
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{tableKey}/systems")
    public EntitiesResponse<MdaSystem> unlinkSystems(@HeaderParam("X-Access-Token") String accessToken, @PathParam("tableKey") int tableKey, int[] systemsKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.unlinkSystems(tableKey, systemsKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaSystem>(e.getMessage());
        }
    }

    // ------------------ Business entities related actions ------------------------------------------------------------

    /**
     * Get table related entities
     * @param accessToken Access token
     * @param tableKey Table key
     * @return EntityResponse[MdaBusinessEntity]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{tableKey}/entities")
    public EntitiesResponse<MdaBusinessEntity> getEntities(@HeaderParam("X-Access-Token") String accessToken, @PathParam("tableKey") int tableKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.getEntities(tableKey);
        } catch (Exception e) {
            return new EntitiesResponse<MdaBusinessEntity>(e.getMessage());
        }
    }

    /**
     * Link entities to table
     * @param accessToken Access token
     * @param tableKey Table key
     * @param entitiesKeys List of keys to link
     * @return EntityResponse[MdaBusinessEntity]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{tableKey}/entities")
    public EntitiesResponse<MdaBusinessEntity> linkEntities(@HeaderParam("X-Access-Token") String accessToken, @PathParam("tableKey") int tableKey, int[] entitiesKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.linkEntities(tableKey, entitiesKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaBusinessEntity>(e.getMessage());
        }
    }

    /**
     * Unlink entities from table
     * @param accessToken Access token
     * @param tableKey Table key
     * @param entitiesKeys List of keys to unlink
     * @return EntityResponse[MdaBusinessEntity]
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{tableKey}/entities")
    public EntitiesResponse<MdaBusinessEntity> unlinkEntities(@HeaderParam("X-Access-Token") String accessToken, @PathParam("tableKey") int tableKey, int[] entitiesKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.unlinkEntities(tableKey, entitiesKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaBusinessEntity>(e.getMessage());
        }
    }

}
