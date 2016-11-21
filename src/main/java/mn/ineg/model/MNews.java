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
import javax.persistence.Lob;
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
@Table(name = "m_news")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MNews.findAll", query = "SELECT m FROM MNews m"),
    @NamedQuery(name = "MNews.findById", query = "SELECT m FROM MNews m WHERE m.id = :id"),
    @NamedQuery(name = "MNews.findByTitle", query = "SELECT m FROM MNews m WHERE m.title = :title")})
public class MNews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne
    private MNewsCategory categoryId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private MStaff userId;

    public MNews() {
    }

    public MNews(Integer id) {
        this.id = id;
    }

    public MNews(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MNewsCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(MNewsCategory categoryId) {
        this.categoryId = categoryId;
    }

    public MStaff getUserId() {
        return userId;
    }

    public void setUserId(MStaff userId) {
        this.userId = userId;
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
        if (!(object instanceof MNews)) {
            return false;
        }
        MNews other = (MNews) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.MNews[ id=" + id + " ]";
    }
    
}
