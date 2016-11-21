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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author developer
 */
@Entity
@Table(name = "score_dtl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScoreDtl.findAll", query = "SELECT s FROM ScoreDtl s"),
    @NamedQuery(name = "ScoreDtl.findByScoreDtlId", query = "SELECT s FROM ScoreDtl s WHERE s.scoreDtlId = :scoreDtlId"),
    @NamedQuery(name = "ScoreDtl.findByTakenScore", query = "SELECT s FROM ScoreDtl s WHERE s.takenScore = :takenScore")})
public class ScoreDtl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "score_dtl_id")
    private Integer scoreDtlId;
    @Column(name = "taken_score")
    private Integer takenScore;
    @JoinColumn(name = "answer_id", referencedColumnName = "answer_id")
    @ManyToOne
    private Answer answerId;
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    @ManyToOne
    private Question questionId;
    @JoinColumn(name = "user_score_id", referencedColumnName = "user_score_id")
    @ManyToOne
    private UserScore userScoreId;

    public ScoreDtl() {
    }

    public ScoreDtl(Integer scoreDtlId) {
        this.scoreDtlId = scoreDtlId;
    }

    public Integer getScoreDtlId() {
        return scoreDtlId;
    }

    public void setScoreDtlId(Integer scoreDtlId) {
        this.scoreDtlId = scoreDtlId;
    }

    public Integer getTakenScore() {
        return takenScore;
    }

    public void setTakenScore(Integer takenScore) {
        this.takenScore = takenScore;
    }

    public Answer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Answer answerId) {
        this.answerId = answerId;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }

    public UserScore getUserScoreId() {
        return userScoreId;
    }

    public void setUserScoreId(UserScore userScoreId) {
        this.userScoreId = userScoreId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scoreDtlId != null ? scoreDtlId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScoreDtl)) {
            return false;
        }
        ScoreDtl other = (ScoreDtl) object;
        if ((this.scoreDtlId == null && other.scoreDtlId != null) || (this.scoreDtlId != null && !this.scoreDtlId.equals(other.scoreDtlId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.ScoreDtl[ scoreDtlId=" + scoreDtlId + " ]";
    }
    
}
