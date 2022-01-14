package Burgeria;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

import Burgeria.Components.*;
import edu.macalester.graphics.*;

/**
 * OrderQueue class creates and manages a queue used to hold the random orders in the Borgeria game.
 * Created by Jack Keller, Tenzin Gyaltsen, Emydius Montes and Arthur Motoyama.
 */
public class OrderQueue {
    
    Queue<CustomerOrder> orderQueue;
    GraphicsGroup window;

    boolean windowIsEmpty;

    /**
     * Constructor
     * Initialize order queue
     * Add Order queue images
     */
    public OrderQueue() {
        orderQueue = new ArrayDeque<CustomerOrder>();
        window = new GraphicsGroup();
        windowIsEmpty = true;
    }

    /**
     * Getter method for Order queue
     * @return return the orderQueue
     */
    public Queue<CustomerOrder> getQueue() {
        return orderQueue;
    }

    /**
     * update method
     * 
     */
    public void update() {
        Iterator<CustomerOrder> iterator = orderQueue.iterator();
        int margin = 0;
        while (iterator.hasNext()) {
            CustomerOrder tempOrder = iterator.next();
            window.add(tempOrder.getSprite(), 200+120*margin, 15);
            margin++;
        }
    }

    public void refreshGraphic() {
        window.removeAll();
        update();
    }

    public void queueOrder(CustomerOrder order) {
        if (orderQueue.size() > 0) {
            window.removeAll();
        }
        orderQueue.offer(order);
        update();
        windowIsEmpty = false;
    }

    public void remove() {
        if (orderQueue.size() > 0) {
            window.removeAll();
            orderQueue.poll();
            update();
        } else System.out.println("Queue already empty");
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(window);
    }

    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(window);
    }
}
