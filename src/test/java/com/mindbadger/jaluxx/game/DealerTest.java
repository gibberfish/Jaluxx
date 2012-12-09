package com.mindbadger.jaluxx.game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mindbadger.jaluxx.card.Card;
import com.mindbadger.jaluxx.card.CardType;
import com.mindbadger.jaluxx.game.Dealer;
import com.mindbadger.jaluxx.player.Player;

public class DealerTest {
	private Dealer dealerUnderTest;
	
	private Card card1 = new Card ("Card1", CardType.ACTION, "img1");
	private Card card2 = new Card ("Card2", CardType.ACTION, "img2");
	private Card card3 = new Card ("Card3", CardType.ACTION, "img3");
	private Card card4 = new Card ("Card4", CardType.ACTION, "img4");
	private Card card5 = new Card ("Card5", CardType.ACTION, "img5");
	private Card card6 = new Card ("Card6", CardType.ACTION, "img6");
	private Card card7 = new Card ("Card7", CardType.ACTION, "img7");
	private Card card8 = new Card ("Card8", CardType.ACTION, "img8");
	private Card card9 = new Card ("Card9", CardType.ACTION, "img9");
	private Card card10 = new Card ("Card10", CardType.ACTION, "img10");
	
	@Before
	public void setup () {
		MockitoAnnotations.initMocks(this);
		
		dealerUnderTest = new Dealer();
	}

	@Test
	public void testDealThreeCards () {
		// Given
		List<Card> pack = getPack();
		List<Player> players = getPlayers();
		
		// When
		dealerUnderTest.deal (pack, players, 3);
		
		// Then
		Player player1 = players.get(0);
		assertEquals (3, player1.getHand().size());
		assertEquals (card10, player1.getHand().get(0));
		assertEquals (card7, player1.getHand().get(1));
		assertEquals (card4, player1.getHand().get(2));
		
		Player player2 = players.get(1);
		assertEquals (3, player2.getHand().size());
		assertEquals (card9, player2.getHand().get(0));
		assertEquals (card6, player2.getHand().get(1));
		assertEquals (card3, player2.getHand().get(2));

		Player player3 = players.get(2);
		assertEquals (3, player3.getHand().size());
		assertEquals (card8, player3.getHand().get(0));
		assertEquals (card5, player3.getHand().get(1));
		assertEquals (card2, player3.getHand().get(2));
		
		assertEquals (1, pack.size());
		assertEquals (card1, pack.get(0));
}

	private List<Player> getPlayers() {
		List<Player> players = new ArrayList<Player> ();
		
		Player player1 = new Player ();
		Player player2 = new Player ();
		Player player3 = new Player ();
		
		players.add(player1);
		players.add(player2);
		players.add(player3);
		return players;
	}

	private List<Card> getPack() {
		List<Card> pack = new ArrayList<Card> ();
		
		pack.add(card1);
		pack.add(card2);
		pack.add(card3);
		pack.add(card4);
		pack.add(card5);
		pack.add(card6);
		pack.add(card7);
		pack.add(card8);
		pack.add(card9);
		pack.add(card10);
		return pack;
	}
}
