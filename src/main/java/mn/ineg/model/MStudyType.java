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
@Table(name = "m_study_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MStudyType.findAll", query = "SELECT m FROM MStudyType m"),
    @NamedQuery(name = "MStudyType.findById", query = "SELECT m FROM MStudyType m WHERE m.id = :id"),
    @NamedQuery(name = "MStudyType.findByName", query = "SELECT m FROM MStudyType m WHERE m.name = :name")})
public class MStudyType implements Serializable {

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
    @OneToMany(mappedBy = "typeId")
    private List<MStudies> mStudiesList;

    public MStudyType() {
    }

    public MStudyType(Integer id) {
        this.id = id;
    }

    public MStudyType(Integer id, String name) {
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
    public List<MStudies> getMStudiesList() {
        return mStudiesList;
    }

    public void setMStudiesList(List<MStudies> mStudiesList) {
        this.mStudiesList = mStudiesList;
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
        if (!(object instanceof MStudyType)) {
            return false;
        }
        MStudyType other = (MStudyType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.model.MStudyType[ id=" + id + " ]";
    }
    
}
