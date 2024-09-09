package it.univaq.ex.webmarket.data.DAO.impl;

import java.util.List;

import it.univaq.ex.webmarket.data.DAO.CategoriaDAO;
import it.univaq.ex.webmarket.data.model.Categoria;
import it.univaq.framework.data.DAO;
import it.univaq.framework.data.DataException;
import it.univaq.framework.data.DataLayer;

public class CategoriaDAOmysql extends DAO implements CategoriaDAO {

    public CategoriaDAOmysql(DataLayer d) {
        super(d);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Categoria createCategoria() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCategoria'");
    }

    @Override
    public Categoria getCategoria(int categoriaKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategoria'");
    }

    @Override
    public List<Categoria> getCategorie() throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategorie'");
    }

    @Override
    public List<Categoria> getSottoCategorie(int categoriaPadreKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSottoCategorie'");
    }

    @Override
    public void storeCategoria(Categoria categoria) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'storeCategoria'");
    }

    @Override
    public void deleteCategoria(int categoriaKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCategoria'");
    }
    
}
