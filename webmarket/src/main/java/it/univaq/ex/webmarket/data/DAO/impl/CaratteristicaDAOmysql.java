package it.univaq.ex.webmarket.data.DAO.impl;

import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.OptimisticLockException;

import it.univaq.ex.webmarket.data.DAO.CaratteristicaDAO;
import it.univaq.ex.webmarket.data.model.Caratteristica;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.impl.proxy.CaratteristicaProxy;
import it.univaq.framework.data.DAO;
import it.univaq.framework.data.DataException;
import it.univaq.framework.data.DataItemProxy;
import it.univaq.framework.data.DataLayer;

public class CaratteristicaDAOmysql extends DAO implements CaratteristicaDAO{

    private PreparedStatement gCaratteristicheByCategoria;


    public CaratteristicaDAOmysql(DataLayer d) {
        super(d);
    }

    public void init() throws DataException{
        try{
            super.init();
            gCaratteristicheByCategoria= connection.prepareStatement("WITH RECURSIVE CategoriaAncestors AS ( SELECT id,nome,descrizione,id_categoriaPadre FROM categoria WHERE id = ?  UNION ALL SELECT c.id,c.nome,c.descrizione,c.id_categoriaPadre FROM categoria c JOIN CategoriaAncestors ca ON c.id = ca.id_categoriaPadre ) SELECT * FROM caratteristica WHERE id_categoria IN (SELECT id FROM CategoriaAncestors);");

        }
        catch(SQLException e){
            throw new DataException("errore di inizializzazione del datalayer caratteristica",e);
        }
    }

    @Override
    public void destroy() throws DataException {
        //anche chiudere i PreparedStamenent è una buona pratica...
        //also closing PreparedStamenents is a good practice...
        try {
            gCaratteristicheByCategoria.close();
        } catch (SQLException ex) {
            throw new DataException("Errore di chiusura dei preparedStatements", ex);
        }
        super.destroy();
    }

    @Override
    public Caratteristica createCaratteristica() {
        return new CaratteristicaProxy(getDataLayer());
    }

    public Caratteristica createCaratteristica(ResultSet rs) throws DataException{
        try{
            CaratteristicaProxy c = (CaratteristicaProxy)createCaratteristica();
            c.setKey(rs.getInt("id"));
            c.setNome(rs.getString("nome"));
            c.setDescrizione(rs.getString("descrizione"));
            return c;
        }
        catch(SQLException e){
            throw new DataException("impossibile creare caratteristica dal resultSet",e);
        }
    }



    @Override
    public List<Caratteristica> getCaratteristicheByCategoria(int key) throws DataException {
        ArrayList<Caratteristica> l=new ArrayList<Caratteristica>();
        Caratteristica c;
        
            //altrimenti lo carichiamo dal database
            //otherwise load it from database
            try {
                
                gCaratteristicheByCategoria.setInt(1 , key);
                try ( ResultSet rs = gCaratteristicheByCategoria.executeQuery()) {
                    
                    while(rs.next()) {
                        //notare come utilizziamo il costrutture
                        //"helper" della classe AuthorImpl
                        //per creare rapidamente un'istanza a
                        //partire dal record corrente
                        //note how we use here the helper constructor
                        //of the AuthorImpl class to quickly
                        //create an instance from the current record
                        
                        c = createCaratteristica(rs);
                        
                        //e lo mettiamo anche nella cache
                        //and put it also in the cache
                        if(!dataLayer.getCache().has(Caratteristica.class, c))
                            dataLayer.getCache().add(Caratteristica.class, c);
                        l.add(c);
                        
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("impossibile ritornare la lista delle caratteristiche", ex);
            
        
        

        
        }
        
    return l;
    }

 

 
}
