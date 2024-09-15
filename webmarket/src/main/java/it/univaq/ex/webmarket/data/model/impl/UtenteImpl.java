package it.univaq.ex.webmarket.data.model.impl;

import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.ex.webmarket.data.model.Ruolo;
import it.univaq.framework.data.DataItemImpl;

public class UtenteImpl extends DataItemImpl<Integer> implements Utente {
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Ruolo ruolo;


    public UtenteImpl() {
        super();
        this.id=0;
        this.nome = "";
        this.cognome = "";
        this.email = "";
        this.password = "";
        this.ruolo = Ruolo.UTENTE;
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
    public String getCognome() {
        return cognome;
    }

    @Override
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Ruolo getRuolo() {
        return ruolo;
    }

    @Override
    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public void setRuolo(String ruolo){
        if(ruolo.equals("admin"))
            this.ruolo=Ruolo.ADMIN;
        if(ruolo.equals("utente"))
            this.ruolo=Ruolo.UTENTE;
        if(ruolo.equals("tecnico"))
            this.ruolo=Ruolo.TECNICO;
    }
}

