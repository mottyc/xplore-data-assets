package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 12/08/2016.
 */
@Entity
@Table(name = "mda_business_entity", schema = "dbo", catalog = "data_asset")
@NamedQueries({
        @NamedQuery(name="MdaBusinessEntityEntity.findAll", query="SELECT a FROM MdaBusinessEntityEntity a"),
        @NamedQuery(name="MdaBusinessEntityEntity.findByKey", query="SELECT a FROM MdaBusinessEntityEntity a WHERE a.businessEntityKey = :businessEntityKey"),
})
public class MdaBusinessEntityEntity {
    private int businessEntityKey;
    private String businessEntityName;
    private String businessEntityNameDisplay;
    private String businessEntityDesc;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "business_entity_key")
    public int getBusinessEntityKey() {
        return businessEntityKey;
    }
    public void setBusinessEntityKey(int businessEntityKey) {
        this.businessEntityKey = businessEntityKey;
    }

    @Basic
    @Column(name = "business_entity_name")
    public String getBusinessEntityName() {
        return businessEntityName;
    }
    public void setBusinessEntityName(String businessEntityName) {
        this.businessEntityName = businessEntityName;
    }

    @Basic
    @Column(name = "business_entity_name_display")
    public String getBusinessEntityNameDisplay() {
        return businessEntityNameDisplay;
    }
    public void setBusinessEntityNameDisplay(String businessEntityNameDisplay) {
        this.businessEntityNameDisplay = businessEntityNameDisplay;
    }

    @Basic
    @Column(name = "business_entity_desc")
    public String getBusinessEntityDesc() {
        return businessEntityDesc;
    }
    public void setBusinessEntityDesc(String businessEntityDesc) {
        this.businessEntityDesc = businessEntityDesc;
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

        MdaBusinessEntityEntity that = (MdaBusinessEntityEntity) o;

        if (businessEntityKey != that.businessEntityKey) return false;
        if (businessEntityName != null ? !businessEntityName.equals(that.businessEntityName) : that.businessEntityName != null)
            return false;
        if (businessEntityNameDisplay != null ? !businessEntityNameDisplay.equals(that.businessEntityNameDisplay) : that.businessEntityNameDisplay != null)
            return false;
        if (businessEntityDesc != null ? !businessEntityDesc.equals(that.businessEntityDesc) : that.businessEntityDesc != null)
            return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = businessEntityKey;
        result = 31 * result + (businessEntityName != null ? businessEntityName.hashCode() : 0);
        result = 31 * result + (businessEntityNameDisplay != null ? businessEntityNameDisplay.hashCode() : 0);
        result = 31 * result + (businessEntityDesc != null ? businessEntityDesc.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
