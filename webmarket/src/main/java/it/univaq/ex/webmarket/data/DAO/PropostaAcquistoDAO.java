package it.univaq.ex.webmarket.data.DAO;

import it.univaq.ex.webmarket.data.model.PropostaAcquisto;
import it.univaq.ex.webmarket.data.model.StatoProposta;
import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.framework.data.DataException;

import java.sql.ResultSet;
import java.util.List;



/**
 * Interfaccia per il DAO delle proposte di acquisto.
 */
public interface PropostaAcquistoDAO {



    // Metodo factory per creare una nuova PropostaAcquisto
    PropostaAcquisto createPropostaAcquisto();

    // Recupera una PropostaAcquisto tramite la sua chiave primaria
    PropostaAcquisto getPropostaAcquisto(int propostaAcquistoKey) throws DataException;



    // Memorizza o aggiorna una PropostaAcquisto
    void storePropostaAcquisto(PropostaAcquisto propostaAcquisto) throws DataException;


    List<PropostaAcquisto> getProposteAcquistoByOrdinante(Utente u)throws DataException;

    List<PropostaAcquisto> getProposteAcquistoByTecnico(Utente u)throws DataException;
}

