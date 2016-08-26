package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 12/08/2016.
 */
@Entity
@Table(name = "mda_schema", schema = "dbo", catalog = "data_asset")
public class MdaSchemaEntity {
    private int schemaKey;
    private String fullSchemaName;
    private Integer domainKey;
    private String dbName;
    private String schemaName;
    private String schemaDesc;
    private String schemaNameDisplay;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @Column(name = "schema_key")
    public int getSchemaKey() {
        return schemaKey;
    }

    public void setSchemaKey(int schemaKey) {
        this.schemaKey = schemaKey;
    }

    @Basic
    @Column(name = "full_schema_name")
    public String getFullSchemaName() {
        return fullSchemaName;
    }

    public void setFullSchemaName(String fullSchemaName) {
        this.fullSchemaName = fullSchemaName;
    }

    @Basic
    @Column(name = "domain_key")
    public Integer getDomainKey() {
        return domainKey;
    }

    public void setDomainKey(Integer domainKey) {
        this.domainKey = domainKey;
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
    @Column(name = "schema_desc")
    public String getSchemaDesc() {
        return schemaDesc;
    }

    public void setSchemaDesc(String schemaDesc) {
        this.schemaDesc = schemaDesc;
    }

    @Basic
    @Column(name = "schema_name_display")
    public String getSchemaNameDisplay() {
        return schemaNameDisplay;
    }

    public void setSchemaNameDisplay(String schemaNameDisplay) {
        this.schemaNameDisplay = schemaNameDisplay;
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

        MdaSchemaEntity that = (MdaSchemaEntity) o;

        if (schemaKey != that.schemaKey) return false;
        if (fullSchemaName != null ? !fullSchemaName.equals(that.fullSchemaName) : that.fullSchemaName != null)
            return false;
        if (domainKey != null ? !domainKey.equals(that.domainKey) : that.domainKey != null) return false;
        if (dbName != null ? !dbName.equals(that.dbName) : that.dbName != null) return false;
        if (schemaName != null ? !schemaName.equals(that.schemaName) : that.schemaName != null) return false;
        if (schemaDesc != null ? !schemaDesc.equals(that.schemaDesc) : that.schemaDesc != null) return false;
        if (schemaNameDisplay != null ? !schemaNameDisplay.equals(that.schemaNameDisplay) : that.schemaNameDisplay != null)
            return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schemaKey;
        result = 31 * result + (fullSchemaName != null ? fullSchemaName.hashCode() : 0);
        result = 31 * result + (domainKey != null ? domainKey.hashCode() : 0);
        result = 31 * result + (dbName != null ? dbName.hashCode() : 0);
        result = 31 * result + (schemaName != null ? schemaName.hashCode() : 0);
        result = 31 * result + (schemaDesc != null ? schemaDesc.hashCode() : 0);
        result = 31 * result + (schemaNameDisplay != null ? schemaNameDisplay.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
