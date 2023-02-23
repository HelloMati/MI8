## Overview
---

Welcome to the MI8 group project. Our text based adventure will take you through a dangerous mission with a single objective, to take down an international crime syndicate.

Using our skills learnt during the first 5 weeks of our bootcamp our API will have a series of HTTP requests to help you navigate this dangerous mission, whilst showcasing our understanding of APIs, 

### The challenge
---
Our MVP was to create a server-side, back-end project with the use of the following technologies/frameworks:


- Java
- Spring Boot
- An SQL database
- Maven 

With the use of Springboot we set out to create an API that would consolidate the knowledge we gathered and create a game. This game sets out to contain interconnected objects as well as bespoke methods which can affect the game-state.

### Screenshots

## Our process
---
The initial process started with narrowing down the scope of our project to an achievable MVP in the 72 hours we had.

![](mi8_diagram/mvp_and_extensions.png "mvp")

we established an MVP which we succesfully achieved as well as some extensions. Those extensions were discarding items and including a mission.

![](mi8_diagram/UML_diagram.png "UML diagram")

![](mi8_diagram/ERD_diagram.png "EDR diagram")

![](mi8_diagram/Story_board.png "Story board")

### Game-requests

There are several ways to reach the end of the game. Here is one way.

Endpoint requests using Postman:

To retrieve the current state of the game, use the following endpoint request:
Endpoint: /game
Method: GET
URL: http://localhost:8080/game

To create a new player with the given name, use the following endpoint request:
Endpoint: /player
Method: POST
URL: http://localhost:8080/player/Max

To create a new game with the given ID, use the following endpoint request:
Endpoint: /game/gameid
Method: POST
URL: http://localhost:8080/game/1

To update the current room of a game with the given ID, use the following endpoint request:
Endpoint: /game/gameid/room
Method: PATCH
URL: http://localhost:8080/game/1/lobby

To update a player's item with the given name, use the following endpoint request:
Endpoint: /player/playerid/itemName
Method: PATCH
URL: http://localhost:8080/player/1/eyes

To update the current room of a game with the given ID, use the following endpoint request:
Endpoint: /game/gameid/room
Method: PATCH
URL: http://localhost:8080/game/1/elevator

To update a player's item with the given name, use the following endpoint request:
Endpoint: /player/playerid/itemName
Method: PATCH
URL: http://localhost:8080/player/1/eyes

To update the current room of a game with the given ID, use the following endpoint request:
Endpoint: /game/gameid/room
Method: PATCH
http://localhost:8080/game/1/security

To update a player's item with the given name, use the following endpoint request:
Endpoint: /player/playerid/itemName
Method: PATCH
URL: http://localhost:8080/player/1/eyes

To update the current room of a game with the given ID, use the following endpoint request:
Endpoint: /game/gameid/room
Method: PATCH
URL: http://localhost:8080/game/1/elevator

To update a player's item with the given name, use the following endpoint request:
Endpoint: /player/playerid/itemName
Method: PATCH
URL: http://localhost:8080/player/1/keycard

To update the current room of a game with the given ID, use the following endpoint request:
Endpoint: /game/gameid/room
Method: PATCH
URL: http://localhost:8080/game/1/ceosoffice

To update a player's item with the given name, use the following endpoint request:
Endpoint: /player/playerid/itemName
Method: PATCH
URL: http://localhost:8080/player/1/eyes

To update the current room of a game with the given ID, use the following endpoint request:
Endpoint: /game/gameid/room
Method: PATCH
URL: http://localhost:8080/game/1/extraction

### How-to-play
(detailed bullet point instructions)

### What we learned

### Continued development

### Useful resources

## Authors

- James Emery
- Forida Matin
- Wilson Sanches

## Acknowledgments