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
@Table(name = "m_reg_doc_gov_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MRegDocGovDetails.findAll", query = "SELECT m FROM MRegDocGovDetails m"),
    @NamedQuery(name = "MRegDocGovDetails.findById", query = "SELECT m FROM MRegDocGovDetails m WHERE m.id = :id"),
    @NamedQuery(name = "MRegDocGovDetails.findByRegNumber", query = "SELECT m FROM MRegDocGovDetails m WHERE m.regNumber = :regNumber"),
    @NamedQuery(name = "MRegDocGovDetails.findByRegContent", query = "SELECT m FROM MRegDocGovDetails m WHERE m.regContent = :regContent"),
    @NamedQuery(name = "MRegDocGovDetails.findByRegApprovedYear", query = "SELECT m FROM MRegDocGovDetails m WHERE m.regApprovedYear = :regApprovedYear"),
    @NamedQuery(name = "MRegDocGovDetails.findByRegDoneBy", query = "SELECT m FROM MRegDocGovDetails m WHERE m.regDoneBy = :regDoneBy")})
public class MRegDocGovDetails implements Serializable {

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
    @Size(min = 1, max = 45)
    @Column(name = "reg_content")
    private String regContent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reg_approved_year")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regApprovedYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "reg_done_by")
    private String regDoneBy;
    @JoinColumn(name = "doc_type_id", referencedColumnName = "id")
    @ManyToOne
    private MRegDocTypeTypes docTypeId;

    public MRegDocGovDetails() {
    }

    public MRegDocGovDetails(Integer id) {
        this.id = id;
    }

    public MRegDocGovDetails(Integer id, int regNumber, String regContent, Date regApprovedYear, String regDoneBy) {
        this.id = id;
        this.regNumber = regNumber;
        this.regContent = regContent;
        this.regApprovedYear = regApprovedYear;
        this.regDoneBy = regDoneBy;
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
        if (!(object instanceof MRegDocGovDetails)) {
            return false;
        }
        MRegDocGovDetails other = (MRegDocGovDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.MRegDocGovDetails[ id=" + id + " ]";
    }
    
}
