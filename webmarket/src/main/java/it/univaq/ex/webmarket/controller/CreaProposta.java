package it.univaq.ex.webmarket.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.univaq.ex.webmarket.data.DAO.impl.WebmarketDataLayer;
import it.univaq.ex.webmarket.data.model.Caratteristica;
import it.univaq.ex.webmarket.data.model.PropostaAcquisto;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.StatoProposta;
import it.univaq.ex.webmarket.data.model.StatoRichiesta;
import it.univaq.framework.data.DataException;
import it.univaq.framework.result.TemplateManagerException;
import it.univaq.framework.result.TemplateResult;
import it.univaq.framework.security.SecurityHelpers;

public class CreaProposta extends WebmarketBaseController{

   private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateManagerException, NumberFormatException, DataException {
        
        TemplateResult result = new TemplateResult(getServletContext());
        request.setAttribute("referrer", request.getParameter("referrer"));
        RichiestaAcquisto r=((WebmarketDataLayer) request.getAttribute("datalayer")).getRichiestaAcquistoDAO().getRichiestaAcquisto(Integer.parseInt(request.getParameter("richiesta")));
            request.setAttribute("richiesta", r);

        
        result.activate("creaProposta.ftl.html", request, response);
    }

    private void action_creaProposta(HttpServletRequest request,HttpServletResponse response){
        request.setAttribute("referrer", request.getParameter("referrer"));
        WebmarketDataLayer dataLayer=(WebmarketDataLayer) request.getAttribute("datalayer");
        try {
            RichiestaAcquisto r=dataLayer.getRichiestaAcquistoDAO().getRichiestaAcquisto(Integer.parseInt(request.getParameter("richiesta")));
            PropostaAcquisto p=dataLayer.getPropostaAcquistoDAO().createPropostaAcquisto();
            p.setRichiestaAcquisto(r);
            p.setNomeProdotto(request.getParameter("nome_prodotto"));
            p.setNomeProduttore(request.getParameter("nome_produttore"));
            p.setCodiceProdotto(request.getParameter("codice_prodotto"));
            p.setPrezzo(Double.parseDouble( request.getParameter("prezzo")));
            p.setUrl(request.getParameter("url"));
            p.setNote(request.getParameter("note"));
            p.setStatoProposta(StatoProposta.IN_ATTESA);
            p.setNotaRespinta(null);
            dataLayer.getPropostaAcquistoDAO().storePropostaAcquisto(p);
            r.setStatoRichiesta(StatoRichiesta.ATTESA_ORDINANTE);
            dataLayer.getRichiestaAcquistoDAO().storeRichiestaAcquisto(r);
            response.sendRedirect("tecnicoHome");
        } catch (NumberFormatException | DataException | IOException e) {
            handleError(e, request, response);
        }
        
        
    }


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        try{
            String https_redirect_url = SecurityHelpers.checkHttps(request);
            request.setAttribute("https-redirect", https_redirect_url);
            if(request.getParameter("crea_proposta")==null)
                action_default(request, response);
                
            else{
                action_creaProposta(request,response);
            }
        }
        catch(Exception ex){
            handleError(ex, request, response);
        }
    }
    
}
