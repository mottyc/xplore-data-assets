package io.xplore.assets.rest;

import io.xplore.assets.Consts;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.*;
import io.xplore.assets.service.UserService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 * User related services
 */
@Path("/users")
@RequestScoped
public class UserResource extends BaseResource {

    @Inject
    private Logger log;

    @Inject
    private UserService service;

    // ------------------ Main entity actions --------------------------------------------------------------------------

    /**
     * Get list of users
     * @param accessToken Access token
     * @param filter Filter by field in the format of field:value (multiple values)
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @return QueryResponse[MdaUser]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaUser> find(@HeaderParam("X-Access-Token") String accessToken,
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
            return new QueryResponse<MdaUser>(e.getMessage());
        }
    }

    /**
     * Search list of users
     * @param accessToken Access token
     * @param sort Sort by field in the format of field:{asc|desc}
     * @param page Page number (for pagination)
     * @param pageSize Number of results per page (default size: 50 items)
     * @param filters List of filters by field
     * @return QueryResponse[MdaUser]
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/search")
    public QueryResponse<MdaUser> search(@HeaderParam("X-Access-Token") String accessToken,
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
            return new QueryResponse<MdaUser>(e.getMessage());
        }
    }

    /**
     * Get specific user data
     * @param accessToken Access token
     * @param userKey User key
     * @return EntityResponse[MdaUser]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{userKey}")
    public EntityResponse<MdaUser> get(@HeaderParam("X-Access-Token") String accessToken, @PathParam("userKey") String userKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.get(userKey);
        } catch (Exception e) {
            return new EntityResponse<MdaUser>(e.getMessage());
        }
    }

    /**
     * Get specific user data by post (since user key is a string)
     * @param accessToken Access token
     * @param userKey User key
     * @return EntityResponse[MdaUser]
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getuser")
    public EntityResponse<MdaUser> getUser(@HeaderParam("X-Access-Token") String accessToken, String userKey) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            return this.service.get(userKey);
        } catch (Exception e) {
            return new EntityResponse<MdaUser>(e.getMessage());
        }
    }

    /**
     * Update user data (editable fields only)
     * @param accessToken Access token
     * @param userKey User key
     * @param user User object to update
     * @return EntityResponse[MdaUser]
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{userKey}")
    public EntityResponse<MdaUser> set(@HeaderParam("X-Access-Token") String accessToken, @PathParam("userKey") int userKey, MdaUser user) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);
            return this.service.set(user);
        } catch (Exception e) {
            return new EntityResponse<MdaUser>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
