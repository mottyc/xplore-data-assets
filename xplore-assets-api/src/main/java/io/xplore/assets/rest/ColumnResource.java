package io.xplore.assets.rest;

import io.xplore.assets.Consts;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaColumn;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;
import io.xplore.assets.service.ColumnService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 * Columns related services
 */
@Path("/columns")
@RequestScoped
public class ColumnResource extends BaseResource {

    @Inject
    private Logger log;

    @Inject
    private ColumnService service;

    // ------------------ Main entity actions --------------------------------------------------------------------------

    /**
     * Get list of columns
     * @param accessToken Access token
     * @param tableKey Filter by table key
     * @param filter Filter by field in the format of field:value (multiple values)
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @return QueryResponse[MdaColumn]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaColumn> find(@HeaderParam("X-Access-Token") String accessToken,
                                         @QueryParam("table") @DefaultValue("-1") int tableKey,
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
            QueryFilter filtering = QueryFilter.create(filter);

            return this.service.find(tableKey, page, pageSize, filtering, sorting);
        } catch (Exception e) {
            return new QueryResponse<MdaColumn>(e.getMessage());
        }
    }

    /**
     * Get specific column data
     * @param accessToken Access token
     * @param columnKey Column key
     * @return EntityResponse[MdaColumn]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{columnKey}")
    public EntityResponse<MdaColumn> get(@HeaderParam("X-Access-Token") String accessToken, @PathParam("columnKey") int columnKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.get(columnKey);
        } catch (Exception e) {
            return new EntityResponse<MdaColumn>(e.getMessage());
        }
    }

    /**
     * Update column data (editable fields only)
     * @param accessToken Access token
     * @param columnKey Column key
     * @param column Column object to update
     * @return EntityResponse[MdaColumn]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{columnKey}")
    public EntityResponse<MdaColumn> set(@HeaderParam("X-Access-Token") String accessToken, @PathParam("columnKey") int columnKey, MdaColumn column) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.set(column);
        } catch (Exception e) {
            return new EntityResponse<MdaColumn>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
