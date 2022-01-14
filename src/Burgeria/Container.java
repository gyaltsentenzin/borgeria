package Burgeria;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.*;

/**
 * Container class that holds a button, container graphics group and button name to be added to the canvas.
 * Created by Jack Keller, Tenzin Gyaltsen, Emydius Montes and Arthur Motoyama.
 */
public class Container {
    GraphicsGroup container;
    Image containerIcon;
    Button containerButton;
    String containerName;
    String ingredientContainerName;

    // ingredient containers
    List<String> ingContainers = new ArrayList<String>();
    Point center = new Point(0, 0);

    /**
     * Basic constructor for Container that initializes container, and sets containerIcon and containerButton to null.
     */
    public Container() {
        container = new GraphicsGroup();
        containerIcon = new Image(null);
        containerButton = new Button(null);
    }

    /**
     * constructor for Container that initializes container, and sets containerName to a given parameter, while centering containerIcon and containerButton.
     * @param containerName
     * @param canvas
     */
    public Container(String containerName, CanvasWindow canvas) {
        this.containerName = containerName;
        container = new GraphicsGroup();
        containerButton = new Button(containerName);

        String noSpaceName = containerName.replaceAll("\\s+", "");
        containerIcon = new Image("container" + noSpaceName + ".png");
        
        containerIcon.setCenter(center);
        containerButton.setCenter(center);
        containerButton.setY(containerIcon.getY() + containerIcon.getHeight());
        
        container.add(containerIcon);
        container.add(containerButton);
    }

    public void setContainerFunction() {

    }

    /**
     * Sets the container point to a specific point.
     */
    public void setContainerPosition(Point point) {
        container.setPosition(point);
    }

    /**
     * Sets the container point to a given x and y values.
     * @param x
     * @param y
     */
    public void setContainerPosition(Double x, Double y) {
        container.setPosition(x, y);
    }

    /**
     * Returns the container name.
     */
    public String getName() {
        return containerName;
    }

}
