import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen( ) {
        LocalTime currentTime = this.getCurrentTime();
        return currentTime.isAfter(this.openingTime) && currentTime.isBefore(this.closingTime);
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return this.menu;
    }

    private Item findItemByName(String itemName) throws ItemNotFoundException {
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        throw new ItemNotFoundException("We are not able to deliver  "+itemName+" at the moment!" );
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }

    public int getOrderValue(String items) throws ItemNotFoundException {
        int orderValue = 0;
        String[] itemNameArray = items.trim().split("[,]", 0);
        for(String itemName :itemNameArray){
            Item menuItem = this.findItemByName(itemName);
            orderValue = orderValue + menuItem.getPrice();
        }
        return orderValue;
    }
    
    public void removeFromMenu(String itemName) throws ItemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new ItemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

}
