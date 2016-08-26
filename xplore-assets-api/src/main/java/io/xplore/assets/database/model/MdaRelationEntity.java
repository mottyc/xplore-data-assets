package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 12/08/2016.
 */
@Entity
@Table(name = "mda_relation", schema = "dbo", catalog = "data_asset")
public class MdaRelationEntity {
    private int relationKey;
    private String fullRelationName;
    private Integer columnKeyPar;
    private Integer columnKeyRef;
    private String dbName;
    private String schemaNamePar;
    private String tableNamePar;
    private String columnNamePar;
    private String schemaNameRef;
    private String tableNameRef;
    private String columnNameRef;
    private String relationDesc;
    private String relationNameDisplay;
    private String relationTypeCd;
    private String relationSource;
    private Double relationScore;
    private Integer dxpColumnGkPar;
    private Integer dxpColumnGkRef;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @Column(name = "relation_key")
    public int getRelationKey() {
        return relationKey;
    }

    public void setRelationKey(int relationKey) {
        this.relationKey = relationKey;
    }

    @Basic
    @Column(name = "full_relation_name")
    public String getFullRelationName() {
        return fullRelationName;
    }

    public void setFullRelationName(String fullRelationName) {
        this.fullRelationName = fullRelationName;
    }

    @Basic
    @Column(name = "column_key_par")
    public Integer getColumnKeyPar() {
        return columnKeyPar;
    }

    public void setColumnKeyPar(Integer columnKeyPar) {
        this.columnKeyPar = columnKeyPar;
    }

    @Basic
    @Column(name = "column_key_ref")
    public Integer getColumnKeyRef() {
        return columnKeyRef;
    }

    public void setColumnKeyRef(Integer columnKeyRef) {
        this.columnKeyRef = columnKeyRef;
    }

    @Basic
    @Column(name = "db_name")
    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Basic
    @Column(name = "schema_name_par")
    public String getSchemaNamePar() {
        return schemaNamePar;
    }

    public void setSchemaNamePar(String schemaNamePar) {
        this.schemaNamePar = schemaNamePar;
    }

    @Basic
    @Column(name = "table_name_par")
    public String getTableNamePar() {
        return tableNamePar;
    }

    public void setTableNamePar(String tableNamePar) {
        this.tableNamePar = tableNamePar;
    }

    @Basic
    @Column(name = "column_name_par")
    public String getColumnNamePar() {
        return columnNamePar;
    }

    public void setColumnNamePar(String columnNamePar) {
        this.columnNamePar = columnNamePar;
    }

    @Basic
    @Column(name = "schema_name_ref")
    public String getSchemaNameRef() {
        return schemaNameRef;
    }

    public void setSchemaNameRef(String schemaNameRef) {
        this.schemaNameRef = schemaNameRef;
    }

    @Basic
    @Column(name = "table_name_ref")
    public String getTableNameRef() {
        return tableNameRef;
    }

    public void setTableNameRef(String tableNameRef) {
        this.tableNameRef = tableNameRef;
    }

    @Basic
    @Column(name = "column_name_ref")
    public String getColumnNameRef() {
        return columnNameRef;
    }

    public void setColumnNameRef(String columnNameRef) {
        this.columnNameRef = columnNameRef;
    }

    @Basic
    @Column(name = "relation_desc")
    public String getRelationDesc() {
        return relationDesc;
    }

    public void setRelationDesc(String relationDesc) {
        this.relationDesc = relationDesc;
    }

    @Basic
    @Column(name = "relation_name_display")
    public String getRelationNameDisplay() {
        return relationNameDisplay;
    }

    public void setRelationNameDisplay(String relationNameDisplay) {
        this.relationNameDisplay = relationNameDisplay;
    }

    @Basic
    @Column(name = "relation_type_cd")
    public String getRelationTypeCd() {
        return relationTypeCd;
    }

    public void setRelationTypeCd(String relationTypeCd) {
        this.relationTypeCd = relationTypeCd;
    }

    @Basic
    @Column(name = "relation_source")
    public String getRelationSource() {
        return relationSource;
    }

    public void setRelationSource(String relationSource) {
        this.relationSource = relationSource;
    }

    @Basic
    @Column(name = "relation_score")
    public Double getRelationScore() {
        return relationScore;
    }

    public void setRelationScore(Double relationScore) {
        this.relationScore = relationScore;
    }

    @Basic
    @Column(name = "dxp_column_gk_par")
    public Integer getDxpColumnGkPar() {
        return dxpColumnGkPar;
    }

    public void setDxpColumnGkPar(Integer dxpColumnGkPar) {
        this.dxpColumnGkPar = dxpColumnGkPar;
    }

    @Basic
    @Column(name = "dxp_column_gk_ref")
    public Integer getDxpColumnGkRef() {
        return dxpColumnGkRef;
    }

    public void setDxpColumnGkRef(Integer dxpColumnGkRef) {
        this.dxpColumnGkRef = dxpColumnGkRef;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "update_date")
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "update_by")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MdaRelationEntity that = (MdaRelationEntity) o;

        if (relationKey != that.relationKey) return false;
        if (fullRelationName != null ? !fullRelationName.equals(that.fullRelationName) : that.fullRelationName != null)
            return false;
        if (columnKeyPar != null ? !columnKeyPar.equals(that.columnKeyPar) : that.columnKeyPar != null) return false;
        if (columnKeyRef != null ? !columnKeyRef.equals(that.columnKeyRef) : that.columnKeyRef != null) return false;
        if (dbName != null ? !dbName.equals(that.dbName) : that.dbName != null) return false;
        if (schemaNamePar != null ? !schemaNamePar.equals(that.schemaNamePar) : that.schemaNamePar != null)
            return false;
        if (tableNamePar != null ? !tableNamePar.equals(that.tableNamePar) : that.tableNamePar != null) return false;
        if (columnNamePar != null ? !columnNamePar.equals(that.columnNamePar) : that.columnNamePar != null)
            return false;
        if (schemaNameRef != null ? !schemaNameRef.equals(that.schemaNameRef) : that.schemaNameRef != null)
            return false;
        if (tableNameRef != null ? !tableNameRef.equals(that.tableNameRef) : that.tableNameRef != null) return false;
        if (columnNameRef != null ? !columnNameRef.equals(that.columnNameRef) : that.columnNameRef != null)
            return false;
        if (relationDesc != null ? !relationDesc.equals(that.relationDesc) : that.relationDesc != null) return false;
        if (relationNameDisplay != null ? !relationNameDisplay.equals(that.relationNameDisplay) : that.relationNameDisplay != null)
            return false;
        if (relationTypeCd != null ? !relationTypeCd.equals(that.relationTypeCd) : that.relationTypeCd != null)
            return false;
        if (relationSource != null ? !relationSource.equals(that.relationSource) : that.relationSource != null)
            return false;
        if (relationScore != null ? !relationScore.equals(that.relationScore) : that.relationScore != null)
            return false;
        if (dxpColumnGkPar != null ? !dxpColumnGkPar.equals(that.dxpColumnGkPar) : that.dxpColumnGkPar != null)
            return false;
        if (dxpColumnGkRef != null ? !dxpColumnGkRef.equals(that.dxpColumnGkRef) : that.dxpColumnGkRef != null)
            return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = relationKey;
        result = 31 * result + (fullRelationName != null ? fullRelationName.hashCode() : 0);
        result = 31 * result + (columnKeyPar != null ? columnKeyPar.hashCode() : 0);
        result = 31 * result + (columnKeyRef != null ? columnKeyRef.hashCode() : 0);
        result = 31 * result + (dbName != null ? dbName.hashCode() : 0);
        result = 31 * result + (schemaNamePar != null ? schemaNamePar.hashCode() : 0);
        result = 31 * result + (tableNamePar != null ? tableNamePar.hashCode() : 0);
        result = 31 * result + (columnNamePar != null ? columnNamePar.hashCode() : 0);
        result = 31 * result + (schemaNameRef != null ? schemaNameRef.hashCode() : 0);
        result = 31 * result + (tableNameRef != null ? tableNameRef.hashCode() : 0);
        result = 31 * result + (columnNameRef != null ? columnNameRef.hashCode() : 0);
        result = 31 * result + (relationDesc != null ? relationDesc.hashCode() : 0);
        result = 31 * result + (relationNameDisplay != null ? relationNameDisplay.hashCode() : 0);
        result = 31 * result + (relationTypeCd != null ? relationTypeCd.hashCode() : 0);
        result = 31 * result + (relationSource != null ? relationSource.hashCode() : 0);
        result = 31 * result + (relationScore != null ? relationScore.hashCode() : 0);
        result = 31 * result + (dxpColumnGkPar != null ? dxpColumnGkPar.hashCode() : 0);
        result = 31 * result + (dxpColumnGkRef != null ? dxpColumnGkRef.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
