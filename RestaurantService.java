import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{

        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        String name = null;
        for(Restaurant restaurant : restaurants){
             String rName = restaurant.getName();
            if(restaurantName.equals(rName)){
                return restaurant;
            }
        }
        if(name == null){
            throw new restaurantNotFoundException(restaurantName );
        }
        return null;
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

    /**
     * Added method for part 3 solution
     * @return order_cost
     */
    public double calculateOrderCost(Restaurant restaurant,ArrayList<String> list) {

        double orderCost = 0;
        if(restaurant == null){
            throw new NullPointerException();
        }
        ArrayList<Item>menuItem = (ArrayList<Item>) restaurant.getMenu();


        if(list == null){
            throw new NullPointerException();
        }
        for (String itemName:list) {
            for (Item mList:menuItem){
                if(itemName.equals(mList.getName())){
                    orderCost = orderCost + mList.getPrice();
                }
            }
        }

        return orderCost;
    }
}
