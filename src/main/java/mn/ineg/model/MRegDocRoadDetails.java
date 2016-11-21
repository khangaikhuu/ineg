/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "m_reg_doc_road_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MRegDocRoadDetails.findAll", query = "SELECT m FROM MRegDocRoadDetails m"),
    @NamedQuery(name = "MRegDocRoadDetails.findById", query = "SELECT m FROM MRegDocRoadDetails m WHERE m.id = :id"),
    @NamedQuery(name = "MRegDocRoadDetails.findByRegNumber", query = "SELECT m FROM MRegDocRoadDetails m WHERE m.regNumber = :regNumber"),
    @NamedQuery(name = "MRegDocRoadDetails.findByRegApprovedYear", query = "SELECT m FROM MRegDocRoadDetails m WHERE m.regApprovedYear = :regApprovedYear"),
    @NamedQuery(name = "MRegDocRoadDetails.findByRegDoneBy", query = "SELECT m FROM MRegDocRoadDetails m WHERE m.regDoneBy = :regDoneBy"),
    @NamedQuery(name = "MRegDocRoadDetails.findByRegCreatedBy", query = "SELECT m FROM MRegDocRoadDetails m WHERE m.regCreatedBy = :regCreatedBy")})
public class MRegDocRoadDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reg_number")
    private int regNumber;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "reg_content")
    private String regContent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reg_approved_year")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regApprovedYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "reg_done_by")
    private String regDoneBy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "reg_created_by")
    private String regCreatedBy;
    @JoinColumn(name = "doc_type_id", referencedColumnName = "id")
    @ManyToOne
    private MRegDocTypeTypes docTypeId;

    public MRegDocRoadDetails() {
    }

    public MRegDocRoadDetails(Integer id) {
        this.id = id;
    }

    public MRegDocRoadDetails(Integer id, int regNumber, String regContent, Date regApprovedYear, String regDoneBy, String regCreatedBy) {
        this.id = id;
        this.regNumber = regNumber;
        this.regContent = regContent;
        this.regApprovedYear = regApprovedYear;
        this.regDoneBy = regDoneBy;
        this.regCreatedBy = regCreatedBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(int regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegContent() {
        return regContent;
    }

    public void setRegContent(String regContent) {
        this.regContent = regContent;
    }

    public Date getRegApprovedYear() {
        return regApprovedYear;
    }

    public void setRegApprovedYear(Date regApprovedYear) {
        this.regApprovedYear = regApprovedYear;
    }

    public String getRegDoneBy() {
        return regDoneBy;
    }

    public void setRegDoneBy(String regDoneBy) {
        this.regDoneBy = regDoneBy;
    }

    public String getRegCreatedBy() {
        return regCreatedBy;
    }

    public void setRegCreatedBy(String regCreatedBy) {
        this.regCreatedBy = regCreatedBy;
    }

    public MRegDocTypeTypes getDocTypeId() {
        return docTypeId;
    }

    public void setDocTypeId(MRegDocTypeTypes docTypeId) {
        this.docTypeId = docTypeId;
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
        if (!(object instanceof MRegDocRoadDetails)) {
            return false;
        }
        MRegDocRoadDetails other = (MRegDocRoadDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.MRegDocRoadDetails[ id=" + id + " ]";
    }
    
}
