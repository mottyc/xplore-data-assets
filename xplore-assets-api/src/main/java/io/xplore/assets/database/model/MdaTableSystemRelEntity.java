package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 12/08/2016.
 */
@Entity
@Table(name = "mda_table_system_rel", schema = "dbo", catalog = "data_asset")
@NamedQueries({
        @NamedQuery(name="MdaTableSystemRelEntity.findBySystemKey", query="SELECT a FROM MdaTableSystemRelEntity a WHERE a.systemKey = :systemKey"),
        @NamedQuery(name="MdaTableSystemRelEntity.findByTableKey", query="SELECT a FROM MdaTableSystemRelEntity a WHERE a.tableKey = :tableKey"),
        @NamedQuery(name="MdaTableSystemRelEntity.findByTableAndSystem", query="SELECT a FROM MdaTableSystemRelEntity a WHERE (a.tableKey = :tableKey) AND (a.systemKey = :systemKey)")
})
public class MdaTableSystemRelEntity {
    private int tableSystemRelKey;
    private String tableSystemRelName;
    private Integer tableKey;
    private Integer systemKey;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "table_system_rel_key")
    public int getTableSystemRelKey() {
        return tableSystemRelKey;
    }
    public void setTableSystemRelKey(int tableSystemRelKey) {
        this.tableSystemRelKey = tableSystemRelKey;
    }

    @Basic
    @Column(name = "table_system_rel_name")
    public String getTableSystemRelName() {
        return tableSystemRelName;
    }
    public void setTableSystemRelName(String tableSystemRelName) {
        this.tableSystemRelName = tableSystemRelName;
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

        MdaTableSystemRelEntity that = (MdaTableSystemRelEntity) o;

        if (tableSystemRelKey != that.tableSystemRelKey) return false;
        if (tableSystemRelName != null ? !tableSystemRelName.equals(that.tableSystemRelName) : that.tableSystemRelName != null)
            return false;
        if (tableKey != null ? !tableKey.equals(that.tableKey) : that.tableKey != null) return false;
        if (systemKey != null ? !systemKey.equals(that.systemKey) : that.systemKey != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableSystemRelKey;
        result = 31 * result + (tableSystemRelName != null ? tableSystemRelName.hashCode() : 0);
        result = 31 * result + (tableKey != null ? tableKey.hashCode() : 0);
        result = 31 * result + (systemKey != null ? systemKey.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
