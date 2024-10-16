package it.univaq.ex.webmarket.data.model.impl;

import it.univaq.ex.webmarket.data.DAO.impl.WebmarketDataLayer;
import it.univaq.ex.webmarket.data.model.Categoria;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.StatoRichiesta;
import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.framework.data.DataItemImpl;
import it.univaq.ex.webmarket.data.DAO.impl.WebmarketDataLayer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class RichiestaAcquistoImpl extends DataItemImpl<Integer> implements RichiestaAcquisto {

    private Utente ordinante;
    private Utente tecnico;
    private Categoria categoria;
    private LocalDateTime dataRichiesta;
    private String note;
    private StatoRichiesta statoRichiesta;

    public RichiestaAcquistoImpl() {
        super();
        this.ordinante = null;
        this.tecnico = null;
        this.categoria = null;
        this.dataRichiesta = null;
        this.note = "";
        this.statoRichiesta = StatoRichiesta.ATTESA_TECNICO;
    }

 
    @Override
    public Utente getOrdinante() {
        return ordinante;
    }

    @Override
    public void setOrdinante(Utente ordinante) {
        this.ordinante = ordinante;
    }

    @Override
    public Utente getTecnico() {
        return tecnico;
    }

    @Override
    public void setTecnico(Utente tecnico) {
        this.tecnico = tecnico;
    }

    @Override
    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public void setCategoria(int id){
        //DA FARE
        
    }

    public void setDataRichiesta(String s){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dataRichiesta = LocalDateTime.parse(s, formatter);
    }

    @Override
    public LocalDateTime getDataRichiesta() {
        return dataRichiesta;
    }

    @Override
    public void setDataRichiesta(LocalDateTime dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public void setNote(String note){
        if(note!=null)
        this.note = note;
        else this.note ="NULL";
    }

    @Override
    public StatoRichiesta getStatoRichiesta() {
        return statoRichiesta;
    }

    @Override
    public void setStatoRichiesta(StatoRichiesta statoRichiesta) {
        this.statoRichiesta = statoRichiesta;
    
    }

    public void setOrdinante(int id){
        //DA FARE
        this.ordinante=null;
    }

    public void setTecnico(int id){
        //DA FARE
        this.tecnico=null;
    }

    public void setCategoria(String s){       
    
    }
    

    public void setStatoRichiesta(String s){
        switch (s) {
            case "attesaTecnico":
            this.statoRichiesta=StatoRichiesta.ATTESA_TECNICO;
            break;
            case "attesaOrdinante":
            this.statoRichiesta=StatoRichiesta.ATTESA_ORDINANTE;
            break;
            case "ordinato":
            this.statoRichiesta=StatoRichiesta.ORDINATO;
                break;
        case "concluso":
        this.statoRichiesta=StatoRichiesta.CONCLUSO;
            default:
                break;
        }
    }
}

