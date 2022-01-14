package Burgeria.Components;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * Prepared Meal class to store and hold a meal that is constructed and built by interacting with the canvas in Borgeria.java.
 * Contains a burger list containing Ingredient objects, a drink object, and a side object. 
 * Created by Jack Keller, Tenzin Gyaltsen, Emydius Montes and Arthur Motoyama.
 */
public class PreparedMeal extends CustomerOrder{
    public Drink preparedDrink = new Drink();
    public Side preparedSide = new Side();
    public Deque<Ingredient> preparedBurger;

    /**
     * Constructor
     * Initializes these variables to null 
     * Initalizes prepared Burger as a new ArrayDeque
     */
    public PreparedMeal() {
        preparedDrink = new Drink();
        preparedSide = new Side();
        preparedBurger = new ArrayDeque<Ingredient>();
    }

    /**
     * Constructor
     * Assigns the values from the parameter input
     * @param drink
     * @param side
     * @param burger
     */
    public PreparedMeal(Drink drink, Side side, Deque<Ingredient> burger){
        preparedDrink = drink;
        preparedSide = side;
        preparedBurger = burger;
    }

    /**
     * add method for drink
     * assign drink to the preparedDrink
     * @param drink
     */
    public void addDrink(Drink drink){
        preparedDrink = drink;
    }

    /**
     * add method for sides
     * assign side to the preparedSide
     * @param side
     */
    public void addSide(Side side){
        preparedSide = side;
    }

    /**
     * push method for Burger
     * adds the Ingredient into the prepared Burger deque
     * @param ingredient
     */
    public void pushBurger(Ingredient ingredient){
        preparedBurger.addFirst(ingredient);
    }

    /**
     * Pop method for Burger Ingredient
     * @return and remove the first ingredient in the burger
     */
    public Ingredient popBurger() {
        return preparedBurger.removeFirst();
    }

    /**
     * peek method for Burger
     * @return the first ingredient in the Prepared Burger deque
     */
    public Ingredient peekBurger() {
        return preparedBurger.getFirst();
    }

    /**
     * Getter Method for Prepared Burger Deque
     * @return the Ingredient for the prepared burger
     */
    public Deque<Ingredient> getStack() {
        return preparedBurger;
    }

    /**
     * Returns the prepared drink.
     * @return
     */
    public Drink getDrink() {
        return preparedDrink;
    }
    /**
     * Returns the prepared side.
     * @return
     */
    public Side getSide() {
        return preparedSide;
    }

    /**
     * To String Method
     * @return a string of the meal prepared
     */
    public String mealToString(){
        String finishedMeal = "Burger: " + preparedBurger + ". Side: " + preparedSide + ". Drink: " 
        + preparedDrink + ".";
        return finishedMeal;
    }

    /**
     * Compare method for Customer Order and the Prepared Meal
     * @param order
     * @return true if the order is the same as the prepared Meal
     */
    public boolean equals(CustomerOrder order){
        boolean equals = true;
        System.out.println(order.burgerToString());
        System.out.println(burgerToString());
        int l1 = burgerToString().length();
        int l2 = order.burgerToString().length();
        int lmin = Math.min(l1, l2);
  
        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)burgerToString().charAt(i);
            int str2_ch = (int)order.burgerToString().charAt(i);
  
            if (str1_ch != str2_ch) {
                equals = false;
            }
        }
        return equals;
    }

    /**
     * To String method for Burger
     * @return String of Burger ingredients
     */
    public String burgerToString(){
        String burgerString = "";
        Iterator<Ingredient> iter = preparedBurger.iterator();
        while (iter.hasNext()) {
            burgerString += iter.next().getIngredientName() + ", ";
        }
        String realBurgerString = burgerString.substring(0, burgerString.length()-2);

        return realBurgerString;
    }


}
