package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 12/08/2016.
 */
@Entity
@Table(name = "mda_table", schema = "dbo", catalog = "data_asset")
@NamedQueries({
        @NamedQuery(name="MdaTableEntity.findAll", query="SELECT a FROM MdaTableEntity a"),
        @NamedQuery(name="MdaTableEntity.findByKey", query="SELECT a FROM MdaTableEntity a WHERE a.tableKey = :tableKey"),
        @NamedQuery(name="MdaTableEntity.findByKeys", query="SELECT a FROM MdaTableEntity a WHERE a.tableKey IN :keys"),
        @NamedQuery(name="MdaTableEntity.findBySchema", query="SELECT a FROM MdaTableEntity a WHERE a.schemaKey = :schemaKey")
})
public class MdaTableEntity {
    private int tableKey;
    private String fullTableName;
    private Integer schemaKey;
    private String dbName;
    private String schemaName;
    private String tableName;
    private String tableDesc;
    private String tableNameDisplay;
    private Integer tableRowCount;
    private Integer tableNumOfCols;
    private String objectType;
    private String tablePk;
    private Integer tableId;
    private Integer dxpTableGk;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "table_key")
    public int getTableKey() {
        return tableKey;
    }
    public void setTableKey(int tableKey) {
        this.tableKey = tableKey;
    }

    @Basic
    @Column(name = "full_table_name")
    public String getFullTableName() {
        return fullTableName;
    }
    public void setFullTableName(String fullTableName) {
        this.fullTableName = fullTableName;
    }

    @Basic
    @Column(name = "schema_key")
    public Integer getSchemaKey() {
        return schemaKey;
    }
    public void setSchemaKey(Integer schemaKey) {
        this.schemaKey = schemaKey;
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
    @Column(name = "table_desc")
    public String getTableDesc() {
        return tableDesc;
    }
    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    @Basic
    @Column(name = "table_name_display")
    public String getTableNameDisplay() {
        return tableNameDisplay;
    }
    public void setTableNameDisplay(String tableNameDisplay) {
        this.tableNameDisplay = tableNameDisplay;
    }

    @Basic
    @Column(name = "table_row_count")
    public Integer getTableRowCount() {
        return tableRowCount;
    }
    public void setTableRowCount(Integer tableRowCount) {
        this.tableRowCount = tableRowCount;
    }

    @Basic
    @Column(name = "table_num_of_cols")
    public Integer getTableNumOfCols() {
        return tableNumOfCols;
    }
    public void setTableNumOfCols(Integer tableNumOfCols) {
        this.tableNumOfCols = tableNumOfCols;
    }

    @Basic
    @Column(name = "object_type")
    public String getObjectType() {
        return objectType;
    }
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Basic
    @Column(name = "table_pk")
    public String getTablePk() {
        return tablePk;
    }
    public void setTablePk(String tablePk) {
        this.tablePk = tablePk;
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

        MdaTableEntity that = (MdaTableEntity) o;

        if (tableKey != that.tableKey) return false;
        if (fullTableName != null ? !fullTableName.equals(that.fullTableName) : that.fullTableName != null)
            return false;
        if (schemaKey != null ? !schemaKey.equals(that.schemaKey) : that.schemaKey != null) return false;
        if (dbName != null ? !dbName.equals(that.dbName) : that.dbName != null) return false;
        if (schemaName != null ? !schemaName.equals(that.schemaName) : that.schemaName != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (tableDesc != null ? !tableDesc.equals(that.tableDesc) : that.tableDesc != null) return false;
        if (tableNameDisplay != null ? !tableNameDisplay.equals(that.tableNameDisplay) : that.tableNameDisplay != null)
            return false;
        if (tableRowCount != null ? !tableRowCount.equals(that.tableRowCount) : that.tableRowCount != null)
            return false;
        if (tableNumOfCols != null ? !tableNumOfCols.equals(that.tableNumOfCols) : that.tableNumOfCols != null)
            return false;
        if (objectType != null ? !objectType.equals(that.objectType) : that.objectType != null) return false;
        if (tablePk != null ? !tablePk.equals(that.tablePk) : that.tablePk != null) return false;
        if (tableId != null ? !tableId.equals(that.tableId) : that.tableId != null) return false;
        if (dxpTableGk != null ? !dxpTableGk.equals(that.dxpTableGk) : that.dxpTableGk != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableKey;
        result = 31 * result + (fullTableName != null ? fullTableName.hashCode() : 0);
        result = 31 * result + (schemaKey != null ? schemaKey.hashCode() : 0);
        result = 31 * result + (dbName != null ? dbName.hashCode() : 0);
        result = 31 * result + (schemaName != null ? schemaName.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (tableDesc != null ? tableDesc.hashCode() : 0);
        result = 31 * result + (tableNameDisplay != null ? tableNameDisplay.hashCode() : 0);
        result = 31 * result + (tableRowCount != null ? tableRowCount.hashCode() : 0);
        result = 31 * result + (tableNumOfCols != null ? tableNumOfCols.hashCode() : 0);
        result = 31 * result + (objectType != null ? objectType.hashCode() : 0);
        result = 31 * result + (tablePk != null ? tablePk.hashCode() : 0);
        result = 31 * result + (tableId != null ? tableId.hashCode() : 0);
        result = 31 * result + (dxpTableGk != null ? dxpTableGk.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
