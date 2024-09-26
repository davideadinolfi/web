package it.univaq.ex.webmarket.data.model;

import it.univaq.framework.data.DataItem;

public interface RichiestaCaratteristica extends DataItem<Integer> {

    RichiestaAcquisto getRichiesta();

    void setRichiesta(RichiestaAcquisto acquisto);

    Caratteristica getCaratteristica();

    void setCaratteristica(Caratteristica caratteristica);

    String getSpecifica();

    void setSpecifica(String specifica);
}

