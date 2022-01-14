package Burgeria.Components;

import java.util.List;

import edu.macalester.graphics.GraphicsGroup;

/**
 * Side class that extends the Food parent class. The side holds a specific size, image, sideType, and sideImage.
 * Created by Jack Keller, Tenzin Gyaltsen, Emydius Montes and Arthur Motoyama.
 */
public class Side extends Food{
    String sideSize;
    String sideType;
    List<String> validsideType; 
    GraphicsGroup sideImage;

    /**
     * Constructor
     * Initialize the variables as null values
     */
    public Side(){
        this.sideSize = null;
        this.sideType = null;
        this.sideImage = null;
    }

    /**
     * Constructor
     * @param size to sideSize
     * @param type to sideType
     * @param image to sideImage
     */
    public Side(String size, String type, GraphicsGroup image) {
        this.sideSize = size;
        this.sideType = type;
        this.sideImage = image;
    }

    /**
     * Setter method for Side size
     * @param size to sideSize
     */
    public void setSideSize(String size){
        this.sideSize = size;
    }

    /**
     * Getter method for Side size
     * @return the sideSize
     */
    public String getSideSize(){
        return sideSize;
    }

    /**
     * Returns 'large' or 'small' or null depending on the sideSize.
     * @return
     */
    public String getSideContainer() {
        if (sideSize == "LargeBowl") {
            return "Large";
        }
        else if (sideSize == "SmallBowl") {
            return "Small";
        }
        else return null;
    }

    /**
     * Setter method for side Type
     * @param type to sideType
     */
    public void setSideType(String type){
        this.sideType = type;
    }

    /**
     * Getter method for side Type
     * @return the sideType
     */
    public String getSideType(){
        return sideType;
    }

    /**
     * Setter method for Side Image
     * @image to sideImage
     */
    public void setGraphicsObject(GraphicsGroup image){
        this.sideImage = image;
    }

    /**
     * Getter method for Side Image
     * @return sideImage
     */
    public GraphicsGroup getGraphicsObject(){
        return sideImage;
    }

    /**
     * Getter method for Side Name
     * @return sideSize and sideType
     */
    public String getName(){
        return sideSize + " " + sideType;
    }

    /**
     * Method to compare the given side object and a sideInput passed as a paramater.
     * @param drinkInput
     * @return 
     */
    public boolean equals(Side sideInput) {
        return this.getSideSize().equals(sideInput.getSideSize()) && this.getSideType().equals(sideInput.getSideType());
    }

}
