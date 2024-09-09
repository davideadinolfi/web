package it.univaq.ex.webmarket.data.model;

import it.univaq.framework.data.DataItem;

public interface Caratteristica extends DataItem<Integer> {

    Categoria getCategoria();

    void setCategoria(Categoria categoria);

    String getNome();

    void setNome(String nome);

    String getDescrizione();

    void setDescrizione(String descrizione);

}

