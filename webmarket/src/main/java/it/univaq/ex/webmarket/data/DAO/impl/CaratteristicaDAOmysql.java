package it.univaq.ex.webmarket.data.DAO.impl;

import java.util.List;

import it.univaq.ex.webmarket.data.DAO.CaratteristicaDAO;
import it.univaq.ex.webmarket.data.model.Caratteristica;
import it.univaq.framework.data.DAO;
import it.univaq.framework.data.DataException;
import it.univaq.framework.data.DataLayer;

public class CaratteristicaDAOmysql extends DAO implements CaratteristicaDAO{

    public CaratteristicaDAOmysql(DataLayer d) {
        super(d);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Caratteristica createCaratteristica() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCaratteristica'");
    }

    @Override
    public Caratteristica getCaratteristica(int caratteristicaKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCaratteristica'");
    }

    @Override
    public List<Caratteristica> getCaratteristiche() throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCaratteristiche'");
    }

    @Override
    public List<Caratteristica> getCaratteristicheByCategoria(int categoriaKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCaratteristicheByCategoria'");
    }

    @Override
    public void storeCaratteristica(Caratteristica caratteristica) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'storeCaratteristica'");
    }

    @Override
    public void deleteCaratteristica(int caratteristicaKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCaratteristica'");
    }
    
}
