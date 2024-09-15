package it.univaq.ex.webmarket.data.DAO;

import it.univaq.ex.webmarket.data.model.Categoria;
import it.univaq.framework.data.DataException;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia per il DAO delle categorie.
 */
public interface CategoriaDAO {

    // Metodo factory per creare una nuova Categoria
    Categoria createCategoria();

    // Recupera una Categoria tramite la sua chiave primaria
    Categoria getCategoria(int categoriaKey) throws DataException;

    // Recupera tutte le Categorie
    List<Categoria> getCategorie() throws DataException, SQLException;

    // Recupera tutte le sotto-categorie di una specifica Categoria
    List<Categoria> getSottoCategorie(int categoriaPadreKey) throws DataException;

    // Memorizza o aggiorna una Categoria
    void storeCategoria(Categoria categoria) throws DataException;

    // Elimina una Categoria
    void deleteCategoria(int categoriaKey) throws DataException;
}
