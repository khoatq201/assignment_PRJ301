/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.email.MailDAO;
import sample.product.Clothes;
import sample.shopping.Cart;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SendMailController", urlPatterns = {"/SendMailController"})
public class SendMailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String mail = request.getParameter("mail");
            String sub = "Thank You for Your CHOOSING!";
            String user = "trandat31.m@gmail.com";
            String password = "vlsy vpfh srcg yzjy";
            Cart cart = (Cart) session.getAttribute("CART_MAIL");
            StringBuilder htmlMessageBuilder = new StringBuilder();
            htmlMessageBuilder.append("<!DOCTYPE html>\n")
                .append("<html lang=\"vi\">\n")
                .append("<head>\n")
                .append("    <meta charset=\"UTF-8\">\n")
                .append("    <style>\n")
                .append("        table { border-collapse: collapse; width: 100%; }\n")
                .append("        th, td { border: 1px solid black; padding: 8px; text-align: left; }\n")
                .append("        th { background-color: #f2f2f2; }\n")
                .append("    </style>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("    <h1 style=\"color: green;\">Your shopping</h1>\n")
                .append("    <table>\n")
                .append("        <thead>\n")
                .append("            <tr>\n")
                .append("                <th>ID</th>\n")
                .append("                <th>Name</th>\n")
                .append("                <th>Price</th>\n")
                .append("                <th>Quantity</th>\n")
                .append("                <th>Total</th>\n")
                .append("            </tr>\n")
                .append("        </thead>\n")
                .append("        <tbody>\n");

            double grandTotal = 0;
            for (Clothes clo : cart.getCart().values()) {
                double itemTotal = clo.getPrice() * clo.getQuantity();
                grandTotal += itemTotal;
                htmlMessageBuilder.append("            <tr>\n")
                    .append("                <td>").append(clo.getId()).append("</td>\n")
                    .append("                <td>").append(clo.getName()).append("</td>\n")
                    .append("                <td>").append(String.format("%.2f", clo.getPrice())).append("$</td>\n")
                    .append("                <td>").append(clo.getQuantity()).append("</td>\n")
                    .append("                <td>").append(String.format("%.2f", itemTotal)).append("$</td>\n")
                    .append("            </tr>\n");
            }

            htmlMessageBuilder.append("            <tr>\n")
                .append("                <td colspan=\"4\" style=\"text-align: right;\"><strong>Grand Total:</strong></td>\n")
                .append("                <td><strong>").append(String.format("%.2f", grandTotal)).append("</strong>$</td>\n")
                .append("            </tr>\n")
                .append("        </tbody>\n")
                .append("    </table>\n")
                .append("</body>\n")
                .append("</html>");
            String htmlMessage = htmlMessageBuilder.toString();         
            MailDAO.send(mail, sub, htmlMessage, user, password);
            url = SUCCESS;
            session.setAttribute("CART_MAIL", null);
            request.setAttribute("MESSAGE_MAIL", "Message sent successfully!");
          
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
