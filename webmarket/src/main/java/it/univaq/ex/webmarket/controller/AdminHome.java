package it.univaq.ex.webmarket.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.univaq.framework.result.TemplateResult;
import it.univaq.ex.webmarket.data.DAO.impl.WebmarketDataLayer;
import it.univaq.ex.webmarket.data.model.Ruolo;
import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.framework.data.DataException;
import it.univaq.framework.result.TemplateManagerException;
import it.univaq.framework.security.SecurityHelpers;

public class AdminHome extends WebmarketBaseController{
    
    private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateManagerException {
        TemplateResult result = new TemplateResult(getServletContext());
        request.setAttribute("referrer", request.getParameter("referrer"));
        result.activate("adminHome.ftl.html", request, response);
    }
    private void action_register(HttpServletRequest request,HttpServletResponse response){
        
            
            
                 try {
                    Utente u = ((WebmarketDataLayer) request.getAttribute("datalayer")).getUtenteDAO().createUtente();
                    u.setEmail(request.getParameter("email"));
                    u.setPassword(SecurityHelpers.getPasswordHashPBKDF2(request.getParameter("password")));
                    u.setNome(request.getParameter("nome"));
                    u.setCognome(request.getParameter("cognome"));
                    u.setRuolo(request.getParameter("ruolo"));
                    
                    ((WebmarketDataLayer) request.getAttribute("datalayer")).getUtenteDAO().storeUtente(u);
                           } catch (DataException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
                            
                    }
    }
    private void action_logout(HttpServletRequest request,HttpServletResponse response){

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException
     */

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            if (request.getParameter("register") != null) {
                action_register(request, response);
            }
            if(request.getParameter("logout") != null){
                action_logout(request,response);
            }
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
  
