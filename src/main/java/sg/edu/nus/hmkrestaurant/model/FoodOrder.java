package sg.edu.nus.hmkrestaurant.model;

import java.io.Serializable;

public class FoodOrder implements Serializable {
    private String OrderID;
    private String alaCarte;
    private String beverage;
    private String comboSet;

    public FoodOrder() { //Prepare Order ID for new Order
        String randEightNum = Integer.toString(100000001+(int)(Math.random()*(1000000000-100000001)));
        this.OrderID = (
            randEightNum.substring(0) + "-" + randEightNum.substring(1,3) + "-" + randEightNum.substring(3,8)
            ) + "-0";
    }

    public FoodOrder(String OrderID) { //Create new order object for model
        this.OrderID = OrderID;
    }

    public FoodOrder(String OrderID, String alaCarte, String beverage, String comboSet) {
        this.OrderID = OrderID;
        this.alaCarte = alaCarte;
        this.beverage = beverage;
        this.comboSet = comboSet;
    }

    public String getOrderID() {
        return this.OrderID;
    }

    public String getAlaCarte() {
        return this.alaCarte;
    }

    public String getBeverage() {
        return this.beverage;
    }

    public String getComboSet() {
        return this.comboSet;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public void setAlaCarte(String alaCarte) {
        this.alaCarte = alaCarte;
    }

    public void setBeverage(String beverage) {
        this.beverage = beverage;
    }

    public void setComboSet(String comboSet) {
        this.comboSet = comboSet;
    }
}
