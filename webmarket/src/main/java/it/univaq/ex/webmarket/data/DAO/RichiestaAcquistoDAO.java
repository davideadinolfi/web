package it.univaq.ex.webmarket.data.DAO;

import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.StatoRichiesta;
import it.univaq.framework.data.DataException;
import java.util.List;

/**
 * Interfaccia per il DAO delle richieste di acquisto.
 */
public interface RichiestaAcquistoDAO {

    // Metodo factory per creare una nuova RichiestaAcquisto
    RichiestaAcquisto createRichiestaAcquisto();

    // Recupera una RichiestaAcquisto tramite la sua chiave primaria
    RichiestaAcquisto getRichiestaAcquisto(int richiestaAcquistoKey) throws DataException;

    // Recupera tutte le RichiestaAcquisto
    List<RichiestaAcquisto> getRichiesteAcquisto() throws DataException;

    // Recupera le RichiestaAcquisto per un ordinante specifico
    List<RichiestaAcquisto> getRichiesteAcquistoByOrdinante(int ordinanteKey) throws DataException;

    // Recupera le RichiestaAcquisto per un tecnico specifico
    List<RichiestaAcquisto> getRichiesteAcquistoByTecnico(int tecnicoKey) throws DataException;

    // Memorizza o aggiorna una RichiestaAcquisto
    void storeRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto) throws DataException;

    // Aggiorna lo stato di una RichiestaAcquisto
    void updateStatoRichiesta(int richiestaAcquistoKey, StatoRichiesta statoRichiesta) throws DataException;

    // Elimina una RichiestaAcquisto
    void deleteRichiestaAcquisto(int richiestaAcquistoKey) throws DataException;
}

