package io.xplore.assets.rest;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaTable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Table related services
 */
@Path("/tables")
@RequestScoped
public class TableResource extends BaseResource {

    @Inject
    private Logger log;

    // ------------------ Main entity actions --------------------------------------------------------------------------

    /**
     * Get list of tables
     * @param accessToken Access token
     * @return QueryResponse[MdaTable]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaTable> find(@HeaderParam("X-Access-Token") String accessToken) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            throw new NotImplementedException();
            //return this.service.findSystems(token);
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

            throw new NotImplementedException();
            //return this.service.findSystems(token);
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
            throw new NotImplementedException();
        } catch (Exception e) {
            return new EntityResponse<MdaTable>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
