package it.univaq.ex.webmarket.data.model;

import it.univaq.framework.data.DataItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface RichiestaAcquisto extends DataItem<Integer> {

    Utente getOrdinante();

    void setOrdinante(Utente ordinante);

    Utente getTecnico();

    void setTecnico(Utente tecnico);

    Categoria getCategoria();

    void setCategoria(Categoria categoria);

    void setCategoria(String s);

    LocalDateTime getDataRichiesta();

    void setDataRichiesta(LocalDateTime dataRichiesta);

    String getNote();

    void setNote(String note);

    StatoRichiesta getStatoRichiesta();

    void setStatoRichiesta(StatoRichiesta statoRichiesta);
}
