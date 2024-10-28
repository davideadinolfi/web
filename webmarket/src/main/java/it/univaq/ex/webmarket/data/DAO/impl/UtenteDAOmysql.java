package it.univaq.ex.webmarket.data.DAO.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.OptimisticLockException;
import it.univaq.ex.webmarket.data.DAO.UtenteDAO;
import it.univaq.ex.webmarket.data.model.Ruolo;
import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.ex.webmarket.data.model.impl.proxy.UtenteProxy;
import it.univaq.framework.data.DAO;
import it.univaq.framework.data.DataException;
import it.univaq.framework.data.DataItemProxy;
import it.univaq.framework.data.DataLayer;

public class UtenteDAOmysql extends DAO implements UtenteDAO{
    
    private PreparedStatement sUserByID, sUserByName, iUser, uUser;
    
    public UtenteDAOmysql(DataLayer d) {
        super(d);
        //TODO Auto-generated constructor stub
    }

        @Override
    public void init() throws DataException {
        try {
            super.init();

            //precompiliamo tutte le query utilizzate nella classe
            //precompile all the queries uses in this class
            sUserByID = connection.prepareStatement("SELECT * FROM utenti WHERE ID=?");
            sUserByName = connection.prepareStatement("SELECT ID FROM utenti WHERE email=?");
            iUser = connection.prepareStatement("INSERT INTO utenti (email,password,nome,cognome,ruolo) VALUES(?,?,?,?,?)" ,Statement.RETURN_GENERATED_KEYS  );
            uUser = connection.prepareStatement("UPDATE utenti SET email=?,password=?,version=? WHERE ID=? and version=?");
        } catch (SQLException ex) {
            throw new DataException("errore di inizializzazione del datalayer utente", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        //anche chiudere i PreparedStamenent è una buona pratica...
        //also closing PreparedStamenents is a good practice...
        try {
            sUserByID.close();
            sUserByName.close();
            iUser.close();
            uUser.close();

        } catch (SQLException ex) {
            throw new DataException("Errore di chiusura dei preparedStatements", ex);
        }
        super.destroy();
    }

    //metodi "factory" che permettono di creare
//e inizializzare opportune implementazioni
//delle interfacce del modello dati, nascondendo
//all'utente tutti i particolari
//factory methods to create and initialize
//suitable implementations of the data model interfaces,
//hiding all the implementation details
    @Override
    public Utente createUtente() {
        return new UtenteProxy(getDataLayer());
    }


    private UtenteProxy createUtente(ResultSet rs) throws DataException {
        try {
            
            UtenteProxy a = (UtenteProxy) createUtente();
            
            a.setKey(rs.getInt("id"));
            a.setNome(rs.getString("nome"));
            a.setPassword(rs.getString("password"));
            a.setCognome(rs.getString("cognome"));
            a.setRuolo(rs.getString("ruolo"));
            
            return a;
        } catch (SQLException ex) {
            throw new DataException("Unable to create user object form ResultSet", ex);
        }
    }



    @Override
    public Utente getUtente(int user_key) throws DataException {
        Utente u = null;
        //prima vediamo se l'oggetto è già stato caricato
        //first look for this object in the cache
        if (dataLayer.getCache().has(Utente.class, user_key)) {
            
            u = dataLayer.getCache().get(Utente.class, user_key);
        } else {
            //altrimenti lo carichiamo dal database
            //otherwise load it from database
            try {
                
                sUserByID.setInt(1, user_key);
                try ( ResultSet rs = sUserByID.executeQuery()) {
                    
                    if (rs.next()) {
                        u = createUtente(rs);
                        
                        //e lo mettiamo anche nella cache
                        //and put it also in the cache
                        dataLayer.getCache().add(Utente.class, u);
                        
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load user by ID", ex);
            }
        }
        return u;
    }



    @Override
    public Utente getUtenteByEmail(String username) throws DataException {

        try {
            sUserByName.setString(1, username);
            
            try ( ResultSet rs = sUserByName.executeQuery()) {
                
                    
                if (rs.next()) {
                    
                    return getUtente(rs.getInt("ID"));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to find user", ex);
        }
        return null;
    
    }
    @Override
    public void storeUtente(Utente user) throws DataException {
        try {
            
            if (user.getKey() != null && user.getKey() > 0) { //update
                //non facciamo nulla se l'oggetto è un proxy e indica di non aver subito modifiche
                //do not store the object if it is a proxy and does not indicate any modification
                if (user instanceof DataItemProxy && !((DataItemProxy) user).isModified()) {
                    return;
                }
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
                }
            } else { //insert
                
                iUser.setString(1, user.getEmail());
                iUser.setString(2, user.getPassword());
                iUser.setString(3, user.getNome());
                iUser.setString(4, user.getCognome());
                iUser.setString(5, user.getRuolo().toString());
             //   iUser.setString(5, user.getRuolo().toString());
             
                if (iUser.executeUpdate() == 1) {
        
                    
                    //per leggere la chiave generata dal database
                    //per il record appena inserito, usiamo il metodo
                    //getGeneratedKeys sullo statement.
                    //to read the generated record key from the database
                    //we use the getGeneratedKeys method on the same statement
                    try ( ResultSet keys = iUser.getGeneratedKeys()) {
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
                            user.setKey(key);
                            //inseriamo il nuovo oggetto nella cache
                            //add the new object to the cache
                            dataLayer.getCache().add(Utente.class, user);
                        }
                    }
                }
            }

            if (user instanceof DataItemProxy) {
                ((DataItemProxy) user).setModified(false);
            }
        } catch (SQLException | OptimisticLockException ex) {
            throw new DataException("Unable to store user", ex);
        }
    }

 
    
}
