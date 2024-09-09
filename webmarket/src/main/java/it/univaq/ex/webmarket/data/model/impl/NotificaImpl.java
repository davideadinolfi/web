package it.univaq.ex.webmarket.data.model.impl;

import it.univaq.ex.webmarket.data.model.Notifica;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.framework.data.DataItemImpl;
import java.util.Date;

public class NotificaImpl extends DataItemImpl<Integer> implements Notifica {

   
    private Utente utente;
    private RichiestaAcquisto richiestaAcquisto;
    private String messaggio;
    private Date data;
    private boolean visualizzato;

    public NotificaImpl() {
        super();
        this.utente = null;
        this.richiestaAcquisto = null;
        this.messaggio = "";
        this.data = new Date();
        this.visualizzato = false;
    }


    @Override
    public Utente getUtente() {
        return utente;
    }

    @Override
    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public RichiestaAcquisto getRichiestaAcquisto() {
        return richiestaAcquisto;
    }

    @Override
    public void setRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto) {
        this.richiestaAcquisto = richiestaAcquisto;
    }

    @Override
    public String getMessaggio() {
        return messaggio;
    }

    @Override
    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    @Override
    public Date getData() {
        return data;
    }

    @Override
    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public boolean isVisualizzato() {
        return visualizzato;
    }

    @Override
    public void setVisualizzato(boolean visualizzato) {
        this.visualizzato = visualizzato;
    }
}

