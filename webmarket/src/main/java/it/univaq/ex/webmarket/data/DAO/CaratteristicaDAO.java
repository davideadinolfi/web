package it.univaq.ex.webmarket.data.DAO;

import it.univaq.ex.webmarket.data.model.Caratteristica;
import it.univaq.framework.data.DataException;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia per il DAO delle caratteristiche.
 */
public interface CaratteristicaDAO {

    // Metodo factory per creare una nuova Caratteristica
    Caratteristica createCaratteristica();

  

    // Recupera tutte le Caratteristiche associate a una specifica Categoria
    List<Caratteristica> getCaratteristicheByCategoria(int categoriaKey) throws DataException;

    
}

