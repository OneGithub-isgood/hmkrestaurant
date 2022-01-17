package sg.edu.nus.hmkrestaurant.repositories;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepos implements Serializable {
    
    @Autowired
    @Qualifier("Restaurant")
    private RedisTemplate<String, Map<String, String>> template;

    public Object retrieveOrder(String keyName, String mapKey) {
        return template.opsForHash().get(keyName, mapKey);
    }

    public void saveRecord(String keyName, Map<String, String> mapKeyValue) {
        template.opsForHash().putAll(keyName, mapKeyValue); //Takes in multimap
    }

    public Set<String> searchOrder(String queryPattern) {
        return template.keys(queryPattern);
    }
}
