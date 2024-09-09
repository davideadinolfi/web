package it.univaq.ex.webmarket.data.model.impl;

import it.univaq.ex.webmarket.data.model.Categoria;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.StatoRichiesta;
import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.framework.data.DataItemImpl;
import java.util.Date;

public class RichiestaAcquistoImpl extends DataItemImpl<Integer> implements RichiestaAcquisto {

    private Utente ordinante;
    private Utente tecnico;
    private Categoria categoria;
    private Date dataRichiesta;
    private String note;
    private StatoRichiesta statoRichiesta;

    public RichiestaAcquistoImpl() {
        super();
        this.ordinante = null;
        this.tecnico = null;
        this.categoria = null;
        this.dataRichiesta = new Date();
        this.note = "";
        this.statoRichiesta = StatoRichiesta.ATTESA_TECNICO;
    }

 
    @Override
    public Utente getOrdinante() {
        return ordinante;
    }

    @Override
    public void setOrdinante(Utente ordinante) {
        this.ordinante = ordinante;
    }

    @Override
    public Utente getTecnico() {
        return tecnico;
    }

    @Override
    public void setTecnico(Utente tecnico) {
        this.tecnico = tecnico;
    }

    @Override
    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public Date getDataRichiesta() {
        return dataRichiesta;
    }

    @Override
    public void setDataRichiesta(Date dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public StatoRichiesta getStatoRichiesta() {
        return statoRichiesta;
    }

    @Override
    public void setStatoRichiesta(StatoRichiesta statoRichiesta) {
        this.statoRichiesta = statoRichiesta;
    }
}

