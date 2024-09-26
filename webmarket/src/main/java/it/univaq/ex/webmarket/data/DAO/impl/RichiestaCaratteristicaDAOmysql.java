package it.univaq.ex.webmarket.data.DAO.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import it.univaq.ex.webmarket.data.DAO.RichiestaCaratteristicaDAO;
import it.univaq.ex.webmarket.data.model.Caratteristica;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.RichiestaCaratteristica;
import it.univaq.ex.webmarket.data.model.impl.proxy.RichiestaCaratteristicaProxy;
import it.univaq.framework.data.DAO;
import it.univaq.framework.data.DataException;
import it.univaq.framework.data.DataLayer;

public class RichiestaCaratteristicaDAOmysql extends DAO implements RichiestaCaratteristicaDAO{

    PreparedStatement gRichiestaCaratteristiche;
    PreparedStatement iCaratteristicheRichiesta;
    public void init(){
       try{
            super.init();
            gRichiestaCaratteristiche=connection.prepareStatement("SELECT * FROM richiesta_caratteristica WHERE id_richiesta_acquisto=?");
            iCaratteristicheRichiesta=connection.prepareStatement("INSERT INTO richiesta_caratteristica(id_richiesta_acquisto,id_caratteristica,specifica) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
       }
       catch(Exception e){

       }

    }

    public RichiestaCaratteristicaDAOmysql(DataLayer d) {
        super(d);
        
        //TODO Auto-generated constructor stub
    }

    public RichiestaCaratteristica createRichiestaCaratteristica(){
        return new RichiestaCaratteristicaProxy(dataLayer);
    }

    public RichiestaCaratteristica createRichiestaCaratteristica(ResultSet rs){
        RichiestaCaratteristica rc=(RichiestaCaratteristicaProxy) createRichiestaCaratteristica();
        try {
            rc.setKey(rs.getInt(1));
            rc.setRichiesta(null);
            rc.setCaratteristica(null);
            rc.setSpecifica(rs.getString(4));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rc;
    }
    
    public List<RichiestaCaratteristica> getRichiestaCaratteristiche(RichiestaAcquisto r){
        ArrayList<RichiestaCaratteristica> l= new ArrayList<RichiestaCaratteristica>();
        try {
            gRichiestaCaratteristiche.setInt(1, r.getKey());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RichiestaCaratteristica rc;
        try(ResultSet rs=gRichiestaCaratteristiche.executeQuery()){
            while(rs.next()){
                rc=createRichiestaCaratteristica(rs);

                if(!dataLayer.getCache().has(RichiestaCaratteristica.class, rc))
                            dataLayer.getCache().add(RichiestaCaratteristica.class, rc);
                        l.add(rc);
            }
        }
        catch(Exception e){
            //TODO
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
    
    
    
}
