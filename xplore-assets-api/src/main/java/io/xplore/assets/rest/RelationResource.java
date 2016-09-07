package io.xplore.assets.rest;

import io.xplore.assets.Consts;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaRelation;
import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;
import io.xplore.assets.service.RelationService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 * Relation related services
 */
@Path("/relations")
@RequestScoped
public class RelationResource extends BaseResource {

    @Inject
    private Logger log;

    @Inject
    private RelationService service;

    // ------------------ Main entity actions --------------------------------------------------------------------------

    /**
     * Get list of relations
     * @param accessToken Access token
     * @param parentKey Filter by parent column key
     * @param filter Filter by field in the format of field:value (multiple values)
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @return QueryResponse[MdaRelation]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaRelation> find(@HeaderParam("X-Access-Token") String accessToken,
                                           @QueryParam("parent") @DefaultValue("-1") int parentKey,
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

            return this.service.find(parentKey, page, pageSize, filtering, sorting);
        } catch (Exception e) {
            return new QueryResponse<MdaRelation>(e.getMessage());
        }
    }

    /**
     * Get specific relation data
     * @param accessToken Access token
     * @param relationKey Relation key
     * @return EntityResponse[MdaRelation]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{relationKey}")
    public EntityResponse<MdaRelation> get(@HeaderParam("X-Access-Token") String accessToken, @PathParam("relationKey") int relationKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.get(relationKey);
        } catch (Exception e) {
            return new EntityResponse<MdaRelation>(e.getMessage());
        }
    }

    /**
     * Update system data (editable fields only)
     * @param accessToken Access token
     * @param relationKey Relation key
     * @param relation Relation object to update
     * @return EntityResponse[MdaRelation]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{relationKey}")
    public EntityResponse<MdaRelation> set(@HeaderParam("X-Access-Token") String accessToken, @PathParam("relationKey") int relationKey, MdaRelation relation) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.set(relation);
        } catch (Exception e) {
            return new EntityResponse<MdaRelation>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
