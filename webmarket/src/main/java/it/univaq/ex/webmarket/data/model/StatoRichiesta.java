package it.univaq.ex.webmarket.data.model;

public enum StatoRichiesta {
    ATTESA_TECNICO ("attesaTecnico"),
    ATTESA_ORDINANTE("attesaOrdinante"),
    ORDINATO("ordinato"),
    CONCLUSO("concluso");

    private String value;
    
    private StatoRichiesta(String value){
        this.value=value;
    }
    public String getValue(){
        return this.value;
    }
}