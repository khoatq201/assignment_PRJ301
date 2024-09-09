/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.util.ArrayList;
import sample.product.Product;

/**
 *
 * @author Admin
 */
public class ProductList {
    private ArrayList<Product> pList;

    public ProductList() {
    }

    public ProductList(ArrayList<Product> pList) {
        this.pList = pList;
    }

    public ArrayList<Product> getpList() {
        return pList;
    }

    public void setpList(ArrayList<Product> pList) {
        this.pList = pList;
    }

}
