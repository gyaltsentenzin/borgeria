package Burgeria.Components;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;


/**
 * Drink class that extends the Food parent class. The drink holds a specific size, image, drinkType, drinkIcon and drinkImage.
 * Created by Jack Keller, Tenzin Gyaltsen, Emydius Montes and Arthur Motoyama.
 */
public class Drink extends Food{
    String drinkSize;
    Image drinkLogo;
    List<String> validDrinkSize = new ArrayList<String>(3);
    String drinkType;
    Image drinkCup;
    GraphicsGroup drinkImage;
    List<String> validDrinkType = new ArrayList<String>(5); //XY and path, name button.png
    GraphicsGroup drinkIcon;
    
    /**
     * Drink constructors initializes drinksize list, drinktype list, and image 
     * declares and add list of drink sizes and type
     */
    public Drink(){
        this.drinkSize = null;
        this.drinkType = null;
        this.drinkIcon = new GraphicsGroup();
        drinkImage = new GraphicsGroup();

        validDrinkSize.add("LargeCup");
        validDrinkSize.add("MediumCup");
        validDrinkSize.add("SmallCup");

        validDrinkType.add("Sprite");
        validDrinkType.add("CocaCola");
        validDrinkType.add("Pepsi");
        validDrinkType.add("RootBeer");
        validDrinkType.add("MountainDew");
    }

    /**
     * Constructor that passes the logo and drink size as parameters. Initializes the drink cup as well.
     * @param size
     * @param type
     * @param image
     */
    public Drink(Image logo, String size) {
        drinkIcon = new GraphicsGroup();
        String concatSize = size.replaceAll("\\s+", "");
        Image cup = new Image(concatSize + ".png");
        drinkIcon.add(cup);
        drinkIcon.add(logo);
        drinkImage = new GraphicsGroup();
    }

    /**
     * Drink constructor to set size, type and image of drink
     * @param size drink size
     * @param type type of drink e.g, sprite, coca cola ,etc
     * @param image image of drink 
     */
    public Drink(String size, String type, GraphicsGroup image) {
        setDrinkSize(size);
        setDrinkType(type);
        this.drinkImage = image;
    }

    /**
     * Sets the size of drink
     * @param size 
     */
    public void setDrinkSize(String size){
        if(validDrinkSize.contains(size)){
            Image cup = new Image("Cup" + size + ".png");
            drinkImage.add(cup);
            this.drinkSize = size;
        }
    }

    /**
     * Returns the size of the drink.
     * @return the size of drink
     */
    public String getDrinkSize(){
        return drinkSize;
    }

    /**
     * Setter method for drinktype
     * @param type
     * If the drink types doesn't contains the type of drink, it sets the drinktype to the parameter drink types
     */
    public void setDrinkType(String type){
        if(validDrinkType.contains(type)){
            Image logo = new Image("Logo" + type + ".png");
            if (drinkImage != null) {
                drinkImage.add(logo);
            }
            this.drinkType = type;
        }
        this.drinkType = type;
    }

    /**
     * Getter method for drinktype
     * @return the type of drink
     */
    public String getDrinkType(){
        return drinkType;
    }

    /**
     * Setter method for drink image
     * @param image
     */
    public void setGraphicsObject(GraphicsGroup image){
        this.drinkImage = image;
    }

    /**
     * Getter method for drink image
     * @return drinkImage
     */
    public GraphicsGroup getGraphicsGroup(){
        return drinkImage;
    }
    
    /**
     * Getter method for drink name
     * @return drink size and drink type string
     */
    public String getName(){
        return drinkSize + " " + drinkType;
    }

    /**
     * Method to compare the given drink object and a drinkInput passed as a paramater.
     * @param drinkInput
     * @return 
     */
    public boolean equals(Drink drinkInput) {
        return this.drinkSize.equals(drinkInput.getDrinkSize()) && this.drinkType.equals(drinkInput.getDrinkType());
    }

}
