package io.xplore.assets.rest;

import io.xplore.assets.Consts;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaDatabase;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;
import io.xplore.assets.service.DatabaseService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 * Database related services
 */
@Path("/databases")
@RequestScoped
public class DatabaseResource extends BaseResource {

    @Inject
    private Logger log;

    @Inject
    private DatabaseService service;

    // ------------------ Main entity actions --------------------------------------------------------------------------

    /**
     * Get list of databases
     * @param accessToken Access token
     * @param serverKey Filter by server key
     * @param filter Filter by field in the format of field:value (multiple values)
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @return QueryResponse[MdaDatabase]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaDatabase> find(@HeaderParam("X-Access-Token") String accessToken,
                                           @QueryParam("server") @DefaultValue("-1") int serverKey,
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

            return this.service.find(serverKey, page, pageSize, filtering, sorting);
        } catch (Exception e) {
            return new QueryResponse<MdaDatabase>(e.getMessage());
        }
    }

    /**
     * Get specific database data
     * @param accessToken Access token
     * @param databaseKey Database key
     * @return EntityResponse[MdaDatabase]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{databaseKey}")
    public EntityResponse<MdaDatabase> get(@HeaderParam("X-Access-Token") String accessToken, @PathParam("databaseKey") int databaseKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.get(databaseKey);
        } catch (Exception e) {
            return new EntityResponse<MdaDatabase>(e.getMessage());
        }
    }

    /**
     * Update database data (editable fields only)
     * @param accessToken Access token
     * @param databaseKey System key
     * @param database Database object to update
     * @return EntityResponse[MdaDatabase]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{databaseKey}")
    public EntityResponse<MdaDatabase> set(@HeaderParam("X-Access-Token") String accessToken, @PathParam("databaseKey") int databaseKey, MdaDatabase database) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            throw new NotImplementedException();
        } catch (Exception e) {
            return new EntityResponse<MdaDatabase>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
