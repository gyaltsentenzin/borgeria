package Burgeria.Components;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;

/**
 * Parent food class that contains a name, image and graphics group.
 * Created by Jack Keller, Tenzin Gyaltsen, Emydius Montes and Arthur Motoyama.
 */
public class Food {
    String foodName;
    Image foodImage; //for ingredients
    GraphicsGroup foodGraphicsGroup; //for sides and drinks (bowl + side / cup + logo)

    /**
     * Constructor Method
     * Initializes foodName, food Image and foogGraphics Group as null
     */
    public Food() {
        foodName = null;
        foodImage = null;
        foodGraphicsGroup = null;
    }

    /**
     * Constructor Method
     * @param foodImage assign foot Image
     * @param foodName assign foorName
     */
    public Food(Image foodImage, String foodName) {
        this.foodImage = foodImage;
        this.foodName = foodName;
    }

    /**
     * Constructor Method
     * @param foodGraphicsGroup assiggns FoodGraphics
     * @param foodName assigns the food name
     */
    public Food(GraphicsGroup foodGraphicsGroup, String foodName) {
        this.foodGraphicsGroup = foodGraphicsGroup;
        this.foodName = foodName;
    }

    /**
     * Setter method
     * @param foodGraphicsGroup
     * Set foodGraphics Group
     */
    public void setGraphicsObject(GraphicsGroup foodGraphicsGroup){
        this.foodGraphicsGroup = foodGraphicsGroup;
    }


}
