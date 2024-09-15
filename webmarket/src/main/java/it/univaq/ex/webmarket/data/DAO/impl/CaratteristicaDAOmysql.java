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
    private PreparedStatement gCaratteristica;
    private PreparedStatement iCaratteristicheRichiesta;

    public CaratteristicaDAOmysql(DataLayer d) {
        super(d);
        //TODO Auto-generated constructor stub
    }

    public void init() throws DataException{
        try{
            super.init();
            gCaratteristicheByCategoria= connection.prepareStatement("SELECT * FROM `caratteristica` WHERE id_categoria=?");
            gCaratteristica=connection.prepareStatement("SELECT * FROM 'caratteristica' WHERE ID=?");
            iCaratteristicheRichiesta=connection.prepareStatement("INSERT INTO richiesta_caratteristica(id_richiesta_acquisto,id_caratteristica,specifica) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
        }
        catch(SQLException e){
            throw new DataException("errore caratteristica DAO");
        }
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
            throw new DataException("capperetti");
        }
    }

    @Override
    public Caratteristica getCaratteristica(int caratteristicaKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCaratteristica'");
    }

    @Override
    public List<Caratteristica> getCaratteristiche(int key) throws DataException {
        throw new UnsupportedOperationException("capperetti");
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
                throw new DataException("Unable to load user by ID", ex);
            
        
        

        
        }
        
    return l;
    }

    @Override
    public void storeCaratteristicaRichiesta(Caratteristica caratteristica,int richiestaKey,String descrizione) throws DataException, SQLException {
        try {
            iCaratteristicheRichiesta.setInt(1, richiestaKey);
            iCaratteristicheRichiesta.setInt(2, caratteristica.getKey());
            iCaratteristicheRichiesta.setString(3, descrizione);

        } catch (SQLException e) {
            //TODO
        }

        if (iCaratteristicheRichiesta.executeUpdate() == 1) {
        
                    
                    //per leggere la chiave generata dal database
                    //per il record appena inserito, usiamo il metodo
                    //getGeneratedKeys sullo statement.
                    //to read the generated record key from the database
                    //we use the getGeneratedKeys method on the same statement
                    try ( ResultSet keys = iCaratteristicheRichiesta.getGeneratedKeys()) {
                        //il valore restituito è un ResultSet con un record
                        //per ciascuna chiave generata (uno solo nel nostro caso)
                        //the returned value is a ResultSet with a distinct record for
                        //each generated key (only one in our case)
                        }
        }
               
    }
    
    

    @Override
    public void deleteCaratteristica(int caratteristicaKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCaratteristica'");
    }
    
}
