package it.univaq.ex.webmarket.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.univaq.ex.webmarket.data.DAO.impl.WebmarketDataLayer;
import it.univaq.ex.webmarket.data.model.Caratteristica;
import it.univaq.ex.webmarket.data.model.Categoria;
import it.univaq.ex.webmarket.data.model.PropostaAcquisto;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.StatoRichiesta;
import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.framework.data.DataException;
import it.univaq.framework.result.TemplateManagerException;
import it.univaq.framework.result.TemplateResult;
import it.univaq.framework.security.SecurityHelpers;

public class Home extends WebmarketBaseController {

    private void loadListaRichieste(HttpServletRequest request,HttpServletResponse response) {
        
    
        try {   
                ArrayList <RichiestaAcquisto> list=(ArrayList<RichiestaAcquisto>) ((WebmarketDataLayer) request.getAttribute("datalayer")).getRichiestaAcquistoDAO().getRichiesteAcquistoByOrdinante(
                ((Utente) request.getSession().getAttribute("user")).getKey());
                request.setAttribute("listaRichieste", list);
        
        } catch (DataException e) {
            //TODO
        }
        
    }

    private void loadListaProposte(HttpServletRequest request,HttpServletResponse response) {
        
    
        try {   
                ArrayList <PropostaAcquisto> list=(ArrayList<PropostaAcquisto>) ((WebmarketDataLayer) request.getAttribute("datalayer")).getPropostaAcquistoDAO().getProposteAcquistoByOrdinante(
                (Utente) request.getSession().getAttribute("user"));
                request.setAttribute("listaProposte", list);
                
        } catch (DataException e) {
            //TODO
        }
        
    }

    private void action_default(HttpServletRequest request, HttpServletResponse response)throws IOException,TemplateManagerException{
        TemplateResult result = new TemplateResult(getServletContext());
        request.setAttribute("referrer", request.getParameter("referrer"));
        loadListaProposte(request, response);
        loadListaRichieste(request, response);
        result.activate("home.ftl.html", request, response);
        result.activate("listaRichieste.ftl.html", request, response);
        result.activate("listaProposte.ftl.html", request, response);
        
        
    }

    private void action_richiedi(HttpServletRequest request,HttpServletResponse response)throws IOException,TemplateManagerException{
        if(request.getSession().getAttribute("caratteristiche") != null){
        ArrayList<Caratteristica> l = (ArrayList<Caratteristica>) request.getSession().getAttribute("caratteristiche");
        RichiestaAcquisto r=((WebmarketDataLayer) request.getAttribute("datalayer") ).getRichiestaAcquistoDAO().createRichiestaAcquisto();
        r.setOrdinante((Utente) request.getSession().getAttribute("user"));
        
        try {
            r.setCategoria((Categoria) ((WebmarketDataLayer) request.getAttribute("datalayer")).getCategoriaDAO().getCategoria(Integer.parseInt((String)request.getSession().getAttribute("categoria"))));
        } catch (NumberFormatException | DataException e) {
            //TODO
        }
        r.setDataRichiesta(LocalDateTime.now());
       
        r.setNote((String)request.getParameter("note"));
        r.setStatoRichiesta(StatoRichiesta.ATTESA_TECNICO);
        try {
            ((WebmarketDataLayer) request.getAttribute("datalayer")).getRichiestaAcquistoDAO().storeRichiestaAcquisto(r);
        } catch (DataException | IOException e) {
            //TODO
        }   
        l.stream().forEach(item ->{
            try {
                ((WebmarketDataLayer) request.getAttribute("datalayer")).getRichiestaCaratteristicaDAO().storeCaratteristicaRichiesta(item,r.getKey(),request.getParameter(item.getNome()));
            } catch (Exception e) {
                //TODO
            }
        });
        l.clear(); 
        request.getSession().removeAttribute("caratteristiche");
        
        }
    }
    

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            
            if(request.getParameter("richiedi")!=null)
                action_richiedi(request,response);
            if(request.getParameter(null) == null) {
                String https_redirect_url = SecurityHelpers.checkHttps(request);
                request.setAttribute("https-redirect", https_redirect_url);
                action_default(request, response);
            }
        } catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
    
}
