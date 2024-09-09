/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

/**
 *
 * @author Admin
 */
public class HistoryOrder {
    private String orderID;
    private String productID;
    private String name;
    private int quantity;
    private String date;

    public HistoryOrder() {
    }

    public HistoryOrder(String orderID, String productID, String name, int quantity, String date) {
        this.orderID = orderID;
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.date = date;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
