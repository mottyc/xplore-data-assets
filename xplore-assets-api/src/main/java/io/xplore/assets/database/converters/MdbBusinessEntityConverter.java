package io.xplore.assets.database.converters;

import io.xplore.assets.database.model.MdaBusinessEntityEntity;
import io.xplore.assets.model.MdaBusinessEntity;

/**
 * Business entity domain model to entity converter
 */
public class MdbBusinessEntityConverter extends _BaseConverter {

    /**
     * Convert database entity to domain model
     * @param entity Entity to convert
     * @return Domain model
     */
    public static MdaBusinessEntity convert(MdaBusinessEntityEntity entity) {

        MdaBusinessEntity item = new MdaBusinessEntity();

        item.businessEntityKey = entity.getBusinessEntityKey();
        item.businessEntityName = entity.getBusinessEntityName();
        item.displayName = entity.getBusinessEntityNameDisplay();
        item.description = entity.getBusinessEntityDesc();

        item.createDate = convertTime(entity.getCreateDate());
        item.updateDate = convertTime(entity.getUpdateDate());
        item.updateBy = entity.getUpdateBy();

        return item;
    }

}
