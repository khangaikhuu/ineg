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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author developer
 */
@Entity
@Table(name = "question_config")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionConfig.findAll", query = "SELECT q FROM QuestionConfig q"),
    @NamedQuery(name = "QuestionConfig.findByQuestionConfigId", query = "SELECT q FROM QuestionConfig q WHERE q.questionConfigId = :questionConfigId"),
    @NamedQuery(name = "QuestionConfig.findByConfigNumber", query = "SELECT q FROM QuestionConfig q WHERE q.configNumber = :configNumber"),
    @NamedQuery(name = "QuestionConfig.findByConfigName", query = "SELECT q FROM QuestionConfig q WHERE q.configName = :configName")})
public class QuestionConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "question_config_id")
    private Integer questionConfigId;
    @Lob
    @Column(name = "config_code")
    private byte[] configCode;
    @Column(name = "config_number")
    private Integer configNumber;
    @Size(max = 50)
    @Column(name = "config_name")
    private String configName;

    public QuestionConfig() {
    }

    public QuestionConfig(Integer questionConfigId) {
        this.questionConfigId = questionConfigId;
    }

    public Integer getQuestionConfigId() {
        return questionConfigId;
    }

    public void setQuestionConfigId(Integer questionConfigId) {
        this.questionConfigId = questionConfigId;
    }

    public byte[] getConfigCode() {
        return configCode;
    }

    public void setConfigCode(byte[] configCode) {
        this.configCode = configCode;
    }

    public Integer getConfigNumber() {
        return configNumber;
    }

    public void setConfigNumber(Integer configNumber) {
        this.configNumber = configNumber;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionConfigId != null ? questionConfigId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionConfig)) {
            return false;
        }
        QuestionConfig other = (QuestionConfig) object;
        if ((this.questionConfigId == null && other.questionConfigId != null) || (this.questionConfigId != null && !this.questionConfigId.equals(other.questionConfigId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.QuestionConfig[ questionConfigId=" + questionConfigId + " ]";
    }
    
}
