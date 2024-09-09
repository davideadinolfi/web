package it.univaq.ex.webmarket.data.model.impl;

import it.univaq.ex.webmarket.data.model.Caratteristica;
import it.univaq.ex.webmarket.data.model.Categoria;
import it.univaq.framework.data.DataItemImpl;

public class CaratteristicaImpl extends DataItemImpl<Integer> implements Caratteristica {

    private String nome;
    private String descrizione;
    private Categoria categoria;

    public CaratteristicaImpl() {
        super();
        this.nome = "";
        this.descrizione = "";
        this.categoria = null;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
