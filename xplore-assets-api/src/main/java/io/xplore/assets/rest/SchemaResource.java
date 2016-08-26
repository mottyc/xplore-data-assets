package io.xplore.assets.rest;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaSchema;
import io.xplore.assets.service.SchemaService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
     * @return QueryResponse[MdaSchema]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaSchema> find(@HeaderParam("X-Access-Token") String accessToken) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            throw new NotImplementedException();
            //return this.service.findSystems(token);
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

            throw new NotImplementedException();
            //return this.service.findSystems(token);
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
            throw new NotImplementedException();
        } catch (Exception e) {
            return new EntityResponse<MdaSchema>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
