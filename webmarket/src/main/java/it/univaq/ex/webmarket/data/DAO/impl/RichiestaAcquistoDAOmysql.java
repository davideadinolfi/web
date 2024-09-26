package it.univaq.ex.webmarket.data.DAO.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.validation.constraints.Null;

import it.univaq.ex.webmarket.data.DAO.RichiestaAcquistoDAO;
import it.univaq.ex.webmarket.data.DAO.UtenteDAO;
import it.univaq.ex.webmarket.data.model.Categoria;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.StatoRichiesta;
import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.ex.webmarket.data.model.impl.proxy.CategoriaProxy;
import it.univaq.ex.webmarket.data.model.impl.proxy.RichiestaAcquistoProxy;
import it.univaq.ex.webmarket.data.model.impl.proxy.UtenteProxy;
import it.univaq.framework.data.DAO;
import it.univaq.framework.data.DataException;
import it.univaq.framework.data.DataItemProxy;
import it.univaq.framework.data.DataLayer;
import it.univaq.framework.security.SecurityHelpers;

public class RichiestaAcquistoDAOmysql extends DAO implements RichiestaAcquistoDAO{
    PreparedStatement iRichiesta;
    PreparedStatement sRichieste;
    PreparedStatement sRichiestaByUser;
    PreparedStatement sRichiestaByid;
    PreparedStatement sRichiesteByTecnico;
    PreparedStatement sRichiesteSenzaTecnico;
    PreparedStatement uRichiesta;
    public RichiestaAcquistoDAOmysql(DataLayer d) {
        super(d);
        //TODO Auto-generated constructor stub
    }

    public void init()throws DataException{
        try {
            super.init();
            iRichiesta = connection.prepareStatement("INSERT INTO richiesta_acquisto (id_ordinante,id_tecnico,id_categoria,data_richiesta,note,stato_richiesta) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            sRichieste = connection.prepareStatement("SELECT id AS IDrichiesta FROM richiesta_acquisto");
            sRichiestaByUser=connection.prepareStatement("SELECT * FROM richiesta_acquisto WHERE id_ordinante=?");
            sRichiestaByid=connection.prepareStatement("SELECT * FROM richiesta_acquisto WHERE id=?");
            sRichiesteByTecnico=connection.prepareStatement("SELECT * FROM richiesta_acquisto where id_tecnico=?");
            sRichiesteSenzaTecnico=connection.prepareStatement("SELECT * FROM richiesta_acquisto where id_tecnico IS NULL");
            uRichiesta=connection.prepareStatement("UPDATE richiesta_acquisto SET id_ordinante = ?, id_tecnico = ?, id_categoria = ?, data_richiesta = ?, note = ?, stato_richiesta = ? WHERE id = ?");
        } catch (SQLException e) {
            throw new DataException("errore di inizializzazione di richiestaAcquisto",e);
        }
    }

    private RichiestaAcquistoProxy createRichiestaAcquisto(ResultSet rs)throws DataException{
        try {
            
            RichiestaAcquistoProxy a = (RichiestaAcquistoProxy) createRichiestaAcquisto();
            a.setKey(rs.getInt("id"));
            Categoria c= ((Categoria) ((WebmarketDataLayer) dataLayer).getCategoriaDAO().getCategoria(rs.getInt("id_categoria")));
            a.setCategoria(c);
            a.setDataRichiesta(rs.getString("data_richiesta"));
            Utente o=((Utente) ((WebmarketDataLayer) dataLayer).getUtenteDAO().getUtente(rs.getInt("id_ordinante")));
            a.setOrdinante(o);
            a.setStatoRichiesta(rs.getString("stato_richiesta"));
            a.setNote(rs.getString("note"));
            Utente t = ((Utente)((WebmarketDataLayer) dataLayer).getUtenteDAO().getUtente(rs.getInt("id_tecnico")));
            a.setTecnico(t);
            return a;
        } catch (SQLException ex) {
            throw new DataException("Unable to create richiestaAcquisto object form ResultSet", ex);
        }
    }

    @Override
    public RichiestaAcquisto createRichiestaAcquisto() {
        return new RichiestaAcquistoProxy(getDataLayer());
    }



    @Override
    public RichiestaAcquisto getRichiestaAcquisto(int richiestaAcquistoKey) throws DataException {
        RichiestaAcquisto r=null;
        if(dataLayer.getCache().has(RichiestaAcquisto.class, richiestaAcquistoKey)){
            r=dataLayer.getCache().get(RichiestaAcquisto.class, richiestaAcquistoKey);
        }
        else{
        try{
            sRichiestaByid.setInt(1, richiestaAcquistoKey);
            try(ResultSet rs=sRichiestaByid.executeQuery()){
                if(rs.next()){
                    r=createRichiestaAcquisto(rs);
                    dataLayer.getCache().add(RichiestaAcquisto.class, r);
                }

            }
        }
        catch(Exception e){
            //TODO
        }
    }
    return r;
    }

    @Override
    public List<RichiestaAcquisto> getRichiesteAcquisto() throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRichiesteAcquisto'");
    }

    @Override
    public List<RichiestaAcquisto> getRichiesteAcquistoByOrdinante(int ordinanteKey) throws DataException {
        ArrayList<RichiestaAcquisto> list = new ArrayList<RichiestaAcquisto>();
        try {
            sRichiestaByUser.setInt(1, ordinanteKey);
            ResultSet rs=sRichiestaByUser.executeQuery();
            while(rs.next()){
                list.add(createRichiestaAcquisto(rs));

            }
        }
        catch(SQLException e){
            //TODO
        }
        return list;
    }

    @Override
    public List<RichiestaAcquisto> getRichiesteAcquistoByTecnico(int tecnicoKey) throws DataException {
        ArrayList<RichiestaAcquisto> list = new ArrayList<RichiestaAcquisto>();
        ResultSet rs;
        try {
            sRichiesteByTecnico.setInt(1, tecnicoKey);
            if(tecnicoKey==0){
                rs=sRichiesteSenzaTecnico.executeQuery();
            }
            else{
                rs=sRichiesteByTecnico.executeQuery();
            }
            while(rs.next()){
                list.add(createRichiestaAcquisto(rs));

            }
        }
        catch(SQLException e){
            //TODO
        }
        return list;
    }

    @Override
    public void storeRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto) throws DataException, IOException {
        try {
            
            
            if (richiestaAcquisto.getKey() != null && richiestaAcquisto.getKey() > 0) { //update
                //non facciamo nulla se l'oggetto è un proxy e indica di non aver subito modifiche
                //do not store the object if it is a proxy and does not indicate any modification
                if (richiestaAcquisto instanceof DataItemProxy && !((DataItemProxy) richiestaAcquisto).isModified()) {
                    return;
                }
                uRichiesta.setInt(1, richiestaAcquisto.getOrdinante().getKey());
                if(richiestaAcquisto.getTecnico()!= null)
                    uRichiesta.setInt(2, richiestaAcquisto.getTecnico().getKey());
                uRichiesta.setInt(3, richiestaAcquisto.getCategoria().getKey());
                uRichiesta.setString(4, richiestaAcquisto.getDataRichiesta().toString());
                uRichiesta.setString(5, richiestaAcquisto.getNote());
                uRichiesta.setString(6, richiestaAcquisto.getStatoRichiesta().getValue());
                uRichiesta.setInt(7, richiestaAcquisto.getKey());
                if(uRichiesta.executeUpdate() == 1){
                    dataLayer.getCache().add(RichiestaAcquisto.class,richiestaAcquisto);
                    if (richiestaAcquisto instanceof DataItemProxy) {
                        ((DataItemProxy) richiestaAcquisto).setModified(false);
                    }
                }
                /* 
                uUser.setString(1, user.getEmail());
                uUser.setString(2, user.getPassword());

                long current_version = user.getVersion();
                long next_version = current_version + 1;

                uUser.setLong(3, next_version);
                uUser.setInt(4, user.getKey());
                uUser.setLong(5, current_version);

                if (uUser.executeUpdate() == 0) {
                    throw new OptimisticLockException(user);
                } else {
                    user.setVersion(next_version);
                }  */
            } else { //insert
                
                
             
                iRichiesta.setInt(1, richiestaAcquisto.getOrdinante().getKey());
        
                iRichiesta.setString(2,null);
                
                iRichiesta.setInt(3, richiestaAcquisto.getCategoria().getKey());
                
                iRichiesta.setString(4, richiestaAcquisto.getDataRichiesta().toString());
            
                iRichiesta.setString(5, richiestaAcquisto.getNote());
                
                iRichiesta.setString(6,richiestaAcquisto.getStatoRichiesta().getValue());
                if (iRichiesta.executeUpdate() == 1) {
        
                    
                    //per leggere la chiave generata dal database
                    //per il record appena inserito, usiamo il metodo
                    //getGeneratedKeys sullo statement.
                    //to read the generated record key from the database
                    //we use the getGeneratedKeys method on the same statement
                    try ( ResultSet keys = iRichiesta.getGeneratedKeys()) {
                        //il valore restituito è un ResultSet con un record
                        //per ciascuna chiave generata (uno solo nel nostro caso)
                        //the returned value is a ResultSet with a distinct record for
                        //each generated key (only one in our case)
                        if (keys.next()) {
                            //i campi del record sono le componenti della chiave
                            //(nel nostro caso, un solo intero)
                            //the record fields are the key componenets
                            //(a single integer in our case)
                            int key = keys.getInt(1);
                            //aggiornaimo la chiave in caso di inserimento
                            //after an insert, uopdate the object key
                            richiestaAcquisto.setKey(key);
                            //inseriamo il nuovo oggetto nella cache
                            //add the new object to the cache
                            dataLayer.getCache().add(RichiestaAcquisto.class, richiestaAcquisto);
                        }
                    }
                }
            }

            if (richiestaAcquisto instanceof DataItemProxy) {
                ((DataItemProxy) richiestaAcquisto).setModified(false);
            }
        } catch (SQLException | OptimisticLockException ex) {
            throw new DataException("Unable to store user", ex);
        }
    }
    

    @Override
    public void updateStatoRichiesta(int richiestaAcquistoKey, StatoRichiesta statoRichiesta) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStatoRichiesta'");
    }

    @Override
    public void deleteRichiestaAcquisto(int richiestaAcquistoKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRichiestaAcquisto'");
    }
    
}
