/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author developer
 */
@Entity
@Table(name = "m_law_international")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MLawInternational.findAll", query = "SELECT m FROM MLawInternational m"),
    @NamedQuery(name = "MLawInternational.findById", query = "SELECT m FROM MLawInternational m WHERE m.id = :id"),
    @NamedQuery(name = "MLawInternational.findByName", query = "SELECT m FROM MLawInternational m WHERE m.name = :name"),
    @NamedQuery(name = "MLawInternational.findByPath", query = "SELECT m FROM MLawInternational m WHERE m.path = :path")})
public class MLawInternational implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 250)
    @Column(name = "name")
    private String name;
    @Size(max = 500)
    @Column(name = "path")
    private String path;
    @JoinColumn(name = "law_type_id", referencedColumnName = "id")
    @ManyToOne
    private MLawTypeInternational lawTypeId;

    public MLawInternational() {
    }

    public MLawInternational(Integer id) {
        this.id = id;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MLawTypeInternational getLawTypeId() {
        return lawTypeId;
    }

    public void setLawTypeId(MLawTypeInternational lawTypeId) {
        this.lawTypeId = lawTypeId;
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
        if (!(object instanceof MLawInternational)) {
            return false;
        }
        MLawInternational other = (MLawInternational) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.MLawInternational[ id=" + id + " ]";
    }
    
}
