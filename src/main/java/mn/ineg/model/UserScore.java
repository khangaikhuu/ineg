/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.model;

import mn.ineg.model.ScoreDtl;
import mn.ineg.model.MStaff;
import mn.ineg.model.ScoreDescription;
import java.io.Serializable;
import java.util.Date;
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
@Table(name = "user_score")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserScore.findAll", query = "SELECT u FROM UserScore u"),
    @NamedQuery(name = "UserScore.findByUserScoreId", query = "SELECT u FROM UserScore u WHERE u.userScoreId = :userScoreId"),
    @NamedQuery(name = "UserScore.findByCreatedDate", query = "SELECT u FROM UserScore u WHERE u.createdDate = :createdDate"),
    @NamedQuery(name = "UserScore.findByTotalScore", query = "SELECT u FROM UserScore u WHERE u.totalScore = :totalScore")})
public class UserScore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_score_id")
    private Integer userScoreId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "total_score")
    private Integer totalScore;
    @OneToMany(mappedBy = "userScoreId")
    private List<ScoreDtl> scoreDtlList;
    @JoinColumn(name = "score_description_id", referencedColumnName = "score_description_id")
    @ManyToOne
    private ScoreDescription scoreDescriptionId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private MStaff userId;
    @JoinColumn(name = "user_selection_id", referencedColumnName = "user_selection_id")
    @ManyToOne
    private UserSelection userSelectionId;

    public UserScore() {
    }

    public UserScore(Integer userScoreId) {
        this.userScoreId = userScoreId;
    }

    public UserScore(Integer userScoreId, Date createdDate) {
        this.userScoreId = userScoreId;
        this.createdDate = createdDate;
    }

    public Integer getUserScoreId() {
        return userScoreId;
    }

    public void setUserScoreId(Integer userScoreId) {
        this.userScoreId = userScoreId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    @XmlTransient
    public List<ScoreDtl> getScoreDtlList() {
        return scoreDtlList;
    }

    public void setScoreDtlList(List<ScoreDtl> scoreDtlList) {
        this.scoreDtlList = scoreDtlList;
    }

    public ScoreDescription getScoreDescriptionId() {
        return scoreDescriptionId;
    }

    public void setScoreDescriptionId(ScoreDescription scoreDescriptionId) {
        this.scoreDescriptionId = scoreDescriptionId;
    }

    public MStaff getUserId() {
        return userId;
    }

    public void setUserId(MStaff userId) {
        this.userId = userId;
    }

    public UserSelection getUserSelectionId() {
        return userSelectionId;
    }

    public void setUserSelectionId(UserSelection userSelectionId) {
        this.userSelectionId = userSelectionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userScoreId != null ? userScoreId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserScore)) {
            return false;
        }
        UserScore other = (UserScore) object;
        if ((this.userScoreId == null && other.userScoreId != null) || (this.userScoreId != null && !this.userScoreId.equals(other.userScoreId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.UserScore[ userScoreId=" + userScoreId + " ]";
    }
    
}
