package io.xplore.assets.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by motty on 12/08/2016.
 */
@Entity
@Table(name = "mda_username", schema = "dbo", catalog = "data_asset")
@NamedQueries({
        @NamedQuery(name="MdaUsernameEntity.findAll", query="SELECT a FROM MdaUsernameEntity a"),
        @NamedQuery(name="MdaUsernameEntity.findByKey", query="SELECT a FROM MdaUsernameEntity a WHERE a.usernameKey = :usernameKey")
})
public class MdaUsernameEntity {
    private String usernameKey;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeNameDisplay;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String updateBy;

    @Id
    @Column(name = "username_key")
    public String getUsernameKey() {
        return usernameKey;
    }
    public void setUsernameKey(String usernameKey) {
        this.usernameKey = usernameKey;
    }

    @Basic
    @Column(name = "employee_first_name")
    public String getEmployeeFirstName() {
        return employeeFirstName;
    }
    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    @Basic
    @Column(name = "employee_last_name")
    public String getEmployeeLastName() {
        return employeeLastName;
    }
    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    @Basic
    @Column(name = "employee_name_display")
    public String getEmployeeNameDisplay() {
        return employeeNameDisplay;
    }
    public void setEmployeeNameDisplay(String employeeNameDisplay) {
        this.employeeNameDisplay = employeeNameDisplay;
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

        MdaUsernameEntity that = (MdaUsernameEntity) o;

        if (usernameKey != null ? !usernameKey.equals(that.usernameKey) : that.usernameKey != null) return false;
        if (employeeFirstName != null ? !employeeFirstName.equals(that.employeeFirstName) : that.employeeFirstName != null)
            return false;
        if (employeeLastName != null ? !employeeLastName.equals(that.employeeLastName) : that.employeeLastName != null)
            return false;
        if (employeeNameDisplay != null ? !employeeNameDisplay.equals(that.employeeNameDisplay) : that.employeeNameDisplay != null)
            return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = usernameKey != null ? usernameKey.hashCode() : 0;
        result = 31 * result + (employeeFirstName != null ? employeeFirstName.hashCode() : 0);
        result = 31 * result + (employeeLastName != null ? employeeLastName.hashCode() : 0);
        result = 31 * result + (employeeNameDisplay != null ? employeeNameDisplay.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
