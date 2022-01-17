package sg.edu.nus.hmkrestaurant.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.hmkrestaurant.HmkrestaurantApplication;
import sg.edu.nus.hmkrestaurant.model.FoodOrder;
import sg.edu.nus.hmkrestaurant.services.RestaurantRedis;

@Controller
public class OrderController {
    private final Logger logger = Logger.getLogger(HmkrestaurantApplication.class.getName());

    @Autowired
    private RestaurantRedis redisService;
    
    @GetMapping("/order")
    public String showOrderForm(Model model) {
        FoodOrder newOrder = new FoodOrder();
        model.addAttribute("FoodOrder", newOrder); //Create new FoodOrder object
        return "order";
    }

    @GetMapping("/order/{OrderID}")
    public String viewOrderForm(Model model, @PathVariable(value="OrderID") String OrderID) {
        logger.info("Order ID > " + OrderID);
        FoodOrder existingOrder = new FoodOrder(OrderID);
        existingOrder.setAlaCarte(redisService.retrieveOrderItem(OrderID, "AlaCarte"));
        existingOrder.setBeverage(redisService.retrieveOrderItem(OrderID, "Beverage"));
        existingOrder.setComboSet(redisService.retrieveOrderItem(OrderID, "ComboSet"));
        logger.info("Combo Set > " + existingOrder.getComboSet());
        model.addAttribute("FoodOrder", existingOrder); //Create new FoodOrder object
        return "orderWithID";
    }

    @PostMapping("/order")
    public String addBookRecord(@ModelAttribute FoodOrder order, Model model) {
        logger.info("Food Order at /Order page ID > " + order.getOrderID());
        FoodOrder redisOrder = new FoodOrder(
            order.getOrderID(), order.getAlaCarte(), order.getBeverage(), order.getComboSet()
            ); //Create new object for redis
        
        redisService.saveOrder(
            redisOrder.getOrderID(), redisOrder.getAlaCarte(), redisOrder.getBeverage(), redisOrder.getComboSet()
            ); //Staging data to save via service
        
        model.addAttribute("FoodOrder", redisOrder); //Create new object for model
        logger.log(Level.WARNING, "Beverage: %s".formatted(redisOrder.getBeverage()));
        return "orderWithID";
    }
}
