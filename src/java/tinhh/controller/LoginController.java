/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tinhh.login.LoginDAO;
import tinhh.login.LoginDTO;
import tinhh.login.LoginErrorObject;
import tinhh.subject.SubjectDAO;
import tinhh.subject.SubjectDTO;

/**
 *
 * @author DELL
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String STUDENT = "student.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String INVALID = "login.jsp";
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
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
        SubjectDAO sbjDAO = new SubjectDAO();
        try {

            String username = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");

            boolean valid = true;
            LoginErrorObject loginErrorObject = new LoginErrorObject();
            if (username.length() == 0) {
                loginErrorObject.setUsernameError("Email can't be blank");
                valid = false;
            }
            if (password.length() == 0) {
                loginErrorObject.setPasswordError("Password can't be blank");
                valid = false;
            }
            if (valid == true) {
                LoginDAO dao = new LoginDAO();
                LoginDTO dto = new LoginDTO();
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] encodedhash = digest.digest(
                        password.getBytes(StandardCharsets.UTF_8));
                password = dto.bytesToHex(encodedhash);
                dto = dao.checkLogin(username, password);
                if (dto.getRole().equals("invalid")) {
                    url = INVALID;
                    loginErrorObject.setUsernameError("Email or password is incorrect");
                    loginErrorObject.setPasswordError("");
                    request.setAttribute("INVALID", loginErrorObject);
                    url = INVALID;
                } else if (dto.getRole().equals("student")) {
                    ArrayList<SubjectDTO> sbjDTOs = sbjDAO.getSubjectList();
                    session.setAttribute("LISTSUBJECT", sbjDTOs);
                    session.setAttribute("ACCOUNT", dto);
                    url = STUDENT;
                } else if (dto.getRole().equals("admin")) {
                    ArrayList<SubjectDTO> sbjDTOs = sbjDAO.getSubjectList();
                    session.setAttribute("LISTSUBJECT", sbjDTOs);
                    session.setAttribute("ACCOUNT", dto);
                    url = ADMIN;
                } else {
                    url = LOGIN;
                }
            } else {
                request.setAttribute("INVALID", loginErrorObject);
                url = INVALID;
            }

        } catch (Exception e) {
            log("Error at LoginController: " + e.getMessage());
            LOGGER.error("Error at LoginController: " + e.getMessage());
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
