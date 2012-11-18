package com.mindbadger.jaluxx;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.mindbadger.jaluxx.Action;
import com.mindbadger.jaluxx.ActionType;
import com.mindbadger.jaluxx.Player;


public class ActionTest {
	private Action actionToTest;
	
	private Player player1;
	private Player player2;
	
	@Before
	public void setup () {
		MockitoAnnotations.initMocks(this);
		
		player1 = new Player ();
		player1.setName("Mark");
		player1.setPassword("myPass");

		player2 = new Player ();
		player2.setName("Anne");
		player2.setPassword("annesPass");
	}

	@Test
	public void displayPlayerIndependentMessage () {
		// Given
		actionToTest = new Action (ActionType.GAME_STARTED, player1);
		
		// When
		String message = actionToTest.getActionMessageFor(player1);
		
		// Then
		assertEquals ("The game has started", message);
	}
	
	@Test
	public void displayMessageForActioningPlayer () {
		// Given
		actionToTest = new Action (ActionType.LOGGED_IN, player1);
		
		// When
		String message = actionToTest.getActionMessageFor(player1);
		
		// Then
		assertEquals ("You have logged in", message);
	}

	@Test
	public void displayMessageForOtherPlayer () {
		// Given
		actionToTest = new Action (ActionType.LOGGED_IN, player1);
		
		// When
		String message = actionToTest.getActionMessageFor(player2);
		
		// Then
		assertEquals ("Mark has logged in", message);
	}

	@Test
	public void displayMessageForOtherPlayerSwappedOver () {
		// Given
		actionToTest = new Action (ActionType.LOGGED_IN, player2);
		
		// When
		String message = actionToTest.getActionMessageFor(player1);
		
		// Then
		assertEquals ("Anne has logged in", message);
	}

}
