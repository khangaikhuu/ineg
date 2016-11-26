/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author developer
 */
@Entity
@Table(name = "m_staff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MStaff.findAll", query = "SELECT m FROM MStaff m")})
public class MStaff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "profile_image")
    private String profileImage;
    @Size(max = 100)
    @Column(name = "family_name")
    private String familyName;
    @Size(max = 200)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 200)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 10)
    @Column(name = "registerno")
    private String registerno;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Size(max = 500)
    @Column(name = "address")
    private String address;
    @Size(max = 10)
    @Column(name = "phone_no1")
    private String phoneNo1;
    @Size(max = 10)
    @Column(name = "phone_no2")
    private String phoneNo2;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 200)
    @Column(name = "email")
    private String email;
    @Size(max = 200)
    @Column(name = "password")
    private String password;
    @Size(max = 20)
    @Column(name = "occ_cert_no")
    private String occCertNo;
    @Column(name = "occ_cert_date")
    @Temporal(TemporalType.DATE)
    private Date occCertDate;
    @Size(max = 20)
    @Column(name = "health_cert_no")
    private String healthCertNo;
    @Column(name = "health_cert_date")
    @Temporal(TemporalType.DATE)
    private Date healthCertDate;
    @Column(name = "eng_level_date")
    @Temporal(TemporalType.DATE)
    private Date engLevelDate;
    @Column(name = "safety_cloth_sdate")
    @Temporal(TemporalType.DATE)
    private Date safetyClothSdate;
    @Column(name = "safety_cloth_edate")
    @Temporal(TemporalType.DATE)
    private Date safetyClothEdate;
    @JoinColumn(name = "work_condition_id", referencedColumnName = "id")
    @ManyToOne
    private MWorkCondition workConditionId;
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    @ManyToOne
    private MGender genderId;
    @JoinColumn(name = "division_id", referencedColumnName = "id")
    @ManyToOne
    private MDivision divisionId;
    @JoinColumn(name = "eng_level_id", referencedColumnName = "id")
    @ManyToOne
    private MEnglishLevel engLevelId;
    @JoinColumn(name = "marry_id", referencedColumnName = "id")
    @ManyToOne
    private MMarry marryId;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne
    private MRole roleId;
    @JoinColumn(name = "skill_category_id", referencedColumnName = "id")
    @ManyToOne
    private MSkillCategory skillCategoryId;
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne
    private MStaffStatus statusId;
    @OneToMany(mappedBy = "userId")
    private List<MNews> mNewsList;

    public MStaff() {
    }

    public MStaff(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getRegisterno() {
        return registerno;
    }

    public void setRegisterno(String registerno) {
        this.registerno = registerno;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo1() {
        return phoneNo1;
    }

    public void setPhoneNo1(String phoneNo1) {
        this.phoneNo1 = phoneNo1;
    }

    public String getPhoneNo2() {
        return phoneNo2;
    }

    public void setPhoneNo2(String phoneNo2) {
        this.phoneNo2 = phoneNo2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOccCertNo() {
        return occCertNo;
    }

    public void setOccCertNo(String occCertNo) {
        this.occCertNo = occCertNo;
    }

    public Date getOccCertDate() {
        return occCertDate;
    }

    public void setOccCertDate(Date occCertDate) {
        this.occCertDate = occCertDate;
    }

    public String getHealthCertNo() {
        return healthCertNo;
    }

    public void setHealthCertNo(String healthCertNo) {
        this.healthCertNo = healthCertNo;
    }

    public Date getHealthCertDate() {
        return healthCertDate;
    }

    public void setHealthCertDate(Date healthCertDate) {
        this.healthCertDate = healthCertDate;
    }

    public Date getEngLevelDate() {
        return engLevelDate;
    }

    public void setEngLevelDate(Date engLevelDate) {
        this.engLevelDate = engLevelDate;
    }

    public Date getSafetyClothSdate() {
        return safetyClothSdate;
    }

    public void setSafetyClothSdate(Date safetyClothSdate) {
        this.safetyClothSdate = safetyClothSdate;
    }

    public Date getSafetyClothEdate() {
        return safetyClothEdate;
    }

    public void setSafetyClothEdate(Date safetyClothEdate) {
        this.safetyClothEdate = safetyClothEdate;
    }

    public MWorkCondition getWorkConditionId() {
        return workConditionId;
    }

    public void setWorkConditionId(MWorkCondition workConditionId) {
        this.workConditionId = workConditionId;
    }

    public MGender getGenderId() {
        return genderId;
    }

    public void setGenderId(MGender genderId) {
        this.genderId = genderId;
    }

    public MDivision getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(MDivision divisionId) {
        this.divisionId = divisionId;
    }

    public MEnglishLevel getEngLevelId() {
        return engLevelId;
    }

    public void setEngLevelId(MEnglishLevel engLevelId) {
        this.engLevelId = engLevelId;
    }

    public MMarry getMarryId() {
        return marryId;
    }

    public void setMarryId(MMarry marryId) {
        this.marryId = marryId;
    }

    public MRole getRoleId() {
        return roleId;
    }

    public void setRoleId(MRole roleId) {
        this.roleId = roleId;
    }

    public MSkillCategory getSkillCategoryId() {
        return skillCategoryId;
    }

    public void setSkillCategoryId(MSkillCategory skillCategoryId) {
        this.skillCategoryId = skillCategoryId;
    }

    public MStaffStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(MStaffStatus statusId) {
        this.statusId = statusId;
    }

    @XmlTransient
    public List<MNews> getMNewsList() {
        return mNewsList;
    }

    public void setMNewsList(List<MNews> mNewsList) {
        this.mNewsList = mNewsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MStaff)) {
            return false;
        }
        MStaff other = (MStaff) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.model.MStaff[ id=" + id + " ]";
    }
    
}
