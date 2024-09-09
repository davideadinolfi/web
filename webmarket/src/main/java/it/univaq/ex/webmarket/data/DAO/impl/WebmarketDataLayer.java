package it.univaq.ex.webmarket.data.DAO.impl;


import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.ex.webmarket.data.DAO.UtenteDAO;
import it.univaq.framework.data.DataException;
import it.univaq.framework.data.DataLayer;

import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Giuseppe Della Penna
 */
public class WebmarketDataLayer extends DataLayer {

    public WebmarketDataLayer(DataSource datasource) throws SQLException {
        super(datasource);
    }

    @Override
    public void init() throws DataException {
        registerDAO(Utente.class, new UtenteDAOmysql(this));
        //registriamo i nostri dao
        //register our daos
       /* registerDAO(Author.class, new AuthorDAO_MySQL(this));
        registerDAO(Issue.class, new IssueDAO_MySQL(this));
        registerDAO(Image.class, new ImageDAO_MySQL(this));
        registerDAO(User.class, new UserDAO_MySQL(this));*/
    }

    /*//helpers
    public ArticleDAO getArticleDAO() {
        return (ArticleDAO) getDAO(Article.class);
    }

    public AuthorDAO getAuthorDAO() {
        return (AuthorDAO) getDAO(Author.class);
    }

    public IssueDAO getIssueDAO() {
        return (IssueDAO) getDAO(Issue.class);
    }

    public ImageDAO getImageDAO() {
        return (ImageDAO) getDAO(Image.class);
    }

    */
    public UtenteDAO getUtenteDAO() {
        return (UtenteDAO) getDAO(Utente.class);
    }


}