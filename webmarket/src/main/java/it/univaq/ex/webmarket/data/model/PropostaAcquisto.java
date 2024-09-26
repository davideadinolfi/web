/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.univaq.ex.webmarket.data.model;

import it.univaq.framework.data.DataItem;

/**
 *
 * @author Simone
 */
public interface PropostaAcquisto extends DataItem<Integer> {

    Utente getTecnico();

    void setTecnico(Utente tecnico);

    RichiestaAcquisto getRichiestaAcquisto();

    void setRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto);

    String getNomeProduttore();

    void setNomeProduttore(String nomeProduttore);

    String getNomeProdotto();

    void setNomeProdotto(String nomeProdotto);

    String getCodiceProdotto();

    void setCodiceProdotto(String codiceProdotto);

    double getPrezzo();

    void setPrezzo(double prezzo);

    String getUrl();

    void setUrl(String url);

    String getNote();

    void setNote(String note);

    StatoProposta getStatoProposta();

    void setStatoProposta(StatoProposta statoProposta);

    void setStatoProposta(String s);

    String getNotaRespinta();

    void setNotaRespinta(String notaRespinta);

    String getUrlImmagine();

    void setUrlImmagine(String urlImmagine);
}
