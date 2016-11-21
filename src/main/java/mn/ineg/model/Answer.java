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
@Table(name = "answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
    @NamedQuery(name = "Answer.findByAnswerId", query = "SELECT a FROM Answer a WHERE a.answerId = :answerId"),
    @NamedQuery(name = "Answer.findByAnswerCode", query = "SELECT a FROM Answer a WHERE a.answerCode = :answerCode"),
    @NamedQuery(name = "Answer.findByOrderNumber", query = "SELECT a FROM Answer a WHERE a.orderNumber = :orderNumber"),
    @NamedQuery(name = "Answer.findByIsCorrect", query = "SELECT a FROM Answer a WHERE a.isCorrect = :isCorrect")})
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "answer_id")
    private Integer answerId;
    @Size(max = 50)
    @Column(name = "answer_code")
    private String answerCode;
    @Lob
    @Size(max = 65535)
    @Column(name = "answer")
    private String answer;
    @Column(name = "order_number")
    private Integer orderNumber;
    @Column(name = "is_correct")
    private Boolean isCorrect;
    @OneToMany(mappedBy = "answerId")
    private List<ScoreDtl> scoreDtlList;
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    @ManyToOne
    private Question questionId;

    public Answer() {
    }

    public Answer(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerCode() {
        return answerCode;
    }

    public void setAnswerCode(String answerCode) {
        this.answerCode = answerCode;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    @XmlTransient
    public List<ScoreDtl> getScoreDtlList() {
        return scoreDtlList;
    }

    public void setScoreDtlList(List<ScoreDtl> scoreDtlList) {
        this.scoreDtlList = scoreDtlList;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answerId != null ? answerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.answerId == null && other.answerId != null) || (this.answerId != null && !this.answerId.equals(other.answerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.Answer[ answerId=" + answerId + " ]";
    }
    
}
