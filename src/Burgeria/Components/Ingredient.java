package Burgeria.Components;

import java.util.ArrayList;
import java.util.List;

// import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;

/**
 * Ingredient class that extends the Food parent class. The ingredient holds a specific name and image.
 * Created by Jack Keller, Tenzin Gyaltsen, Emydius Montes and Arthur Motoyama.
 */
public class Ingredient {
    Image ingredientImage;
    String ingredientName;
    List<String> validIngredientName = new ArrayList<String>();

    /**
     * Constructor
     * initializes ingredient image and name to null
     */
    public Ingredient() {
        ingredientImage = null;
        ingredientName = null;
    }

    /**
     * Constructor
     * @param image assigns Ingredient image
     * @param name assigns Ingredient name
     */
    public Ingredient(Image image, String name){
        ingredientImage = image;
        ingredientName = name;
    }

    /**
     * Setter method for Ingredient Name
     * @param name set the name of the Ingredient
     */
    public void setIngredientName(String name){
        ingredientName = name;
    }

    /**
     * Getter Method for Ingredient Name
     * @return Ingredient Name
     */
    public String getIngredientName(){
        return ingredientName;
    }

    /**
     * Setter Method for Ingredient Image
     * @param image set the image of the ingredient
     */
    public void setGraphicsObject(Image image){
        ingredientImage = image;
    }

    /**
     * Getter method for Ingredient Image
     * @return ingredient image
     */
    public Image getImage(){
        return ingredientImage;
    }

    /**
     * Compare / equal method
     * @param ingredientInput 
     * @return return if it is equal to the ingredient here
     */
    public boolean equals(Ingredient ingredientInput) {
        return this.ingredientName.equals(ingredientInput.ingredientName);
    }
}
