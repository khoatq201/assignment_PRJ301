/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Cart;
import sample.product.Order;
import sample.product.OrderDetail;
import sample.product.Product;
import sample.product.ProductDAO;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String SUCCESS = "viewCart.jsp";
    private static final String ERROR = "viewCart.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        List<String> message = new ArrayList<>();
        double total = 0;
        try  {
            /* TODO output your page here. You may use following sample code. */
            boolean check1 = false;
            boolean check2 = false;
            boolean check3 = false;
            ProductDAO pDao = new ProductDAO();
            UserDAO userDAO = new UserDAO();
            int i=0;
            ArrayList<Product> pList = (ArrayList<Product>) pDao.getListProduct();
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            for(Product product : pList){
                if(cart.getCart().containsKey(product.getProductID())){
                    if(cart.getCart().get(product.getProductID()).getQuantity()> product.getQuantity()){
                        message.add("Product " +product.getName()+" is not enough quantity");
                        i++;
              
                    }
                }
            }
            request.setAttribute("MESSAGE_CHECKOUT", message);
            if(i==0){
                Order order = new Order("O"+String.valueOf(pDao.getListOrder().size()+1),user.getUserID(),userDAO.getCurrentDateTime(),total,1);
                pDao.writeOrder(order);
                for(Product product : pList){
                    String productID = product.getProductID();
                    if(cart.getCart().containsKey(productID)){
                        if(cart.getCart().get(productID).getQuantity()<= product.getQuantity()){
                            total = total+ cart.getCart().get(productID).getQuantity()*cart.getCart().get(productID).getPrice();
                            OrderDetail orderDetail = new OrderDetail(order.getOrderID(),product.getProductID(),product.getPrice(),cart.getCart().get(productID).getQuantity(),1);
                            check1 = pDao.updateProduct(product.getProductID(), product.getQuantity()-cart.getCart().get(productID).getQuantity());
                            check2 = pDao.writeOrderDetail(orderDetail);
                        }
                    }
                }
                check3 = pDao.updateOrder(order.getOrderID(), total, order.getDate());
               
                if(check1&&check2&&check3){
                    session.setAttribute("CART_MAIL", cart);
                    session.setAttribute("CART", null);
                    request.setAttribute("MESSAGE", "SUCSESSFULLY!");
                    url = SUCCESS;
                } else{
                    request.setAttribute("ERROR", "Unknown Error!");
                }
            }
        } catch(Exception e){
            log("Error at CheckoutController: "+e.toString());
        } finally{
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
