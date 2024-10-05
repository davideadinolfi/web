package it.univaq.ex.webmarket.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.univaq.ex.webmarket.data.DAO.impl.WebmarketDataLayer;
import it.univaq.ex.webmarket.data.model.PropostaAcquisto;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.framework.data.DataException;
import it.univaq.framework.result.TemplateManagerException;
import it.univaq.framework.result.TemplateResult;
import it.univaq.framework.security.SecurityHelpers;

public class TecnicoHome extends WebmarketBaseController{

    private void action_default(HttpServletRequest request, HttpServletResponse response)throws IOException,TemplateManagerException{
        TemplateResult result = new TemplateResult(getServletContext());
        request.setAttribute("referrer", request.getParameter("referrer"));
        loadListaProposte(request, response);
        loadListaRichieste(request, response);
        result.activate("tecnicoHome.ftl.html", request, response);

    }

    private void loadListaRichieste(HttpServletRequest request, HttpServletResponse response){
         try {  Utente tecnico = (Utente) request.getSession().getAttribute("user");
                ArrayList <RichiestaAcquisto> list=(ArrayList<RichiestaAcquisto>) ((WebmarketDataLayer) request.getAttribute("datalayer")).getRichiestaAcquistoDAO().getRichiesteAcquistoByTecnico(
                (tecnico.getKey()));
                list.addAll((ArrayList<RichiestaAcquisto>) ((WebmarketDataLayer) request.getAttribute("datalayer")).getRichiestaAcquistoDAO().getRichiesteAcquistoByTecnico(0));
                request.setAttribute("listaRichieste", list);
                
        } catch (DataException e) {
            handleError(e, request, response);
        }
    }

        private void loadListaProposte(HttpServletRequest request,HttpServletResponse response) {
        
    
        try {   
                ArrayList <PropostaAcquisto> list=(ArrayList<PropostaAcquisto>) ((WebmarketDataLayer) request.getAttribute("datalayer")).getPropostaAcquistoDAO().getProposteAcquistoByTecnico(
                (Utente) request.getSession().getAttribute("user"));
                request.setAttribute("listaProposte", list);
                
        } catch (DataException e) {
            handleError(e, request, response);
        }
    }


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String https_redirect_url = SecurityHelpers.checkHttps(request);
            request.setAttribute("https-redirect", https_redirect_url);
           action_default(request, response);
        }
         catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}
