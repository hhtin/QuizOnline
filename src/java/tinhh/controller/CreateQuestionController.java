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
import tinhh.question.QuestionDAO;
import tinhh.subject.SubjectDTO;

/**
 *
 * @author DELL
 */
public class CreateQuestionController extends HttpServlet {

    private static final String ERROR = "createQuiz.jsp";
    private static final String SUCCESS = "admin.jsp";
        private static final Logger LOGGER = Logger.getLogger(CreateQuestionController.class);
    

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
            QuestionDAO quesDAO = new QuestionDAO();
            String questionContent = request.getParameter("txtQuestionContent");
            String ansA = request.getParameter("txtAnsA");
            String ansB = request.getParameter("txtAnsB");
            String ansC = request.getParameter("txtAnsC");
            String ansD = request.getParameter("txtAnsD");
            String correcAns = request.getParameter("txtCorrectAnswer");
            String subjectName = request.getParameter("txtSubject");
            String subjectId = "";
            ArrayList<SubjectDTO> sbjDTO = (ArrayList<SubjectDTO>) session.getAttribute("LISTSUBJECT");
            for (SubjectDTO subjectDTO : sbjDTO) {
                if (subjectDTO.getSubjectName().equals(subjectName)) {
                    subjectId = subjectDTO.getSubjectId();
                }
            }
            String questionId = quesDAO.getLastQuestionId(subjectId);
            if (questionId == null || questionId.isEmpty()) {
                questionId = subjectId + "-1";
            } else {
                String[] spQuestionId = questionId.split("-");
                int number = Integer.parseInt(spQuestionId[2]) + 1;
                questionId = subjectId + "-" + number;
            }
            quesDAO.createQuestion(questionId, questionContent, ansA, ansB, ansC, ansD, correcAns, subjectId, true);
            request.setAttribute("SUCCESSMSG", "Create Question Success");
            url = SUCCESS;

        } catch (Exception e) {
            log("Error at CreateQuestionController: " + e.getMessage());
            LOGGER.error("Error at CreateQuestionController: " + e.getMessage());
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
