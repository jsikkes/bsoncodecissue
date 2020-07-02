package com.sikkes.bsoncodecissue.models.product;

import java.util.Objects;

public class ProductId {
    private final String value;

    public ProductId(String value) {
        this.value = value;
    }

    public ProductId(Integer value) {
        this.value = String.valueOf(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return value.equals(productId.value);
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
