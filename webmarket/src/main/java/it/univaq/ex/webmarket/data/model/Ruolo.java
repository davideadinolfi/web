/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.univaq.ex.webmarket.data.model;


public enum Ruolo {
    TECNICO("tecnico"),
    UTENTE("utente"),
    ADMIN("admin");
    private String value;
    private Ruolo(String value){
        this.value=value;
    }
    public String getValue(){
        return this.value;
    }
    
    
   
}
 