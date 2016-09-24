package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 23/09/2016.
 */
@Entity
@Table(name = "mda_process_system_rel", schema = "dbo", catalog = "data_asset")
@NamedQueries({
        @NamedQuery(name="MdaProcessSystemRelEntity.findBySystemKey", query="SELECT a FROM MdaProcessSystemRelEntity a WHERE a.systemKey = :systemKey"),
        @NamedQuery(name="MdaProcessSystemRelEntity.findByProcessKey", query="SELECT a FROM MdaProcessSystemRelEntity a WHERE a.processKey = :processKey"),
        @NamedQuery(name="MdaProcessSystemRelEntity.findByProcessAndSystem", query="SELECT a FROM MdaProcessSystemRelEntity a WHERE (a.processKey = :processKey) AND (a.systemKey = :systemKey)")
})
public class MdaProcessSystemRelEntity {
    private int processSystemRelKey;
    private String processSystemRelName;
    private Integer processKey;
    private Integer systemKey;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "process_system_rel_key")
    public int getProcessSystemRelKey() {
        return processSystemRelKey;
    }
    public void setProcessSystemRelKey(int processSystemRelKey) {
        this.processSystemRelKey = processSystemRelKey;
    }

    @Basic
    @Column(name = "process_system_rel_name")
    public String getProcessSystemRelName() {
        return processSystemRelName;
    }
    public void setProcessSystemRelName(String processSystemRelName) {
        this.processSystemRelName = processSystemRelName;
    }

    @Basic
    @Column(name = "process_key")
    public Integer getProcessKey() {
        return processKey;
    }
    public void setProcessKey(Integer processKey) {
        this.processKey = processKey;
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

        MdaProcessSystemRelEntity that = (MdaProcessSystemRelEntity) o;

        if (processSystemRelKey != that.processSystemRelKey) return false;
        if (processSystemRelName != null ? !processSystemRelName.equals(that.processSystemRelName) : that.processSystemRelName != null)
            return false;
        if (processKey != null ? !processKey.equals(that.processKey) : that.processKey != null) return false;
        if (systemKey != null ? !systemKey.equals(that.systemKey) : that.systemKey != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = processSystemRelKey;
        result = 31 * result + (processSystemRelName != null ? processSystemRelName.hashCode() : 0);
        result = 31 * result + (processKey != null ? processKey.hashCode() : 0);
        result = 31 * result + (systemKey != null ? systemKey.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
