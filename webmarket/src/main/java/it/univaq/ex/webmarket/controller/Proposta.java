package it.univaq.ex.webmarket.controller;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.univaq.ex.webmarket.data.DAO.impl.WebmarketDataLayer;
import it.univaq.ex.webmarket.data.model.PropostaAcquisto;
import it.univaq.ex.webmarket.data.model.RichiestaAcquisto;
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

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException {
        try {
            action_default(request, response);
            }
            catch(Exception e){

            }
        } 
    }
    

