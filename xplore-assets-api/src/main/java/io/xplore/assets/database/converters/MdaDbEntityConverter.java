package io.xplore.assets.database.converters;

import io.xplore.assets.database.model.MdaDbEntity;
import io.xplore.assets.model.MdaDatabase;

/**
 * Column entity domain model converter
 */
public class MdaDbEntityConverter extends _BaseConverter {

    /**
     * Convert database entity to domain model
     * @param entity Entity to convert
     * @return Domain model
     */
    public static MdaDatabase get(MdaDbEntity entity) {

        MdaDatabase item = new MdaDatabase();

        item.domainKey = entity.getDomainKey();
        item.fullDbName = entity.getFullDbName();
        item.serverKey = entity.getServerKey();
        item.dbName = entity.getDbName();
        item.displayName = entity.getDbNameDisplay();
        item.description = entity.getDbDesc();
        item.dbTypeCd = entity.getDbTypeCd();
        item.technicalOwnerUsernameKey = entity.getTechnicalOwnerUsernameKey();
        item.dxpConnectionId = entity.getDxpConnectionId();

        item.createDate = convertTime(entity.getCreateDate());
        item.updateDate = convertTime(entity.getUpdateDate());
        item.updateBy = entity.getUpdateBy();

        return item;
    }

}
