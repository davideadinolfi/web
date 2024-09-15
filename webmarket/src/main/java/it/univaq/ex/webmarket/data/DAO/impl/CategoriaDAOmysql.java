package it.univaq.ex.webmarket.data.DAO.impl;

import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import it.univaq.ex.webmarket.data.DAO.CategoriaDAO;
import it.univaq.ex.webmarket.data.model.Categoria;
import it.univaq.ex.webmarket.data.model.impl.proxy.CategoriaProxy;
import it.univaq.framework.data.DAO;
import it.univaq.framework.data.DataException;
import it.univaq.framework.data.DataLayer;

public class CategoriaDAOmysql extends DAO implements CategoriaDAO {

    PreparedStatement sCategorie;
    PreparedStatement sCategoria;

    @Override
    public void init()throws DataException{
        super.init();
        try {
            sCategorie = connection.prepareStatement("SELECT * FROM categoria");
            sCategoria = connection.prepareStatement("SELECT * FROM categoria WHERE ID=?");
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }

    public CategoriaDAOmysql(DataLayer d) {
        super(d);
        //TODO Auto-generated constructor stub
    }

 
    private CategoriaProxy createCategoria(ResultSet rs) {
        CategoriaProxy c = (CategoriaProxy) createCategoria();
        try {
            c.setNome(rs.getString("nome"));
            c.setCategoriaPadre(null);
            c.setDescrizione(rs.getString("descrizione"));
            c.setKey(rs.getInt("id"));
        } 
        catch(Exception e){

        }
        return c;
    }

    @Override
    public Categoria getCategoria(int categoriaKey) throws DataException {
        try {
            sCategoria.setInt(1, categoriaKey);
        } catch (SQLException e) {
            //TODO
        }
        try(ResultSet rs = sCategoria.executeQuery()){
            if(rs.next()){
                Categoria c= createCategoria(rs);
                
                return c;
            }
        }
        catch(Exception e){
            //TODO
        }
        return null;
        
    }

    @Override
    public List<Categoria> getCategorie() throws DataException, SQLException {
        List<Categoria> list = new ArrayList<Categoria>();
        try(ResultSet rs = sCategorie.executeQuery()){
            while(rs.next()){
                Categoria c = createCategoria(rs);
                dataLayer.getCache().add(Categoria.class, c);
            }

        }
        catch(Exception e){

        }
        return list;
            
        };
            
       
        

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

    @Override
    public Categoria createCategoria() {
        return new CategoriaProxy(getDataLayer());
    }
    
}
