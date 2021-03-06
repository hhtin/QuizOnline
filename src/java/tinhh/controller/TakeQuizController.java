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
import tinhh.question.QuestionDTO;
import tinhh.quiz.QuizDAO;
import tinhh.quiz.QuizDTO;

/**
 *
 * @author DELL
 */
public class TakeQuizController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "viewQuizInfo.jsp";
    private static final Logger LOGGER = Logger.getLogger(TakeQuizController.class);
    
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
        QuizDAO quizDAO=new QuizDAO();
        QuestionDAO quesDAO=new QuestionDAO();
        try {
            String quizId=request.getParameter("txtChooseQuiz");
            QuizDTO quizDTO=quizDAO.getQuizInfo(quizId);
            int numOfQues=quizDTO.getNumberQuestion()*5-3;
            ArrayList<QuestionDTO> quesDTOs= quesDAO.getRandomListQuestion(numOfQues,quizDTO.getSubjectId());
            session.setAttribute("startQuizTime", null);
            session.setAttribute("QUIZINFO", quizDTO);
            session.setAttribute("LISTQUESTIONTAKEQUIZ", quesDTOs);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at TakeQuizController: " + e.getMessage());
            LOGGER.error("Error at TakeQuizController: " + e.getMessage());
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
