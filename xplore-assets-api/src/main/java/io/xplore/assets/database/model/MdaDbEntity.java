package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 12/08/2016.
 */
@Entity
@Table(name = "mda_db", schema = "dbo", catalog = "data_asset")
@NamedQueries({
        @NamedQuery(name="MdaDbEntity.findAll", query="SELECT a FROM MdaDbEntity a"),
        @NamedQuery(name="MdaDbEntity.findByKey", query="SELECT a FROM MdaDbEntity a WHERE a.domainKey = :domainKey")
})
public class MdaDbEntity {
    private int domainKey;
    private String fullDbName;
    private Integer serverKey;
    private String dbName;
    private String dbDesc;
    private String dbNameDisplay;
    private String technicalOwnerUsernameKey;
    private String dbTypeCd;
    private Integer dxpConnectionId;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "domain_key")
    public int getDomainKey() {
        return domainKey;
    }
    public void setDomainKey(int domainKey) {
        this.domainKey = domainKey;
    }

    @Basic
    @Column(name = "full_db_name")
    public String getFullDbName() {
        return fullDbName;
    }
    public void setFullDbName(String fullDbName) {
        this.fullDbName = fullDbName;
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
    @Column(name = "db_name")
    public String getDbName() {
        return dbName;
    }
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Basic
    @Column(name = "db_desc")
    public String getDbDesc() {
        return dbDesc;
    }
    public void setDbDesc(String dbDesc) {
        this.dbDesc = dbDesc;
    }

    @Basic
    @Column(name = "db_name_display")
    public String getDbNameDisplay() {
        return dbNameDisplay;
    }
    public void setDbNameDisplay(String dbNameDisplay) {
        this.dbNameDisplay = dbNameDisplay;
    }

    @Basic
    @Column(name = "technical_owner_username_key")
    public String getTechnicalOwnerUsernameKey() {
        return technicalOwnerUsernameKey;
    }
    public void setTechnicalOwnerUsernameKey(String technicalOwnerUsernameKey) {
        this.technicalOwnerUsernameKey = technicalOwnerUsernameKey;
    }

    @Basic
    @Column(name = "db_type_cd")
    public String getDbTypeCd() {
        return dbTypeCd;
    }
    public void setDbTypeCd(String dbTypeCd) {
        this.dbTypeCd = dbTypeCd;
    }

    @Basic
    @Column(name = "dxp_connection_id")
    public Integer getDxpConnectionId() {
        return dxpConnectionId;
    }
    public void setDxpConnectionId(Integer dxpConnectionId) {
        this.dxpConnectionId = dxpConnectionId;
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

        MdaDbEntity that = (MdaDbEntity) o;

        if (domainKey != that.domainKey) return false;
        if (fullDbName != null ? !fullDbName.equals(that.fullDbName) : that.fullDbName != null) return false;
        if (serverKey != null ? !serverKey.equals(that.serverKey) : that.serverKey != null) return false;
        if (dbName != null ? !dbName.equals(that.dbName) : that.dbName != null) return false;
        if (dbDesc != null ? !dbDesc.equals(that.dbDesc) : that.dbDesc != null) return false;
        if (dbNameDisplay != null ? !dbNameDisplay.equals(that.dbNameDisplay) : that.dbNameDisplay != null)
            return false;
        if (technicalOwnerUsernameKey != null ? !technicalOwnerUsernameKey.equals(that.technicalOwnerUsernameKey) : that.technicalOwnerUsernameKey != null)
            return false;
        if (dbTypeCd != null ? !dbTypeCd.equals(that.dbTypeCd) : that.dbTypeCd != null) return false;
        if (dxpConnectionId != null ? !dxpConnectionId.equals(that.dxpConnectionId) : that.dxpConnectionId != null)
            return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = domainKey;
        result = 31 * result + (fullDbName != null ? fullDbName.hashCode() : 0);
        result = 31 * result + (serverKey != null ? serverKey.hashCode() : 0);
        result = 31 * result + (dbName != null ? dbName.hashCode() : 0);
        result = 31 * result + (dbDesc != null ? dbDesc.hashCode() : 0);
        result = 31 * result + (dbNameDisplay != null ? dbNameDisplay.hashCode() : 0);
        result = 31 * result + (technicalOwnerUsernameKey != null ? technicalOwnerUsernameKey.hashCode() : 0);
        result = 31 * result + (dbTypeCd != null ? dbTypeCd.hashCode() : 0);
        result = 31 * result + (dxpConnectionId != null ? dxpConnectionId.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
