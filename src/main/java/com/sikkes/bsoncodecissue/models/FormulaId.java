package com.sikkes.bsoncodecissue.models;

import java.util.Objects;

public class FormulaId {
    private final String value;

    public FormulaId(String value) {
        this.value = value;
    }

    public FormulaId(Integer value) {
        this.value = String.valueOf(value);
    }

    public FormulaId(FormulaId formulaId) {
        this.value = formulaId.value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormulaId formulaId = (FormulaId) o;
        return value.equals(formulaId.value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
