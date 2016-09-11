package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 12/08/2016.
 */
@Entity
@Table(name = "mda_server", schema = "dbo", catalog = "data_asset")
@NamedQueries({
        @NamedQuery(name="MdaServerEntity.findAll", query="SELECT a FROM MdaServerEntity a"),
        @NamedQuery(name="MdaServerEntity.findByKey", query="SELECT a FROM MdaServerEntity a WHERE a.serverKey = :serverKey"),
        @NamedQuery(name="MdaServerEntity.findByKeys", query="SELECT a FROM MdaServerEntity a WHERE a.serverKey IN :keys"),
        @NamedQuery(name="MdaServerEntity.findByType", query="SELECT a FROM MdaServerEntity a WHERE a.serverTypeCd = :serverTypeCd")
})
public class MdaServerEntity {
    private int serverKey;
    private String serverName;
    private String serverNameDisplay;
    private String serverDesc;
    private String serverTypeCd;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "server_key")
    public int getServerKey() {
        return serverKey;
    }
    public void setServerKey(int serverKey) {
        this.serverKey = serverKey;
    }

    @Basic
    @Column(name = "server_name")
    public String getServerName() {
        return serverName;
    }
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Basic
    @Column(name = "server_name_display")
    public String getServerNameDisplay() {
        return serverNameDisplay;
    }
    public void setServerNameDisplay(String serverNameDisplay) {
        this.serverNameDisplay = serverNameDisplay;
    }

    @Basic
    @Column(name = "server_desc")
    public String getServerDesc() {
        return serverDesc;
    }
    public void setServerDesc(String serverDesc) {
        this.serverDesc = serverDesc;
    }

    @Basic
    @Column(name = "server_type_cd")
    public String getServerTypeCd() {
        return serverTypeCd;
    }
    public void setServerTypeCd(String serverTypeCd) {
        this.serverTypeCd = serverTypeCd;
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

        MdaServerEntity that = (MdaServerEntity) o;

        if (serverKey != that.serverKey) return false;
        if (serverName != null ? !serverName.equals(that.serverName) : that.serverName != null) return false;
        if (serverNameDisplay != null ? !serverNameDisplay.equals(that.serverNameDisplay) : that.serverNameDisplay != null)
            return false;
        if (serverDesc != null ? !serverDesc.equals(that.serverDesc) : that.serverDesc != null) return false;
        if (serverTypeCd != null ? !serverTypeCd.equals(that.serverTypeCd) : that.serverTypeCd != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serverKey;
        result = 31 * result + (serverName != null ? serverName.hashCode() : 0);
        result = 31 * result + (serverNameDisplay != null ? serverNameDisplay.hashCode() : 0);
        result = 31 * result + (serverDesc != null ? serverDesc.hashCode() : 0);
        result = 31 * result + (serverTypeCd != null ? serverTypeCd.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
