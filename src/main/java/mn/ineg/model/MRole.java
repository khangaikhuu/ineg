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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author developer
 */
@Entity
@Table(name = "m_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MRole.findAll", query = "SELECT m FROM MRole m"),
    @NamedQuery(name = "MRole.findById", query = "SELECT m FROM MRole m WHERE m.id = :id"),
    @NamedQuery(name = "MRole.findByName", query = "SELECT m FROM MRole m WHERE m.name = :name"),
    @NamedQuery(name = "MRole.findByCode", query = "SELECT m FROM MRole m WHERE m.code = :code")})
public class MRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 100)
    @Column(name = "code")
    private String code;
    @OneToMany(mappedBy = "roleId")
    private List<MStaff> mStaffList;

    public MRole() {
    }

    public MRole(Integer id) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    public List<MStaff> getMStaffList() {
        return mStaffList;
    }

    public void setMStaffList(List<MStaff> mStaffList) {
        this.mStaffList = mStaffList;
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
        if (!(object instanceof MRole)) {
            return false;
        }
        MRole other = (MRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.MRole[ id=" + id + " ]";
    }
    
}
