package io.xplore.assets.database.converters;

import io.xplore.assets.database.model.MdaServerEntity;
import io.xplore.assets.model.MdaServer;

/**
 * Column entity domain model converter
 */
public class MdaServerEntityConverter extends _BaseConverter {

    /**
     * Convert database entity to domain model
     * @param entity Entity to convert
     * @return Domain model
     */
    public static MdaServer get(MdaServerEntity entity) {

        MdaServer item = new MdaServer();

        item.serverKey = entity.getServerKey();
        item.serverName = entity.getServerName();
        item.displayName = entity.getServerNameDisplay();
        item.description = entity.getServerDesc();
        item.serverTypeCd = entity.getServerTypeCd();

        item.createDate = convertTime(entity.getCreateDate());
        item.updateDate = convertTime(entity.getUpdateDate());
        item.updateBy = entity.getUpdateBy();

        return item;
    }

}
