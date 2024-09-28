package it.univaq.ex.webmarket.data.DAO.impl;

import java.beans.Statement;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.OptimisticLockException;

import it.univaq.ex.webmarket.data.DAO.PropostaAcquistoDAO;
import it.univaq.ex.webmarket.data.model.PropostaAcquisto;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.StatoProposta;
import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.ex.webmarket.data.model.impl.proxy.PropostaAcquistoProxy;
import it.univaq.framework.data.DAO;
import it.univaq.framework.data.DataException;
import it.univaq.framework.data.DataItemProxy;
import it.univaq.framework.data.DataLayer;

public class PropostaAcquistoDAOmysql extends DAO implements PropostaAcquistoDAO{

    PreparedStatement iPropostaAcquisto;
    PreparedStatement uPropostaAcquisto;
    PreparedStatement gPropostaAcquistoByOrdinante;
    PreparedStatement gPropostaAcquisto;
    PreparedStatement gPropostaAcquistoByTecnico;

    public PropostaAcquistoDAOmysql(DataLayer d) {
        super(d);
        //TODO Auto-generated constructor stub
    }

    public void init()throws DataException{
        try{
            super.init();
            iPropostaAcquisto=connection.prepareStatement("INSERT INTO proposta_acquisto (ID_richiesta_acquisto, nome_produttore, nome_prodotto, codice_prodotto, prezzo, URL, note, stato_proposta, nota_respinta) VALUES (?,?,?,?,?,?,?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
            uPropostaAcquisto=connection.prepareStatement("UPDATE proposta_acquisto SET ID_richiesta_acquisto=?, nome_produttore = ?, nome_prodotto = ?, codice_prodotto = ?, prezzo = ?, URL = ?, note = ?, stato_proposta = ?, nota_respinta = ? WHERE ID = ?;");
            gPropostaAcquistoByOrdinante=connection.prepareStatement("SELECT proposta_acquisto.* FROM proposta_acquisto INNER JOIN richiesta_acquisto ON proposta_acquisto.id_richiesta_acquisto=richiesta_acquisto.id WHERE richiesta_acquisto.id_ordinante=?");
            gPropostaAcquisto=connection.prepareStatement("SELECT * FROM proposta_acquisto where id=?");
            gPropostaAcquistoByTecnico=connection.prepareStatement("SELECT proposta_acquisto.* FROM proposta_acquisto INNER JOIN richiesta_acquisto ON proposta_acquisto.id_richiesta_acquisto=richiesta_acquisto.id WHERE richiesta_acquisto.id_tecnico=?");
        }
         catch (SQLException e) {
            throw new DataException("errore di inizializzazione di propostaAcquisto",e);
        }

    }

    @Override
    public PropostaAcquisto createPropostaAcquisto() {
        return new PropostaAcquistoProxy(getDataLayer());
    }

    @Override
    public PropostaAcquisto createPropostaAcquisto(ResultSet rs){
        PropostaAcquisto p=createPropostaAcquisto();
      
        try {
            FileWriter file=new FileWriter("D:/roba//uni/webmarket/webmarket/log.txt");
            file.write("ciaoogrrrrr");
            file.close();
            p.setKey(rs.getInt("id"));
            p.setRichiestaAcquisto((RichiestaAcquisto)((WebmarketDataLayer)dataLayer).getRichiestaAcquistoDAO().getRichiestaAcquisto(rs.getInt("id_richiesta_acquisto")));
            p.setNomeProduttore(rs.getString("nome_produttore"));
            p.setNomeProdotto(rs.getString("nome_prodotto"));
            p.setCodiceProdotto(rs.getString("codice_prodotto"));
            p.setPrezzo(rs.getInt("prezzo"));
            p.setUrl(rs.getString("URL"));
            p.setNote(rs.getString("note"));
            p.setStatoProposta(rs.getString("stato_proposta"));
            p.setNotaRespinta(rs.getString("nota_respinta"));
        } catch (DataException | SQLException | IOException e )  {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        return p;
    }

    @Override
    public List<PropostaAcquisto> getProposteAcquistoByOrdinante(Utente u) throws DataException {
        ArrayList<PropostaAcquisto> list= new ArrayList<PropostaAcquisto>();
        ResultSet rs;
        try {
            gPropostaAcquistoByOrdinante.setInt(1, u.getKey());
            rs=gPropostaAcquistoByOrdinante.executeQuery();
            while (rs.next()) {
                list.add(createPropostaAcquisto(rs));
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<PropostaAcquisto> getProposteAcquistoByTecnico(Utente u) throws DataException {
        ArrayList<PropostaAcquisto> list= new ArrayList<PropostaAcquisto>();
        ResultSet rs;
        try {
            gPropostaAcquistoByTecnico.setInt(1, u.getKey());
            rs=gPropostaAcquistoByTecnico.executeQuery();
            while (rs.next()) {
                list.add(createPropostaAcquisto(rs));
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public PropostaAcquisto getPropostaAcquisto(int propostaAcquistoKey) throws DataException {
        try {
            gPropostaAcquisto.setInt(1, propostaAcquistoKey);
            try(ResultSet rs = gPropostaAcquisto.executeQuery()){
                rs.next();
                return createPropostaAcquisto(rs);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PropostaAcquisto> getProposteAcquisto() throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProposteAcquisto'");
    }

    @Override
    public List<PropostaAcquisto> getProposteAcquistoByRichiesta(int richiestaAcquistoKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProposteAcquistoByRichiesta'");
    }

    @Override
    public void storePropostaAcquisto(PropostaAcquisto propostaAcquisto) throws DataException {
        try {
            
            
            if (propostaAcquisto.getKey() != null && propostaAcquisto.getKey() > 0) { //update
                //non facciamo nulla se l'oggetto è un proxy e indica di non aver subito modifiche
                //do not store the object if it is a proxy and does not indicate any modification
                if (propostaAcquisto instanceof DataItemProxy && !((DataItemProxy) propostaAcquisto).isModified()) {
                    return;
                }
                if(uPropostaAcquisto.executeUpdate() == 1){
                    dataLayer.getCache().add(PropostaAcquisto.class,propostaAcquisto);
                    if (propostaAcquisto instanceof DataItemProxy) {
                        ((DataItemProxy) propostaAcquisto).setModified(false);
                    }
                }

                uPropostaAcquisto.setInt(1, propostaAcquisto.getRichiestaAcquisto().getKey());
                uPropostaAcquisto.setString(2, propostaAcquisto.getNomeProduttore());
                uPropostaAcquisto.setString(3, propostaAcquisto.getNomeProdotto());
                uPropostaAcquisto.setString(4, propostaAcquisto.getCodiceProdotto());
                uPropostaAcquisto.setDouble(5, propostaAcquisto.getPrezzo());
                uPropostaAcquisto.setString(6, propostaAcquisto.getUrl());
                uPropostaAcquisto.setString(7, propostaAcquisto.getNote());
                uPropostaAcquisto.setString(8, propostaAcquisto.getStatoProposta().getValue());
                uPropostaAcquisto.setString(9, propostaAcquisto.getNotaRespinta());
                if(uPropostaAcquisto.executeUpdate() == 1){
                    dataLayer.getCache().add(PropostaAcquisto.class,propostaAcquisto);
                    if (propostaAcquisto instanceof DataItemProxy) {
                        ((DataItemProxy) propostaAcquisto).setModified(false);
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
                iPropostaAcquisto.setInt(1, propostaAcquisto.getRichiestaAcquisto().getKey());
                iPropostaAcquisto.setString(2, propostaAcquisto.getNomeProduttore());
                iPropostaAcquisto.setString(3, propostaAcquisto.getNomeProdotto());
                iPropostaAcquisto.setString(4, propostaAcquisto.getCodiceProdotto());
                iPropostaAcquisto.setDouble(5, propostaAcquisto.getPrezzo());
                iPropostaAcquisto.setString(6, propostaAcquisto.getUrl());
                iPropostaAcquisto.setString(7, propostaAcquisto.getNote());
                iPropostaAcquisto.setString(8, propostaAcquisto.getStatoProposta().getValue());
                iPropostaAcquisto.setString(9, propostaAcquisto.getNotaRespinta());
                if (iPropostaAcquisto.executeUpdate() == 1) {
        
                    
                    //per leggere la chiave generata dal database
                    //per il record appena inserito, usiamo il metodo
                    //getGeneratedKeys sullo statement.
                    //to read the generated record key from the database
                    //we use the getGeneratedKeys method on the same statement
                    try ( ResultSet keys = iPropostaAcquisto.getGeneratedKeys()) {
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
                            propostaAcquisto.setKey(key);
                            //inseriamo il nuovo oggetto nella cache
                            //add the new object to the cache
                            dataLayer.getCache().add(PropostaAcquisto.class, propostaAcquisto);
                        }
                    }
                }
            }

         //   if (propostaAcquisto instanceof DataItemProxy) {
         //       ((DataItemProxy) propostaAcquisto).setModified(false);
      //      }
        } catch (SQLException | OptimisticLockException ex) {
            throw new DataException("Unable to store user", ex);
        }
    }

    @Override
    public void updateStatoProposta(int propostaAcquistoKey, StatoProposta statoProposta) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStatoProposta'");
    }

    @Override
    public void deletePropostaAcquisto(int propostaAcquistoKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePropostaAcquisto'");
    }



   
    
}
