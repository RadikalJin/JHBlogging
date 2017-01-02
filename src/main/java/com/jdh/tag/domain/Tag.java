package com.jdh.tag.domain;

import javax.persistence.*;

@Entity
@Table(name = "TAG")
public class Tag implements java.io.Serializable {

    @Id
    @Column(name = "TAG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TAG_NAME", nullable = true)
    private String tagName;


    public Tag() {
        super();
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return id.equals(tag.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
