import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeAll
    public static void beforeAll(){

    }

    @AfterAll
    public static void afterAll(){

    }

    @BeforeEach
    public  void setup(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Tom's Cafe","Nagpur",openingTime,closingTime);

        service = new RestaurantService();
        service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

    }

    @AfterEach
    public void afterEach(){

    }

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE

        Restaurant restaurant = service.findRestaurantByName("Amelie's cafe");
        assertEquals("Amelie's cafe",restaurant.getName());
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE

        final restaurantNotFoundException e = assertThrows(restaurantNotFoundException.class, () -> {

            service.findRestaurantByName("Toms cafe");
        });

        assertThat(e.getMessage(),containsString("Toms cafe"));
        assertThat(e.getMessage(),notNullValue());
        assertThat(e.getClass().getName(),containsString("restaurantNotFoundException"));



    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
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



    //<<<<<<<<<<<<<<<<<< ORDER: CALCULATE ORDER COST starts >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void calculate_order_cost_should_pass_as_ordered_items_will_be_from_selected_list(){
        ArrayList<String> list =new ArrayList();
        list.add("Sweet corn soup");
        list.add("Vegetable lasagne");
        double cost = service.calculateOrderCost(restaurant,list);
        //System.out.println(cost);
        assertNotNull(cost);
    }

    @Test
    public void calculate_order_cost_should_fail_as_empty_list_is_passed(){
        ArrayList<String>list =new ArrayList();
        double cost = service.calculateOrderCost(restaurant,list);
        //System.out.println(cost);
        assertEquals(0,cost);
    }

    @Test
    public void calculate_order_cost_should_throw_NullPointerExcetion_as_null_list_is_passed(){

        ArrayList<String>list =null;
        assertThrows(NullPointerException.class, () -> {

            service.calculateOrderCost(restaurant,list);
        });

    }

    @Test
    public void calculate_order_cost_should_throw_NullPointerExcetion_as_null_restaurant_is_passed(){

        ArrayList<String>list =null;
        assertThrows(NullPointerException.class, () -> {

            service.calculateOrderCost(null,list);
        });

    }

    //<<<<<<<<<<<<<<<<<< ORDER: CALCULATE ORDER COST ends >>>>>>>>>>>>>>>>>>>>>>>>>>>

}