package io.xplore.assets.database.converters;

import io.xplore.assets.database.model.MdaSchemaEntity;
import io.xplore.assets.model.MdaSchema;

/**
 * Column entity domain model converter
 */
public class MdbSchemaEntityConverter extends _BaseConverter {

    /**
     * Convert database entity to domain model
     * @param entity Entity to convert
     * @return Domain model
     */
    public static MdaSchema convert(MdaSchemaEntity entity) {

        MdaSchema item = new MdaSchema();

        item.schemaKey = entity.getSchemaKey();
        item.fullSchemaName = entity.getFullSchemaName();
        item.domainKey = entity.getDomainKey();
        item.schemaName = entity.getSchemaName();
        item.dbName = entity.getDbName();

        item.displayName = entity.getSchemaNameDisplay();
        item.description = entity.getSchemaDesc();

        item.createDate = convertTime(entity.getCreateDate());
        item.updateDate = convertTime(entity.getUpdateDate());
        item.updateBy = entity.getUpdateBy();

        return item;
    }

}
