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
    `carbs` int NOT NULL,
    `protein` int NOT NULL,
    `mealTime` varchar(255) DEFAULT "Breakfast/Lunch/Dinner",
    `user` varchar(255) DEFAULT "ALL",
    `favorite` int DEFAULT 0,
    PRIMARY KEY (`food_id`)
);
INSERT INTO recipes(food_id, name, ingredients, instruction,carbs, protein,mealTime) VALUES (
1,'Baked Potato','1 large russet potato,salt,peper,1 tablespoon butter,1 tablespoon sour cream','Scrub potato and prick with a fork. Place on a microwave-safe plate. 
Microwave on full power for 5 minutes. Turn potato over, and microwave until soft, about 5 more minutes. Remove potato from 
the microwave, and cut in half lengthwise. Season with salt and pepper and mash up the inside a little with a fork. Add butter 
and Cheddar cheese. Microwave until melted, about 1 more minute. Top with sour cream, and serve.',9,39,'Lunch/Dinner'),
(2,'Teriyaki Chicken','2 lb chicken thighs sliced into chunks, 1 cup soy sauce(240 ml),1/2 cup brown sugar(110 g)',"Sear the chicken thighs evenly in a pan, then flip. Add the
 soy sauce and brown sugar, stirring and bringing to a boil. Stir until the sauce has reduced and evenly glazes the chicken.",48,20,'Lunch');
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
INSERT INTO id(recipeID) VALUE (2);

SELECT * FROM users