package it.univaq.ex.webmarket.data.DAO;

import it.univaq.ex.webmarket.data.model.Notifica;
import it.univaq.framework.data.DataException;
import java.util.List;

/**
 * Interfaccia per il DAO delle notifiche.
 */
public interface NotificaDAO {

    // Metodo factory per creare una nuova Notifica
    Notifica createNotifica();

    // Recupera una Notifica tramite la sua chiave primaria
    Notifica getNotifica(int notificaKey) throws DataException;

    // Recupera tutte le Notifiche
    List<Notifica> getNotifiche() throws DataException;

    // Recupera tutte le Notifiche per un utente specifico
    List<Notifica> getNotificheByUtente(int utenteKey) throws DataException;

    // Recupera tutte le Notifiche per una specifica RichiestaAcquisto
    List<Notifica> getNotificheByRichiesta(int richiestaAcquistoKey) throws DataException;

    // Memorizza o aggiorna una Notifica
    void storeNotifica(Notifica notifica) throws DataException;

    // Aggiorna lo stato visualizzato di una Notifica
    void updateVisualizzato(int notificaKey, boolean visualizzato) throws DataException;

    // Elimina una Notifica
    void deleteNotifica(int notificaKey) throws DataException;
}

