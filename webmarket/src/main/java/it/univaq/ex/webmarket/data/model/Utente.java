/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.univaq.ex.webmarket.data.model;

import it.univaq.framework.data.DataItem;

public interface Utente extends DataItem<Integer> {
    void setid(int id);

    int getid();

    String getNome();

    void setNome(String nome);

    String getCognome();

    void setCognome(String cognome);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    Ruolo getRuolo();

    void setRuolo(Ruolo ruolo);

    void setRuolo(String ruolo);
}

