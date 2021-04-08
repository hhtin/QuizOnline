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
import tinhh.question.QuestionDAO;
import tinhh.question.QuestionDTO;
import tinhh.subject.SubjectDTO;

/**
 *
 * @author DELL
 */
public class PagingQuestionController extends HttpServlet {

    private static final String SUCCESS = "viewQuestion.jsp";
    private static final String ERROR = "error.jsp";
    private static final Logger LOGGER = Logger.getLogger(PagingQuestionController.class);
    
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

        try {
            HttpSession session = request.getSession();
            LoginDTO dto = (LoginDTO) session.getAttribute("ACCOUNT");
            if (dto == null) {
                dto = new LoginDTO();
                dto.setRole("Guess");
            }
            String index = request.getParameter("index");
            if (index == null) {
                index = "1";
            }
            int indexPage = Integer.parseInt(index);
            QuestionDAO dao = new QuestionDAO();
            String questionContent = request.getParameter("searchQuestionContent");
            String subjectId = request.getParameter("txtSubject");
            String strStatus = request.getParameter("txtStatus");
            ArrayList<SubjectDTO> sbjDTOs = (ArrayList<SubjectDTO>) session.getAttribute("LISTSUBJECT");
            for (SubjectDTO sbjDTO : sbjDTOs) {
                if (sbjDTO.getSubjectName().equals(subjectId)) {
                    subjectId = sbjDTO.getSubjectId();
                }
            }

            if (questionContent == null) {
                questionContent = "%%";
            } else {
                questionContent = "%" + questionContent + "%";
            }
            if (subjectId == null || subjectId.isEmpty()) {
                subjectId = "%%";
            } else if (subjectId.equals("Show all Subject")) {
                subjectId = "%%";
            }
            ArrayList<QuestionDTO> dtos = new ArrayList<>();
            int numberPage = 0;
            boolean status = true;
            if (strStatus == null || strStatus.isEmpty()) {
                dtos = dao.getPagingAllStatus(indexPage, questionContent, subjectId);
                numberPage = dao.getNumberPageAllStatus(questionContent, subjectId);
            } else if (strStatus.equals("Show All")) {
                dtos = dao.getPagingAllStatus(indexPage, questionContent, subjectId);
                numberPage = dao.getNumberPageAllStatus(questionContent, subjectId);
            } else if (strStatus.equals("Deleted")) {
                status = false;
                dtos = dao.getPaging(indexPage, questionContent, subjectId, status);
                numberPage = dao.getNumberPage(questionContent, subjectId, status);
            } else if (strStatus.equals("Normally")) {
                status = true;
                dtos = dao.getPaging(indexPage, questionContent, subjectId, status);
                numberPage = dao.getNumberPage(questionContent, subjectId, status);
            }
            request.setAttribute("searchQuestionContent", questionContent);
            request.setAttribute("txtSubject", subjectId);
            request.setAttribute("txtStatus", strStatus);
            session.setAttribute("QUESTIONLIST", dtos);
            session.setAttribute("NUMBERPAGE", numberPage);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at PagingQuestionController: " + e.getMessage());
            LOGGER.error("Error at PagingQuestionController: " + e.getMessage());
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
