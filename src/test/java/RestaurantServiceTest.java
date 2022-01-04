import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    @BeforeEach
    public void beforeEachTest(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");

        restaurant = service.addRestaurant("Amelie's cafe","Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        Restaurant tempRestaurant = service.findRestaurantByName("Amelie's cafe");
        assertEquals("Amelie's cafe", tempRestaurant.getName());
    }

    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
       assertThrows(restaurantNotFoundException.class, ()->{
           service.findRestaurantByName("Delhi Darbar");
       });
    }


    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
       
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>

    // TODO
    // calling calculate price method with list of items should return total amount
    // calling calculate price method with empty list should return 0

    @Test
    public void calulate_total_price_should_return_total_price_of_items_selected(){

       List<String> itemsSelected = new ArrayList<String>();
       itemsSelected.add("Sweet corn soup");
       itemsSelected.add("Vegetable lasagne");

       int totalAmount = restaurant.calculateTotalAmount(itemsSelected);          
        assertEquals(388, totalAmount);
    }

    @Test
    public void calculate_total_price_with_empty_list_should_return_0(){
        List<String> itemsSelected = new ArrayList<String>();

        int totalAmount = restaurant.calculateTotalAmount(itemsSelected);          
        assertEquals(0, totalAmount);
    }


}