/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class ProductDAO {
    private static final String PRODUCT = "SELECT * from tblProduct";
    private static final String UPDATE_PRODUCT = "UPDATE tblProduct SET quantity=? WHERE productID =?";
    private static final String ORDER = "INSERT INTO tblOrder(orderID,userID,date,total,status) VALUES(?,?,CONVERT(DATETIME, ?, 105),?,?)";
    private static final String ORDER_DETAIL = "INSERT INTO tblOrderDetail(orderID,productID,price,quantity,status) VALUES(?,?,?,?,?)";
    private static final String ORDER_LIST = "SELECT * FROM tblOrder";
    private static final String ORDER_DETAIL_LIST = "SELECT * FROM tblOrderDetail";
    private static final String ORDER_UPDATE = "UPDATE tblOrder SET date=CONVERT(DATETIME, ?, 105),total=? WHERE orderID =?";
    private static final String ADD_PRODUCT = "INSERT INTO tblProduct(productID,name,price,quantity,status) VALUES(?,?,?,?,?)";
    private static final String UPDATE_PRODUCT1 = "UPDATE tblProduct SET name =?, price =?, quantity=?, status=?  WHERE productID =?";
    private static final String DELTE_PRODUCT = "DELETE tblProduct where productID = ?";
    private static final String ORDER_HISTORY = "SELECT od.orderID,od.productID,p.name,od.quantity,o.date\n" +
                                                "FROM tblOrderDetail AS od\n" +
                                                "INNER JOIN tblOrder AS o ON od.orderID = o.orderID\n" +
                                                "INNER JOIN tblProduct AS p ON od.productID = p.productID\n" +
                                                "WHERE o.userID = ?";
    public List<Product> getListProduct() throws SQLException{
        List<Product> list = new ArrayList<Product>();
        Connection conn = null;
        PreparedStatement ptm = null;
         ResultSet rs = null;
         try{
             conn = DBUtils.getConnection();
             ptm = conn.prepareStatement(PRODUCT);
             rs = ptm.executeQuery();
            while(rs.next()){
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    double price = Double.parseDouble(rs.getString("price"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    int status = Integer.parseInt(rs.getString("status"));
                    list.add(new Product(productID,name, price, quantity,status));
                }
         } catch (Exception e){
             
         } finally {
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
         }
         return list;
    }
    public boolean updateProduct(String id, int quantity) throws ClassNotFoundException, SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm  = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(UPDATE_PRODUCT);
            ptm.setString(1, String.valueOf(quantity));
            ptm.setString(2, id);
            check= ptm.executeUpdate()>0?true:false;
        } catch(Exception e){
            
        } finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }
     public boolean writeOrder(Order order) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm  = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(ORDER);
             String a = String.valueOf(order.getDate());
            ptm.setString(1, order.getOrderID());
            ptm.setString(2, order.getUserID());
            ptm.setString(3, order.getDate());
            ptm.setDouble(4, order.getTotal());
            ptm.setInt(5, order.getStatus());
            check= ptm.executeUpdate()>0?true:false;
        } catch(Exception e){
            
        } finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }
     public boolean writeOrderDetail(OrderDetail orderDetail) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm  = null;
        try{
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(ORDER_DETAIL);
            ptm.setString(1,  orderDetail.getOrderID());
            ptm.setString(2, orderDetail.getProductID());
            ptm.setDouble(3,orderDetail.getPrice());
            ptm.setInt(4, orderDetail.getQuantity());
            ptm.setInt(5, orderDetail.getStatus());
            check= ptm.executeUpdate()>0?true:false;
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }
      public List<Order> getListOrder() throws SQLException{
        ArrayList<Order> list = new ArrayList<Order>();
        Connection conn = null;
        PreparedStatement ptm = null;
         ResultSet rs = null;
         try{
             conn = DBUtils.getConnection();
             ptm = conn.prepareStatement(ORDER_LIST);
             rs = ptm.executeQuery();
            while(rs.next()){
                    String orderID = rs.getString("orderID");
                    String userID = rs.getString("userID");
                    String date =rs.getString("date");
                    double total = Double.parseDouble(rs.getString("total"));
                    int status = Integer.parseInt(rs.getString("status"));
                    list.add(new Order(orderID,userID, date, total,status));
                }
         } catch (Exception e){
             list = null;
         } finally {
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
         }
         return list;
    }
      public List<OrderDetail> getListOrderDetail() throws SQLException{
        List<OrderDetail> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
         ResultSet rs = null;
         try{
             conn = DBUtils.getConnection();
             ptm = conn.prepareStatement(ORDER_DETAIL_LIST);
             rs = ptm.executeQuery();
            while(rs.next()){
                    String orderID = rs.getString("orderID");
                    String productID = rs.getString("productID");
                    double price = Double.parseDouble(rs.getString("price"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    int status = Integer.parseInt(rs.getString("status"));
                    list.add(new OrderDetail(orderID,productID, price, quantity,status));
                }
             for(OrderDetail o: list){
               System.out.println("ok duoc");
               System.out.println(o.getOrderID());
           }
         } catch (Exception e){
             list = null;
         } finally {
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
         }
         return list;
    }
    public boolean updateOrder(String id,double total, String date) throws ClassNotFoundException, SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm  = null;
        
        try{
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(ORDER_UPDATE);
            ptm.setString(1,date );
            ptm.setDouble(2, total);
            ptm.setString(3, id);
            check= ptm.executeUpdate()>0?true:false;
        } catch(Exception e){
            e.printStackTrace();
        } finally{
       
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }
    public boolean addProduct(Product product) throws ClassNotFoundException, SQLException, NamingException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try{
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(ADD_PRODUCT);
            ptm.setString(1, product.getProductID());
            ptm.setString(2, product.getName());
            ptm.setDouble(3, product.getPrice());
            ptm.setInt(4, product.getQuantity());
            ptm.setInt(5, product.getStatus());
            check = ptm.executeUpdate()>0?true:false;
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }
    public boolean updateProduct1(Product product) throws ClassNotFoundException, SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm  = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(UPDATE_PRODUCT1);
            ptm.setString(1, product.getName());
            ptm.setDouble(2, product.getPrice());
            ptm.setInt(3, product.getQuantity());
            ptm.setInt(4, product.getStatus());
            ptm.setString(5, product.getProductID());
            check= ptm.executeUpdate()>0?true:false;
        } catch(Exception e){
            
        } finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }
     public boolean deleteProduct(String productID) throws ClassNotFoundException, SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm  = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(DELTE_PRODUCT);
            ptm.setString(1, productID);
            check= ptm.executeUpdate()>0?true:false;
        } catch(Exception e){
            
        } finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }
     public List<HistoryOrder> getListHistoryOrder(String userID) throws SQLException, ClassNotFoundException, NamingException{
         List<HistoryOrder> list = new ArrayList<>();
         Connection conn = null;
         PreparedStatement ptm = null;
         ResultSet rs = null;
         try{
             conn = DBUtils.getConnection();
             ptm = conn.prepareStatement(ORDER_HISTORY);
             ptm.setString(1, userID);
             rs = ptm.executeQuery();
             while(rs.next()){
                 String orderID = rs.getString("orderID");
                 String productID = rs.getString("productID");
                 String name = rs.getString("name");
                 int quantity = rs.getInt("quantity");
                 String date = rs.getString("date");
                 list.add(new HistoryOrder(orderID, productID, name, quantity, date));
             }
         } catch(Exception e){
             
         } finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
         }
         return list;
     }
}
