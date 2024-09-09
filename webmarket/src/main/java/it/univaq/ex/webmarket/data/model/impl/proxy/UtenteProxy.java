package it.univaq.ex.webmarket.data.model.impl.proxy;

import it.univaq.ex.webmarket.data.model.impl.UtenteImpl;
import it.univaq.framework.data.DataItemProxy;
import it.univaq.framework.data.DataLayer;

public class UtenteProxy extends UtenteImpl implements DataItemProxy  {

    protected boolean modified;
    protected DataLayer dataLayer;

    public UtenteProxy(DataLayer d) {
        super();
        //dependency injection
        this.dataLayer = d;
        this.modified = false;
    }

  
    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }


    @Override
    public void setNome(String name) {
        super.setNome(name);
        this.modified = true;
    }

    @Override
    public void setPassword(String surname) {
        super.setPassword(surname);
        this.modified = true;
    }

    //METODI DEL PROXY
    //PROXY-ONLY METHODS

    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }

    @Override
    public boolean isModified() {
        return modified;
    }

}
