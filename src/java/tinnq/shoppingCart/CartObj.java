/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinnq.shoppingCart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ADMINS
 */
public class CartObj implements Serializable {
    private String customerID;
    private Map<String,Integer> items;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String title, int quantity) {
        if (quantity == 0) return;
        if (this.items == null) {
            this.items = new HashMap<String,Integer>();
        }
        if (this.items.containsKey(title)) {
            quantity += this.items.get(title);
        }
        this.items.put(title, quantity);
    }
    
    public void removeItemFromCart(String title, int quantity) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(title)) {
            int currentAmount = this.items.get(title);
            currentAmount -= quantity;
            this.items.replace(title, currentAmount);
            if (currentAmount == 0) {
                this.items.remove(title);
            }
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
    
}
