package com.sikkes.bsoncodecissue.models;

import org.bson.codecs.pojo.annotations.BsonId;

import java.util.Objects;

public class Formula {
    @BsonId
    private FormulaId formulaId;

    public Formula() {
    }

    public Formula(FormulaId formulaId) {
        this.formulaId = formulaId;
    }

    public Formula(Formula formula) {
        this.formulaId = new FormulaId(formula.formulaId);
    }

    public FormulaId getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(FormulaId formulaId) {
        this.formulaId = formulaId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Formula)) return false;
        Formula formula = (Formula) o;
        return formulaId.equals(formula.formulaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formulaId);
    }

    @Override
    public String toString() {
        return "Formula{" +
                "formulaId=" + formulaId +
                '}';
    }
}
