package com.sikkes.bsoncodecissue.models.product;

import com.sikkes.bsoncodecissue.models.Formula;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ProductCategory {

    private String code;

    private Integer rank;

    private Integer number;

    private String name;

    private String image;

    private String icon;

    private ProductCategory parent;

    private Set<ProductCategory> children = new HashSet<>();

    private Set<Formula> formulas = new HashSet<>();

    public ProductCategory() {
    }

    public ProductCategory(String categoryCode) {
        this.code = categoryCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public ProductCategory withRank(Integer rank) {
        this.rank = rank;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public ProductCategory withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory withName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductCategory withImage(String image) {
        this.image = image;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Set<ProductCategory> getChildren() {
        return children;
    }

    public void setChildren(Set<ProductCategory> children) {
        this.children = children;
    }

    public void addChild(ProductCategory category) {
        this.children.add(category);
    }

    public ProductCategory withChild(ProductCategory category) {
        this.children.add(category);
        return this;
    }

    public ProductCategory getParent() {
        return parent;
    }

    public void setParent(ProductCategory parent) {
        this.parent = parent;
    }

    public ProductCategory withParent(ProductCategory parent) {
        this.parent = parent;
        return this;
    }

    public Set<Formula> getFormulas() {
        return formulas;
    }

    public void setFormulas(Set<Formula> formulas) {
        this.formulas = formulas;
    }

    public void addFormula(Formula formula) {
        this.formulas.add(formula);
    }

    public ProductCategory withFormula(Formula formula) {
        this.formulas.add(formula);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory that = (ProductCategory) o;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "code='" + code + '\'' +
                ", rank=" + rank +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", icon='" + icon + '\'' +
                ", parent=" + parent +
                ", formulas=" + formulas +
                '}';
    }
}
