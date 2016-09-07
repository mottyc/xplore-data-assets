/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;



import io.xplore.assets.database.model.*;
import io.xplore.assets.messages.EntityResponse;
import io.xplore.assets.model.*;
import io.xplore.assets.service.DashboardService;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

/**
 * User DB service
 */
@Stateless
public class DbDashboardServiceImpl extends _DbBaseServiceImpl<Statistics> implements DashboardService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;


    /**
     * Get statistics
     *
     * @return EntityResponse<Statistics>
     */
    @Override
    public EntityResponse<Statistics> get() {
        try {
            Statistics stats = new Statistics();

            Session session = em.unwrap(Session.class);

            stats.entitiesCount = (long)session.createCriteria(MdaBusinessEntityEntity.class).setProjection(Projections.rowCount()).uniqueResult();
            stats.systemsCount = (long)session.createCriteria(MdaSystemEntity.class).setProjection(Projections.rowCount()).uniqueResult();
            stats.serversCount = (long)session.createCriteria(MdaServerEntity.class).setProjection(Projections.rowCount()).uniqueResult();
            stats.databasesCount = (long)session.createCriteria(MdaDbEntity.class).setProjection(Projections.rowCount()).uniqueResult();
            stats.schemasCount = (long)session.createCriteria(MdaSchemaEntity.class).setProjection(Projections.rowCount()).uniqueResult();
            stats.tablesCount = (long)session.createCriteria(MdaTableEntity.class).setProjection(Projections.rowCount()).uniqueResult();
            stats.columnsCount = (long)session.createCriteria(MdaColumnEntity.class).setProjection(Projections.rowCount()).uniqueResult();
            stats.relationsCount = (long)session.createCriteria(MdaRelationEntity.class).setProjection(Projections.rowCount()).uniqueResult();
            stats.usersCount = (long)session.createCriteria(MdaUsernameEntity.class).setProjection(Projections.rowCount()).uniqueResult();



            return new EntityResponse<Statistics>(stats);
        } catch (Exception ex) {
            String err = String.format("Action failed: %s", ex.getMessage());
            log.severe(err);
            return new EntityResponse<Statistics>(err);
        }
    }
}
