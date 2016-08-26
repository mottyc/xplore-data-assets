package io.xplore.assets.rest;

import io.xplore.assets.Consts;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaTable;
import io.xplore.assets.model.MdaUser;
import io.xplore.assets.service.UserService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
     * @param filter Filter by name
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
                                       @QueryParam("filter") @DefaultValue("") String filter,
                                       @QueryParam("sort") @DefaultValue("") String sort,
                                       @QueryParam("page") @DefaultValue("1") int page,
                                       @QueryParam("pageSize") @DefaultValue("0") int pageSize) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            // Pagination
            page = (page > 0) ? page : 1;
            pageSize = (pageSize > 0) ? pageSize : Consts.DB_PAGE_SIZE;

            return this.service.find(page, pageSize);
        } catch (Exception e) {
            return new QueryResponse<MdaUser>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
