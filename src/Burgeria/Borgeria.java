package Burgeria;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Burgeria.Components.*;
import Burgeria.Components.PreparedMeal;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.*;

/**
 * Borgeria class runs the Borgeria restaurant simulator, creating the canvas, initiating images, etc.
 * Created by Jack Keller, Tenzin Gyaltsen, Emydius Montes and Arthur Motoyama.
 */
public class Borgeria{
    
    boolean orderWindowOpen = false;
    int burgerHeight;

    // BURGERIA GRAPHICS
    CanvasWindow canvas;
    Rectangle table;
    Image tray;
    Image orderBar; 
    GraphicsGroup burger;
    GraphicsGroup drink;
    GraphicsGroup side;
    GraphicsGroup orderWindow;
    Image receipt;
    GraphicsText orderText;
    
    // UI BUTTONS
    Button orderButton;
    Button orderUpButton;
    Button removeOrderButton;
    Button popBurger;
    Button restartGame;

    // POSSIBLE FOOD
    List<String> possibleDrinks = new ArrayList<String>();
    List<String> possibleSides = new ArrayList<String>();
    List<Ingredient> possibleIngredients = new ArrayList<Ingredient>();;

    // CONTAINERS
    List<String> ingContainers = new ArrayList<String>();
    List<String> condimentContainers = new ArrayList<String>();
    List<String> drinkTypeContainers = new ArrayList<String>();
    List<String> drinkSizeContainers = new ArrayList<String>();
    List<String> sideTypeContainers = new ArrayList<String>();
    List<String> sideSizeContainers = new ArrayList<String>();

    // BACK END VARIABLES
    OrderQueue orderQueue;
    CustomerOrder currentOrder;
    PreparedMeal currentMeal;
    PreparedMeal newMeal;
    int numOrdersCompleted = 0;
    List<CustomerOrder> completedOrderList;
    GraphicsText numOrdersButton;
    GraphicsText clearMessage;

    /**
     * constructs a new Burgeria.
     */
    public Borgeria() {
        // possible drinks --> move to DRINK class
        possibleDrinks.add("Sprite");
        possibleDrinks.add("CocaCola");
        possibleDrinks.add("Pepsi");
        possibleDrinks.add("RootBeer");
        possibleDrinks.add("MountainDew");

        // possible sides --> move to SIDE class
        possibleSides.add("Fries");
        possibleSides.add("Mash");
        possibleSides.add("Chips");
        possibleSides.add("Salad");

        // possible ingredients --> move to INGREDIENT class
        possibleIngredients.add(new Ingredient(new Image("IngLettuce.png"), "Lettuce"));
        possibleIngredients.add(new Ingredient(new Image("IngTomato.png"), "Tomato"));
        possibleIngredients.add(new Ingredient(new Image("IngCheese.png"), "Cheese"));
        possibleIngredients.add(new Ingredient(new Image("IngMayo.png"), "Mayo"));
        possibleIngredients.add(new Ingredient(new Image("IngKetchup.png"), "Ketchup"));
        possibleIngredients.add(new Ingredient(new Image("IngMushrooms.png"), "Mushrooms"));
        possibleIngredients.add(new Ingredient(new Image("IngSpecialSauce.png"), "SpecialSauce"));
        
        // ingredient containers
        ingContainers.add("BottomBun");
        ingContainers.add("BeefPatty");
        ingContainers.add("Cheese");
        ingContainers.add("Mushrooms");
        ingContainers.add("Tomato");
        ingContainers.add("Lettuce");
        ingContainers.add("TopBun");

        // condiment containers
        condimentContainers.add("Ketchup");
        condimentContainers.add("Mayo");
        condimentContainers.add("SpecialSauce");

        // drinktype containers
        drinkTypeContainers.add("Sprite");
        drinkTypeContainers.add("RootBeer");
        drinkTypeContainers.add("CocaCola");
        drinkTypeContainers.add("MountainDew");
        drinkTypeContainers.add("Pepsi");

        // drinksize containers
        drinkSizeContainers.add("LargeCup");
        drinkSizeContainers.add("MediumCup");
        drinkSizeContainers.add("SmallCup");

        // sidetype containers
        sideTypeContainers.add("Fries");
        sideTypeContainers.add("Chips");
        sideTypeContainers.add("Salad");
        sideTypeContainers.add("Mash");

        // sidesize containers
        sideSizeContainers.add("LargeBowl");
        sideSizeContainers.add("SmallBowl");

        // construct UI
        canvas = new CanvasWindow("Burger Stacks", 1920, 1080);
        canvas.setBackground(Color.LIGHT_GRAY);
        buildBurgeria();
        addUIButtons();
        double increment = canvas.getWidth()*0.072;
        addContainers(ingContainers, new Point(canvas.getWidth()*0.52, canvas.getHeight()*0.75), "Ingredient", increment);
        addContainers(condimentContainers, new Point(canvas.getWidth()*0.54, canvas.getHeight()*0.58), "Ingredient", increment);
        addContainers(drinkSizeContainers, new Point(canvas.getWidth()*0.79,canvas.getHeight()*0.57), "Cup", increment);
        addContainers(drinkTypeContainers, new Point(canvas.getWidth()*0.57,canvas.getHeight()*0.39), "Drink", canvas.getWidth()*0.078);
        addContainers(sideSizeContainers, new Point(canvas.getWidth()*0.54, canvas.getHeight()*0.88), "Bowl", canvas.getWidth()*0.082);
        addContainers(sideTypeContainers, new Point(canvas.getWidth()*0.7, canvas.getHeight()*0.88), "Side", increment);
        
        // instantiate order elements
        orderQueue = new OrderQueue();
        currentMeal = new PreparedMeal();
        orderWindow = new GraphicsGroup();
        receipt = new Image("receiptTemplate3.png");
        orderWindow.add(receipt);
        orderWindow.setPosition(new Point(canvas.getWidth()*0.01, canvas.getHeight()*0.02));
        orderText = new GraphicsText();
        orderText.setFontSize(18);
        orderText.setWrappingWidth(orderWindow.getWidth()*0.8);
        orderWindow.add(orderText, orderWindow.getWidth()*0.08, orderWindow.getHeight()*0.5);
        
        burger = new GraphicsGroup(0, 0);
        burger.setPosition(tray.getX() + tray.getWidth()*0.33, tray.getY() + tray.getHeight()*0.6);
        drink = new GraphicsGroup(canvas.getWidth()*0.3, canvas.getHeight()*0.4);
        side = new GraphicsGroup(tray.getX(), tray.getY() - tray.getHeight()*0.75);
        canvas.add(drink);
        canvas.add(side);
        canvas.add(burger);
        orderQueue.addToCanvas(canvas);
        canvas.draw();
        Timer timer = new Timer(); 
    }

    /**
     * 
     */
    public void run(){
        Timer timer = new Timer();
        TimerTask task = new Helper();
        timer.schedule(task, 3000, 10000);

        orderButton.onClick(() -> {
            if (!orderWindowOpen) {
                canvas.add(orderWindow);
                orderWindowOpen = true;
                orderWindow.add(new GraphicsText(currentOrder.toString()));
            } else {
                canvas.remove(orderWindow);
                orderWindowOpen = false;
            }
        });

        orderUpButton.onClick(() -> {
            Iterator<Ingredient> userBurger = currentMeal.preparedBurger.iterator();
            Iterator<Ingredient> orderBurger = currentOrder.getOrderBurger().iterator();
            
            boolean burgersEqual = true;
            boolean drinksEqual = true;
            boolean sidesEqual = true;
            
            for (int i = 0; i < Math.min(
            currentMeal.preparedBurger.size(),
            currentOrder.getOrderBurger().size()); i++) {
                Ingredient nextUserBurgerIngredient = userBurger.next();
                Ingredient nextOrderBurgerIngredient = orderBurger.next();
                System.out.println("user burger ingredient: " + nextUserBurgerIngredient.getIngredientName());
                System.out.println("order burger ingredient: " + nextOrderBurgerIngredient.getIngredientName());
                if (!nextUserBurgerIngredient.equals(nextOrderBurgerIngredient)) {
                    System.out.println("Burger Not Equal!");
                    burgersEqual = false;
                    break;
                } else System.out.println("Burger Equal!");
            }
            if (currentMeal.preparedBurger.size() == 0) {
                burgersEqual = false;
            }

            System.out.println(currentMeal.preparedDrink.getDrinkSize() + " " + currentMeal.preparedDrink.getDrinkType() + " vs. " + currentOrder.getOrderDrink().getDrinkSize() + currentOrder.getOrderDrink().getDrinkType());
            if(currentMeal.preparedDrink.equals(currentOrder.getOrderDrink())) {
                drinksEqual = true;
                System.out.println("Drinks equal!");
            } else {
                drinksEqual = false;
                System.out.println("Drinks not equal!");
            }

            System.out.println(currentMeal.preparedSide.getSideSize() + " " + currentMeal.preparedSide.getSideType() + " vs. " + currentOrder.getOrderSide().getSideSize() + " " + currentOrder.getOrderSide().getSideType());
            if(!currentMeal.preparedSide.equals(currentOrder.getOrderSide())) {
                sidesEqual = false;
                System.out.println("SIdes not equal!");
            } else System.out.println("Sides equal!");
            
            if (burgersEqual && drinksEqual && sidesEqual) {
                numOrdersCompleted++;
                numOrdersButton.setText("Number of Satisfied Customers: " + numOrdersCompleted);
            }
            
            currentMeal = new PreparedMeal();
            refresh();
            orderQueue.remove();
            if (orderQueue.getQueue().peek() != null) {
                setNewCurrent(orderQueue.getQueue().peek());
            } else orderText.setText("");
            canvas.draw();

            // If 5 orders are successfully completed, adds replay button 
            if(numOrdersCompleted == 5){
                clearMessage = new GraphicsText("STAGE CLEAR!");
                clearMessage.setFontSize(40);
                clearMessage.setCenter(tray.getCenter().getX(), tray.getCenter().getY() + 50);
                canvas.add(clearMessage);
                canvas.add(restartGame);
                return;
            }
        });

        // pop burger ingredients
        popBurger.onClick(() -> {
            currentMeal.popBurger();
            refresh();
        });

        // If replay button is clicked, sets number of succesful order completed to 0 
        // remove the restartGame button and clearMessage Text from canvas
        restartGame.onClick(() -> {
            numOrdersCompleted = 0;
            canvas.remove(clearMessage);
            canvas.remove(restartGame);
            canvas.removeAll();
            numOrdersButton.setText("Number of Satisfied Customers: " + numOrdersCompleted);
        });

    }

    /**
     * Builds the table, tray and orderBar and adds to canvas.
     */
    public void buildBurgeria() {
        table = new Rectangle(
            0, 
            canvas.getHeight()*0.6, 
            canvas.getWidth(), 
            canvas.getHeight());
        table.setFilled(true);
        table.setFillColor(Color.ORANGE);

        tray = new Image("tray.png");
        tray.setPosition(canvas.getWidth()*0.02, canvas.getHeight()*0.63);

        orderBar = new Image("receiptBar.png");
        orderBar.setPosition(0, canvas.getHeight()*0.05);

        canvas.add(table);
        canvas.add(tray);
        canvas.add(orderBar);
    }

    /**
     * Constructs, initializes and adds the multiple UI buttons to canvas.
     */
    public void addUIButtons() {    

        // order up button, checks if currentMeal matches currentOrder. 
        orderUpButton = new Button("Order Up!");
        orderUpButton.setPosition(canvas.getWidth()*0.23, canvas.getHeight()*0.27);
        canvas.add(orderUpButton);
        
        numOrdersButton = new GraphicsText("Number of Completed Orders: " + numOrdersCompleted);
        numOrdersButton.setPosition(orderUpButton.getX(), orderUpButton.getY() - orderUpButton.getHeight());
        canvas.add(numOrdersButton);
        
        orderButton = new Button("Order Receipt");
        orderButton.setPosition(orderUpButton.getX() + orderUpButton.getWidth(), orderUpButton.getY());
        canvas.add(orderButton);

        popBurger = new Button("Pop Burger");
        popBurger.setPosition(orderButton.getX() + orderButton.getWidth(), orderUpButton.getY());
        canvas.add(popBurger);

        restartGame = new Button("play again");
        restartGame.setCenter(tray.getCenter().getX(), tray.getCenter().getY());
    }

    /**
     * addContainers initializes the multiple food containers using the Container class and adds each container to canvas and containerList.
     * @param containerNameList
     * @param containerOrigin
     * @param containerType
     * @param increment
     */
    public void addContainers(List<String> containerNameList, Point containerOrigin, String containerType, double increment) {
        List<Container> containerList = new ArrayList<Container>();

        int j = 0;
        for (String containerName : containerNameList) {
            Container newContainer = new Container(containerName, canvas);
            newContainer.setContainerPosition(containerOrigin.getX() + j, containerOrigin.getY());
            containerList.add(newContainer);
            canvas.add(newContainer.container);
            j += increment;
        }

        if(containerType == "Ingredient") {
            for (Container container : containerList) {
                container.containerButton.onClick(() -> {
                    String containerName = container.getName();
                    String containerNameConcat = containerName.replaceAll("\\s+", "");
                    Image foodImage = new Image(containerNameConcat + ".png");
                    Ingredient newIng = new Ingredient(foodImage, containerName);
                    clear();
                    currentMeal.pushBurger(newIng);
                    refresh();
                    System.out.println(currentMeal.getStack());
                });
            }
        } else if (containerType == "Drink") {
            for (Container container : containerList) {
                container.containerButton.onClick(() -> {
                    if (!currentMeal.getDrink().getDrinkSize().equals(null)) {
                        currentMeal.getDrink().setDrinkType(container.getName().replaceAll("\\s+", ""));
                    } else System.out.println("no cup placed");
                    refresh();
                });
            }
        } else if (containerType == "Cup") {
            for (Container container : containerList) {
                container.containerButton.onClick(() -> {
                    currentMeal.getDrink().setDrinkSize(container.getName());
                    refresh();
                });
            }
        } else if (containerType == "Side") {
            for (Container container : containerList) {
                container.containerButton.onClick(() -> {
                    currentMeal.getSide().setSideType(container.getName());
                    refresh();
                });
            }
        } else if (containerType == "Bowl") {
            for (Container container : containerList) {
                container.containerButton.onClick(() -> {
                    currentMeal.getSide().setSideSize(container.getName());
                    refresh();
                });
            }
        }
    }

    /**
     * Helper method to remove burger ingredient images from the burger graphics group
     */
    private void clear() {
        Iterator<Ingredient> iterator = currentMeal.preparedBurger.iterator();
        while (iterator.hasNext()) {
            burger.remove(iterator.next().getImage());
        }
    }

    /**
     * Returns a random new order from the orderQueue.
     */
    public CustomerOrder queueNewOrder() {
        CustomerOrder randomNewOrder = new CustomerOrder();
        randomNewOrder.setRandomOrder(possibleDrinks, possibleSides, possibleIngredients);
        orderQueue.queueOrder(randomNewOrder);
        return randomNewOrder;
    }

    /**
     * Sets a new current order.
     * @param newOrder
     */
    public void setNewCurrent(CustomerOrder newOrder) {
        if (currentOrder != null) {
            currentOrder.setCurrentOrder(false);
        }
        currentOrder = newOrder;
        newOrder.setCurrentOrder(true);
        canvas.draw();
        String orderString = newOrder.orderToString();
        orderText.setText(orderString);
    }

    /**
     * Removes all ingredients from the burger list, initiates a new prepared meal to clear the user's order and removes the previous constructed meal from the canvas.
     */
    public void refresh() {
        burger.removeAll();
        
        Iterator<Ingredient> iterator = currentMeal.preparedBurger.descendingIterator();
        int burgerSpacing = 1;
        while (iterator.hasNext()) {
            Ingredient tempOrder = iterator.next();
            burger.add(tempOrder.getImage(), 0, 0 - 20*burgerSpacing);
            burgerSpacing++;
        }

        drink.removeAll();
        if (currentMeal.getDrink().getDrinkSize() != null){
            drink.add(new Image(currentMeal.getDrink().getDrinkSize() + ".png"));
            if (currentMeal.getDrink().getDrinkType() != null)
                drink.add(new Image("Logo" + currentMeal.getDrink().getDrinkType().replaceAll("\\s+", "") + ".png"), drink.getWidth()*0.2, drink.getHeight()*0.5);
        }

        side.removeAll();
        if (currentMeal.getSide().getSideType() != null) {
            side.add(new Image(currentMeal.getSide().getSideType() + currentMeal.getSide().getSideContainer() + ".png"));
        }
        else if (currentMeal.getSide().getSideContainer() != null) {
            side.add(new Image(currentMeal.getSide().getSideSize().replaceAll("\\s+", "") + ".png"));
        }
        canvas.draw();
    }

    class Helper extends TimerTask {
        public void run() {
            if (orderQueue.getQueue().size() == 0) {
                setNewCurrent(queueNewOrder());
                orderQueue.refreshGraphic();
            }
            else queueNewOrder();
        }
    }

    public static void main(String[] args) {
        Borgeria borgeria = new Borgeria();
        borgeria.run();
    }
}
