# BorgerBot Burger Restaurant Sim
**Team Members**: Tenzin Gyaltsen, Jack Keller, Emydius Montes, Arthur Motoyama
<img width="879" alt="groupProject" src="https://user-images.githubusercontent.com/40735388/149462304-db754550-03e4-4565-9fca-05dcdfc08d46.png">


# Inspiration and goal
- Loosely based off of the Papa’s Burgeria game series, we wanted to make a burger restaurant order simulation/game. Including a drink, burger and side option, our game focused on burger creation. 

- This game simulates a burger restaurant order system. The game generates a randomized list of customer orders by every time interval that the chef "cooks" by clicking the button of ingredients and items to complete the order. The order would be refused/not completed if the prepared meal is not the same as the ordered meal.


# User Interface 
<img width="760" alt="uiDiagram" src="https://user-images.githubusercontent.com/40735388/149462331-aa459425-2e75-4477-b77a-b294317a4d37.png">


# Class Structures
<img width="825" alt="classDiagram" src="https://user-images.githubusercontent.com/40735388/149462366-3a44b356-b738-4987-b27e-672fe333641e.png">



# Data Structures
**Array Deque data structures**
- Store ingredients of the Burger Order.
- Removing ingredients, if it is not the correct order
 
**List data structures**
- Store static list of menu items such as drinks, burger ingredients, etc. 
- UI Construction (addContainers())

**Queue data structures**
- To queue up orders and remove them or add them when receiving and completing orders.

# Flow of the Game
- **Build UI** 
    - Buttons corresponding to different items and actions
- **Get Order (“Customer Order”)** 
    - Order object containing pieces of order like burger, drink, and sides.
- **Make Meal (“Prepared Meal”)** 
    - Build a meal by interacting with the UI (pressing buttons to construct a burger, side & drink).
    - The current features allows you to remove ingredients of burgers
- **Submit** 
    - Press the order up button, if the order is correct the “Number of Satisfied Customers” counter will increment by 1.
    - Otherwise, you will move on to next order without an increment of "Satisfied Customers"
- **Win**
    - The win condition of the game is to successfully complete 5 orders, after which you will have "cleared the stage" and can choose to play again.



