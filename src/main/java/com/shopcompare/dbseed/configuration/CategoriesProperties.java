package com.shopcompare.dbseed.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties
@PropertySource(value = "categories.properties", encoding = "UTF-8")
@Getter
@Setter
public class CategoriesProperties {
    private List<CategoryProperty> categories;
}
