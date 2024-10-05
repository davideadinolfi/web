package it.univaq.ex.webmarket.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import antlr.collections.List;
import it.univaq.ex.webmarket.data.DAO.CaratteristicaDAO;
import it.univaq.ex.webmarket.data.DAO.impl.CaratteristicaDAOmysql;
import it.univaq.ex.webmarket.data.DAO.impl.WebmarketDataLayer;
import it.univaq.ex.webmarket.data.model.Caratteristica;
import it.univaq.ex.webmarket.data.model.impl.proxy.CaratteristicaProxy;
import it.univaq.framework.data.DataException;
import it.univaq.framework.result.TemplateManagerException;
import it.univaq.framework.result.TemplateResult;
import it.univaq.framework.security.SecurityHelpers;

public class CreaRichiesta extends WebmarketBaseController{


    private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateManagerException {
        
        TemplateResult result = new TemplateResult(getServletContext());
        request.setAttribute("referrer", request.getParameter("referrer"));
        request.setAttribute("categoria",request.getParameter("categoria"));
        request.getSession().setAttribute("categoria", request.getAttribute("categoria"));
        try {
            ArrayList<Caratteristica> l= (ArrayList<Caratteristica>) ((WebmarketDataLayer) request.getAttribute("datalayer")).getCaratteristicaDAO().
            getCaratteristicheByCategoria(Integer.parseInt(request.getAttribute("categoria").toString()));
            request.setAttribute("caratteristiche", l);
            request.getSession().setAttribute("caratteristiche", l);
            
        } catch (NumberFormatException | DataException e) {
            handleError(e, request, response);
        }
        
        result.activate("creaRichiesta.ftl.html", request, response);
    }


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        try{
            String https_redirect_url = SecurityHelpers.checkHttps(request);
            request.setAttribute("https-redirect", https_redirect_url);
            action_default(request, response);
        }
        catch(Exception e){
            handleError(e, request, response);
        }
    }
    
}
