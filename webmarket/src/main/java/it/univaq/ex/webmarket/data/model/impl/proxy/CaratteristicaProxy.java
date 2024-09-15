package it.univaq.ex.webmarket.data.model.impl.proxy;

import it.univaq.ex.webmarket.data.model.impl.CaratteristicaImpl;
import it.univaq.framework.data.DataItemProxy;
import it.univaq.framework.data.DataLayer;

public class CaratteristicaProxy extends CaratteristicaImpl implements DataItemProxy{

    
    protected boolean modified;
    protected DataLayer dataLayer;

    public CaratteristicaProxy(DataLayer d){
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
