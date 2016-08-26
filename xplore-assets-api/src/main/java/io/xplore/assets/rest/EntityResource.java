package io.xplore.assets.rest;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaBusinessEntity;
import io.xplore.assets.service.BusinessEntityService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
     * @param filter Filter by name
     * @return QueryResponse[MdaBusinessEntity]
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaBusinessEntity> find(@HeaderParam("X-Access-Token") String accessToken, String filter ) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            throw new NotImplementedException();
            //return this.service.findSystems(token);
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

            throw new NotImplementedException();
            //return this.service.findSystems(token);
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
