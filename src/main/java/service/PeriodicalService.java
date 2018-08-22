package service;

import persistence.dao.IPeriodical;
import persistence.dao.daoFactory.DAOFactory;
import persistence.entities.Periodical;

import java.util.ArrayList;

/**
 * Created by Julia on 15.08.2018
 */
public class PeriodicalService {
    private IPeriodical iPeriodical = DAOFactory.getMySqlDAOFactory().getPeriodicalDAO();

    public ArrayList<Periodical> getPeriodicals() {
        return iPeriodical.findAllPeriodicals();
    }

    public Periodical getPeriodical(int id) {
        return iPeriodical.findPeriodicalById(id);
    }
}
