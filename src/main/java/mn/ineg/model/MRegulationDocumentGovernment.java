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
@Table(name = "m_regulation_document_government")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MRegulationDocumentGovernment.findAll", query = "SELECT m FROM MRegulationDocumentGovernment m"),
    @NamedQuery(name = "MRegulationDocumentGovernment.findById", query = "SELECT m FROM MRegulationDocumentGovernment m WHERE m.id = :id"),
    @NamedQuery(name = "MRegulationDocumentGovernment.findByRegNumber", query = "SELECT m FROM MRegulationDocumentGovernment m WHERE m.regNumber = :regNumber"),
    @NamedQuery(name = "MRegulationDocumentGovernment.findByApprovedYear", query = "SELECT m FROM MRegulationDocumentGovernment m WHERE m.approvedYear = :approvedYear"),
    @NamedQuery(name = "MRegulationDocumentGovernment.findByRegulatedBy", query = "SELECT m FROM MRegulationDocumentGovernment m WHERE m.regulatedBy = :regulatedBy"),
    @NamedQuery(name = "MRegulationDocumentGovernment.findByRegulationOrganisation", query = "SELECT m FROM MRegulationDocumentGovernment m WHERE m.regulationOrganisation = :regulationOrganisation")})
public class MRegulationDocumentGovernment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "reg_number")
    private String regNumber;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "reg_content")
    private String regContent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "approved_year")
    @Temporal(TemporalType.DATE)
    private Date approvedYear;
    @Size(max = 100)
    @Column(name = "regulated_by")
    private String regulatedBy;
    @Size(max = 100)
    @Column(name = "regulation_organisation")
    private String regulationOrganisation;
    @JoinColumn(name = "regulation_type", referencedColumnName = "id")
    @ManyToOne
    private MRegulationDocumentGovType regulationType;

    public MRegulationDocumentGovernment() {
    }

    public MRegulationDocumentGovernment(Integer id) {
        this.id = id;
    }

    public MRegulationDocumentGovernment(Integer id, String regNumber, String regContent, Date approvedYear) {
        this.id = id;
        this.regNumber = regNumber;
        this.regContent = regContent;
        this.approvedYear = approvedYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegContent() {
        return regContent;
    }

    public void setRegContent(String regContent) {
        this.regContent = regContent;
    }

    public Date getApprovedYear() {
        return approvedYear;
    }

    public void setApprovedYear(Date approvedYear) {
        this.approvedYear = approvedYear;
    }

    public String getRegulatedBy() {
        return regulatedBy;
    }

    public void setRegulatedBy(String regulatedBy) {
        this.regulatedBy = regulatedBy;
    }

    public String getRegulationOrganisation() {
        return regulationOrganisation;
    }

    public void setRegulationOrganisation(String regulationOrganisation) {
        this.regulationOrganisation = regulationOrganisation;
    }

    public MRegulationDocumentGovType getRegulationType() {
        return regulationType;
    }

    public void setRegulationType(MRegulationDocumentGovType regulationType) {
        this.regulationType = regulationType;
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
        if (!(object instanceof MRegulationDocumentGovernment)) {
            return false;
        }
        MRegulationDocumentGovernment other = (MRegulationDocumentGovernment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.model.MRegulationDocumentGovernment[ id=" + id + " ]";
    }
    
}
