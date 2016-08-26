package io.xplore.assets.database.converters;

import io.xplore.assets.database.model.MdaSystemEntity;
import io.xplore.assets.model.MdaSystem;

/**
 * Column entity domain model converter
 */
public class MdaSystemEntityConverter extends _BaseConverter {

    /**
     * Convert database entity to domain model
     * @param entity Entity to convert
     * @return Domain model
     */
    public static MdaSystem get(MdaSystemEntity entity) {

        MdaSystem item = new MdaSystem();

        item.systemKey = entity.getSystemKey();
        item.systemName = entity.getSystemName();
        item.appName = entity.getAppName();

        item.appExpertUsernameKey = entity.getAppExpertUsernameKey();
        item.appOwnerUsernameKey = entity.getAppOwnerUsernameKey();
        item.moduleOwnerUsernameKey = entity.getModuleOwnerUsernameKey();
        item.displayName = entity.getSystemNameDisplay();
        item.description = entity.getSystemDesc();
        item.systemDepartment = entity.getSystemDepartment();

        item.createDate = convertTime(entity.getCreateDate());
        item.updateDate = convertTime(entity.getUpdateDate());
        item.updateBy = entity.getUpdateBy();

        return item;
    }

}
