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
import javax.persistence.Lob;
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
@Table(name = "score_description")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScoreDescription.findAll", query = "SELECT s FROM ScoreDescription s"),
    @NamedQuery(name = "ScoreDescription.findByScoreDescriptionId", query = "SELECT s FROM ScoreDescription s WHERE s.scoreDescriptionId = :scoreDescriptionId"),
    @NamedQuery(name = "ScoreDescription.findByStartScore", query = "SELECT s FROM ScoreDescription s WHERE s.startScore = :startScore"),
    @NamedQuery(name = "ScoreDescription.findByEndScore", query = "SELECT s FROM ScoreDescription s WHERE s.endScore = :endScore")})
public class ScoreDescription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "score_description_id")
    private Integer scoreDescriptionId;
    @Column(name = "start_score")
    private Integer startScore;
    @Column(name = "end_score")
    private Integer endScore;
    @Lob
    @Column(name = "description_code")
    private byte[] descriptionCode;
    @Lob
    @Size(max = 65535)
    @Column(name = "score_description")
    private String scoreDescription;
    @OneToMany(mappedBy = "scoreDescriptionId")
    private List<UserScore> userScoreList;

    public ScoreDescription() {
    }

    public ScoreDescription(Integer scoreDescriptionId) {
        this.scoreDescriptionId = scoreDescriptionId;
    }

    public Integer getScoreDescriptionId() {
        return scoreDescriptionId;
    }

    public void setScoreDescriptionId(Integer scoreDescriptionId) {
        this.scoreDescriptionId = scoreDescriptionId;
    }

    public Integer getStartScore() {
        return startScore;
    }

    public void setStartScore(Integer startScore) {
        this.startScore = startScore;
    }

    public Integer getEndScore() {
        return endScore;
    }

    public void setEndScore(Integer endScore) {
        this.endScore = endScore;
    }

    public byte[] getDescriptionCode() {
        return descriptionCode;
    }

    public void setDescriptionCode(byte[] descriptionCode) {
        this.descriptionCode = descriptionCode;
    }

    public String getScoreDescription() {
        return scoreDescription;
    }

    public void setScoreDescription(String scoreDescription) {
        this.scoreDescription = scoreDescription;
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
        hash += (scoreDescriptionId != null ? scoreDescriptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScoreDescription)) {
            return false;
        }
        ScoreDescription other = (ScoreDescription) object;
        if ((this.scoreDescriptionId == null && other.scoreDescriptionId != null) || (this.scoreDescriptionId != null && !this.scoreDescriptionId.equals(other.scoreDescriptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.ScoreDescription[ scoreDescriptionId=" + scoreDescriptionId + " ]";
    }
    
}
