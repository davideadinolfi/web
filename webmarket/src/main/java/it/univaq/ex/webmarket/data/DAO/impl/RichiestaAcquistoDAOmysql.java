package it.univaq.ex.webmarket.data.DAO.impl;

import java.util.List;

import it.univaq.ex.webmarket.data.DAO.RichiestaAcquistoDAO;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.StatoRichiesta;
import it.univaq.framework.data.DAO;
import it.univaq.framework.data.DataException;
import it.univaq.framework.data.DataLayer;

public class RichiestaAcquistoDAOmysql extends DAO implements RichiestaAcquistoDAO{

    public RichiestaAcquistoDAOmysql(DataLayer d) {
        super(d);
        //TODO Auto-generated constructor stub
    }

    @Override
    public RichiestaAcquisto createRichiestaAcquisto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRichiestaAcquisto'");
    }

    @Override
    public RichiestaAcquisto getRichiestaAcquisto(int richiestaAcquistoKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRichiestaAcquisto'");
    }

    @Override
    public List<RichiestaAcquisto> getRichiesteAcquisto() throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRichiesteAcquisto'");
    }

    @Override
    public List<RichiestaAcquisto> getRichiesteAcquistoByOrdinante(int ordinanteKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRichiesteAcquistoByOrdinante'");
    }

    @Override
    public List<RichiestaAcquisto> getRichiesteAcquistoByTecnico(int tecnicoKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRichiesteAcquistoByTecnico'");
    }

    @Override
    public void storeRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'storeRichiestaAcquisto'");
    }

    @Override
    public void updateStatoRichiesta(int richiestaAcquistoKey, StatoRichiesta statoRichiesta) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStatoRichiesta'");
    }

    @Override
    public void deleteRichiestaAcquisto(int richiestaAcquistoKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRichiestaAcquisto'");
    }
    
}
