package io.xplore.assets.rest;

import io.xplore.assets.Consts;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaSchema;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;
import io.xplore.assets.service.SchemaService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 * Schema related services
 */
@Path("/schemas")
@RequestScoped
public class SchemaResource extends BaseResource {

    @Inject
    private Logger log;

    @Inject
    private SchemaService service;

    // ------------------ Main entity actions --------------------------------------------------------------------------

    /**
     * Get list of schemas
     * @param accessToken Access token
     * @param databaseKey Filter by database key
     * @param filter Filter by field in the format of field:value (multiple values)
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @return QueryResponse[MdaSchema]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaSchema> find(@HeaderParam("X-Access-Token") String accessToken,
                                         @QueryParam("database") @DefaultValue("-1") int databaseKey,
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

            return this.service.find(databaseKey, page, pageSize, filtering, sorting);
        } catch (Exception e) {
            return new QueryResponse<MdaSchema>(e.getMessage());
        }
    }

    /**
     * Get specific schema data
     * @param accessToken Access token
     * @param schemaKey schema key
     * @return EntityResponse[MdaSchema]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{schemaKey}")
    public EntityResponse<MdaSchema> get(@HeaderParam("X-Access-Token") String accessToken, @PathParam("schemaKey") int schemaKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.get(schemaKey);
        } catch (Exception e) {
            return new EntityResponse<MdaSchema>(e.getMessage());
        }
    }

    /**
     * Update schema data (editable fields only)
     * @param accessToken Access token
     * @param schemaKey schema key
     * @param schema Schema object to update
     * @return EntityResponse[MdaSchema]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{schemaKey}")
    public EntityResponse<MdaSchema> set(@HeaderParam("X-Access-Token") String accessToken, @PathParam("schemaKey") int schemaKey, MdaSchema schema) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.set(schema);
        } catch (Exception e) {
            return new EntityResponse<MdaSchema>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
