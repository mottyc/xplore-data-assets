package io.xplore.assets.rest;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaColumn;
import io.xplore.assets.service.ColumnService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
     * @return QueryResponse[MdaColumn]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaColumn> find(@HeaderParam("X-Access-Token") String accessToken) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            throw new NotImplementedException();
            //return this.service.findSystems(token);
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

            throw new NotImplementedException();
            //return this.service.findSystems(token);
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
            throw new NotImplementedException();
        } catch (Exception e) {
            return new EntityResponse<MdaColumn>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
