/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.util.ArrayList;
import sample.product.OrderDetail;

/**
 *
 * @author Admin
 */
public class OrderDetailList {
    private ArrayList<OrderDetail> OrderDetailList;

    public OrderDetailList() {
    }

    public OrderDetailList(ArrayList<OrderDetail> OrderDetailList) {
        this.OrderDetailList = OrderDetailList;
    }

    public ArrayList<OrderDetail> getOrderDetailList() {
        return OrderDetailList;
    }

    public void setOrderDetailList(ArrayList<OrderDetail> OrderDetailList) {
        this.OrderDetailList = OrderDetailList;
    }
    
}
