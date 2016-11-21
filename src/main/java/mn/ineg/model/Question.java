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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findByQuestionId", query = "SELECT q FROM Question q WHERE q.questionId = :questionId"),
    @NamedQuery(name = "Question.findByQuestionScore", query = "SELECT q FROM Question q WHERE q.questionScore = :questionScore")})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "question_id")
    private Integer questionId;
    @Lob
    @Size(max = 65535)
    @Column(name = "question_text")
    private String questionText;
    @Column(name = "question_score")
    private Integer questionScore;
    @OneToMany(mappedBy = "questionId")
    private List<ScoreDtl> scoreDtlList;
    @JoinColumn(name = "question_category_id", referencedColumnName = "question_category_id")
    @ManyToOne
    private QuestionCategory questionCategoryId;
    @OneToMany(mappedBy = "questionId")
    private List<Answer> answerList;

    public Question() {
    }

    public Question(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Integer getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Integer questionScore) {
        this.questionScore = questionScore;
    }

    @XmlTransient
    public List<ScoreDtl> getScoreDtlList() {
        return scoreDtlList;
    }

    public void setScoreDtlList(List<ScoreDtl> scoreDtlList) {
        this.scoreDtlList = scoreDtlList;
    }

    public QuestionCategory getQuestionCategoryId() {
        return questionCategoryId;
    }

    public void setQuestionCategoryId(QuestionCategory questionCategoryId) {
        this.questionCategoryId = questionCategoryId;
    }

    @XmlTransient
    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null ? questionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.questionId == null && other.questionId != null) || (this.questionId != null && !this.questionId.equals(other.questionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.Question[ questionId=" + questionId + " ]";
    }
    
}
