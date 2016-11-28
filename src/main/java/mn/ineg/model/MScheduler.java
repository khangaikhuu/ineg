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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author developer
 */
@Entity
@Table(name = "m_scheduler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MScheduler.findAll", query = "SELECT m FROM MScheduler m"),
    @NamedQuery(name = "MScheduler.findByInt1", query = "SELECT m FROM MScheduler m WHERE m.int1 = :int1"),
    @NamedQuery(name = "MScheduler.findByName", query = "SELECT m FROM MScheduler m WHERE m.name = :name"),
    @NamedQuery(name = "MScheduler.findByDate", query = "SELECT m FROM MScheduler m WHERE m.date = :date"),
    @NamedQuery(name = "MScheduler.findByFilePath", query = "SELECT m FROM MScheduler m WHERE m.filePath = :filePath")})
public class MScheduler implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "int")
    private Integer int1;
    @Size(max = 500)
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 500)
    @Column(name = "file_path")
    private String filePath;
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @ManyToOne
    private MScheduleType typeId;

    public MScheduler() {
    }

    public MScheduler(Integer int1) {
        this.int1 = int1;
    }

    public Integer getInt1() {
        return int1;
    }

    public void setInt1(Integer int1) {
        this.int1 = int1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public MScheduleType getTypeId() {
        return typeId;
    }

    public void setTypeId(MScheduleType typeId) {
        this.typeId = typeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int1 != null ? int1.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MScheduler)) {
            return false;
        }
        MScheduler other = (MScheduler) object;
        if ((this.int1 == null && other.int1 != null) || (this.int1 != null && !this.int1.equals(other.int1))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.model.MScheduler[ int1=" + int1 + " ]";
    }
    
}
