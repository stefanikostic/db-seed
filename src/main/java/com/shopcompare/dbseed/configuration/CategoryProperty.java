package com.shopcompare.dbseed.configuration;

public record CategoryProperty (String name, Integer superCategoryId) {

    @Override
    public int hashCode() {
        return name.hashCode() + (superCategoryId != null ? superCategoryId.hashCode() : 0);
    }
}
