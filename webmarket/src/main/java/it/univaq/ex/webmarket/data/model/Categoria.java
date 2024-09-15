package it.univaq.ex.webmarket.data.model;

import it.univaq.framework.data.DataItem;

public interface Categoria extends DataItem<Integer> {

    

    String getNome();

    void setNome(String nome);

    String getDescrizione();

    void setDescrizione(String descrizione);

    Categoria getCategoriaPadre();

    void setCategoriaPadre(Categoria categoriaPadre);

}

