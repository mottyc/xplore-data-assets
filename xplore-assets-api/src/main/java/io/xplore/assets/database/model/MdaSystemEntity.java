package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 12/08/2016.
 */
@Entity
@Table(name = "mda_system", schema = "dbo", catalog = "data_asset")
public class MdaSystemEntity {
    private int systemKey;
    private String systemName;
    private String appName;
    private String appExpertUsernameKey;
    private String appOwnerUsernameKey;
    private String moduleOwnerUsernameKey;
    private String systemNameDisplay;
    private String systemDesc;
    private String systemDepartment;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @Column(name = "system_key")
    public int getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(int systemKey) {
        this.systemKey = systemKey;
    }

    @Basic
    @Column(name = "system_name")
    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    @Basic
    @Column(name = "app_name")
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Basic
    @Column(name = "app_expert_username_key")
    public String getAppExpertUsernameKey() {
        return appExpertUsernameKey;
    }

    public void setAppExpertUsernameKey(String appExpertUsernameKey) {
        this.appExpertUsernameKey = appExpertUsernameKey;
    }

    @Basic
    @Column(name = "app_owner_username_key")
    public String getAppOwnerUsernameKey() {
        return appOwnerUsernameKey;
    }

    public void setAppOwnerUsernameKey(String appOwnerUsernameKey) {
        this.appOwnerUsernameKey = appOwnerUsernameKey;
    }

    @Basic
    @Column(name = "module_owner_username_key")
    public String getModuleOwnerUsernameKey() {
        return moduleOwnerUsernameKey;
    }

    public void setModuleOwnerUsernameKey(String moduleOwnerUsernameKey) {
        this.moduleOwnerUsernameKey = moduleOwnerUsernameKey;
    }

    @Basic
    @Column(name = "system_name_display")
    public String getSystemNameDisplay() {
        return systemNameDisplay;
    }

    public void setSystemNameDisplay(String systemNameDisplay) {
        this.systemNameDisplay = systemNameDisplay;
    }

    @Basic
    @Column(name = "system_desc")
    public String getSystemDesc() {
        return systemDesc;
    }

    public void setSystemDesc(String systemDesc) {
        this.systemDesc = systemDesc;
    }

    @Basic
    @Column(name = "system_department")
    public String getSystemDepartment() {
        return systemDepartment;
    }

    public void setSystemDepartment(String systemDepartment) {
        this.systemDepartment = systemDepartment;
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

        MdaSystemEntity that = (MdaSystemEntity) o;

        if (systemKey != that.systemKey) return false;
        if (systemName != null ? !systemName.equals(that.systemName) : that.systemName != null) return false;
        if (appName != null ? !appName.equals(that.appName) : that.appName != null) return false;
        if (appExpertUsernameKey != null ? !appExpertUsernameKey.equals(that.appExpertUsernameKey) : that.appExpertUsernameKey != null)
            return false;
        if (appOwnerUsernameKey != null ? !appOwnerUsernameKey.equals(that.appOwnerUsernameKey) : that.appOwnerUsernameKey != null)
            return false;
        if (moduleOwnerUsernameKey != null ? !moduleOwnerUsernameKey.equals(that.moduleOwnerUsernameKey) : that.moduleOwnerUsernameKey != null)
            return false;
        if (systemNameDisplay != null ? !systemNameDisplay.equals(that.systemNameDisplay) : that.systemNameDisplay != null)
            return false;
        if (systemDesc != null ? !systemDesc.equals(that.systemDesc) : that.systemDesc != null) return false;
        if (systemDepartment != null ? !systemDepartment.equals(that.systemDepartment) : that.systemDepartment != null)
            return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = systemKey;
        result = 31 * result + (systemName != null ? systemName.hashCode() : 0);
        result = 31 * result + (appName != null ? appName.hashCode() : 0);
        result = 31 * result + (appExpertUsernameKey != null ? appExpertUsernameKey.hashCode() : 0);
        result = 31 * result + (appOwnerUsernameKey != null ? appOwnerUsernameKey.hashCode() : 0);
        result = 31 * result + (moduleOwnerUsernameKey != null ? moduleOwnerUsernameKey.hashCode() : 0);
        result = 31 * result + (systemNameDisplay != null ? systemNameDisplay.hashCode() : 0);
        result = 31 * result + (systemDesc != null ? systemDesc.hashCode() : 0);
        result = 31 * result + (systemDepartment != null ? systemDepartment.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
