
/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.utils;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans. As it is a stateful bean, it
 * can produce extended persistence contexts.
 *
 */
public class Resources {


    /**
     * Logger
     * @param ip InjectionPoint
     * @return
     */
    @Produces
    public Logger getLogger(InjectionPoint ip) {
        String category = ip.getMember().getDeclaringClass().getName();
        return Logger.getLogger(category);
    }

}

