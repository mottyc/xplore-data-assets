package io.xplore.assets.database.converters;

import io.xplore.assets.database.model.MdaTableEntity;
import io.xplore.assets.model.MdaTable;

/**
 * Column entity domain model converter
 */
public class MdbTableEntityConverter extends _BaseConverter {

    /**
     * Convert database entity to domain model
     * @param entity Entity to convert
     * @return Domain model
     */
    public static MdaTable convert(MdaTableEntity entity) {

        MdaTable item = new MdaTable();

        item.tableKey = entity.getTableKey();
        item.fullTableName = entity.getFullTableName();
        item.schemaKey = entity.getSchemaKey();
        item.dbName = entity.getDbName();
        item.schemaName = entity.getSchemaName();
        item.tableName = entity.getTableName();
        item.displayName = entity.getTableNameDisplay();
        item.description = entity.getTableDesc();
        item.tableRowCount = entity.getTableRowCount();
        item.tableNumOfCols = entity.getTableNumOfCols();
        item.objectType = entity.getObjectType();
        item.tablePk = entity.getTablePk();
        item.tableId = entity.getTableId();
        item.dxpTableGk = entity.getDxpTableGk();

        item.createDate = convertTime(entity.getCreateDate());
        item.updateDate = convertTime(entity.getUpdateDate());
        item.updateBy = entity.getUpdateBy();

        return item;
    }

}
