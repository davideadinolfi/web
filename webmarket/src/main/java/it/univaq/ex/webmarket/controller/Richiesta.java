package it.univaq.ex.webmarket.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.univaq.ex.webmarket.data.DAO.impl.WebmarketDataLayer;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.RichiestaCaratteristica;
import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.ex.webmarket.data.model.impl.proxy.RichiestaAcquistoProxy;
import it.univaq.framework.data.DataException;
import it.univaq.framework.result.TemplateManagerException;
import it.univaq.framework.result.TemplateResult;
import it.univaq.framework.security.SecurityHelpers;

public class Richiesta extends WebmarketBaseController{

    private void action_default(HttpServletRequest request,HttpServletResponse response) throws TemplateManagerException{
        TemplateResult result = new TemplateResult(getServletContext());
        request.setAttribute("referrer", request.getParameter("referrer"));
        try {
            RichiestaAcquisto r=((WebmarketDataLayer) request.getAttribute("datalayer")).getRichiestaAcquistoDAO().getRichiestaAcquisto(Integer.parseInt(request.getParameter("richiesta")));
            request.setAttribute("richiesta", r);
        } catch (NumberFormatException | DataException e) {
            handleError(e, request, response);
        }
        if(((Utente)request.getSession().getAttribute("user")).getRuolo().getValue().equals("tecnico"))
            request.setAttribute("tecnico", true);
        loadCaratteristiche(request, response);
        result.activate("richiesta.ftl.html", request, response);
        
    }

    private void action_assegnaRichiesta(HttpServletRequest request, HttpServletResponse response){
        try {
            RichiestaAcquistoProxy r=(RichiestaAcquistoProxy) ((WebmarketDataLayer) request.getAttribute("datalayer")).getRichiestaAcquistoDAO().getRichiestaAcquisto(Integer.parseInt(request.getParameter("richiesta")));
            r.setTecnico((Utente)request.getSession().getAttribute("user"));
            r.setModified(true);
            ((WebmarketDataLayer) request.getAttribute("datalayer")).getRichiestaAcquistoDAO().storeRichiestaAcquisto(r);
            response.sendRedirect("tecnicoHome");  
        } catch (DataException | IOException e) {
            handleError(e, request, response);
        }
    }

    private void loadCaratteristiche(HttpServletRequest request,HttpServletResponse response){
        RichiestaAcquisto r;
        try {
            r = (RichiestaAcquisto) ((WebmarketDataLayer) request.getAttribute("datalayer")).getRichiestaAcquistoDAO().getRichiestaAcquisto(Integer.parseInt(request.getParameter("richiesta")));
            ArrayList<RichiestaCaratteristica> l=(ArrayList<RichiestaCaratteristica>)((WebmarketDataLayer) request.getAttribute("datalayer")).getRichiestaCaratteristicaDAO().getRichiestaCaratteristiche(r);
            request.setAttribute("caratteristiche", l);
            request.getSession().setAttribute("caratteristiche", l);
        } catch (NumberFormatException | DataException e) {
            handleError(e, request, response);
        }
        
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
         try {
            if(request.getParameter("assegna")!=null){
                action_assegnaRichiesta(request, response);
            }
            else{
                //devo per forza usare il controller per prendere la richiesta purtroppo
                String https_redirect_url = SecurityHelpers.checkHttps(request);
                request.setAttribute("https-redirect", https_redirect_url);
                
                action_default(request, response);
            }
        } catch ( TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
    
}
