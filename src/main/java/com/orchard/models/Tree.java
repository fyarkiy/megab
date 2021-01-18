package com.orchard.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trees")
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tree_id")
    private Long treeId;
    @ManyToOne
    private Specie specie;
    @Column(name = "varieties")
    private String variety;
    @Column(name = "age")
    private Long age;
    private Boolean deleted;

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return treeId.equals(tree.treeId)
                && specie.equals(tree.specie)
                && Objects.equals(variety, tree.variety)
                && age.equals(tree.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(treeId, specie, variety, age);
    }

    @Override
    public String toString() {
        return "Tree{ " + specie
                + ", variety='" + variety + "', age=" + age +" }";
    }
}
