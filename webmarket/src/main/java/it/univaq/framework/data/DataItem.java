/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.univaq.framework.data;


/**
 *
 * @author Simone
 */
public interface DataItem<KT>{
    KT getKey();
    
    long getVersion();
    
    void setKey(KT key);
    
    void setVersion(long version);
    
}
