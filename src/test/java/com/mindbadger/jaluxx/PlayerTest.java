package com.mindbadger.jaluxx;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.mindbadger.jaluxx.Player;
import com.mindbadger.jaluxx.PlayerStatus;
import com.mindbadger.jaluxx.card.Card;
import com.mindbadger.jaluxx.card.CardType;

public class PlayerTest {

	private Player playerBeingTested;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		playerBeingTested = new Player();
	}

	@Test
	public void newPlayerHasCorrectStatusAndHasNoCards() {
		// Given

		// When

		// Then
		assertEquals(PlayerStatus.NOT_IN_GAME, playerBeingTested.getStatus());
		assertEquals(0, playerBeingTested.getHand().size());
	}

	@Test
	public void addCardToHand() {
		// Given
		Card card = new Card ("Test", CardType.ACTION, "img");

		// When
		playerBeingTested.addCardToHand(card);

		// Then
		assertEquals(1, playerBeingTested.getHand().size());
	}
}
