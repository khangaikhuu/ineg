/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author developer
 */
@Entity
@Table(name = "m_law")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MLaw.findAll", query = "SELECT m FROM MLaw m"),
    @NamedQuery(name = "MLaw.findByLawId", query = "SELECT m FROM MLaw m WHERE m.lawId = :lawId"),
    @NamedQuery(name = "MLaw.findByLawName", query = "SELECT m FROM MLaw m WHERE m.lawName = :lawName"),
    @NamedQuery(name = "MLaw.findByApprovedYear", query = "SELECT m FROM MLaw m WHERE m.approvedYear = :approvedYear"),
    @NamedQuery(name = "MLaw.findByChangedYear", query = "SELECT m FROM MLaw m WHERE m.changedYear = :changedYear"),
    @NamedQuery(name = "MLaw.findByCreatedAt", query = "SELECT m FROM MLaw m WHERE m.createdAt = :createdAt"),
    @NamedQuery(name = "MLaw.findByCreatedBy", query = "SELECT m FROM MLaw m WHERE m.createdBy = :createdBy"),
    @NamedQuery(name = "MLaw.findByPath", query = "SELECT m FROM MLaw m WHERE m.path = :path")})
public class MLaw implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "law_id")
    private Integer lawId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "law_name")
    private String lawName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "approved_year")
    @Temporal(TemporalType.DATE)
    private Date approvedYear;
    @Column(name = "changed_year")
    @Temporal(TemporalType.DATE)
    private Date changedYear;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "created_by")
    private Integer createdBy;
    @Size(max = 500)
    @Column(name = "path")
    private String path;
    @JoinColumn(name = "law_type_id", referencedColumnName = "id")
    @ManyToOne
    private MLawType lawTypeId;

    public MLaw() {
    }

    public MLaw(Integer lawId) {
        this.lawId = lawId;
    }

    public MLaw(Integer lawId, String lawName, Date approvedYear) {
        this.lawId = lawId;
        this.lawName = lawName;
        this.approvedYear = approvedYear;
    }

    public Integer getLawId() {
        return lawId;
    }

    public void setLawId(Integer lawId) {
        this.lawId = lawId;
    }

    @JsonProperty("name")
    public String getLawName() {
        return lawName;
    }

    public void setLawName(String lawName) {
        this.lawName = lawName;
    }

    public Date getApprovedYear() {
        return approvedYear;
    }

    public void setApprovedYear(Date approvedYear) {
        this.approvedYear = approvedYear;
    }

    public Date getChangedYear() {
        return changedYear;
    }

    public void setChangedYear(Date changedYear) {
        this.changedYear = changedYear;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MLawType getLawTypeId() {
        return lawTypeId;
    }

    public void setLawTypeId(MLawType lawTypeId) {
        this.lawTypeId = lawTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lawId != null ? lawId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MLaw)) {
            return false;
        }
        MLaw other = (MLaw) object;
        if ((this.lawId == null && other.lawId != null) || (this.lawId != null && !this.lawId.equals(other.lawId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.MLaw[ lawId=" + lawId + " ]";
    }
    
}
