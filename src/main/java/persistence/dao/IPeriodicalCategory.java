package persistence.dao;

import persistence.entities.PeriodicalCategory;

import java.util.ArrayList;

/**
 * Created by Julia on 09.08.2018
 */
public interface IPeriodicalCategory {

    /**
     * Category's selection by id
     *
     * @param id - category id
     * @return - category or null
     */
    PeriodicalCategory findCategoryById(int id);

    /**
     * Selection all categories in the database
     *
     * @return - list of categories
     */
    ArrayList<PeriodicalCategory> getAllCategories();

    /**
     * Insert new category
     *
     * @param category - category to be inserted to the database
     * @return - {@code true} if new category id added, {@code false} if no records is inserted
     */
    boolean insertCategory(PeriodicalCategory category);

    /**
     * Update category info
     *
     * @param category - category info to be updated in the database
     * @return - {@code true} if category info is updated, {@code false} if no records is updated
     */
    boolean updateCategory(PeriodicalCategory category);

    /**
     * Delete category
     *
     * @param category - category to be deleted from the database
     * @return - {@code true} if category is deleted, {@code false} if no records is deleted
     */
    boolean deleteCategory(PeriodicalCategory category);
}
