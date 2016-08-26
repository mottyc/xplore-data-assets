package io.xplore.assets.database.converters;

import io.xplore.assets.database.model.MdaColumnEntity;
import io.xplore.assets.model.MdaColumn;

/**
 * Column entity domain model converter
 */
public class MdbColumnEntityConverter extends _BaseConverter {

    /**
     * Convert database entity to domain model
     * @param entity Entity to convert
     * @return Domain model
     */
    public static MdaColumn convert(MdaColumnEntity entity) {

        MdaColumn item = new MdaColumn();

        item.columnKey = entity.getColumnKey();
        item.fullColumnName = entity.getFullColumnName();
        item.tableKey = entity.getTableKey();
        item.dbName = entity.getDbName();


        item.schemaName = entity.getSchemaName();
        item.tableName = entity.getTableName();
        item.columnName = entity.getColumnName();
        item.displayName = entity.getColumnNameDisplay();
        item.description = entity.getColumnDesc();

        item.columnDataType = entity.getColumnDataType();
        item.isPk = ((entity.getColumnIsPk() != null) && (entity.getColumnIsPk() > 0));

        item.columnPkSource = entity.getColumnPkSource();
        item.columnId = entity.getColumnId();
        item.tableId = entity.getTableId();
        item.dxpColumnGk = entity.getDxpColumnGk();
        item.dxpTableGk = entity.getDxpTableGk();

        item.createDate = convertTime(entity.getCreateDate());
        item.updateDate = convertTime(entity.getUpdateDate());
        item.updateBy = entity.getUpdateBy();

        return item;
    }

}
