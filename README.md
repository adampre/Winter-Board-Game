# Winter Themed Board Game
*2-4 Player Game*

**INTRODUCTION**

Coding assignment for my class, HL IB Computer Science 2. I coded a winter themed board game. Inspired from my drum teacher, who used this to do a winter board game with his class. 

**RULES**
1. Each game has a randomly generated map. 
2. Each player starts with 10 health points (HP), a strength (maximum snowball carrying capacity), and a speed (maximum tile movement per turn). You get a maximum of 5 skill points, each can go towards strength or speed. For example, you can choose to have 2 strength and 3 speed, or 1 strength and 4 speed. 
3. Each turn you do 2 things. Move (up to your max speed), and throw a snowball. You can decide the order. 
4. A snowball can only be thrown horizontally and vertically and through a path. [^1]
5. Damage a snowball does is based on distance. The following table is used to calculate snowball damage. (A 6-sided die is used to calculate both odds of doing damage and the actual damage dealt. Each damage number is shown in order, ex. A roll of 4, 0 spaces away would yield 1 damage).

|Number of Squares Away|Damage|
|:---:|:---:|
|0|1 1 1 1 2 2|
|1|0 1 1 1 1 2|
|2|0 0 1 1 1 2|
|3|0 0 0 1 1 2|
|4|0 0 0 0 1 2|
|5|0 0 0 0 0 2|
6. When you run out of snowballs, you must go to a snowball fort tile. You can spend one turn "safe" while on a snowball fort tile. [^2]
7. When your HP reaches 0, you are out. 
8. When hit by a snowball, you are thrown back one tile, regardless whether or not there is a path or not. [^1]

[^1]: A path is a trail leading a straight line. If the trail turns or ends, even before the edge of the board, that specified path ends. 
[^2]: Forts are spread throughout the board, and the amount of them depends on the amount of players playing. Forts only respawn once every other fort on the board has been used. 
