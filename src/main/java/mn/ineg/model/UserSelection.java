/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author developer
 */
@Entity
@Table(name = "user_selection")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSelection.findAll", query = "SELECT u FROM UserSelection u"),
    @NamedQuery(name = "UserSelection.findByUserSelectionId", query = "SELECT u FROM UserSelection u WHERE u.userSelectionId = :userSelectionId"),
    @NamedQuery(name = "UserSelection.findByUserId", query = "SELECT u FROM UserSelection u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserSelection.findByCategoryId", query = "SELECT u FROM UserSelection u WHERE u.categoryId = :categoryId"),
    @NamedQuery(name = "UserSelection.findByQuestionNumber", query = "SELECT u FROM UserSelection u WHERE u.questionNumber = :questionNumber"),
    @NamedQuery(name = "UserSelection.findByCreatedDate", query = "SELECT u FROM UserSelection u WHERE u.createdDate = :createdDate")})
public class UserSelection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_selection_id")
    private Integer userSelectionId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "question_number")
    private Integer questionNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @OneToMany(mappedBy = "userSelectionId")
    private List<UserScore> userScoreList;

    public UserSelection() {
    }

    public UserSelection(Integer userSelectionId) {
        this.userSelectionId = userSelectionId;
    }

    public UserSelection(Integer userSelectionId, Date createdDate) {
        this.userSelectionId = userSelectionId;
        this.createdDate = createdDate;
    }

    public Integer getUserSelectionId() {
        return userSelectionId;
    }

    public void setUserSelectionId(Integer userSelectionId) {
        this.userSelectionId = userSelectionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    public List<UserScore> getUserScoreList() {
        return userScoreList;
    }

    public void setUserScoreList(List<UserScore> userScoreList) {
        this.userScoreList = userScoreList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userSelectionId != null ? userSelectionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSelection)) {
            return false;
        }
        UserSelection other = (UserSelection) object;
        if ((this.userSelectionId == null && other.userSelectionId != null) || (this.userSelectionId != null && !this.userSelectionId.equals(other.userSelectionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.UserSelection[ userSelectionId=" + userSelectionId + " ]";
    }
    
}
