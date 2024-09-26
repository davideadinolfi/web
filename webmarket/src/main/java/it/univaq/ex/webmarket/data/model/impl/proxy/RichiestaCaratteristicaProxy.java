package it.univaq.ex.webmarket.data.model.impl.proxy;

import it.univaq.ex.webmarket.data.model.impl.RichiestaCaratteristicaImpl;
import it.univaq.framework.data.DataItemProxy;
import it.univaq.framework.data.DataLayer;

public class RichiestaCaratteristicaProxy extends RichiestaCaratteristicaImpl implements DataItemProxy{
    protected DataLayer dataLayer;
    protected boolean modified;
    
    public RichiestaCaratteristicaProxy(DataLayer d){
        super();
        //dependency injection
        this.dataLayer = d;
        this.modified = false;
    }

    @Override
    public boolean isModified() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isModified'");
    }

    @Override
    public void setModified(boolean dirty) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setModified'");
    }
    
}


