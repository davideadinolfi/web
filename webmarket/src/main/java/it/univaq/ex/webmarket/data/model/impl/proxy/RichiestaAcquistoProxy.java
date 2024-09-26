package it.univaq.ex.webmarket.data.model.impl.proxy;

import it.univaq.ex.webmarket.data.model.impl.RichiestaAcquistoImpl;
import it.univaq.framework.data.DataItemProxy;
import it.univaq.framework.data.DataLayer;

public class RichiestaAcquistoProxy extends RichiestaAcquistoImpl implements DataItemProxy{
    
    protected boolean modified;
    protected DataLayer dataLayer;


    public RichiestaAcquistoProxy(DataLayer d){
        super();
        //dependency injection
        this.dataLayer = d;
        this.modified = false;
    }

    @Override
    public boolean isModified() {
        return modified;
    }

    @Override
    public void setModified(boolean dirty) {
        this.modified=dirty;
    }
    
}
