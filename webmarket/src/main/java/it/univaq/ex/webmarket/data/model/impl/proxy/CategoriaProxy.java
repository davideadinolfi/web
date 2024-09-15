package it.univaq.ex.webmarket.data.model.impl.proxy;

import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;

import it.univaq.ex.webmarket.data.model.impl.CategoriaImpl;
import it.univaq.framework.data.DataItemProxy;
import it.univaq.framework.data.DataLayer;

public class CategoriaProxy extends CategoriaImpl implements DataItemProxy{

    protected boolean modified;
    protected DataLayer dataLayer;

    public CategoriaProxy(DataLayer d){
        super();
        //dependency injection
        this.dataLayer = d;
        this.modified = false;
    }

    public static CategoriaProxy createCategoria(ResultSet rs){
        return null;
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
