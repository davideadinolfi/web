package it.univaq.ex.webmarket.controller;

import it.univaq.ex.webmarket.data.DAO.impl.WebmarketDataLayer;
import it.univaq.framework.controller.AbstractBaseController;
import it.univaq.framework.data.DataLayer;

import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
 *
 * @author Giuseppe Della Penna
 */
public abstract class WebmarketBaseController extends AbstractBaseController {

    @Override
    protected DataLayer createDataLayer(DataSource ds) throws ServletException {
        try {
            return new WebmarketDataLayer(ds);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

}
