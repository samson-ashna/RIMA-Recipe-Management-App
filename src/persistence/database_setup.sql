create database if not exists rimaDB;
use rimaDB;
drop table if exists recipes;
drop table if exists users;
drop table if exists id;
CREATE TABLE `recipes` (
	`food_id` int NOT NULL,
    `name` varchar(255) NOT NULL,
	`ingredients` text,
    `instruction` text,
    `prep_time` int DEFAULT NULL,
    `calories` int DEFAULT NULL,
    `carbs` int NOT NULL,
    `protein` int NOT NULL,
    `fiber` int DEFAULT NULL,
    `carbohydrates` int DEFAULT NULL,
    `user` varchar(255) DEFAULT "ALL",
    PRIMARY KEY (`food_id`)
);
INSERT INTO recipes(food_id, name, ingredients, instruction, prep_time, calories, carbs, protein, fiber, carbohydrates) VALUES (
1,'Baked Potato','1 large russet potato,salt,peper,1 tablespoon butter,1 tablespoon sour cream','Scrub potato and prick with a fork. Place on a microwave-safe plate. 
Microwave on full power for 5 minutes. Turn potato over, and microwave until soft, about 5 more minutes. Remove potato from 
the microwave, and cut in half lengthwise. Season with salt and pepper and mash up the inside a little with a fork. Add butter 
and Cheddar cheese. Microwave until melted, about 1 more minute. Top with sour cream, and serve.',12,368,39,9,3,39),
(2,'Teriyaki Chicken','2 lb chicken thighs sliced into chunks, 1 cup soy sauce(240 ml),1/2 cup brown sugar(110 g)',"Sear the chicken thighs evenly in a pan, then flip. Add the
 soy sauce and brown sugar, stirring and bringing to a boil. Stir until the sauce has reduced and evenly glazes the chicken.",27,366,20,48,0,17);
CREATE TABLE `users` (
    `name` varchar(255) NOT NULL,
    `password` varchar(255) DEFAULT NULL,
    `myRecipes` json DEFAULT NULL ,
    `myIngredients` json DEFAULT NULL ,
	`allergies` json DEFAULT NULL ,
    PRIMARY KEY(`name`)
);
CREATE TABLE `id` (
	`recipeID` int DEFAULT 2
);
