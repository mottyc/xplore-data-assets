
/*
 * Copyright (c) 2016. xplore.io
 *
 */

package io.xplore.assets.rest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * CORS filter (see )
 * See http://stackoverflow.com/questions/31902612/access-control-allow-origin-to-wildfly-8-1-0-configuration for details
 *
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {
    @Override
    public void filter(final ContainerRequestContext requestContext,
                       final ContainerResponseContext responseContext) throws IOException {

        // Check if origin is in the white list

        String result = this.validateOrigin(requestContext);

        // TODO: for security reasons, replace * with white list of origins
        responseContext.getHeaders().putSingle("Access-Control-Allow-Origin", "*");

        responseContext.getHeaders().add("Access-Control-Allow-Headers", "Origin, Content-type, Content-Disposition, Accept, Accept-Encoding, Authorization, X-Access-Token, X-Requested-With");
        responseContext.getHeaders().add("Access-Control-Expose-Headers", "Content-type, Content-Disposition, Accept");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
    }

    /**
     * Check if call origin is from a well known origin (in the white list)
     * @param requestContext Request context to check the origin of the request
     * @return "*" if origin approved or "null" if not
     */
    private String validateOrigin(ContainerRequestContext requestContext) {

        try {
            String origin = requestContext.getHeaderString("Access-Control-Allow-Origin");
            // TODO: Implement white list of origins
            return "*";
        } catch (Exception ex) {
            return "";
        }
    }
}
