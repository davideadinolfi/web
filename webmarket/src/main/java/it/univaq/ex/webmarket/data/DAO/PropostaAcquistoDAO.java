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

    public PropostaAcquisto createPropostaAcquisto(ResultSet rs);

    // Metodo factory per creare una nuova PropostaAcquisto
    PropostaAcquisto createPropostaAcquisto();

    // Recupera una PropostaAcquisto tramite la sua chiave primaria
    PropostaAcquisto getPropostaAcquisto(int propostaAcquistoKey) throws DataException;

    // Recupera tutte le PropostaAcquisto
    List<PropostaAcquisto> getProposteAcquisto() throws DataException;

    // Recupera le PropostaAcquisto associate a una specifica RichiestaAcquisto
    List<PropostaAcquisto> getProposteAcquistoByRichiesta(int richiestaAcquistoKey) throws DataException;

    // Memorizza o aggiorna una PropostaAcquisto
    void storePropostaAcquisto(PropostaAcquisto propostaAcquisto) throws DataException;

    // Aggiorna lo stato di una PropostaAcquisto
    void updateStatoProposta(int propostaAcquistoKey, StatoProposta statoProposta) throws DataException;

    // Elimina una PropostaAcquisto
    void deletePropostaAcquisto(int propostaAcquistoKey) throws DataException;

    List<PropostaAcquisto> getProposteAcquistoByOrdinante(Utente u)throws DataException;

    List<PropostaAcquisto> getProposteAcquistoByTecnico(Utente u)throws DataException;
}

