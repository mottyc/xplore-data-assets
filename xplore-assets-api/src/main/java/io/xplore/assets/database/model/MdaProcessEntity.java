package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 23/09/2016.
 */
@Entity
@Table(name = "mda_process", schema = "dbo", catalog = "data_asset")
@NamedQueries({
        @NamedQuery(name="MdaProcessEntity.findAll", query="SELECT a FROM MdaProcessEntity a"),
        @NamedQuery(name="MdaProcessEntity.findByKey", query="SELECT a FROM MdaProcessEntity a WHERE a.processKey = :processKey")
})
public class MdaProcessEntity {
    private int processKey;
    private String processName;
    private String processNameDisplay;
    private String processDesc;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "process_key")
    public int getProcessKey() {
        return processKey;
    }
    public void setProcessKey(int processKey) {
        this.processKey = processKey;
    }

    @Basic
    @Column(name = "process_name")
    public String getProcessName() {
        return processName;
    }
    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Basic
    @Column(name = "process_name_display")
    public String getProcessNameDisplay() {
        return processNameDisplay;
    }
    public void setProcessNameDisplay(String processNameDisplay) {
        this.processNameDisplay = processNameDisplay;
    }

    @Basic
    @Column(name = "process_desc")
    public String getProcessDesc() {
        return processDesc;
    }
    public void setProcessDesc(String processDesc) {
        this.processDesc = processDesc;
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

        MdaProcessEntity that = (MdaProcessEntity) o;

        if (processKey != that.processKey) return false;
        if (processName != null ? !processName.equals(that.processName) : that.processName != null) return false;
        if (processNameDisplay != null ? !processNameDisplay.equals(that.processNameDisplay) : that.processNameDisplay != null)
            return false;
        if (processDesc != null ? !processDesc.equals(that.processDesc) : that.processDesc != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = processKey;
        result = 31 * result + (processName != null ? processName.hashCode() : 0);
        result = 31 * result + (processNameDisplay != null ? processNameDisplay.hashCode() : 0);
        result = 31 * result + (processDesc != null ? processDesc.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
