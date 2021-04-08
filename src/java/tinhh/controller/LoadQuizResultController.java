/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tinhh.login.LoginDTO;
import tinhh.quizresult.QuizResultDAO;
import tinhh.quizresult.QuizResultDTO;

/**
 *
 * @author DELL
 */
public class LoadQuizResultController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "viewQuizResult.jsp";
    private static final Logger LOGGER = Logger.getLogger(LoadQuizResultController.class);
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        try {
            LoginDTO account = (LoginDTO) session.getAttribute("ACCOUNT");
            if (account == null) {
                account = new LoginDTO();
                account.setRole("Guess");
            }
            String index = request.getParameter("index");
            if (index == null) {
                index = "1";
            }
            int indexPage = Integer.parseInt(index);
            QuizResultDAO dao = new QuizResultDAO();
            String subjectName = request.getParameter("txtSubject");
            if (subjectName == null || subjectName.isEmpty()) {
                subjectName = "%%";
            } else if (subjectName.equals("Show all Subject")) {
                subjectName = "%%";
            }
            ArrayList<QuizResultDTO> dtos = new ArrayList<>();
            int numberPage = 0;
            dtos=dao.getPaging(indexPage, account.getEmail(), subjectName);
            numberPage=dao.getNumberPage(account.getEmail(), subjectName);
            request.setAttribute("txtSubject", subjectName);
            session.setAttribute("QUIZRESULTLIST", dtos);
            session.setAttribute("NUMBERPAGE", numberPage);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at LoadQuizResultController: " + e.getMessage());
            LOGGER.error("Error at LoadQuizResultController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
