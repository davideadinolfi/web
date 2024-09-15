package it.univaq.ex.webmarket.data.model.impl;

import it.univaq.ex.webmarket.data.model.Categoria;
import it.univaq.framework.data.DataItemImpl;

public class CategoriaImpl extends DataItemImpl<Integer> implements Categoria {
    private int id;
    private String nome;
    private String descrizione;
    private Categoria categoriaPadre;

    public CategoriaImpl() {
        super();
        this.nome = "";
        this.descrizione = "";
        this.categoriaPadre = null;
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
    public Categoria getCategoriaPadre() {
        return categoriaPadre;
    }

    @Override
    public void setCategoriaPadre(Categoria categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }
}
