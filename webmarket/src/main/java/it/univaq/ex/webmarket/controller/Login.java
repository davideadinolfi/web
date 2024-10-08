package it.univaq.ex.webmarket.controller;

import it.univaq.ex.webmarket.data.DAO.impl.WebmarketDataLayer;
import it.univaq.ex.webmarket.data.model.Ruolo;
import it.univaq.ex.webmarket.data.model.Utente;
import it.univaq.framework.data.DataException;
import it.univaq.framework.result.TemplateManagerException;
import it.univaq.framework.result.TemplateResult;
import it.univaq.framework.security.SecurityHelpers;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Ingegneria del Web
 * @version
 */
public class Login extends WebmarketBaseController {

    private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateManagerException {
        TemplateResult result = new TemplateResult(getServletContext());
        request.setAttribute("referrer", request.getParameter("referrer"));
        result.activate("login.ftl.html", request, response);

//        //esempio di creazione utente
//        //create user example
//        try {
//            User u = ((NewspaperDataLayer) request.getAttribute("datalayer")).getUserDAO().createUser();
//            u.setUsername("a");
//            u.setPassword(SecurityHelpers.getPasswordHashPBKDF2("p"));
//            ((NewspaperDataLayer) request.getAttribute("datalayer")).getUserDAO().storeUser(u);
//        } catch (DataException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
//            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    //nota: usente di default nel database: nome a, password p
    //note: default user in the database: name: a, password p
    private void action_login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("u");
        String password = request.getParameter("p");
            if (!username.isEmpty() && !password.isEmpty()) {
           
                Utente u;
                try {
                    u = ((WebmarketDataLayer) request.getAttribute("datalayer")).getUtenteDAO().getUtenteByEmail(username);
                
                
                    //TOGLI I COMMENTI DAL CODICE CINGHIALE

                if (u != null    &&    SecurityHelpers.checkPasswordHashPBKDF2(password, u.getPassword())  ) {
                    
                    //se la validazione ha successo
                    //if the identity validation succeeds
                    SecurityHelpers.createSession(request, username, u.getKey(), u.getRuolo());
                    request.getSession().setAttribute("user", u);
                    request.getSession().setAttribute("ruolo", u.getRuolo().getValue());
                    //se è stato trasmesso un URL di origine, torniamo a quell'indirizzo
                    //if an origin URL has been transmitted, return to it
                    
                    /* 
                    if (request.getParameter("referrer") != null) {
                        response.sendRedirect(request.getParameter("referrer"));
                    } else {
                        response.sendRedirect("issues");
                    }*/

                    if(u.getRuolo() == Ruolo.ADMIN)
                        response.sendRedirect("adminHome");
                    if(u.getRuolo() == Ruolo.UTENTE)
                        response.sendRedirect("home");
                    if(u.getRuolo() == Ruolo.TECNICO)
                        response.sendRedirect("tecnicoHome");
                    return;
                }
              
            } catch (NoSuchAlgorithmException | InvalidKeySpecException | DataException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //se la validazione fallisce...
        //if the validation fails...
        handleError("Login failed", request, response);
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            if(request.getParameter("logout") != null){
                SecurityHelpers.disposeSession(request);
        
            }
            if (request.getParameter("login") != null) {
                action_login(request, response);
            } else {
                String https_redirect_url = SecurityHelpers.checkHttps(request);
                request.setAttribute("https-redirect", https_redirect_url);
                action_default(request, response);
            }
        } catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}