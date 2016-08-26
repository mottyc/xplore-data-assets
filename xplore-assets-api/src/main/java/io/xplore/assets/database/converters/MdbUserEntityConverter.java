package io.xplore.assets.database.converters;

import io.xplore.assets.database.model.MdaUsernameEntity;
import io.xplore.assets.model.MdaUser;

/**
 * Column entity domain model converter
 */
public class MdbUserEntityConverter extends _BaseConverter {

    /**
     * Convert database entity to domain model
     * @param entity Entity to convert
     * @return Domain model
     */
    public static MdaUser convert(MdaUsernameEntity entity) {

        MdaUser item = new MdaUser();

        item.usernameKey = entity.getUsernameKey();
        item.firstName = entity.getEmployeeFirstName();
        item.lastName = entity.getEmployeeLastName();
        item.displayName = entity.getEmployeeNameDisplay();

        item.createDate = convertTime(entity.getCreateDate());
        item.updateDate = convertTime(entity.getUpdateDate());
        item.updateBy = entity.getUpdateBy();

        return item;
    }

}
