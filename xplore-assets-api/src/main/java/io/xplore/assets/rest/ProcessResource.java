package io.xplore.assets.rest;

import io.xplore.assets.Consts;
import io.xplore.assets.messages.EntitiesResponse;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.*;
import io.xplore.assets.service.ProcessService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 * Business entities related services
 */
@Path("/processes")
@RequestScoped
public class ProcessResource extends BaseResource {

    @Inject
    private Logger log;

    @Inject
    private ProcessService service;

    // ------------------ Main entity actions --------------------------------------------------------------------------
    /**
     * Get list of processes
     * @param accessToken Access token
     * @param filter Filter by field in the format of field:value (multiple values)
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @return QueryResponse[MdaProcess]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaProcess> find(@HeaderParam("X-Access-Token") String accessToken,
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
            return new QueryResponse<MdaProcess>(e.getMessage());
        }
    }

    /**
     * Search list of processes
     * @param accessToken Access token
     * @param serverKey Filter by server key
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @param filters List of filters by field
     * @return QueryResponse[MdaProcess]
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/search")
    public QueryResponse<MdaProcess> search(@HeaderParam("X-Access-Token") String accessToken,
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
            return new QueryResponse<MdaProcess>(e.getMessage());
        }
    }

    /**
     * Get specific process data
     * @param accessToken Access token
     * @param processKey Process key
     * @return EntityResponse[MdaProcess]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{processKey}")
    public EntityResponse<MdaProcess> get(@HeaderParam("X-Access-Token") String accessToken, @PathParam("processKey") int processKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.get(processKey);
        } catch (Exception e) {
            return new EntityResponse<MdaProcess>(e.getMessage());
        }
    }

    /**
     * Update system data (editable fields only)
     * @param accessToken Access token
     * @param processKey Process key
     * @param process Process object to update
     * @return EntityResponse[MdaProcess]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{processKey}")
    public EntityResponse<MdaProcess> set(@HeaderParam("X-Access-Token") String accessToken, @PathParam("processKey") int processKey, MdaProcess process) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.set(process);
        } catch (Exception e) {
            return new EntityResponse<MdaProcess>(e.getMessage());
        }
    }

    // ------------------ Related Systems actions ----------------------------------------------------------------------

    /**
     * Get process related systems
     * @param accessToken Access token
     * @param processKey Process key
     * @return EntityResponse[MdaSystem]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{processKey}/systems")
    public EntitiesResponse<MdaSystem> getSystems(@HeaderParam("X-Access-Token") String accessToken, @PathParam("processKey") int processKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.getSystems(processKey);
        } catch (Exception e) {
            return new EntitiesResponse<MdaSystem>(e.getMessage());
        }
    }

    /**
     * Link systems to process
     * @param accessToken Access token
     * @param processKey Process key
     * @param systemsKeys List of system keys to link
     * @return EntityResponse[MdaSystem]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{processKey}/systems")
    public EntitiesResponse<MdaSystem> linkSystems(@HeaderParam("X-Access-Token") String accessToken,
                                                   @PathParam("processKey") int processKey,
                                                   int[] systemsKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.linkSystems(processKey, systemsKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaSystem>(e.getMessage());
        }
    }

    /**
     * Unlink systems from process
     * @param accessToken Access token
     * @param processKey Process key
     * @param systemsKeys List of systems keys to link
     * @return EntityResponse[MdaSystem]
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{processKey}/systems")
    public EntitiesResponse<MdaSystem> unlinkSystems(@HeaderParam("X-Access-Token") String accessToken,
                                                   @PathParam("processKey") int processKey,
                                                   int[] systemsKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.unlinkSystems(processKey, systemsKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaSystem>(e.getMessage());
        }
    }

    // ------------------ Related Tables actions -----------------------------------------------------------------------

    /**
     * Get process related tables
     * @param accessToken Access token
     * @param processKey Process key
     * @return EntityResponse[MdaTable]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{processKey}/tables")
    public EntitiesResponse<MdaTable> getTables(@HeaderParam("X-Access-Token") String accessToken, @PathParam("processKey") int processKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.getTables(processKey);
        } catch (Exception e) {
            return new EntitiesResponse<MdaTable>(e.getMessage());
        }
    }


    /**
     * Link tables to process
     * @param accessToken Access token
     * @param processKey Process key
     * @param tablesKeys List of tables keys to link
     * @return EntityResponse[MdaTable]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{processKey}/tables")
    public EntitiesResponse<MdaTable> linkTables(@HeaderParam("X-Access-Token") String accessToken,
                                                   @PathParam("processKey") int processKey,
                                                   int[] tablesKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.linkTables(processKey, tablesKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaTable>(e.getMessage());
        }
    }

    /**
     * Unlink tables from process
     * @param accessToken Access token
     * @param processKey Process key
     * @param tablesKeys List of tables keys to link
     * @return EntityResponse[MdaSystem]
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{processKey}/tables")
    public EntitiesResponse<MdaTable> unlinkTables(@HeaderParam("X-Access-Token") String accessToken,
                                                     @PathParam("processKey") int processKey,
                                                     int[] tablesKeys) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.unlinkTables(processKey, tablesKeys);
        } catch (Exception e) {
            return new EntitiesResponse<MdaTable>(e.getMessage());
        }
    }
}
