package sg.edu.nus.hmkrestaurant.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.hmkrestaurant.services.RestaurantRedis;

@Controller
@RequestMapping(path="/search", produces=MediaType.TEXT_HTML_VALUE)
public class SearchController {
    
    @Autowired
    private RestaurantRedis redisService;

    @GetMapping
    public String searchResult(@RequestParam(required = true) String queryPattern, Model model) {
        List<String> resultList = redisService.searchOrder(queryPattern);
        model.addAttribute("results", resultList);
        return "search";
    }
}
