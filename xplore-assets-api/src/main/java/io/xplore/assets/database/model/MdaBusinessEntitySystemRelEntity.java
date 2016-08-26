package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 12/08/2016.
 */
@Entity
@Table(name = "mda_business_entity_system_rel", schema = "dbo", catalog = "data_asset")
public class MdaBusinessEntitySystemRelEntity {
    private int businessEntitySystemRelKey;
    private String businessEntitySystemRelName;
    private Integer businessEntityKey;
    private Integer systemKey;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @Column(name = "business_entity_system_rel_key")
    public int getBusinessEntitySystemRelKey() {
        return businessEntitySystemRelKey;
    }

    public void setBusinessEntitySystemRelKey(int businessEntitySystemRelKey) {
        this.businessEntitySystemRelKey = businessEntitySystemRelKey;
    }

    @Basic
    @Column(name = "business_entity_system_rel_name")
    public String getBusinessEntitySystemRelName() {
        return businessEntitySystemRelName;
    }

    public void setBusinessEntitySystemRelName(String businessEntitySystemRelName) {
        this.businessEntitySystemRelName = businessEntitySystemRelName;
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
    @Column(name = "system_key")
    public Integer getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(Integer systemKey) {
        this.systemKey = systemKey;
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

        MdaBusinessEntitySystemRelEntity that = (MdaBusinessEntitySystemRelEntity) o;

        if (businessEntitySystemRelKey != that.businessEntitySystemRelKey) return false;
        if (businessEntitySystemRelName != null ? !businessEntitySystemRelName.equals(that.businessEntitySystemRelName) : that.businessEntitySystemRelName != null)
            return false;
        if (businessEntityKey != null ? !businessEntityKey.equals(that.businessEntityKey) : that.businessEntityKey != null)
            return false;
        if (systemKey != null ? !systemKey.equals(that.systemKey) : that.systemKey != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = businessEntitySystemRelKey;
        result = 31 * result + (businessEntitySystemRelName != null ? businessEntitySystemRelName.hashCode() : 0);
        result = 31 * result + (businessEntityKey != null ? businessEntityKey.hashCode() : 0);
        result = 31 * result + (systemKey != null ? systemKey.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
