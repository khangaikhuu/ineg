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
@Table(name = "m_law_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MLawType.findAll", query = "SELECT m FROM MLawType m"),
    @NamedQuery(name = "MLawType.findById", query = "SELECT m FROM MLawType m WHERE m.id = :id"),
    @NamedQuery(name = "MLawType.findByName", query = "SELECT m FROM MLawType m WHERE m.name = :name")})
public class MLawType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "lawTypeId")
    private List<MLaw> mLawList;

    public MLawType() {
    }

    public MLawType(Integer id) {
        this.id = id;
    }

    public MLawType(Integer id, String name) {
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
    public List<MLaw> getMLawList() {
        return mLawList;
    }

    public void setMLawList(List<MLaw> mLawList) {
        this.mLawList = mLawList;
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
        if (!(object instanceof MLawType)) {
            return false;
        }
        MLawType other = (MLawType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.MLawType[ id=" + id + " ]";
    }
    
}
