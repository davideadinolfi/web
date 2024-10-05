package it.univaq.ex.webmarket.data.DAO;

import java.sql.SQLException;
import java.util.List;

import it.univaq.ex.webmarket.data.model.Caratteristica;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.RichiestaCaratteristica;
import it.univaq.framework.data.DataException;

public interface RichiestaCaratteristicaDAO {
 
     public List<RichiestaCaratteristica> getRichiestaCaratteristiche(RichiestaAcquisto r)throws DataException;

      void storeCaratteristicaRichiesta(Caratteristica caratteristica,int richestaKey,String descrizione) throws DataException,SQLException;

}
