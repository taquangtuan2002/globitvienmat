package com.globits.emr.domain;

import com.globits.core.domain.BaseObject;
import com.globits.core.domain.Person;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_person_attribute")
public class PersonAttribute extends BaseObject {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "person_attribute_type_Id")
    private PersonAttributeType personAttributeType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id")
    private Person person;

    private String value;

    public PersonAttributeType getPersonAttributeType() {
        return personAttributeType;
    }

    public void setPersonAttributeType(PersonAttributeType personAttributeType) {
        this.personAttributeType = personAttributeType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
