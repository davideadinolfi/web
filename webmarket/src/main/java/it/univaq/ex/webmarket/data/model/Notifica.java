package it.univaq.ex.webmarket.data.model;

import it.univaq.framework.data.DataItem;
import java.util.Date;

public interface Notifica extends DataItem<Integer> {

    Utente getUtente();

    void setUtente(Utente utente);

    RichiestaAcquisto getRichiestaAcquisto();

    void setRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto);

    String getMessaggio();

    void setMessaggio(String messaggio);

    Date getData();

    void setData(Date data);

    boolean isVisualizzato();

    void setVisualizzato(boolean visualizzato);
}
