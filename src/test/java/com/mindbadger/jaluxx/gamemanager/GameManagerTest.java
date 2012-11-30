package com.mindbadger.jaluxx.gamemanager;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.mindbadger.jaluxx.Action;
import com.mindbadger.jaluxx.GameStatus;
import com.mindbadger.jaluxx.JaluxxException;
import com.mindbadger.jaluxx.Game;
import com.mindbadger.jaluxx.Player;
import com.mindbadger.jaluxx.PlayerStatus;
import com.mindbadger.jaluxx.gamemanager.GameManager;

public class GameManagerTest {
	private GameManager gameManagerBeingTested;
	
	private Player player1;
	private Player player2;

	@Before
	public void setup () {
		MockitoAnnotations.initMocks(this);
		gameManagerBeingTested = new GameManager ();
		
		player1 = new Player ();
		player1.setName("Mark");
		player1.setPassword("myPass");

		player2 = new Player ();
		player2.setName("Anne");
		player2.setPassword("annesPass");
	}
	  
	@Test
	public void newGameManagerInitialisedCorrectly() {
		// Given
		
		// When
		Map<String, Player> registeredPlayers = gameManagerBeingTested.getRegisteredPlayers ();
		List<Action> actions = gameManagerBeingTested.getActions ();
		List<Game> games = gameManagerBeingTested.getGames ();
		
		// Then
		assertEquals (0, registeredPlayers.size());
		assertEquals (0, actions.size());
		assertEquals (0, games.size());
	}
		
	@Test
	public void registerNewPlayer() {
		// Given
		
		// When
		Player returnedPlayer = gameManagerBeingTested.registerNewPlayer (player1);
		
		// Then
		assertEquals (player1, returnedPlayer);
		Map<String, Player> registeredPlayers = gameManagerBeingTested.getRegisteredPlayers ();
		Player playerLogin = registeredPlayers.get("Mark");
		assertEquals ("Mark", playerLogin.getName());
		assertEquals ("myPass", playerLogin.getPassword());
		
		List<Action> actions = gameManagerBeingTested.getActions ();
		assertEquals (1, actions.size());
		Action action = actions.get(0);
		
		assertEquals ("You have logged in", action.getActionMessageFor(player1));
		assertEquals ("Mark has logged in", action.getActionMessageFor(player2));
	}

	@Test
	public void playerAlreadyRegisteredAndWrongPassword() {
		// Given
		Player player1 = new Player ();
		player1.setName("Mark");
		player1.setPassword("myPass");
		gameManagerBeingTested.registerNewPlayer (player1);

		Player player2 = new Player ();
		player2.setName("Mark");
		player2.setPassword("differentPass");

		// When
		try {
			gameManagerBeingTested.registerNewPlayer (player2);
			
			fail ("Should throw an exception stating the password is wrong");
		} catch (JaluxxException e) {
			assertEquals("Mark has already registered with a different password. Please use a unique name or the correct password.", e.getMessage());
		}		
	}
	
	@Test
	public void playerAlreadyRegisteredAndCorrectPassword() {
		// Given
		Player player1 = new Player ();
		player1.setName("Mark");
		player1.setPassword("myPass");
		gameManagerBeingTested.registerNewPlayer (player1);

		Player player2 = new Player ();
		player2.setName("Mark");
		player2.setPassword("myPass");

		// When
		Player returnedPlayer = gameManagerBeingTested.registerNewPlayer (player2);
		
		// Then
		assertEquals (player1, returnedPlayer);
		Map<String, Player> registeredPlayers = gameManagerBeingTested.getRegisteredPlayers ();
		Player playerLogin = registeredPlayers.get("Mark");
		assertEquals ("Mark", playerLogin.getName());
		assertEquals ("myPass", playerLogin.getPassword());

		List<Action> actions = gameManagerBeingTested.getActions ();
		assertEquals (1, actions.size());
	}
	
	@Test
	public void playerStartsNewGame() {
		// Given
		
		// When
		gameManagerBeingTested.startNewGameForPlayer(player1);
		
		// Then
		List<Game> games = gameManagerBeingTested.getGames();
		assertEquals (1, games.size());
		Game game = games.get(0);
		
		List<Player> playersInNewGame = game.getPlayers();
		assertEquals (1, playersInNewGame.size());
		assertEquals (playersInNewGame.get(0), player1);
		
		assertEquals (PlayerStatus.JOINED_GAME, player1.getStatus());
		
		assertEquals (game, player1.getGame ());
		
		List<Action> actions = gameManagerBeingTested.getActions ();
		assertEquals (1, actions.size());
		Action action = actions.get(0);
		
		assertEquals ("You have started a new game", action.getActionMessageFor(player1));
		assertEquals ("Mark has started a new game", action.getActionMessageFor(player2));

	}

	@Test
	public void playerJoinsGame() {
		// Given
		gameManagerBeingTested.startNewGameForPlayer(player1);
		Game game = gameManagerBeingTested.getGames().get(0);
		
		// When
		gameManagerBeingTested.joinGame(player2, Long.toString(game.getGameId()));
		
		// Then
		List<Game> games = gameManagerBeingTested.getGames();
		assertEquals (1, games.size());
		assertEquals (game, games.get(0));
		
		List<Player> playersInNewGame = game.getPlayers();
		assertEquals (2, playersInNewGame.size());
		assertEquals (playersInNewGame.get(0), player1);
		assertEquals (playersInNewGame.get(1), player2);
		
		assertEquals (game, player1.getGame ());
		assertEquals (game, player2.getGame ());
		
		assertEquals (PlayerStatus.JOINED_GAME, player2.getStatus());
		
		List<Action> actions = gameManagerBeingTested.getActions ();
		assertEquals (2, actions.size());
		Action action = actions.get(1);
		
		assertEquals ("You have joined a game", action.getActionMessageFor(player2));
		assertEquals ("Anne has joined a game", action.getActionMessageFor(player1));

	}

	@Test
	public void onePlayerIsReadyToPlay() {
		// Given
		gameManagerBeingTested.startNewGameForPlayer(player1);
		Game game = gameManagerBeingTested.getGames().get(0);
		gameManagerBeingTested.joinGame(player2, Long.toString(game.getGameId()));
		
		// When
		gameManagerBeingTested.readyToPlay(player1);
		
		// Then		
		assertEquals (PlayerStatus.READY_TO_PLAY, player1.getStatus());
		assertEquals (PlayerStatus.JOINED_GAME, player2.getStatus());
		
		assertEquals (GameStatus.SETUP, game.getStatus());
		
		List<Action> actions = gameManagerBeingTested.getActions ();
		assertEquals (3, actions.size());
		Action action = actions.get(2);
		
		assertEquals ("You are ready to play", action.getActionMessageFor(player1));
		assertEquals ("Mark is ready to play", action.getActionMessageFor(player2));
	}

	@Test
	public void bothsPlayersAreReadyToPlay() {
		// Given
		gameManagerBeingTested.startNewGameForPlayer(player1);
		Game game = gameManagerBeingTested.getGames().get(0);
		gameManagerBeingTested.joinGame(player2, Long.toString(game.getGameId()));
		gameManagerBeingTested.readyToPlay(player1);
		
		// When
		gameManagerBeingTested.readyToPlay(player2);
		
		// Then		
		assertEquals (PlayerStatus.READY_TO_PLAY, player1.getStatus());
		assertEquals (PlayerStatus.READY_TO_PLAY, player2.getStatus());
		
		assertEquals (GameStatus.PLAYING, game.getStatus());
		
		List<Action> actions = gameManagerBeingTested.getActions ();
		assertEquals (4, actions.size());
		Action action = actions.get(3);
		
		assertEquals ("You are ready to play", action.getActionMessageFor(player2));
		assertEquals ("Anne is ready to play", action.getActionMessageFor(player1));
	}

	@Test
	public void playerCantBeReadyToPlayIfNotInAGame () {
		// Given
		gameManagerBeingTested.registerNewPlayer (player1);
		
		// When
		try {
			gameManagerBeingTested.readyToPlay(player1);
			fail ("Exception expected because player can't be ready to play if they're not in a game");
		} catch (JaluxxException e) {
			// Then
			assertEquals ("Player can't be ready to play if they're not in a game", e.getMessage());
		}
		
	}
}
