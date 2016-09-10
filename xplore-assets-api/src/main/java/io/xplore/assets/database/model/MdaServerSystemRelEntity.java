package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 12/08/2016.
 */
@Entity
@Table(name = "mda_server_system_rel", schema = "dbo", catalog = "data_asset")
@NamedQueries({
        @NamedQuery(name="MdaServerSystemRelEntity.findBySystemKey", query="SELECT a FROM MdaServerSystemRelEntity a WHERE a.systemKey = :systemKey"),
        @NamedQuery(name="MdaServerSystemRelEntity.findByServerKey", query="SELECT a FROM MdaServerSystemRelEntity a WHERE a.serverKey = :serverKey"),
        @NamedQuery(name="MdaServerSystemRelEntity.findByServerAndSystem", query="SELECT a FROM MdaServerSystemRelEntity a WHERE (a.serverKey = :serverKey) AND (a.systemKey = :systemKey)")
})
public class MdaServerSystemRelEntity {
    private int mdaServerSystemKey;
    private String mdaServerSystemName;
    private Integer serverKey;
    private Integer systemKey;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "mda_server_system_key")
    public int getMdaServerSystemKey() {
        return mdaServerSystemKey;
    }
    public void setMdaServerSystemKey(int mdaServerSystemKey) {
        this.mdaServerSystemKey = mdaServerSystemKey;
    }

    @Basic
    @Column(name = "mda_server_system_name")
    public String getMdaServerSystemName() {
        return mdaServerSystemName;
    }
    public void setMdaServerSystemName(String mdaServerSystemName) {
        this.mdaServerSystemName = mdaServerSystemName;
    }

    @Basic
    @Column(name = "server_key")
    public Integer getServerKey() {
        return serverKey;
    }
    public void setServerKey(Integer serverKey) {
        this.serverKey = serverKey;
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

        MdaServerSystemRelEntity that = (MdaServerSystemRelEntity) o;

        if (mdaServerSystemKey != that.mdaServerSystemKey) return false;
        if (mdaServerSystemName != null ? !mdaServerSystemName.equals(that.mdaServerSystemName) : that.mdaServerSystemName != null)
            return false;
        if (serverKey != null ? !serverKey.equals(that.serverKey) : that.serverKey != null) return false;
        if (systemKey != null ? !systemKey.equals(that.systemKey) : that.systemKey != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mdaServerSystemKey;
        result = 31 * result + (mdaServerSystemName != null ? mdaServerSystemName.hashCode() : 0);
        result = 31 * result + (serverKey != null ? serverKey.hashCode() : 0);
        result = 31 * result + (systemKey != null ? systemKey.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
