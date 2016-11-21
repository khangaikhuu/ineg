/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author developer
 */
@Entity
@Table(name = "m_reg_doc_type_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MRegDocTypeTypes.findAll", query = "SELECT m FROM MRegDocTypeTypes m"),
    @NamedQuery(name = "MRegDocTypeTypes.findById", query = "SELECT m FROM MRegDocTypeTypes m WHERE m.id = :id"),
    @NamedQuery(name = "MRegDocTypeTypes.findByName", query = "SELECT m FROM MRegDocTypeTypes m WHERE m.name = :name")})
public class MRegDocTypeTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "docTypeId")
    private List<MRegDocGovDetails> mRegDocGovDetailsList;
    @JoinColumn(name = "doc_type_id", referencedColumnName = "id")
    @ManyToOne
    private MRegDocType docTypeId;
    @OneToMany(mappedBy = "docTypeId")
    private List<MRegDocRoadDetails> mRegDocRoadDetailsList;
    @OneToMany(mappedBy = "docTypeId")
    private List<MnRegDocInegDetails> mnRegDocInegDetailsList;

    public MRegDocTypeTypes() {
    }

    public MRegDocTypeTypes(Integer id) {
        this.id = id;
    }

    public MRegDocTypeTypes(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<MRegDocGovDetails> getMRegDocGovDetailsList() {
        return mRegDocGovDetailsList;
    }

    public void setMRegDocGovDetailsList(List<MRegDocGovDetails> mRegDocGovDetailsList) {
        this.mRegDocGovDetailsList = mRegDocGovDetailsList;
    }

    public MRegDocType getDocTypeId() {
        return docTypeId;
    }

    public void setDocTypeId(MRegDocType docTypeId) {
        this.docTypeId = docTypeId;
    }

    @XmlTransient
    public List<MRegDocRoadDetails> getMRegDocRoadDetailsList() {
        return mRegDocRoadDetailsList;
    }

    public void setMRegDocRoadDetailsList(List<MRegDocRoadDetails> mRegDocRoadDetailsList) {
        this.mRegDocRoadDetailsList = mRegDocRoadDetailsList;
    }

    @XmlTransient
    public List<MnRegDocInegDetails> getMnRegDocInegDetailsList() {
        return mnRegDocInegDetailsList;
    }

    public void setMnRegDocInegDetailsList(List<MnRegDocInegDetails> mnRegDocInegDetailsList) {
        this.mnRegDocInegDetailsList = mnRegDocInegDetailsList;
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
        if (!(object instanceof MRegDocTypeTypes)) {
            return false;
        }
        MRegDocTypeTypes other = (MRegDocTypeTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.MRegDocTypeTypes[ id=" + id + " ]";
    }
    
}
