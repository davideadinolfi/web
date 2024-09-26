package it.univaq.ex.webmarket.data.model.impl.proxy;

import it.univaq.ex.webmarket.data.model.impl.PropostaAcquistoImpl;
import it.univaq.framework.data.DataItemProxy;
import it.univaq.framework.data.DataLayer;

public class PropostaAcquistoProxy extends PropostaAcquistoImpl implements DataItemProxy{
    DataLayer dataLayer;
    Boolean modified;
    
    public PropostaAcquistoProxy(DataLayer d){
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
