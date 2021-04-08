/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import tinhh.register.RegisterDAO;
import tinhh.register.RegisterDTO;
import tinhh.register.RegisterErrorObject;

/**
 *
 * @author DELL
 */
public class RegisterController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "login.jsp";
    private static final String INVALID = "register.jsp";
    private static final Logger LOGGER = Logger.getLogger(RegisterController.class);
   
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
            RegisterDTO dto=new RegisterDTO();
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String rePassword = request.getParameter("txtRePassword");
            String fullName = request.getParameter("txtFullName");
            String phoneNumber = request.getParameter("txtPhoneNumber");
            String sex = request.getParameter("txtSex");
            boolean isSex = true;
            boolean valid = true;
            if (sex.equals("Man")) {
                isSex = true;
            } else {
                isSex = false;
            }
            RegisterErrorObject registerErrorObject = new RegisterErrorObject();
            RegisterDAO dao = new RegisterDAO();
            if (dao.checkExistAccount(email)) {
                registerErrorObject.setEmailError("Email already exist");
                valid = false;
            }
            if (!password.equals(rePassword)) {
                registerErrorObject.setPasswordError("Password and RePassword are not same");
                valid = false;
            }
            if (!phoneNumber.matches("[0-9]{10,11}")) {
                registerErrorObject.setPhoneNumberError("You need give me 10 - 11 number");
                valid = false;
            }
            if (valid == true) {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] encodedhash = digest.digest(
                        password.getBytes(StandardCharsets.UTF_8));
                password=dto.bytesToHex(encodedhash);

                if (dao.registerAccount(email, password, fullName, isSex, phoneNumber, "student", true)) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESSMSG", "Register Success");
                } else {
                    url = INVALID;
                    request.setAttribute("FALSEMSG", "Register False");
                }
            } else {
                request.setAttribute("INVALID", registerErrorObject);
                url = INVALID;
            }

        } catch (Exception e) {
            log("Error at RegisterController: " + e.getMessage());
            LOGGER.error("Error at RegisterController: " + e.getMessage());
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
