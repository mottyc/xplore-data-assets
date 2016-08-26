
/*
 * Copyright (c) 2016. xplore.io
 *
 */

package io.xplore.assets.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @Produces
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    //@Produces
    //private CurrencyConverter currencyConverter;

}

