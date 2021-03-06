package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 12/08/2016.
 */
@Entity
@Table(name = "mda_column", schema = "dbo", catalog = "data_asset")
@NamedQueries({
        @NamedQuery(name="MdaColumnEntity.findAll", query="SELECT a FROM MdaColumnEntity a"),
        @NamedQuery(name="MdaColumnEntity.findByKey", query="SELECT a FROM MdaColumnEntity a WHERE a.columnKey = :columnKey"),
        @NamedQuery(name="MdaColumnEntity.findByTable", query="SELECT a FROM MdaColumnEntity a WHERE a.tableKey = :tableKey")
})
public class MdaColumnEntity {
    private int columnKey;
    private String fullColumnName;
    private Integer tableKey;
    private String dbName;
    private String schemaName;
    private String tableName;
    private String columnName;
    private String columnDesc;
    private String columnNameDisplay;
    private String columnDataType;
    private Integer columnIsPk;
    private String columnPkSource;
    private Integer columnId;
    private Integer tableId;
    private Integer dxpColumnGk;
    private Integer dxpTableGk;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "column_key")
    public int getColumnKey() {
        return columnKey;
    }
    public void setColumnKey(int columnKey) {
        this.columnKey = columnKey;
    }

    @Basic
    @Column(name = "full_column_name")
    public String getFullColumnName() {
        return fullColumnName;
    }
    public void setFullColumnName(String fullColumnName) {
        this.fullColumnName = fullColumnName;
    }

    @Basic
    @Column(name = "table_key")
    public Integer getTableKey() {
        return tableKey;
    }
    public void setTableKey(Integer tableKey) {
        this.tableKey = tableKey;
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
    @Column(name = "schema_name")
    public String getSchemaName() {
        return schemaName;
    }
    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    @Basic
    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "column_name")
    public String getColumnName() {
        return columnName;
    }
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Basic
    @Column(name = "column_desc")
    public String getColumnDesc() {
        return columnDesc;
    }
    public void setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc;
    }

    @Basic
    @Column(name = "column_name_display")
    public String getColumnNameDisplay() {
        return columnNameDisplay;
    }
    public void setColumnNameDisplay(String columnNameDisplay) {
        this.columnNameDisplay = columnNameDisplay;
    }

    @Basic
    @Column(name = "column_data_type")
    public String getColumnDataType() {
        return columnDataType;
    }
    public void setColumnDataType(String columnDataType) {
        this.columnDataType = columnDataType;
    }

    @Basic
    @Column(name = "column_is_pk")
    public Integer getColumnIsPk() {
        return columnIsPk;
    }
    public void setColumnIsPk(Integer columnIsPk) {
        this.columnIsPk = columnIsPk;
    }

    @Basic
    @Column(name = "column_pk_source")
    public String getColumnPkSource() {
        return columnPkSource;
    }
    public void setColumnPkSource(String columnPkSource) {
        this.columnPkSource = columnPkSource;
    }

    @Basic
    @Column(name = "column_id")
    public Integer getColumnId() {
        return columnId;
    }
    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    @Basic
    @Column(name = "table_id")
    public Integer getTableId() {
        return tableId;
    }
    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    @Basic
    @Column(name = "dxp_column_gk")
    public Integer getDxpColumnGk() {
        return dxpColumnGk;
    }
    public void setDxpColumnGk(Integer dxpColumnGk) {
        this.dxpColumnGk = dxpColumnGk;
    }

    @Basic
    @Column(name = "dxp_table_gk")
    public Integer getDxpTableGk() {
        return dxpTableGk;
    }
    public void setDxpTableGk(Integer dxpTableGk) {
        this.dxpTableGk = dxpTableGk;
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

        MdaColumnEntity that = (MdaColumnEntity) o;

        if (columnKey != that.columnKey) return false;
        if (fullColumnName != null ? !fullColumnName.equals(that.fullColumnName) : that.fullColumnName != null)
            return false;
        if (tableKey != null ? !tableKey.equals(that.tableKey) : that.tableKey != null) return false;
        if (dbName != null ? !dbName.equals(that.dbName) : that.dbName != null) return false;
        if (schemaName != null ? !schemaName.equals(that.schemaName) : that.schemaName != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (columnName != null ? !columnName.equals(that.columnName) : that.columnName != null) return false;
        if (columnDesc != null ? !columnDesc.equals(that.columnDesc) : that.columnDesc != null) return false;
        if (columnNameDisplay != null ? !columnNameDisplay.equals(that.columnNameDisplay) : that.columnNameDisplay != null)
            return false;
        if (columnDataType != null ? !columnDataType.equals(that.columnDataType) : that.columnDataType != null)
            return false;
        if (columnIsPk != null ? !columnIsPk.equals(that.columnIsPk) : that.columnIsPk != null) return false;
        if (columnPkSource != null ? !columnPkSource.equals(that.columnPkSource) : that.columnPkSource != null)
            return false;
        if (columnId != null ? !columnId.equals(that.columnId) : that.columnId != null) return false;
        if (tableId != null ? !tableId.equals(that.tableId) : that.tableId != null) return false;
        if (dxpColumnGk != null ? !dxpColumnGk.equals(that.dxpColumnGk) : that.dxpColumnGk != null) return false;
        if (dxpTableGk != null ? !dxpTableGk.equals(that.dxpTableGk) : that.dxpTableGk != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = columnKey;
        result = 31 * result + (fullColumnName != null ? fullColumnName.hashCode() : 0);
        result = 31 * result + (tableKey != null ? tableKey.hashCode() : 0);
        result = 31 * result + (dbName != null ? dbName.hashCode() : 0);
        result = 31 * result + (schemaName != null ? schemaName.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (columnDesc != null ? columnDesc.hashCode() : 0);
        result = 31 * result + (columnNameDisplay != null ? columnNameDisplay.hashCode() : 0);
        result = 31 * result + (columnDataType != null ? columnDataType.hashCode() : 0);
        result = 31 * result + (columnIsPk != null ? columnIsPk.hashCode() : 0);
        result = 31 * result + (columnPkSource != null ? columnPkSource.hashCode() : 0);
        result = 31 * result + (columnId != null ? columnId.hashCode() : 0);
        result = 31 * result + (tableId != null ? tableId.hashCode() : 0);
        result = 31 * result + (dxpColumnGk != null ? dxpColumnGk.hashCode() : 0);
        result = 31 * result + (dxpTableGk != null ? dxpTableGk.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
