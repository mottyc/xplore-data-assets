package io.xplore.assets.database.converters;

import io.xplore.assets.database.model.MdaProcessEntity;
import io.xplore.assets.model.MdaProcess;

/**
 * Process entity domain model converter
 */
public class MdaProcessEntityConverter extends _BaseConverter {

    /**
     * Convert database entity to domain model
     * @param entity Entity to convert
     * @return Domain model
     */
    public static MdaProcess get(MdaProcessEntity entity) {

        MdaProcess item = new MdaProcess();

        item.processKey = entity.getProcessKey();
        item.processName = entity.getProcessName();
        item.displayName = entity.getProcessNameDisplay();
        item.description = entity.getProcessDesc();

        item.createDate = convertTime(entity.getCreateDate());
        item.updateDate = convertTime(entity.getUpdateDate());
        item.updateBy = entity.getUpdateBy();

        return item;
    }

}
