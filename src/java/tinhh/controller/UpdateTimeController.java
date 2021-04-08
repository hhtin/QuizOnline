/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tinhh.quiz.QuizDTO;

/**
 *
 * @author DELL
 */
public class UpdateTimeController extends HttpServlet {

    private static final String SUCCESS = "takeQuiz.jsp";
    private static final String ERROR = "error.jsp";
    private static final Logger LOGGER = Logger.getLogger(UpdateTimeController.class);
    
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
            Date timeStart = (Date) session.getAttribute("startQuizTime");
            
            if (timeStart == null) {
                Date date = new Date();
                timeStart = date;
            }
            Date curTime = new Date();
            long diff = curTime.getTime() - timeStart.getTime();
            
            long diffSeconds = diff / 1000;
            String strSecond=diffSeconds+"";
            int second=Integer.parseInt(strSecond)%60;
            long diffMinutes = diff / (60 * 1000);
            String strMinute=diffMinutes+"";
            int minutes=Integer.parseInt(strMinute);
            QuizDTO quizDTO=(QuizDTO) session.getAttribute("QUIZINFO");
            int minuteRemaining=0;
            int secondRemaining=0;
            if(second!=0){
                minuteRemaining=quizDTO.getLimitTime()-(minutes+1);
                secondRemaining=60-second;
            } else{
                minuteRemaining=quizDTO.getLimitTime()-minutes;
                secondRemaining=0;
            }
            session.setAttribute("MINUTES", minuteRemaining);
            session.setAttribute("SECOND", secondRemaining);
            session.setAttribute("startQuizTime", timeStart);

            url = SUCCESS;

        } catch (Exception e) {
            log("Error at UpdateTimeController: " + e.getMessage());
            LOGGER.error("Error at UpdateTimeController: " + e.getMessage());
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
