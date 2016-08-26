package io.xplore.assets.rest;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaDatabase;
import io.xplore.assets.service.DatabaseService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
     * @return QueryResponse[MdaDatabase]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaDatabase> find(@HeaderParam("X-Access-Token") String accessToken) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            throw new NotImplementedException();
            //return this.service.findSystems(token);
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

            throw new NotImplementedException();
            //return this.service.findSystems(token);
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
