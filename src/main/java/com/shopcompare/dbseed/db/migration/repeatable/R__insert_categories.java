package com.shopcompare.dbseed.db.migration.repeatable;

import com.shopcompare.dbseed.configuration.CategoriesProperties;
import com.shopcompare.dbseed.configuration.CategoryProperty;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * Java repeatable migration for inserting categories.
 */
@Component
@RequiredArgsConstructor
public class R__insert_categories extends BaseJavaMigration {

    private static final String ID_COLUMN_LABEL = "id";
    private static final String SELECT_CATEGORY_QUERY = "SELECT * FROM category WHERE id = ?";
    private static final String INSERT_CATEGORY_QUERY = "INSERT INTO category (name, " +
            "super_category_id) VALUES (?, ?)";
    private static final String UPDATE_CATEGORY_QUERY = "UPDATE category SET name = ?, " +
            "super_category_id = ? WHERE id = ?";
    private static final int NAME_PARAMETER_INDEX = 1;
    private static final int SUPER_CATEGORY_PARAMETER_INDEX = 2;
    private static final int SELECT_QUERY_ID_PARAMETER_INDEX = 1;
    private static final int UPDATE_QUERY_ID_PARAMETER_INDEX = 3;

    private final CategoriesProperties categoriesProperties;

    /**
     * Migrates categories by going through all category properties from configuration and performing insert or update
     * of the category if present.
     *
     * @param context The context relevant for this migration, containing things like the JDBC connection to use and the
     *                current Flyway configuration.
     */
    @Override
    public void migrate(Context context) throws SQLException {
        Connection connection = context.getConnection();
        PreparedStatement selectStatement = connection.prepareStatement(SELECT_CATEGORY_QUERY);
        PreparedStatement insertStatement = connection.prepareStatement(INSERT_CATEGORY_QUERY);
        PreparedStatement updateStatement = connection.prepareStatement(UPDATE_CATEGORY_QUERY);

        List<CategoryProperty> categories = categoriesProperties.getCategories();
        for (CategoryProperty categoryProperty : categories) {
            int categoryPropertyIdByConfiguration = categories.indexOf(categoryProperty) + 1;
            selectStatement.setInt(SELECT_QUERY_ID_PARAMETER_INDEX, categoryPropertyIdByConfiguration);

            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                int categoryIdInDB = resultSet.getInt(ID_COLUMN_LABEL);
                updateStatement.setString(NAME_PARAMETER_INDEX, categoryProperty.name());
                populateCategoryInStatement(categoryProperty, updateStatement);
                updateStatement.setInt(UPDATE_QUERY_ID_PARAMETER_INDEX, categoryIdInDB);
                updateStatement.addBatch();
            } else {
                insertStatement.setString(NAME_PARAMETER_INDEX, categoryProperty.name());
                populateCategoryInStatement(categoryProperty, insertStatement);
                insertStatement.addBatch();
            }
        }

        updateStatement.executeBatch();
        updateStatement.close();
        insertStatement.executeBatch();
        insertStatement.close();
        selectStatement.close();
    }

    private void populateCategoryInStatement(CategoryProperty categoryProperty, PreparedStatement statement)
            throws SQLException {
        if (categoryProperty.superCategoryId() != null) {
            statement.setInt(SUPER_CATEGORY_PARAMETER_INDEX, categoryProperty.superCategoryId());
        } else {
            statement.setNull(SUPER_CATEGORY_PARAMETER_INDEX, Types.INTEGER);
        }
    }

    /**
     * @return sum of the hash codes of the category properties from configuration file.
     */
    @Override
    public Integer getChecksum() {
        return categoriesProperties.getCategories().stream().mapToInt(Record::hashCode).sum();
    }
}
