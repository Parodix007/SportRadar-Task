# SportRadar-Task

Recruitment task for SportRadar

## Table of content

* [Get started](#how-to-use)
* [Starting a game](#starting-a-game)
* [Update a game](#update-game-score)
* [Finish a game](#finish-a-game)
* [Ended games](#ended-games)

### How to use

* This library provide a facade which allows you to execute every functionality according to the
  business documentation.

```java 
  GameBoard gameBoard=new GameBoard(GamesStore.newEmptyInstance());
```

Example above shows how to create a facade which allows us to start using this library.

### Starting a game

* To start a new game in this system you need to create a ``StartGameDto``. This dto accepts
  name for home and away team and start date of the game.

```java
    String homeTeam="DummyName";
    String awayTeam="DummyName1";
    ZonedDateTime start=ZonedDateTime.now();

    StartGameDto startGameDto=new StartGameDto(homeTeam,awayTeam,start);
```

When you are ready with dto now it's time for start the game!

```java
    gamesBoard.startGame(startGameDto)
```

This method start a game with <b>initial score for both teams 0</b>. This method returns <b>uniq
game UUID</b>

### Update Game Score

* This library allows you to update a game score. To do that you need to create a
  ```UpdateGameDto```.

```java
    int homeScore=1;
    int awayScore=0;
    UpdateGameDto updateGameDto=new UpdateGameDto(gameUuid,homeScore,awayScore);
```

Now pass this dto to the facade.

```java
  gamesBoard.updateGameScore(updateGameDto);
```

### Finish a game

* Finish a game is the simplest because all you need to do is pass a game uuid and end date

```java
    ZonedDateTime end=ZonedDateTime.now();
    gamesBoard.finishGame(gameUuid,end);
```

### Ended Games

* Application allows you to get all ended games. Those games are returner ordered by draws -
  games with a draw score will be firsts in the list
```java
List<EndedGameDto> endedGames = gamesBoard.getEndedGames();
/**
 * EndedGameDto
 * DTO of a finished game
 *
 * @param homeTeam  {@link String} Name of the home team
 * @param awayTeam  {@link String} Name of the away team
 * @param homeScore {@link Integer} Score of the home team
 * @param awayScore {@link Integer} Score of the away team
 * @param endDate   {@link ZonedDateTime} Date of game finish
 * @author Sebastian Siarczyński
 */
```
