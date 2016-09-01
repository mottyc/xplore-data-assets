package io.xplore.assets.rest;

import io.xplore.assets.Consts;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaBusinessEntity;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;
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
            QueryFilter filtering = QueryFilter.create(filter);

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
            throw new NotImplementedException();
        } catch (Exception e) {
            return new EntityResponse<MdaBusinessEntity>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
