package com.shopcompare.dbseed.configuration;

/**
 * Model representing category property from configuration properties.
 *
 * @param name name of the category
 * @param superCategoryId id of its super category
 */
public record CategoryProperty(String name, Integer superCategoryId) {

    @Override
    public int hashCode() {
        return name.hashCode() + (superCategoryId != null ? superCategoryId.hashCode() : 0);
    }
}
