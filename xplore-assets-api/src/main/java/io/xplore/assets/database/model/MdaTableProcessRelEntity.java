package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 23/09/2016.
 */
@Entity
@Table(name = "mda_table_process_rel", schema = "dbo", catalog = "data_asset")
@NamedQueries({
        @NamedQuery(name="MdaTableProcessRelEntity.findByTableKey", query="SELECT a FROM MdaTableProcessRelEntity a WHERE a.tableKey = :tableKey"),
        @NamedQuery(name="MdaTableProcessRelEntity.findByProcessKey", query="SELECT a FROM MdaTableProcessRelEntity a WHERE a.processKey = :processKey"),
        @NamedQuery(name="MdaTableProcessRelEntity.findByProcessAndTable", query="SELECT a FROM MdaTableProcessRelEntity a WHERE (a.processKey = :processKey) AND (a.tableKey = :tableKey)")
})
public class MdaTableProcessRelEntity {
    private int tableProcessRelKey;
    private String tableProcessRelName;
    private Integer tableKey;
    private Integer processKey;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "table_process_rel_key")
    public int getTableProcessRelKey() {
        return tableProcessRelKey;
    }
    public void setTableProcessRelKey(int tableProcessRelKey) {
        this.tableProcessRelKey = tableProcessRelKey;
    }

    @Basic
    @Column(name = "table_process_rel_name")
    public String getTableProcessRelName() {
        return tableProcessRelName;
    }
    public void setTableProcessRelName(String tableProcessRelName) {
        this.tableProcessRelName = tableProcessRelName;
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
    @Column(name = "process_key")
    public Integer getProcessKey() {
        return processKey;
    }
    public void setProcessKey(Integer processKey) {
        this.processKey = processKey;
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

        MdaTableProcessRelEntity that = (MdaTableProcessRelEntity) o;

        if (tableProcessRelKey != that.tableProcessRelKey) return false;
        if (tableProcessRelName != null ? !tableProcessRelName.equals(that.tableProcessRelName) : that.tableProcessRelName != null)
            return false;
        if (tableKey != null ? !tableKey.equals(that.tableKey) : that.tableKey != null) return false;
        if (processKey != null ? !processKey.equals(that.processKey) : that.processKey != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableProcessRelKey;
        result = 31 * result + (tableProcessRelName != null ? tableProcessRelName.hashCode() : 0);
        result = 31 * result + (tableKey != null ? tableKey.hashCode() : 0);
        result = 31 * result + (processKey != null ? processKey.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
