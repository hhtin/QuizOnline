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
import tinhh.question.QuestionDTO;
import tinhh.quiz.QuizDTO;
import tinhh.quizresult.QuizResultDAO;
import tinhh.quizresult.QuizResultDTO;

/**
 *
 * @author DELL
 */
public class FinishQuizController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "resultQuiz.jsp";
    private static final Logger LOGGER = Logger.getLogger(FinishQuizController.class);
    
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
        QuizResultDAO quizResultDAO=new QuizResultDAO();
        try {
            LoginDTO account=(LoginDTO)session.getAttribute("ACCOUNT");
            ArrayList<QuestionDTO> quesDTOs = (ArrayList<QuestionDTO>) session.getAttribute("LISTQUESTIONTAKEQUIZ");
            Date timeStart = (Date) session.getAttribute("startQuizTime");
            Date timeEnd =new Date();
            QuizDTO quizDTO = (QuizDTO) session.getAttribute("QUIZINFO");
            int numOfCorrectAns = 0;
            for (int i = 0; i < quesDTOs.size(); i++) {
                String answer = request.getParameter("answer" + i);
                if(answer==null){
                    answer="E";
                }
                if (answer.equals(quesDTOs.get(i).getCorrectAns())) {
                    numOfCorrectAns++;
                }
            }
            int score = numOfCorrectAns* 10 / quizDTO.getNumberQuestion() ;
            String lastId =quizResultDAO.getLastIdQuizResult(account.getEmail());
            String[] spLastId=lastId.split("-");
            int number=Integer.parseInt(spLastId[1])+1;
            String takeQuizId=account.getEmail()+"-"+number;
            QuizResultDTO quizResultDTO=new QuizResultDTO(takeQuizId, account.getEmail(), quizDTO.getQuizId(),quizDTO.getSubjectId(),quizDTO.getSubjectName(),numOfCorrectAns, score, timeStart, timeEnd);
            quizResultDTO.setQuizName(quizDTO.getQuizName());
            if(quizResultDAO.insertQuizResult(takeQuizId, account.getEmail(), quizDTO.getQuizId(), numOfCorrectAns, score, timeStart, timeEnd, quizDTO.getSubjectId())){
                url = SUCCESS;
                session.setAttribute("startQuizTime", null);
                session.setAttribute("SCOREOFQUIZ", quizResultDTO);
            }else{
                url=ERROR;
            }


            

        } catch (Exception e) {
            log("Error at FinishQuizController: " + e.getMessage());
            LOGGER.error("Error at FinishQuizController: " + e.getMessage());
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
