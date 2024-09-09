package it.univaq.ex.webmarket.data.model.impl;

import it.univaq.ex.webmarket.data.model.PropostaAcquisto;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.StatoProposta;
import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.framework.data.DataItemImpl;

public class PropostaAcquistoImpl extends DataItemImpl<Integer> implements PropostaAcquisto {

    private RichiestaAcquisto richiestaAcquisto;
    private Utente tecnico;
    private String nomeProduttore;
    private String nomeProdotto;
    private String codiceProdotto;
    private double prezzo;
    private String url;
    private String note;
    private StatoProposta statoProposta;
    private String notaRespinta;
    private String urlImmagine;

    public PropostaAcquistoImpl() {
        super();
        this.richiestaAcquisto = null;
        this.tecnico = null;
        this.nomeProduttore = "";
        this.nomeProdotto = "";
        this.codiceProdotto = "";
        this.prezzo = 0.0;
        this.url = "";
        this.note = "";
        this.statoProposta = StatoProposta.IN_ATTESA;
        this.notaRespinta = "";
        this.urlImmagine = "";
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
    public Utente getTecnico() {
        return tecnico;
    }

    @Override
    public void setTecnico(Utente tecnico) {
        this.tecnico = tecnico;
    }

    @Override
    public String getNomeProduttore() {
        return nomeProduttore;
    }

    @Override
    public void setNomeProduttore(String nomeProduttore) {
        this.nomeProduttore = nomeProduttore;
    }

    @Override
    public String getNomeProdotto() {
        return nomeProdotto;
    }

    @Override
    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    @Override
    public String getCodiceProdotto() {
        return codiceProdotto;
    }

    @Override
    public void setCodiceProdotto(String codiceProdotto) {
        this.codiceProdotto = codiceProdotto;
    }

    @Override
    public double getPrezzo() {
        return prezzo;
    }

    @Override
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
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
    public StatoProposta getStatoProposta() {
        return statoProposta;
    }

    @Override
    public void setStatoProposta(StatoProposta statoProposta) {
        this.statoProposta = statoProposta;
    }

    @Override
    public String getNotaRespinta() {
        return notaRespinta;
    }

    @Override
    public void setNotaRespinta(String notaRespinta) {
        this.notaRespinta = notaRespinta;
    }

    @Override
    public String getUrlImmagine() {
        return urlImmagine;
    }

    @Override
    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }
}
