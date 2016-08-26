package io.xplore.assets.rest;

import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.messages.QueryResponse;
import io.xplore.assets.messages.TokenData;
import io.xplore.assets.model.MdaRelation;
import io.xplore.assets.service.RelationService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
     * @return QueryResponse[MdaRelation]
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public QueryResponse<MdaRelation> find(@HeaderParam("X-Access-Token") String accessToken) {
        try {
            // Validation
            TokenData token = this.parseJWT(accessToken);

            throw new NotImplementedException();
            //return this.service.findSystems(token);
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

            throw new NotImplementedException();
            //return this.service.findSystems(token);
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
            throw new NotImplementedException();
        } catch (Exception e) {
            return new EntityResponse<MdaRelation>(e.getMessage());
        }
    }

    // ------------------ Sub entities actions -------------------------------------------------------------------------

}
