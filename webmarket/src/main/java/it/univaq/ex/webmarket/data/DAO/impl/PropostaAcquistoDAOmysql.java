package it.univaq.ex.webmarket.data.DAO.impl;

import java.util.List;

import it.univaq.ex.webmarket.data.DAO.PropostaAcquistoDAO;
import it.univaq.ex.webmarket.data.model.PropostaAcquisto;
import it.univaq.ex.webmarket.data.model.StatoProposta;
import it.univaq.framework.data.DAO;
import it.univaq.framework.data.DataException;
import it.univaq.framework.data.DataLayer;

public class PropostaAcquistoDAOmysql extends DAO implements PropostaAcquistoDAO{

    public PropostaAcquistoDAOmysql(DataLayer d) {
        super(d);
        //TODO Auto-generated constructor stub
    }

    @Override
    public PropostaAcquisto createPropostaAcquisto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPropostaAcquisto'");
    }

    @Override
    public PropostaAcquisto getPropostaAcquisto(int propostaAcquistoKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPropostaAcquisto'");
    }

    @Override
    public List<PropostaAcquisto> getProposteAcquisto() throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProposteAcquisto'");
    }

    @Override
    public List<PropostaAcquisto> getProposteAcquistoByRichiesta(int richiestaAcquistoKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProposteAcquistoByRichiesta'");
    }

    @Override
    public void storePropostaAcquisto(PropostaAcquisto propostaAcquisto) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'storePropostaAcquisto'");
    }

    @Override
    public void updateStatoProposta(int propostaAcquistoKey, StatoProposta statoProposta) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStatoProposta'");
    }

    @Override
    public void deletePropostaAcquisto(int propostaAcquistoKey) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePropostaAcquisto'");
    }
    
}
