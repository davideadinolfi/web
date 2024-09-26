package it.univaq.ex.webmarket.data.model.impl;

import it.univaq.ex.webmarket.data.model.Caratteristica;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.RichiestaCaratteristica;
import it.univaq.framework.data.DataItemImpl;

public class RichiestaCaratteristicaImpl extends DataItemImpl<Integer> implements RichiestaCaratteristica{

    private RichiestaAcquisto richiesta;
    private Caratteristica caratteristica;
    private String specifica;

    
    public RichiestaAcquisto getRichiesta() {
        return richiesta;
    }
    public void setRichiesta(RichiestaAcquisto richiesta) {
        this.richiesta = richiesta;
    }
    public Caratteristica getCaratteristica() {
        return caratteristica;
    }
    public void setCaratteristica(Caratteristica caratteristica) {
        this.caratteristica = caratteristica;
    }
    public String getSpecifica() {
        return specifica;
    }
    public void setSpecifica(String specifica) {
        this.specifica = specifica;
    }

    
}