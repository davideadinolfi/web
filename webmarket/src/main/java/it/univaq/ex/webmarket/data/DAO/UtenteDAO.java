package it.univaq.ex.webmarket.data.DAO;

import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.framework.data.DataException;
import java.util.List;

/**
 * Interfaccia per il DAO degli utenti.
 */
public interface UtenteDAO {

    // Metodo factory per creare un nuovo Utente
    Utente createUtente();

    // Recupera un Utente tramite la sua chiave primaria
    Utente getUtente(int utenteKey) throws DataException;



    // Recupera un Utente tramite l'email
    Utente getUtenteByEmail(String email) throws DataException;

    // Memorizza o aggiorna un Utente
    void storeUtente(Utente utente) throws DataException;


}

