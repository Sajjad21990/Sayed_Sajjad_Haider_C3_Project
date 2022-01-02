import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
       

    ListIterator<Restaurant> itr = restaurants.listIterator();

    while(itr.hasNext()){
        Restaurant r = itr.next();
        if(r.getName() == restaurantName){
            return r;
        }
    }

    throw new restaurantNotFoundException("Restaurant not found");
    
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

  
}