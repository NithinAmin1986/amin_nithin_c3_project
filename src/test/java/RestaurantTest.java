import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    RestaurantService restaurantService = new RestaurantService();
    LocalTime openingTime, closingTime;
    @BeforeEach
    public void setUp(){
        System.out.println("Before Each:");
        openingTime = LocalTime.parse("10:30:00");
        closingTime = LocalTime.parse("22:00:00");
    }
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        restaurant = restaurantService.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
         LocalTime currentTestTime = LocalTime.parse("12:50:00");
         Restaurant mocRestaurant = Mockito.spy(restaurant);
         Mockito.when(mocRestaurant.getCurrentTime()).thenReturn(currentTestTime);
         assertEquals(true, mocRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        restaurant = restaurantService.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        LocalTime currentTestTime = LocalTime.parse("10:00:00");
        Restaurant mocRestaurant = Mockito.spy(restaurant);
        Mockito.when(mocRestaurant.getCurrentTime()).thenReturn(currentTestTime);
        boolean isRestaurantOpen = mocRestaurant.isRestaurantOpen();
        assertEquals(false, mocRestaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void item_names_passed_should_be_part_of_restaurant_menu(){
         String itemName = "Sweet corn soup, Vegetable lasagne";
         assertTrue(false);

    }

    @Test
    public void any_one_of_item_names_passed_do_not_exist_in_restaurant_menu_throw_exception(){
        String itemName = "Sweet corn soup, Vegetable lasagne";
        assertTrue(false);
    }

    @Test
    public void item_names_total_value_should_be_same_as_sum_of_restaurant_menu_item_value(){
       assertTrue(false);
    }
}