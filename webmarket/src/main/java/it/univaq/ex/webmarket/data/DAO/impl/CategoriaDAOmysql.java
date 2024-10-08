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
            throw new DataException("errore di inizializzazione del datalayer categoria",e);
        }
    }

    @Override
    public void destroy() throws DataException {
        //anche chiudere i PreparedStamenent è una buona pratica...
        //also closing PreparedStamenents is a good practice...
        try {
            sCategoria.close();
            sCategorie.close();
        } catch (SQLException ex) {
            throw new DataException("Errore di chiusura dei preparedStatements", ex);
        }
        super.destroy();
    }

    public CategoriaDAOmysql(DataLayer d) {
        super(d);
    }

 
    private CategoriaProxy createCategoria(ResultSet rs) throws DataException{
        CategoriaProxy c = (CategoriaProxy) createCategoria();
        try {
            c.setNome(rs.getString("nome"));
            c.setCategoriaPadre(null);
            c.setDescrizione(rs.getString("descrizione"));
            c.setKey(rs.getInt("id"));
        } 
        catch(SQLException e){
            throw new DataException("impossibile creare categoria dal resultSet",e);
        }
        return c;
    }

    @Override
    public Categoria getCategoria(int categoriaKey) throws DataException {
        try {
            sCategoria.setInt(1, categoriaKey);
        } catch (SQLException e) {
            throw new DataException("impossibile settare i placeholder del prepareStatement categoria",e);
        }
        try(ResultSet rs = sCategoria.executeQuery()){
            if(rs.next()){
                Categoria c= createCategoria(rs);
                
                return c;
            }
        }
        catch(Exception e){
            throw new DataException("impossibile ritornare la categoria",e);
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
            throw new DataException("impossibile ritornare la lista di categorie",e);
        }
        return list;
            
        };
            
       
    
    @Override
    public Categoria createCategoria() {
        return new CategoriaProxy(getDataLayer());
    }
    
}
