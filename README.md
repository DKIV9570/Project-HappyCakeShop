# Happy cake shop

## "Running your own cake shop"

This is a game of running a cake shop. In this game, player can:

- **Buy materials** from the market
- **Make cakes** with the material inventory
- Set price and **sell cakes** to the residents of a town and earn money

The game is design for players who want to **experience running a cake shop** , and
players who **enjoy the trade off strategy**. My original intention to make this game 
is I enjoy the strategy game myself.

**Enjoy it!**

Jin Niu

## User Stories

- As a user, I want to be able to chose materials from the inventory 
and use them to make cakes, set the price for the cakes, and add them to cake inventory of the shop
- As a user, I want to be able to buy materials from the market and add them to material inventory of the shop
- As a user, I want to be able to sell the cakes and earn funds
- As a user, I want to be able to view the cake inventory
- As a user, I want to be able to view the material inventory
- As a user, I want to be able to save my game progress to file
- As a user, I want to be able to be able to load my game progress from file

## Phase 4: Task 2

I implemented the Map interface, actually it has been used in several classes, for example, in the Cakeshop class,
I used 4 Maps to represent the inventory of each kind of material, also the inventory of the cake. In Resident class, 
I used a Map to represent the favor of the resident.

## Phase 4: Task 3

I would do 2 tasks if have enough time:

- Remove the relationship between ControlBar and Material, and make it a parameter passed by correspond method in GameMenu
- Make Class CakeShop to be part of Class Town, such that we can only import Town in GUI, then access CakeShop through Town
thus can reduce coupling.