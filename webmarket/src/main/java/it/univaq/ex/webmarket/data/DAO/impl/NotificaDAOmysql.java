package it.univaq.ex.webmarket.data.DAO.impl;

import java.util.Date;

import it.univaq.ex.webmarket.data.model.Notifica;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.framework.data.DAO;
import it.univaq.framework.data.DataLayer;

public class NotificaDAOmysql extends DAO implements Notifica{

    public NotificaDAOmysql(DataLayer d) {
        super(d);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Integer getKey() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getKey'");
    }

    @Override
    public long getVersion() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVersion'");
    }

    @Override
    public void setKey(Integer key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setKey'");
    }

    @Override
    public void setVersion(long version) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVersion'");
    }

    @Override
    public Utente getUtente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUtente'");
    }

    @Override
    public void setUtente(Utente utente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUtente'");
    }

    @Override
    public RichiestaAcquisto getRichiestaAcquisto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRichiestaAcquisto'");
    }

    @Override
    public void setRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRichiestaAcquisto'");
    }

    @Override
    public String getMessaggio() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessaggio'");
    }

    @Override
    public void setMessaggio(String messaggio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMessaggio'");
    }

    @Override
    public Date getData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getData'");
    }

    @Override
    public void setData(Date data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setData'");
    }

    @Override
    public boolean isVisualizzato() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isVisualizzato'");
    }

    @Override
    public void setVisualizzato(boolean visualizzato) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVisualizzato'");
    }
    
}
