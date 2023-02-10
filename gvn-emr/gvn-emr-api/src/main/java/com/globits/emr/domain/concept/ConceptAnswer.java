package com.globits.emr.domain.concept;

import com.globits.core.domain.BaseObject;

import javax.persistence.*;

@Entity
@Table(name = "tbl_concept_answer")
public class ConceptAnswer extends BaseObject {
    private static final long serialVersionUID = -902288385568906216L;

    @ManyToOne
    @JoinColumn(name = "concept_id")
    private Concept concept;

    @ManyToOne
    @JoinColumn(name = "answer_concept")
    private Concept answerConcept;

    @Column(name = "view_index")
    private Integer viewIndex;// thứ tự hiển thị (thứ tự cao là kết quả cao)

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public Concept getAnswerConcept() {
        return answerConcept;
    }

    public void setAnswerConcept(Concept answerConcept) {
        this.answerConcept = answerConcept;
    }

    public Integer getViewIndex() {
        return viewIndex;
    }

    public void setViewIndex(Integer viewIndex) {
        this.viewIndex = viewIndex;
    }
}
