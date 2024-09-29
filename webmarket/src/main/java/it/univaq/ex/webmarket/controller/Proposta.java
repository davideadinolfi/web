package it.univaq.ex.webmarket.controller;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.univaq.ex.webmarket.data.DAO.PropostaAcquistoDAO;
import it.univaq.ex.webmarket.data.DAO.impl.WebmarketDataLayer;
import it.univaq.ex.webmarket.data.model.PropostaAcquisto;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
import it.univaq.ex.webmarket.data.model.StatoProposta;
import it.univaq.ex.webmarket.data.model.impl.proxy.PropostaAcquistoProxy;
import it.univaq.framework.data.DataException;
import it.univaq.framework.result.TemplateManagerException;
import it.univaq.framework.result.TemplateResult;
import it.univaq.framework.security.SecurityHelpers;

public class Proposta extends WebmarketBaseController{

     private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateManagerException {
        TemplateResult result = new TemplateResult(getServletContext());
        request.setAttribute("referrer", request.getParameter("referrer"));
        try {
            FileWriter file=new FileWriter("D:/roba//uni/webmarket/webmarket/log.txt");
            file.write("ciaoo"+request.getParameter("proposta"));
            file.close();
            PropostaAcquisto p=((WebmarketDataLayer) request.getAttribute("datalayer")).getPropostaAcquistoDAO().getPropostaAcquisto(Integer.parseInt(request.getParameter("proposta")));
            request.setAttribute("proposta", p);
        } catch (NumberFormatException | DataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        result.activate("proposta.ftl.html", request, response);
     }

     private void action_ordina(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateManagerException, DataException {
        PropostaAcquistoDAO dao=((WebmarketDataLayer) request.getAttribute("datalayer")).getPropostaAcquistoDAO();
        PropostaAcquistoProxy p =(PropostaAcquistoProxy) dao.getPropostaAcquisto(Integer.parseInt(request.getParameter("proposta")));
        p.setStatoProposta(StatoProposta.ORDINATO);
        p.setModified(true);
        dao.storePropostaAcquisto(p);
        response.sendRedirect("tecnicoHome");
        

     }

     private void action_accetta(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateManagerException, DataException {
        PropostaAcquistoDAO dao=((WebmarketDataLayer) request.getAttribute("datalayer")).getPropostaAcquistoDAO();
        PropostaAcquistoProxy p =(PropostaAcquistoProxy)dao.getPropostaAcquisto(Integer.parseInt(request.getParameter("proposta")));
        p.setStatoProposta(StatoProposta.APPROVATO);
        p.setModified(true);
        dao.storePropostaAcquisto(p);
        response.sendRedirect("home");
     }
     private void action_rifiuta(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateManagerException, DataException {
        PropostaAcquistoDAO dao=((WebmarketDataLayer) request.getAttribute("datalayer")).getPropostaAcquistoDAO();
        PropostaAcquistoProxy p =(PropostaAcquistoProxy)dao.getPropostaAcquisto(Integer.parseInt(request.getParameter("proposta")));
        p.setStatoProposta(StatoProposta.RESPINTO);
        p.setModified(true);
        dao.storePropostaAcquisto(p);
        response.sendRedirect("home");
     }
     private void action_termina(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateManagerException, DataException {
        PropostaAcquistoDAO dao=((WebmarketDataLayer) request.getAttribute("datalayer")).getPropostaAcquistoDAO();
        PropostaAcquistoProxy p =(PropostaAcquistoProxy)dao.getPropostaAcquisto(Integer.parseInt(request.getParameter("proposta")));
        p.setStatoProposta(StatoProposta.TERMINATO);
        p.setModified(true);
        dao.storePropostaAcquisto(p);
        response.sendRedirect("home");
     }
     private void action_NF(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateManagerException, DataException {
        PropostaAcquistoDAO dao=((WebmarketDataLayer) request.getAttribute("datalayer")).getPropostaAcquistoDAO();
        PropostaAcquistoProxy p =(PropostaAcquistoProxy)dao.getPropostaAcquisto(Integer.parseInt(request.getParameter("proposta")));
        p.setStatoProposta(StatoProposta.NF);
        p.setModified(true);
        dao.storePropostaAcquisto(p);
        response.sendRedirect("home");
     }
     private void action_NC(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateManagerException, DataException {
        PropostaAcquistoDAO dao=((WebmarketDataLayer) request.getAttribute("datalayer")).getPropostaAcquistoDAO();
        PropostaAcquistoProxy p =(PropostaAcquistoProxy)dao.getPropostaAcquisto(Integer.parseInt(request.getParameter("proposta")));
        p.setStatoProposta(StatoProposta.NC);
        p.setModified(true);
        dao.storePropostaAcquisto(p);
        response.sendRedirect("home");
     }
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException {
        try {
            if(request.getParameter("ordina")!= null)
                action_ordina(request,response);
            if(request.getParameter("accettato")!=null)
                action_accetta(request,response);
            if(request.getParameter("rifiutato")!=null)
                action_rifiuta(request,response);
            if(request.getParameter("termina")!=null)
                action_termina(request,response);
            if(request.getParameter("NF")!=null)
                action_NF(request,response);
            if(request.getParameter("NC")!=null)
                action_NC(request,response);
            action_default(request, response);
            }
            catch(Exception e){

            }
        } 
    }
    

