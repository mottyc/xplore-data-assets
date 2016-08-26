package io.xplore.assets.database.converters;

import io.xplore.assets.database.model.MdaRelationEntity;
import io.xplore.assets.model.MdaRelation;

/**
 * Column entity domain model converter
 */
public class MdaRelationEntityConverter extends _BaseConverter {

    /**
     * Convert database entity to domain model
     * @param entity Entity to convert
     * @return Domain model
     */
    public static MdaRelation get(MdaRelationEntity entity) {

        MdaRelation item = new MdaRelation();

        item.relationKey = entity.getRelationKey();
        item.fullRelationName = entity.getFullRelationName();
        item.columnKeyPar = entity.getColumnKeyPar();
        item.columnKeyRef = entity.getColumnKeyRef();
        item.dbName = entity.getDbName();

        item.schemaNamePar = entity.getSchemaNamePar();
        item.tableNamePar = entity.getTableNamePar();
        item.columnNamePar = entity.getColumnNamePar();

        item.schemaNameRef = entity.getSchemaNameRef();
        item.tableNameRef = entity.getTableNameRef();
        item.columnNameRef = entity.getColumnNameRef();

        item.displayName = entity.getRelationNameDisplay();
        item.description = entity.getRelationDesc();

        item.relationTypeCd = entity.getRelationTypeCd();
        item.relationSource = entity.getRelationSource();
        item.relationScore = entity.getRelationScore();

        item.dxpColumnGkPar = entity.getDxpColumnGkPar();
        item.dxpColumnGkRef = entity.getDxpColumnGkRef();

        item.createDate = convertTime(entity.getCreateDate());
        item.updateDate = convertTime(entity.getUpdateDate());
        item.updateBy = entity.getUpdateBy();

        return item;
    }

}
