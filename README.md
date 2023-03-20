# Recipe and Ingredient Management App (RIMA)

**Before Running:**
1. Open MySQL Workbench
2. Import database/database_setup.sql and run it

![image](https://user-images.githubusercontent.com/51792193/224358614-d0b80140-b5ec-4310-b698-9420474e1587.png)

3. Change user and/or password in src/persistence/DBSetup.java
4. Set databaseType = 0 (access real database) or databaseType = 1 (access stub database)

**To Run: src/presentation/Main.java**
## Team 1 Group Members
- Sheida Khodabakhshian Khansari (**SheidaK**, 215534340)
- Natalie Dean (**NatalieRDean**, 217880022)
- Ashna Samson (**samson-ashna**, 216196446)
- Zhenghao Wang (**218623264**, 218623264)
- Shaun Bautista (**ShaunB1**, 218750935)

## Description
The Recipe and Ingredient Management App (RIMA) facilitates a quick and convenient way to create or follow recipes. RIMA will come with preloaded recipes and the users can additionally add their recipes to the database. RIMA combines the numerous functionalities of similar apps, while also improving upon these features according to user feedback. Its key functionalities include the ability to search for recipes according to the user’s ingredient of choice, or the user can search for the recipe and it will provide the required ingredients.

## Architecture Sketch
![archsketch (2)](https://user-images.githubusercontent.com/51792193/224328216-22a2c073-6d05-4ef2-8101-28c49e7bec75.jpg)

## Iteration 1 Developer Tasks
**View Recipes:  As a cook, I want to be able to view a list of the recipes available in the app that I can save to my own personal recipe collection. Priority: High. Cost: 4 days**
- [x] Save Recipe to personal recipe collection: As a cook, I want to be able to add recipes available in the app to my own personal collection of recipes. Priority: High. Cost: 1 days
  - Create method to save recipes (0.5 days)
  - Create junit test for method (0.5 days)
- [x] Display Recipe Information: Display the recipes available in the app with detailed instructions, along with the ingredient list. Priority: High. Cost: 4 days

**Manipulate Local Recipe Collection: As the app user, I want to be able to modify the information available in my recipe collection.  Priority: High. Cost: 7 days**
- [x] Add Recipes to Personal Recipe Collection:  As a cook, I want to be able to add recipes to my own recipe collection. Priority: High. Cost: 3 days
- [x] Remove Recipes from Personal Recipe Collection:  As a cook, I want to be able to remove recipes from my own recipe collection. Priority: low. Cost: 2 days
- [x] Edit Recipes:  As a cook, I want to be able to modify the instructions and ingredients of any recipe available in my personal collection of recipes. Priority: High. Cost: 2 days

**User Profile:  As an app user I want to be able to add and view my user information, and dietary restrictions/preferences.  Priority: High. Cost: 3 days**
- [x] Create User Profile:  Ask the user to enter username, allergies or dietary restrictions/preferences .  Priority: High. Cost: 1 day
- [x] View User Profile: As a user I want to be able to view my user profile information (username, dietary restrictions/preferences). Priority: High. Cost: 1 day 
- [x] Edit User Profile:  As an app user, I want to be able to modify my user information.  Priority: High. Cost: 1 day
- [x] Create a Dietary Restrictions Survey: Ask the user to go through a series of questions to customize likes, dislikes, and other preferences for the user's profile and recommended recipe search results. Priority: High. Cost: 2 days
- [x] User LogIn: Create a User Login to come back to the app with saved progress each time. Priority: High. Cost: 2 days
- [x] User SignUp: Create a User Sign Up Page to create new user accounts to save recipies and find new recipies. Priority: High. Cost: 2 days

## Iteration 2 Developer Tasks
**Search Recipes:  As a cook, I want to be able to search recipes from the database by name, ingredients, and category.  Priority: High. Cost: 6 days**

**Manipulate Food Ingredients Inventory:  As a cook, I want to be able to view the list of all available food ingredients, add and remove from the list, and modify information on the quantity, cost, and expiration date.  Priority: Medium. Cost: 4 days**

**Favourite Recipe List:  As an app user, I want to be able to add my favourite recipes to a list for easier access.  Priority: Medium. Cost: 3 days**

**Meal Planning:  As a home cook, I want to be able to plan my meals for the coming week and I would like to view recipe suggestions based on my favourite recipe list, available food ingredients, and dietary restrictions and preferences.  Priority: Medium. Cost: 5 days**
#### Detailed User Stories For Iteration 2
“Search Recipes” - 
1. Display a Search Bar: As a user, I want to quickly find a search bar that can aid me in finding recipes with minimal effort.
2. Display Categories: As a user, I would like to easily search for what type of food I’m willing to make based on the time of day, the ingredients I own, and other categories based on meal time, dietary restrictions and such.
3. Display Searched Recipes - As a user, I want the specific recipes based on the search result to be filtered and displayed from the database.

“Manipulate Food Ingredients” - 
1. Display the Recipe Ingredients: As a user, I want to be able to view a list of the recipe list before I make the recipe in order to know whether I have the proper required ingredients to make the dish.
2. Add/Remove ingredients
3. Manage my Inventory: As a user, I want to be able to add notes to the items I have included in my list of food ingredients and edit their information such as the food’s expiration date.

“Favorite Recipe List” -
1. Add a Favorites Page: As a user, I would want the recipes I “like” or “save” to be displayed on one page under my app account.
2. Display a Collection: As a user, I want to be able to view a collection of recipes I made and recipes I recently viewed.

“Meal Planning” -
1. Create a Calendar Page:  Add a calendar page for the week where the user can select a day and add recipes to that day from the personal collection list
2. Edit Meals Added: As a user, I want to be able to delete and edit the meals that are added to the page in case I change my mind about want I want to create during the week.

## Iteration 3 Developer Tasks
**Manage Pantry:  As a user, I want to be able to keep track of food expiration dates and be notified accordingly.  Priority: Low. Cost: 9 days**

**Add Recipes from Websites:  As a home cook, I want to be able to add recipes I find on the internet to the management system to view later.  Priority: Low. Cost: 2 days**

**Shopping List:  As a home cook, I would like to be able to view a list of food ingredients that I need to purchase and add/remove from the list.  Priority: Low. Cost: 3 days**
