package com.mindbadger.jaluxx.game;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mindbadger.jaluxx.action.Action;
import com.mindbadger.jaluxx.card.Card;
import com.mindbadger.jaluxx.card.CardType;
import com.mindbadger.jaluxx.gamemanager.GameFactory;
import com.mindbadger.jaluxx.player.Player;
import com.mindbadger.jaluxx.player.PlayerStatus;
import com.mindbadger.jaluxx.turn.PlayerTurn;

public class GameTest {

	private Game gameBeingTested;

	@Mock
	private GameFactory mockGameFactory;
	@Mock
	private Dealer mockDealer;
	@Mock
	private PlayerTurn mockPlayerTurn;

	private Player player1;
	private Player player2;

	private Pack pack = new Pack();

	private Card card1 = new Card("card1", CardType.ACTION, "image1");
	private Card card2 = new Card("card2", CardType.KEEPER, "image2");
	private Card card3 = new Card("card3", CardType.RULE, "image3");

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		player1 = new Player();
		player1.setName("Mark");
		player1.setPassword("myPass");

		player2 = new Player();
		player2.setName("Anne");
		player2.setPassword("annesPass");

		List<Card> cardsInPack = new ArrayList<Card>();
		cardsInPack.add(card1);
		cardsInPack.add(card2);
		cardsInPack.add(card3);
		pack.setCardsInPack(cardsInPack);

		gameBeingTested = new Game(player1, pack, mockDealer, mockGameFactory);

	}

	@Test
	public void drawPileIsCreatedFromPackOnStartup() {
		// Given

		// When

		// Then
		List<Card> drawPile = gameBeingTested.getDrawPile();
		if (drawPile == pack.getCardsInPack())
			fail("The draw pile should contain the same objects, but not be the same");

		assertEquals(3, drawPile.size());
		assertEquals(card1, drawPile.get(0));
		assertEquals(card2, drawPile.get(1));
		assertEquals(card3, drawPile.get(2));
	}

	@Test
	public void gameIsNotPlayingOnStartup() {
		// Given

		// When

		// Then
		assertEquals(GameStatus.SETUP, gameBeingTested.getStatus());
	}

	@Test
	public void discardPileIsEmptyOnStartup() {
		// Given

		// When

		// Then
		assertEquals(0, gameBeingTested.getDiscardPile().size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void noPlayerTurnOnStartup() {
		// Given

		// When

		// Then
		verify(mockGameFactory, never()).createNewTurnForPlayer(
				any(Player.class), any(List.class), any(List.class));
	}

	@Test
	public void takeACardFromTheDrawPile() {
		// Given

		// When
		Card card = gameBeingTested.takeCardFromDrawPile();

		// Then
		assertEquals(card3, card);
		assertEquals(2, gameBeingTested.getDrawPile().size());
		assertEquals(card1, gameBeingTested.getDrawPile().get(0));
		assertEquals(card2, gameBeingTested.getDrawPile().get(1));
	}

	@Test
	public void startGame() {
		// Given
		List<Card> rules = new ArrayList<Card>();
		when(
				mockGameFactory.createNewTurnForPlayer(player1,
						gameBeingTested.getPlayers(), rules)).thenReturn(
				mockPlayerTurn);

		// When
		gameBeingTested.startGame();
		List<Player> playerList = gameBeingTested.getPlayers();

		// Then
		assertEquals(GameStatus.PLAYING, gameBeingTested.getStatus());
		verify(mockDealer).deal(pack.getCardsInPack(), playerList, 3);

		assertEquals(0, gameBeingTested.getWhosTurn());

		List<Action> actions = gameBeingTested.getActions();
		assertEquals(1, actions.size());
		Action action = actions.get(0);

		assertEquals("Your turn", action.getActionMessageFor(player1));
		assertEquals("Mark's turn", action.getActionMessageFor(player2));

		verify(mockGameFactory).createNewTurnForPlayer(player1,
				gameBeingTested.getPlayers(), rules);
	}
}
