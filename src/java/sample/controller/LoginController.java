/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "login.jsp";
    private static final String US = "US";
    private static final String AD = "AD";
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String USER_PAGE= "user.jsp";
    private static final String ERROR_MESSAGE = "Your role is not support yet";
    private static final String INVALID_MESSAGE = "Incorrect userID or password";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try  {
            /* TODO output your page here. You may use following sample code. */
           String userID = request.getParameter("userID");
           String password = request.getParameter("password");
           UserDAO dao = new UserDAO();
           UserDTO loginUser = dao.checkLogin(userID,password);
//xac thuc o day
            if(loginUser != null){
                //phan quyen o day ne
                String roleID = loginUser.getRoleID();
                HttpSession session = request.getSession();
                String fullName = loginUser.getFullName();
                session.setAttribute("LOGIN_USER", loginUser);
                if(AD.equals(roleID)){
                    url = ADMIN_PAGE;
                    
                }else if(US.equals(roleID)){
                    url = USER_PAGE;
                }else {
                     request.setAttribute("ERROR", ERROR_MESSAGE);
                }
                }else {
                        request.setAttribute( "ERROR", INVALID_MESSAGE);
                }
        } catch ( Exception e){
            log("Error at LoginController: "+e.toString());
        }finally{
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
