package sg.edu.nus.hmkrestaurant.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.hmkrestaurant.repositories.RestaurantRepos;

@Service
public class RestaurantRedis implements Serializable {

    @Autowired
    private RestaurantRepos newRepos;

    public String retrieveOrderItem(String orderID, String menuCat) {
        return (newRepos.retrieveOrder(orderID, menuCat)).toString();
    }
    
    public void saveOrder(String orderID, 
        String alacarte, String beverage, String comboSet) {
        Map<String, String> orderMap = new HashMap<>();
        orderMap.put("AlaCarte", alacarte);
        orderMap.put("Beverage", beverage);
        orderMap.put("ComboSet", comboSet);
        this.saveOrder(orderID, orderMap);
    }
    
    public void saveOrder(String keyName, Map<String, String> mapKeyValue) {
        newRepos.saveRecord(keyName, mapKeyValue);
    }

    public List<String> searchOrder(String query) {
        List<String> resultList = new ArrayList<>();
        Set<String> resultSet = newRepos.searchOrder("*" + query + "*");
        for (String result : resultSet)
            resultList.add(result);
        return resultList;
    }
}
