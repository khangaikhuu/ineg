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
@Table(name = "question_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionCategory.findAll", query = "SELECT q FROM QuestionCategory q"),
    @NamedQuery(name = "QuestionCategory.findByQuestionCategoryId", query = "SELECT q FROM QuestionCategory q WHERE q.questionCategoryId = :questionCategoryId")})
public class QuestionCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "question_category_id")
    private Integer questionCategoryId;
    @Lob
    @Size(max = 65535)
    @Column(name = "question_category")
    private String questionCategory;
    @OneToMany(mappedBy = "parentId")
    private List<QuestionCategory> questionCategoryList;
    @JoinColumn(name = "parent_id", referencedColumnName = "question_category_id")
    @ManyToOne
    private QuestionCategory parentId;
    @OneToMany(mappedBy = "questionCategoryId")
    private List<Question> questionList;

    public QuestionCategory() {
    }

    public QuestionCategory(Integer questionCategoryId) {
        this.questionCategoryId = questionCategoryId;
    }

    public Integer getQuestionCategoryId() {
        return questionCategoryId;
    }

    public void setQuestionCategoryId(Integer questionCategoryId) {
        this.questionCategoryId = questionCategoryId;
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    @XmlTransient
    public List<QuestionCategory> getQuestionCategoryList() {
        return questionCategoryList;
    }

    public void setQuestionCategoryList(List<QuestionCategory> questionCategoryList) {
        this.questionCategoryList = questionCategoryList;
    }

    public QuestionCategory getParentId() {
        return parentId;
    }

    public void setParentId(QuestionCategory parentId) {
        this.parentId = parentId;
    }

    @XmlTransient
    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionCategoryId != null ? questionCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionCategory)) {
            return false;
        }
        QuestionCategory other = (QuestionCategory) object;
        if ((this.questionCategoryId == null && other.questionCategoryId != null) || (this.questionCategoryId != null && !this.questionCategoryId.equals(other.questionCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.QuestionCategory[ questionCategoryId=" + questionCategoryId + " ]";
    }
    
}
