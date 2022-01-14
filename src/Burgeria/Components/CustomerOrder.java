package Burgeria.Components;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import edu.macalester.graphics.Image;

/**
 * Customer Order to store and create a random order that contains a burger list containing Ingredient objects, a drink object, and a side object. 
 * Created by Jack Keller, Tenzin Gyaltsen, Emydius Montes and Arthur Motoyama.
 */
public class CustomerOrder {
    int orderNumber;
    boolean currentOrder = false;
    Drink drink = new Drink();
    Side side = new Side();
    Deque<Ingredient> orderedBurger = new ArrayDeque<Ingredient>();
    Image sprite;
    Random rand = new Random();


    /**
     * Constructor 
     * initializes the sprite image
     */
    public CustomerOrder(){
        sprite = new Image("OrderSprite2.png");
    }

    /**
     * Constructor to set drink,size and burger fillings ingredients for the customer
     * @param drink
     * @param side
     * @param burger
     */
    public CustomerOrder(Drink drink, Side side, Deque<Ingredient> burger){
        this.side = side;
        this.drink = drink;
        this.orderedBurger = burger;
        sprite = new Image("OrderSprite2.png");
    }

    /**
     * Setter method for random order
     * Set random drinks, size and burger to closely emulate an actual customer orders experiences
     * @param possibleDrinks
     * @param possibleSides
     * @param possibleIngredients
     */
    public void setRandomOrder(List<String> possibleDrinks, List<String> possibleSides, List<Ingredient> possibleIngredients){
        //possibly a seperate class?
        setRandomDrink(possibleDrinks);
        setRandomSide(possibleSides);
        //figure out random burger
        setRandomBurger(possibleIngredients);
    }

    /**
     * Setter methods for random drinks
     * @param possibleDrinks List of Possible drinks
     * Choose a random drink by setting random size and 
     */
    public void setRandomDrink(List<String> possibleDrinks){ 
        int i = rand.nextInt(possibleDrinks.size());
        drink.setDrinkType(possibleDrinks.get(i));
        int j = rand.nextInt(3);
        if (j == 0){
            drink.setDrinkSize("SmallCup");
        }
        else if(j == 1) {
            drink.setDrinkSize("MediumCup");
        }
        else {
            drink.setDrinkSize("LargeCup");
        }
    }

    /**
     * Setter method for random sides of the meal
     * @param possibleSides list of possible sides
     * generates random side by setting type of sides and the size
     */
    public void setRandomSide(List<String> possibleSides){ //take a list of possibly drinks and returns one at random?
        int i = rand.nextInt(possibleSides.size());
        side.setSideType(possibleSides.get(i));
        int j = rand.nextInt(2);
        if (j == 0){
            side.setSideSize("SmallBowl");
        }
        else {
            side.setSideSize("LargeBowl");
        }
    }

    /**
     * Setter method for random burger ingredients (fillings inside the burger)
     * @param possibleIngredients list of possible ingredients
     * Set a random burger by adding random fillings with Bottom bun at first and the top bun at last
     */
    public void setRandomBurger(List<Ingredient> possibleIngredients) {
        int i = rand.nextInt(possibleIngredients.size());
        orderedBurger.addFirst(new Ingredient(new Image("IngBottomBun.png"), "BottomBun"));
        orderedBurger.addFirst(new Ingredient(new Image("IngBeefPatty.png"), "BeefPatty"));
        for (int j = 0; j < i; j++) {
            int k = rand.nextInt(possibleIngredients.size());
            orderedBurger.addFirst(possibleIngredients.remove(k));
        }
        orderedBurger.addFirst(new Ingredient(new Image("IngTopBun.png"), "TopBun"));
    }

    /**
     * Setter method for current order
     * @param b boolean true or false
     * If current order exists, sets sprite image to selected Order Image
     * Else sets sprite image to a regular Order Sprite Image
     */
    public void setCurrentOrder(boolean b) {
        currentOrder = b;
        if (currentOrder) {
            sprite = new Image("SelectedOrderSprite.png");
        } else sprite = new Image("OrderSprite2.png");
    }

    /**
     * Getter method for Sprite Image
     * @return sprite image
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * Getter method for Order Drink
     * @return Ordered Drink
     */
    public Drink getOrderDrink(){
        return drink;
    }

    /**
     * Getter method for Order Side
     * @return Ordered Side
     */
    public Side getOrderSide(){
        return side;
    }

    /**
     * Getter method for the Burger Order
     * @return Deque of Burger Ingredients in the order
     */
    public Deque<Ingredient> getOrderBurger(){
        return orderedBurger;
    }

    /**
     * To String Method
     * @return a String of Order from the Burger choice, Sides and the Drink 
     */
    public String orderToString(){
        String orderedMeal = 
            // "Burger: " + orderedBurger.toString() +
            // ". Side: " + side + 
            // ". Drink: " + drink + ".";
            "Burger: " + burgerToString() + 
            ". Side: " + side.getName() +
            ". Drink: " + drink.getName() + ".";
        return orderedMeal;
    }

    /**
     * To String method
     * @return Burger ingredients or fillings
     */
    public String burgerToString(){
        String burgerString = "";
        for (Ingredient ingredient : orderedBurger) {
            burgerString += ingredient.getIngredientName() + ", ";
        }
        String realBurgerString = burgerString.substring(0, burgerString.length()-2);

        return realBurgerString;
    }

}
