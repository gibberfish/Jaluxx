package com.mindbadger.jaluxx;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.mindbadger.jaluxx.Player;
import com.mindbadger.jaluxx.PlayerStatus;

public class PlayerTest {

	private Player playerBeingTested;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		playerBeingTested = new Player();
	}

	@Test
	public void newPlayerHasCorrectStatus() {
		// Given

		// When

		// Then
		assertEquals(PlayerStatus.NOT_IN_GAME, playerBeingTested.getStatus());
	}
}
