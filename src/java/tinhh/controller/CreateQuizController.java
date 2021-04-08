/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tinhh.login.LoginDTO;
import tinhh.quiz.QuizDAO;
import tinhh.quiz.QuizDTO;
import tinhh.quiz.QuizErrorObject;
import tinhh.subject.SubjectDTO;

/**
 *
 * @author DELL
 */
public class CreateQuizController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "admin.jsp";
    private static final String INVALID = "createQuiz.jsp";
        private static final Logger LOGGER = Logger.getLogger(CreateQuizController.class);
    

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
            QuizDTO quizDTO = new QuizDTO();
            QuizDAO quizDAO = new QuizDAO();
            String quizName = request.getParameter("txtQuizName");
            int limitTime = Integer.parseInt(request.getParameter("txtLimitTime"));
            String subjectName = request.getParameter("txtSubject");
            int numberOfQuestion = Integer.parseInt(request.getParameter("txtNumberOfQuestion"));
            Date timeStart = null;
            Date timeEnd = null;
            String strTimeStart = request.getParameter("txtTimeStart");
            String strTimeEnd = request.getParameter("txtTimeEnd");
            String subjectId = "";
            QuizErrorObject errorObj = new QuizErrorObject();
            boolean valid = true;
            ArrayList<SubjectDTO> sbjDTO = (ArrayList<SubjectDTO>) session.getAttribute("LISTSUBJECT");

            for (SubjectDTO subjectDTO : sbjDTO) {
                if (subjectDTO.getSubjectName().equals(subjectName)) {
                    subjectId = subjectDTO.getSubjectId();
                }
            }
            if (subjectId.equals("") || subjectId.isEmpty()) {
                errorObj.setSubjectError("You need to choose subject");
                valid = false;
            }
            if (strTimeStart == null || strTimeStart.isEmpty()) {
                errorObj.setTimeStartError("You need to choose timeStart");
                valid = false;
            } else {
                timeStart = quizDTO.formatDateValue(strTimeStart);
            }
            if (strTimeEnd == null || strTimeEnd.isEmpty()) {
                errorObj.setTimeEndError("You need to choose timeEnd");
                valid = false;
            } else {
                timeEnd = quizDTO.formatDateValue(strTimeEnd);
            }

            if (timeEnd != null && timeStart != null) {
                if (!timeEnd.after(timeStart)) {
                    errorObj.setTimeEndError("Time End Need After TimeStart");
                    valid = false;
                }
            }

            if (valid) {
                String quizId = quizDAO.getLastQuizId();
                if (quizId == null || quizId.isEmpty()) {
                    quizId = account.getEmail() + "-1";
                } else {
                    String[] spQuizId = quizId.split("-");
                    int number = Integer.parseInt(spQuizId[1]) + 1;
                    quizId = spQuizId[0] + "-" + number;
                }
                quizDAO.createQuiz(quizId, quizName, limitTime, account.getEmail(), subjectId, numberOfQuestion, timeStart, timeEnd, true);
                request.setAttribute("SUCCESSMSG", "Create Quiz Success");
                url = SUCCESS;
            } else {
                request.setAttribute("INVALID", errorObj);
                url = INVALID;
            }

        } catch (Exception e) {
            log("Error at CreateQuizController: " + e.getMessage());
            LOGGER.error("Error at CreateQuizController: " + e.getMessage());
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
