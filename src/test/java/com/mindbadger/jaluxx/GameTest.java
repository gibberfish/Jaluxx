package com.mindbadger.jaluxx;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mindbadger.jaluxx.Game;
import com.mindbadger.jaluxx.Pack;
import com.mindbadger.jaluxx.Player;
import com.mindbadger.jaluxx.PlayerStatus;
import com.mindbadger.jaluxx.card.Card;
import com.mindbadger.jaluxx.card.CardType;

public class GameTest {

	private Game gameBeingTested;

	private Player player1;
	private Player player2;
	@Mock Pack mockPack;
	
	@Before
	public void setup () {
		MockitoAnnotations.initMocks(this);
		
		player1 = new Player ();
		player1.setName("Mark");
		player1.setPassword("myPass");

		player2 = new Player ();
		player2.setName("Anne");
		player2.setPassword("annesPass");
		
		gameBeingTested = new Game (player1, mockPack);
	}
	
	@Test
	public void drawPileIsCreatedFromPackOnStartup() {
		// Given
		List<Card> cardsInPack = new ArrayList<Card> ();
		Card card1 = new Card ("card1", CardType.ACTION, "image1");
		Card card2 = new Card ("card2", CardType.KEEPER, "image2");
		Card card3 = new Card ("card3", CardType.RULE, "image3");
		cardsInPack.add(card1);
		cardsInPack.add(card2);
		cardsInPack.add(card3);
		
		when (mockPack.getCardsInPack()).thenReturn(cardsInPack);
		
		// When
		gameBeingTested = new Game (player1, mockPack);

		// Then
		List<Card> drawPile = gameBeingTested.getDrawPile ();
		if (drawPile == cardsInPack) fail ("The draw pile should contain the same objects, but not be the same");
		
		assertEquals (3, drawPile.size());
		assertEquals (card1, drawPile.get(0));
		assertEquals (card2, drawPile.get(1));
		assertEquals (card3, drawPile.get(2));
	}

	@Test
	public void gameIsNotPlayingWhenOnePlayerJoinedGame() {
		// Given
		player1.setStatus(PlayerStatus.JOINED_GAME);

		// When
		boolean isPlaying = gameBeingTested.isPlaying ();

		// Then
		assertFalse(isPlaying);
	}

	@Test
	public void gameIsNotPlayingWhenOnlyOnePlayerIsReady() {
		// Given
		player1.setStatus(PlayerStatus.READY_TO_PLAY);

		// When
		boolean isPlaying = gameBeingTested.isPlaying ();

		// Then
		assertFalse(isPlaying);
	}

	@Test
	public void gameIsNotPlayingWhenTwoPlayersNotReady() {
		// Given
		player1.setStatus(PlayerStatus.JOINED_GAME);
		gameBeingTested.addPlayer(player2);

		// When
		boolean isPlaying = gameBeingTested.isPlaying ();

		// Then
		assertFalse(isPlaying);
	}

	@Test
	public void gameIsNotPlayingWhenTwoPlayersReady() {
		// Given
		player1.setStatus(PlayerStatus.READY_TO_PLAY);
		gameBeingTested.addPlayer(player2);
		player2.setStatus(PlayerStatus.READY_TO_PLAY);

		// When
		boolean isPlaying = gameBeingTested.isPlaying ();

		// Then
		assertTrue(isPlaying);
	}
}
