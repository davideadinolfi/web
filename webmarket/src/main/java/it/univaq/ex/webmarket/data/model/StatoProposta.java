/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.univaq.ex.webmarket.data.model;

/**
 *
 * @author Simone
 */
public enum StatoProposta {
    IN_ATTESA("in attesa"),
    APPROVATO("approvato"),
    RESPINTO("respinto"),
    ORDINATO("ordinato"),
    TERMINATO("terminato"),
    NC("nc"),
    NF("nf");


    private String value;
    
    private StatoProposta(String value){
        this.value=value;
    }
    public String getValue(){
        return this.value;
    }
}
