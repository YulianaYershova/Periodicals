package persistence.dao;

import persistence.entities.Periodical;

import java.util.ArrayList;

/**
 * Created by Julia on 09.08.2018
 */
public interface IPeriodical {

    /**
     * periodical's selection by id
     *
     * @param id - periodical id
     * @return - periodical or null
     */
    Periodical findPeriodicalById(int id);

    /**
     * Selection all periodicals in the database
     *
     * @return - list of periodicals
     */
    ArrayList<Periodical> findAllPeriodicals();

    /**
     * Insert new periodical
     *
     * @param periodical - periodical to be inserted to the database
     * @return - {@code true} if new periodical id added, {@code false} if no records is inserted
     */
    boolean insertPeriodical(Periodical periodical);

    /**
     * Update periodical info
     *
     * @param periodical - periodical info to be updated in the database
     * @return - {@code true} if periodical info is updated, {@code false} if no records is updated
     */
    boolean updatePeriodical(Periodical periodical);

    /**
     * Delete periodical
     *
     * @param periodical - periodical to be deleted from the database
     * @return - {@code true} if periodical is deleted, {@code false} if no records is deleted
     */
    boolean deletePeriodical(Periodical periodical);

}
