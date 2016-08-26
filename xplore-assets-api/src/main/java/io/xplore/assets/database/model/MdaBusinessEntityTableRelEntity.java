package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 12/08/2016.
 */
@Entity
@Table(name = "mda_business_entity_table_rel", schema = "dbo", catalog = "data_asset")
public class MdaBusinessEntityTableRelEntity {
    private int businessEntityTableRelKey;
    private String businessEntityTableRelName;
    private Integer businessEntityKey;
    private Integer tableKey;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @Column(name = "business_entity_table_rel_key")
    public int getBusinessEntityTableRelKey() {
        return businessEntityTableRelKey;
    }

    public void setBusinessEntityTableRelKey(int businessEntityTableRelKey) {
        this.businessEntityTableRelKey = businessEntityTableRelKey;
    }

    @Basic
    @Column(name = "business_entity_table_rel_name")
    public String getBusinessEntityTableRelName() {
        return businessEntityTableRelName;
    }

    public void setBusinessEntityTableRelName(String businessEntityTableRelName) {
        this.businessEntityTableRelName = businessEntityTableRelName;
    }

    @Basic
    @Column(name = "business_entity_key")
    public Integer getBusinessEntityKey() {
        return businessEntityKey;
    }

    public void setBusinessEntityKey(Integer businessEntityKey) {
        this.businessEntityKey = businessEntityKey;
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

        MdaBusinessEntityTableRelEntity that = (MdaBusinessEntityTableRelEntity) o;

        if (businessEntityTableRelKey != that.businessEntityTableRelKey) return false;
        if (businessEntityTableRelName != null ? !businessEntityTableRelName.equals(that.businessEntityTableRelName) : that.businessEntityTableRelName != null)
            return false;
        if (businessEntityKey != null ? !businessEntityKey.equals(that.businessEntityKey) : that.businessEntityKey != null)
            return false;
        if (tableKey != null ? !tableKey.equals(that.tableKey) : that.tableKey != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = businessEntityTableRelKey;
        result = 31 * result + (businessEntityTableRelName != null ? businessEntityTableRelName.hashCode() : 0);
        result = 31 * result + (businessEntityKey != null ? businessEntityKey.hashCode() : 0);
        result = 31 * result + (tableKey != null ? tableKey.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
